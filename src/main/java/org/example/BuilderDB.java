package org.example;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Iterator;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.*;

public class BuilderDB {
    private String url = "jdbc:postgresql://dpg-ch0m7fjh4hsukp3vd9a0-a.frankfurt-postgres.render.com:5432/reactorsbd";
    private String user = "kkolbassa";
    private String password = "RxLS1HZOi09baytXk1r0dFQ9Y4yG0nq2";
    private Integer[] order = {3, 2, 4, 1, 0};



    public void createBD(){
        try(Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement()) {

            String sql1 = "CREATE TABLE IF NOT EXISTS public.units ("
                    + "id SERIAL PRIMARY KEY,"
                    + "code VARCHAR(50),"
                    + "unit_name VARCHAR(100),"
                    + "site SMALLINT,"
                    + "status VARCHAR(50),"
                    + "type VARCHAR(50),"
                    + "model VARCHAR(50),"
                    + "class VARCHAR(50),"
                    + "ru_design VARCHAR(50) ,"
                    + "operator serial references companies (id),"
                    + "nsss_supplier SMALLINT,"
                    + "thermal_capacity SMALLINT,"
                    + "gross_capacity SMALLINT,"
                    + "net_capacity SMALLINT,"
                    + "construction_start DATE,"
                    + "commercial_operation DATE,"
                    + "date_shutdown DATE,"
                    + "enrichment NUMERIC(5),"
                    + "load_factor SMALLINT"
                    + ")";
            String sql2 = "CREATE TABLE IF NOT EXISTS public.sites (" +
                    "id SERIAL PRIMARY KEY, " +
                    "npp_name VARCHAR(255) NOT NULL, " +
                    "place serial references countries (id), " +
                    "owner_id serial references companies (id), " +
                    "operator SMALLINT, " +
                    "builder SMALLINT )";

            String sql3 = "CREATE TABLE IF NOT EXISTS public.countries ("
                    + "id SERIAL PRIMARY KEY,"
                    + "country_name VARCHAR(200),"
                    + "subregion VARCHAR(200),"
                    + "region VARCHAR(200),"
                    + "region_id serial references regions (id)"
                    + ");";

            String sql4 = "CREATE TABLE IF NOT EXISTS public.regions ("
                    + "id SERIAL PRIMARY KEY,"
                    + "region_name VARCHAR(200)"
                    + ");";

            String sql5 = "CREATE TABLE IF NOT EXISTS public.companies ("
                    + "id SERIAL PRIMARY KEY,"
                    + "companies_name VARCHAR(200),"
                    + "full_name VARCHAR(200),"
                    + "country_id SMALLINT"
                    + ");";

            stmt.executeUpdate(sql4);
            stmt.executeUpdate(sql3);
            stmt.executeUpdate(sql5);
            stmt.executeUpdate(sql2);
            stmt.executeUpdate(sql1);

            System.out.println("Таблица успешно создана!");

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных: " + e.getMessage());
        }
    }

        public void deleteBD(){
            try (Connection conn = DriverManager.getConnection(url, user, password);
                 Statement stmt = conn.createStatement()) {

                // Удаление таблицы
                String sql1 = "DROP TABLE IF EXISTS public.units;";
                String sql2 = "DROP TABLE IF EXISTS public.sites;";
                String sql3 = "DROP TABLE IF EXISTS public.countries;";
                String sql5 = "DROP TABLE IF EXISTS public.companies;";
                String sql4 = "DROP TABLE IF EXISTS public.regions;";
                stmt.executeUpdate(sql1);
                stmt.executeUpdate(sql2);
                stmt.executeUpdate(sql5);
                stmt.executeUpdate(sql3);
                stmt.executeUpdate(sql4);

                System.out.println("Таблица успешно удалена");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public void fillBD() throws SQLException {
            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                // Чтение данных из Excel-файла и вставка их в таблицу базы данных
                File file = new File("./data/ReactorData.xlsx");
                FileInputStream fis = new FileInputStream(file);
                Workbook workbook = WorkbookFactory.create(fis);

                for (Integer i : order) {
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
                                        if (cell.getStringCellValue()==""){
                                            pstmt.setNull(cell.getColumnIndex() + 1, Types.NULL);
                                        }else{
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

}
