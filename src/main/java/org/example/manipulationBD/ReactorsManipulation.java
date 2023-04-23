package org.example.manipulationBD;

import org.example.dataBD.Country;
import org.example.dataBD.Region;
import org.example.dataBD.Unit;
import org.example.dataManipulation.ReactorCollection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReactorsManipulation {
    private final StorageBD storageBDInitial;
    private  StorageBD storageBD;
    private final ReactorCollection reactorCollection;

    public ReactorsManipulation(StorageBD storageBD, ReactorCollection reactorCollection) {
        this.storageBDInitial = storageBD;
        this.storageBD = storageBD.copy();
        this.reactorCollection = reactorCollection;
    }

    public StorageBD getStorageBD() {
        return storageBD;
    }

    public void setStorageBD(StorageBD storageBD) {
        this.storageBD = storageBD;
    }

    public ReactorCollection getReactorCollection() {
        return reactorCollection;
    }

    public void filterUnitsInOperation(){
        storageBD.setUnits ((ArrayList<Unit>) storageBD.getUnits().stream()
                .filter(unit -> unit.getStatus().equals("in operation"))
                .collect(Collectors.toList()));
    }

    public void addInfo2Units(){

        Map<String, Double> reactorBurnupMap = reactorCollection.getReactors().stream()
                .collect(Collectors.toMap(r -> r.getClassReactor(), r -> r.getBurnup()));

        storageBD.getUnits().forEach(u -> {u.setBurnup(reactorBurnupMap.getOrDefault(u.getClass_(), 0.0));
        });
        storageBD.getUnits().forEach(u -> {if(u.getBurnup()==0.0) u.setBurnup(reactorBurnupMap.getOrDefault(u.getType(), 0.0));
        });
       // storageBD.getUnits().forEach(u -> System.out.println(u.getId() + "  "+ u.getBurnup()));
    }

    public void addFuelConsumption(){
        storageBD.getUnits().forEach(u -> {
            if(u.getLoad_factor() == 0) u.setLoad_factor(90);
            if(u.getBurnup()!=0.0) {
                u.setFuelConsumption(365*u.getThermal_capacity() / u.getBurnup() * u.getLoad_factor() * 0.01/1000);
            }
        });

        Map<String, Double> reactorFirstLoadMap = reactorCollection.getReactors().stream()
                .collect(Collectors.toMap(r -> r.getClassReactor(), r -> r.getFirst_load()));

        storageBDInitial.getUnits().forEach(unit -> {
            if(unit.getCommercial_operation()!=null&&unit.getCommercial_operation().substring(0, 4).equals("2023")){
                storageBD.addUnit(unit);
                int index = storageBD.getUnits().indexOf(unit);
                storageBD.getUnits().get(index).setFuelConsumption(reactorFirstLoadMap.getOrDefault(unit.getType(), 0.0));
            }
        });

        storageBD.getUnits().forEach(unit -> System.out.println(unit.getUnit_name()+" " + unit.getFuelConsumption()));

    }
    public Map<String, Double> aggregateCountry(){

        //Map<id_site, List<Unit>>
        Map<Integer, List<Unit>> reactorsBySite = storageBD.getUnits().stream()
                .collect(Collectors.groupingBy(Unit::getSite));

        // Map<country, fuelConsumption>
        Map<String, Double> countryFuelConsumption = new HashMap<>();

        for (Country country : storageBD.getCountries()) {
            double fuelConsumption = reactorsBySite.entrySet().stream()
                    .filter(entry -> storageBD.getSites().stream()
                            .anyMatch(site -> site.getId() == entry.getKey() && site.getPlace() == country.getId()))
                    .flatMap(entry -> entry.getValue().stream())
                    .mapToDouble(Unit::getFuelConsumption)
                    .sum();
            countryFuelConsumption.put(country.getCountry_name(), fuelConsumption);
        }
        System.out.println(countryFuelConsumption);
        return countryFuelConsumption;
    }

    public Map<String, Double> aggregateRegion(){

        Map<String, Double> regionFuelConsumption = new HashMap<>();

        Map<String, Double> fuelConsumptionByCountry = aggregateCountry();

        for (Region region : storageBD.getRegions()) {
            double sumFuelConsumption = storageBD.getCountries().stream()
                    .filter(country -> country.getRegion_id() == region.getId())
                    .mapToDouble(country -> fuelConsumptionByCountry.getOrDefault(country.getCountry_name(), 0.0))
                    .sum();
            regionFuelConsumption.put(region.getRegion_name(), sumFuelConsumption);
        }

        System.out.println(regionFuelConsumption);
        return regionFuelConsumption;
    }

    public Map<String, Double> aggregateCompany(){

        Map<Integer, Double> reactorsByOperator = storageBD.getUnits().stream()
                .collect(Collectors.groupingBy(Unit::getOperator,
                        Collectors.summingDouble(Unit::getFuelConsumption)));

        Map<String, Double> companyFuelConsumption = new HashMap<>();

        storageBD.getCompanies().forEach(company -> {
            companyFuelConsumption.put(company.getCompanies_name(), reactorsByOperator.getOrDefault(company.getId(), 0.0));
        });
        System.out.println(companyFuelConsumption);
        return companyFuelConsumption;
    }

}
