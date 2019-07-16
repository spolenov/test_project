package com.century.test_project_spolenov.initializer;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.PreparedStatement;

import static org.dbunit.operation.DatabaseOperation.CLEAN_INSERT;

@Slf4j
public class DbInitializer {
    @Setter
    @Autowired
    private DataSource dataSource;

    @Setter
    @Autowired(required = false)
    private String schema;

    private String dataset;

    public DbInitializer(String dataset) {
        this.dataset = dataset;
    }

    IDataSet readDataSet() throws Exception {
        InputStream stream = this.getClass().getResourceAsStream(dataset);
        return new FlatXmlDataSetBuilder().build(stream);
    }

    void initDb() throws Exception {
        DatabaseConnection databaseConnection = null;
        try {
            IDataSet iDataSet = readDataSet();
            ReplacementDataSet replacementDataSet = new ReplacementDataSet(iDataSet);

            replacementDataSet.addReplacementObject("[NULL]", null);
            databaseConnection = new DatabaseConnection(dataSource.getConnection(), schema);

            PreparedStatement pst;
            try {
                CLEAN_INSERT.execute(databaseConnection, replacementDataSet);
            } catch (Exception e) {
                log.error("Failed to execute CLEAN_INSERT:", e);
            }

        } finally {
            if (databaseConnection != null) {
                databaseConnection.close();
            }
        }
    }
}

