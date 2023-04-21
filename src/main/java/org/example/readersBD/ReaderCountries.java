package org.example.readersBD;

import org.example.manipulationBD.Connector;
import org.example.dataBD.Country;
import org.example.manipulationBD.StorageBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReaderCountries implements ReaderBD{
    @Override
    public void getData(Connector connector, StorageBD storageBD) throws SQLException {
        ArrayList<Country> countries = new ArrayList<>();
        try (Connection connection = connector.getConnection()) {
            String sql = "SELECT * FROM public.countries";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                countries.add(new Country(resultSet.getInt("id"),
                 resultSet.getString("country_name"),
                resultSet.getString("subregion"),
                resultSet.getString("region"),
                resultSet.getInt("region_id")
                ));

                storageBD.setCountries(countries);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
