package org.example.readersBD;

import org.example.Connector;
import org.example.dataBD.Company;
import org.example.dataBD.StorageBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReaderCompanies implements ReaderBD{
    @Override
    public void getData(Connector connector, StorageBD storageBD) throws SQLException {
        ArrayList<Company> companies = new ArrayList<>();
        try (Connection con = connector.getConnection();
             Statement st = con.createStatement();

             ResultSet resultSet = st.executeQuery("SELECT * FROM public.companies")) {

            while (resultSet.next()) {
                companies.add(new Company(resultSet.getInt("id"),
                        resultSet.getString("companies_name"),
                        resultSet.getString("full_name"),
                        resultSet.getInt("country_id")));
            }
            storageBD.setCompanies(companies);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
