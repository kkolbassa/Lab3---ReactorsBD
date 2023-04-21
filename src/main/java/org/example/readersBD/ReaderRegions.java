package org.example.readersBD;

import org.example.Connector;
import org.example.dataBD.Region;
import org.example.dataBD.StorageBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReaderRegions implements ReaderBD{
    @Override
    public void getData(Connector connector, StorageBD storageBD) throws SQLException {
        ArrayList<Region> regions = new ArrayList<>();
        try (Connection connection = connector.getConnection()) {
            String sql = "SELECT * FROM public.regions";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                regions.add(new Region(
                        resultSet.getInt("id"),
                        resultSet.getString("region_name")
                ));

                storageBD.setRegions(regions);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
