package org.example.readersBD;

import org.example.manipulationBD.Connector;
import org.example.manipulationBD.StorageBD;
import org.example.dataBD.Unit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReaderUnits implements ReaderBD{
    @Override
    public void getData(Connector connector, StorageBD storageBD) throws SQLException {
        ArrayList<Unit> units = new ArrayList<>();
        try (Connection connection = connector.getConnection()) {
            String sql = "SELECT * FROM public.units";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                units.add(new Unit(
                        result.getInt("id"),
                        result.getString("code"),
                        result.getString("unit_name"),
                        result.getInt("site"),
                        result.getString("status").trim(),
                        result.getString("type").trim(),
                        result.getString("model"),
                        result.getString("class").trim(),
                        result.getString("ru_design"),
                        result.getInt("operator"),
                        result.getInt("nsss_supplier"),
                        result.getInt("thermal_capacity"),
                        result.getInt("gross_capacity"),
                        result.getInt("net_capacity"),
                        result.getString("construction_start"),
                        result.getString("commercial_operation"),
                        result.getString("date_shutdown"),
                        result.getDouble("enrichment"),
                        result.getInt("load_factor")
                ));

                storageBD.setUnits(units);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
