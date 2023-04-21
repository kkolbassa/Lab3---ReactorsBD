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

             ResultSet rs = st.executeQuery("SELECT * FROM public.companies")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String companyName = rs.getString("companies_name");
                String fullName = rs.getString("full_name");
                int countryId = rs.getInt("country_id");

                companies.add(new Company(id, companyName, fullName, countryId));
            }
            storageBD.setCompanies(companies);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
