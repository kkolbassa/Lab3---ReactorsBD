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

public class BuilderBD {
    private Connector connector = new Connector();
    private ArrayList<String> tablesCreation;
    private ArrayList<String> tablesDelete;
    private ArrayList<ReaderBD> readerBDS = new ArrayList<>();

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

        readerBDS.add(new ReaderCompanies());
        readerBDS.add(new ReaderUnits());
        readerBDS.add(new ReaderCountries());
        readerBDS.add(new ReaderSites());
        readerBDS.add(new ReaderRegions());
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
            System.out.println("Таблица успешно создана!");
        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных: " + e.getMessage());
        }
    }

    public void deleteBD() {
        try (Connection conn = connector.getConnection();
             Statement stmt = conn.createStatement()) {

            tablesDelete.forEach(table -> {
                try {
                    stmt.executeUpdate(table);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

            System.out.println("Таблица успешно удалена");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void fillBD() throws SQLException {
        try (Connection conn = connector.getConnection()) {
            // Чтение данных из Excel-файла и вставка их в таблицу базы данных
            File file = new File("./data/ReactorData.xlsx");
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
                    System.out.println(tableName);

                    pstmt.executeUpdate();
                }

                pstmt.close();
            }

            System.out.println("Данные успешно загружены в базу данных");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
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
