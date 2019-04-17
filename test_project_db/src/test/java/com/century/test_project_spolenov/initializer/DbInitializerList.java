package com.century.test_project_spolenov.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

public class DbInitializerList {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private List<DbInitializer> serviceDbInitializerList;

    @PostConstruct
    public void initDb() throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("SET REFERENTIAL_INTEGRITY FALSE");
        for (DbInitializer serviceDbInitializer : serviceDbInitializerList) {
            serviceDbInitializer.initDb();
        }
        jdbcTemplate.update("SET REFERENTIAL_INTEGRITY TRUE ");
    }
}
