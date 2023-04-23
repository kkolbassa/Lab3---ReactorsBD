package org.example.manipulationBD;

import org.example.dataBD.Unit;
import org.example.dataManipulation.ReactorCollection;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class ReactorsManipulation {
    private StorageBD storageBD;
    private ReactorCollection reactorCollection;

    public ReactorsManipulation(StorageBD storageBD, ReactorCollection reactorCollection) {
        this.storageBD = storageBD;
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

    public void setReactorCollection(ReactorCollection reactorCollection) {
        this.reactorCollection = reactorCollection;
    }

    public void filterUnitsInOperation(){
        storageBD.setUnits ((ArrayList<Unit>) storageBD.getUnits().stream().filter(unit -> unit.getStatus().equals("in operation")).collect(Collectors.toList()));
    }
    public void addInfo2Units(){

        Map<String, Double> reactorBurnupMap = reactorCollection.getReactors().stream()
                .collect(Collectors.toMap(r -> r.getClassReactor(), r -> r.getBurnup()));

        storageBD.getUnits().forEach(u -> {u.setBurnup(reactorBurnupMap.getOrDefault(u.getClass_(), 0.0));
        });
        storageBD.getUnits().forEach(u -> {if(u.getBurnup()==0.0) u.setBurnup(reactorBurnupMap.getOrDefault(u.getType(), 0.0));
        });
        //storageBD.getUnits().forEach(u -> System.out.println(u.getId() + "  "+ u.getBurnup()));
    }

    public void addFuelConsumption(){
        storageBD.getUnits().forEach(u -> {
            if(u.getLoad_factor() == 0) u.setLoad_factor(90);
            if(u.getBurnup()!=0.0) {
                u.setFuelConsumption(365*u.getThermal_capacity() / u.getBurnup() * u.getLoad_factor() * 0.01/1000);
                System.out.println(u.getFuelConsumption());
            }
        });
    }

}
