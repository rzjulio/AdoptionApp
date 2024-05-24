package org.expeditors.mexicoapps.adoptionapp.jconfig;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

//@Component
public class InitDB {
    public static void main(String[] args) {

    }

    public static void initSchema() {
        String schemaFile = "sql/postgres/1-create-db-and-tbls.sql";
        runSchemaFile(getDataSource(), schemaFile);
    }

    public static void initData() {
        String insertData = "sql/postgres/2-insert-data.sql";
        runSchemaFile(getDataSource(), insertData);
    }

    private static DataSource getDataSource(){
        String url = "jdbc:postgresql://localhost:5433/adoptapp";
        String user = "larku";
        String pw = "larku";
        DriverManagerDataSource dataSource = new DriverManagerDataSource(url, user, pw);
        return dataSource;
    }
    private static void runSchemaFile(DataSource dataSource, String schemaFile) {
        try (Connection conn = dataSource.getConnection()) {
            System.err.println("Running sql: " + schemaFile);
            ScriptUtils.executeSqlScript(conn, new ClassPathResource(schemaFile));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
