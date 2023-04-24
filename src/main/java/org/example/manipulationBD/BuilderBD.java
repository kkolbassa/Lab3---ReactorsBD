package org.example.manipulationBD;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.*;
import org.example.collections.StorageBD;
import org.example.readersBD.*;

import javax.swing.*;

public class BuilderBD {
    private Connector connector = new Connector();
    private ArrayList<String> tablesCreation;
    private ArrayList<String> tablesDelete;
    private ArrayList<ReaderBD> readerBDS = new ArrayList<>();
    private String[] tableNames;

    public BuilderBD() {
        tablesCreation = new ArrayList<>();
        tablesCreation.add("CREATE TABLE IF NOT EXISTS public.units ("
                + "id SERIAL PRIMARY KEY,"
                + "code TEXT,"
                + "unit_name TEXT,"
                + "site SMALLINT,"
                + "status TEXT,"
                + "type TEXT,"
                + "model TEXT,"
                + "class TEXT,"
                + "ru_design TEXT ,"
                + "operator SMALLINT,"
                + "nsss_supplier SMALLINT,"
                + "thermal_capacity SMALLINT,"
                + "gross_capacity SMALLINT,"
                + "net_capacity SMALLINT,"
                + "construction_start DATE,"
                + "commercial_operation DATE,"
                + "date_shutdown DATE,"
                + "enrichment NUMERIC(6,5),"
                + "load_factor SMALLINT"
                + ")");
        tablesCreation.add("CREATE TABLE IF NOT EXISTS public.sites (" +
                "id SERIAL PRIMARY KEY, " +
                "npp_name TEXT, " +
                "place SMALLINT, " +
                "owner_id SMALLINT, " +
                "operator SMALLINT, " +
                "builder SMALLINT )");
        tablesCreation.add("CREATE TABLE IF NOT EXISTS public.countries ("
                + "id SERIAL PRIMARY KEY,"
                + "country_name TEXT,"
                + "subregion TEXT,"
                + "region TEXT,"
                + "region_id SMALLINT"
                + ");");
        tablesCreation.add("CREATE TABLE IF NOT EXISTS public.regions ("
                + "id SERIAL PRIMARY KEY,"
                + "region_name TEXT"
                + ");");
        tablesCreation.add("CREATE TABLE IF NOT EXISTS public.companies ("
                + "id SERIAL PRIMARY KEY,"
                + "companies_name TEXT,"
                + "full_name TEXT,"
                + "country_id SMALLINT"
                + ");");

        tablesDelete = new ArrayList<>();
        tablesDelete.add("DROP TABLE IF EXISTS public.units;");
        tablesDelete.add("DROP TABLE IF EXISTS public.sites;");
        tablesDelete.add("DROP TABLE IF EXISTS public.countries;");
        tablesDelete.add("DROP TABLE IF EXISTS public.companies;");
        tablesDelete.add("DROP TABLE IF EXISTS public.regions;");

        this.tableNames = new String[]{"public.units", "public.sites", "public.countries", "public.companies", "public.regions"};

        readerBDS.add(new ReaderCompanies());
        readerBDS.add(new ReaderUnits());
        readerBDS.add(new ReaderCountries());
        readerBDS.add(new ReaderSites());
        readerBDS.add(new ReaderRegions());
    }

    public String areTablesExist() {
        String areExist = "БД создана";

        try (Connection conn = connector.getConnection();
             Statement stmt = conn.createStatement()) {
            for (String tableName : tableNames) {
                ResultSet resultSet = stmt.executeQuery("SELECT COUNT(*) FROM " + tableName);
                if (!resultSet.next()) areExist = "БД не создана";
            }
        }catch (SQLException e) {
                areExist = "БД не создана";
                }

        return areExist;
    }

    public String areDataExist() {
        String areExist = "Данные загружены в БД";

        try (Connection conn = connector.getConnection();
             Statement stmt = conn.createStatement()) {
            for (String tableName : tableNames) {
                ResultSet resultSet = stmt.executeQuery("SELECT COUNT(*) FROM " + tableName);
                if (!resultSet.next()) areExist = "Данные не загружены в БД";
                else {
                    int rowCount = resultSet.getInt(1);
                    if (rowCount == 0) areExist = "Данные не загружены в БД";
                }
            }
        }catch (SQLException e) {
            areExist = "Данные не загружены в БД";
        }

        return areExist;
    }

    public void createBD() {
        try (Connection conn = connector.getConnection();
             Statement stmt = conn.createStatement()) {
            tablesCreation.forEach(table -> {
                try {
                    stmt.executeUpdate(table);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ошибка создания БД", "Oшибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteBD() {
        try (Connection conn = connector.getConnection();
             Statement stmt = conn.createStatement()) {

            tablesDelete.forEach(table -> {
                try {
                    stmt.executeUpdate(table);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Oшибка", JOptionPane.ERROR_MESSAGE);
                }
            });
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Oшибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void fillBD(String filePath) throws SQLException {
        try (Connection conn = connector.getConnection()) {
            // Чтение данных из Excel-файла и вставка их в таблицу базы данных
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(fis);

            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);
                String tableName = sheet.getSheetName();

                int numColumns = sheet.getRow(0).getLastCellNum();
                String sql = "INSERT INTO " + tableName + " VALUES (";
                for (int j = 0; j < numColumns; j++) {
                    sql += "?";
                    if (j < numColumns - 1) {
                        sql += ",";
                    }
                }
                sql += ")";

                PreparedStatement pstmt = conn.prepareStatement(sql);

                Iterator<Row> rowIterator = sheet.iterator();
                rowIterator.next();
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();

                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        switch (cell.getCellType()) {
                            case NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    java.util.Date date = cell.getDateCellValue();
                                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                                    pstmt.setDate(cell.getColumnIndex() + 1, sqlDate);
                                } else {
                                    String cellValue = cell.toString();
                                    if (NumberUtils.isCreatable(cellValue)) {
                                        double doubleValue = Double.parseDouble(cellValue);
                                        if (doubleValue == Math.floor(doubleValue)) {
                                            int intValue = (int) doubleValue;
                                            pstmt.setInt(cell.getColumnIndex() + 1, intValue);
                                        } else {
                                            pstmt.setDouble(cell.getColumnIndex() + 1, doubleValue);
                                        }
                                    }
                                }
                                break;
                            case STRING:
                                if (cell.getStringCellValue() == "") {
                                    pstmt.setNull(cell.getColumnIndex() + 1, Types.NULL);
                                } else {
                                    pstmt.setString(cell.getColumnIndex() + 1, cell.getStringCellValue());
                                }
                                break;
                            case BOOLEAN:
                                pstmt.setString(cell.getColumnIndex() + 1, String.valueOf(cell.getBooleanCellValue()));
                                break;
                            case BLANK:
                                pstmt.setNull(cell.getColumnIndex() + 1, Types.NULL);
                                break;
                        }

                    }
                    pstmt.executeUpdate();
                }

                pstmt.close();
            }

        } catch (IOException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Ошибка загрузки данных в БД", "Oшибка", JOptionPane.ERROR_MESSAGE);
        }
    }
    public StorageBD getDataFromBD(){
        StorageBD storageBD = new StorageBD();
        readerBDS.forEach(r -> {
            try {
                r.getData(connector, storageBD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return storageBD;
    }

}
