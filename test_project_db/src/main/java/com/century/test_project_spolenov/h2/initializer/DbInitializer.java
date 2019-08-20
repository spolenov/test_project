package com.century.test_project_spolenov.h2.initializer;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp.BasicDataSource;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.util.Arrays;

import static org.dbunit.operation.DatabaseOperation.*;

@Slf4j
public class DbInitializer {
    @Autowired
    @Setter
    private BasicDataSource dataSource;
    @Setter
    @Autowired(required = false)
    private String schema;

    private String dataset;

    public DbInitializer(String dataset) {
        this.dataset = dataset;
    }

    public IDataSet readDataSet() throws Exception {
        InputStream stream = this.getClass().getResourceAsStream(dataset);
        return new FlatXmlDataSetBuilder().setCaseSensitiveTableNames(true).build(stream);
    }

    public void initDb() throws Exception {
        DatabaseConnection databaseConnection = null;
        try {
            IDataSet iDataSet = readDataSet();
            Arrays.sort(iDataSet.getTableNames());

            ReplacementDataSet replacementDataSet = new ReplacementDataSet(iDataSet);

            replacementDataSet.addReplacementObject("[NULL]", null);
            databaseConnection = new DatabaseConnection(dataSource.getConnection(), schema);

            DatabaseConfig dBConfig = databaseConnection.getConfig(); // dBConn is a IDatabaseConnection
            dBConfig.setProperty(DatabaseConfig.FEATURE_CASE_SENSITIVE_TABLE_NAMES, Boolean.FALSE);
            dBConfig.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new PostgresqlDataTypeFactory());

            PreparedStatement statement = databaseConnection.getConnection()
                    .prepareStatement("ALTER SEQUENCE test.order_head_id_seq RESTART WITH 100000;");
            statement.execute();

            statement = databaseConnection.getConnection()
                    .prepareStatement("ALTER SEQUENCE test.order_line_id_seq RESTART WITH 100000;");
            statement.execute();

            statement = databaseConnection.getConnection()
                    .prepareStatement("ALTER SEQUENCE test.goods_id_seq RESTART WITH 100000;");
            statement.execute();

            CLEAN_INSERT.execute(databaseConnection, replacementDataSet);

        } catch(Exception e){
            log.info("Failed to init db");
            throw e;
        } finally {
            if(databaseConnection != null){
                databaseConnection.close();
            }
        }
    }
}

