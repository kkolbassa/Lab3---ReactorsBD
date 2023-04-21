package org.example.readersBD;

import org.example.manipulationBD.Connector;
import org.example.manipulationBD.StorageBD;

import java.sql.SQLException;

public interface ReaderBD {
    void getData(Connector connector, StorageBD storageBD) throws SQLException;
}
