package com.century.test_project_spolenov.h2.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

public class DbInitializerList {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private List<DbInitializer> dbInitializerList;

    @PostConstruct
    public void initDb() throws Exception {
       // try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.update("SET REFERENTIAL_INTEGRITY FALSE");
            for (DbInitializer serviceDbInitializer : dbInitializerList) {
                serviceDbInitializer.initDb();
            }
            jdbcTemplate.update("SET REFERENTIAL_INTEGRITY TRUE ");
        //} catch(Exception e){
            //NOP
        //}
    }
}
