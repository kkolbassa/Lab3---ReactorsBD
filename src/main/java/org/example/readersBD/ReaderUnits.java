package org.example.readersBD;

import org.example.manipulationBD.Connector;
import org.example.collections.StorageBD;
import org.example.dataBD.Unit;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReaderUnits implements ReaderBD{
    @Override
    public void getData(Connector connector, StorageBD storageBD) throws SQLException {
        ArrayList<Unit> units = new ArrayList<>();
        try (Statement st = connector.createStatement()) {
            String sql = "SELECT * FROM public.units";
            ResultSet result = st.executeQuery(sql);

            while (result.next()) {
                units.add(new Unit(
                        result.getInt("id"),
                        result.getString("code"),
                        result.getString("unit_name"),
                        result.getInt("site"),
                        result.getString("status"),
                        result.getString("type"),
                        result.getString("model"),
                        result.getString("class"),
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
            JOptionPane.showMessageDialog(null, "Ошибка получения данных из БД"+e.getMessage(), "Oшибка", JOptionPane.ERROR_MESSAGE);

        }
    }
}
