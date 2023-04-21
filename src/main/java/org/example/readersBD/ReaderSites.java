package org.example.readersBD;

import org.example.manipulationBD.Connector;
import org.example.dataBD.Site;
import org.example.manipulationBD.StorageBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReaderSites implements ReaderBD{
    @Override
    public void getData(Connector connector, StorageBD storageBD) throws SQLException {
        ArrayList<Site> sites = new ArrayList<>();
        try (Connection connection = connector.getConnection()) {
            String sql = "SELECT * FROM public.sites";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                sites.add(new Site(
                        resultSet.getInt("id"),
                        resultSet.getString("npp_name"),
                        resultSet.getInt("place"),
                        resultSet.getInt("owner_id"),
                        resultSet.getInt("operator"),
                        resultSet.getInt("builder")
                ));

                storageBD.setSites(sites);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
