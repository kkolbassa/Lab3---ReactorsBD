package org.example;

import org.example.manipulationBD.BuilderBD;

import java.io.IOException;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws IOException, SQLException {
       // new JFrameProgram().setVisible(true);
        BuilderBD bdb = new BuilderBD();
/*        bdb.deleteBD();
        bdb.createBD();
        bdb.fillBD();*/
        bdb.getDataFromBD();
    }
}