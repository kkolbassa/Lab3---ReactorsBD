package org.example;

import org.example.parserManipulation.ParserManipulation;
import org.example.manipulationBD.BuilderBD;
import org.example.reactorsManipulation.ReactorsManipulation;

import java.io.IOException;



public class Main {
    public static void main(String[] args) throws IOException {
        new JFrameProgram().setVisible(true);
        /*BuilderBD bdb = new BuilderBD();
        ParserManipulation dm = new ParserManipulation();
        *//*bdb.deleteBD();
        bdb.createBD();
        bdb.fillBD();*//*
        ReactorsManipulation rm = new ReactorsManipulation(bdb.getDataFromBD(),dm.importData("./data/Reactors_json.json"));
        rm.filterUnitsInOperation();
        rm.addInfo2Units();
        rm.addFuelConsumption();
        *//*rm.aggregateCountry();
        rm.aggregateRegion();
        rm.aggregateCompany();*//*
*/
    }
}