package org.example;

import org.example.dataManipulation.DataManipulation;
import org.example.manipulationBD.BuilderBD;
import org.example.manipulationBD.ReactorsManipulation;

import java.io.IOException;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws IOException, SQLException {
       // new JFrameProgram().setVisible(true);
        BuilderBD bdb = new BuilderBD();
        DataManipulation dm = new DataManipulation();
        /*bdb.deleteBD();
        bdb.createBD();
        bdb.fillBD();*/
        ReactorsManipulation rm = new ReactorsManipulation(bdb.getDataFromBD(),dm.importData("./data/Reactors_json.json"));
        rm.filterUnitsInOperation();
        rm.addInfo2Units();
        rm.addFuelConsumption();

    }
}