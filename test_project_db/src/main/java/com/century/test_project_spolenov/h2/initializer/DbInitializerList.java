package com.century.test_project_spolenov.h2.initializer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Slf4j
public class DbInitializerList {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private List<DbInitializer> dbInitializerList;

    @Value("${h2.data.init}")
    private boolean isInitDataH2;

    @Value("${pg.data.init}")
    private boolean isInitDataPg;

    @Resource
    private String initialDataPg;

    @PostConstruct
    public void initDb() throws Exception {

        if(isInitDataH2){
            jdbcTemplate.update("SET REFERENTIAL_INTEGRITY FALSE");
            for (DbInitializer serviceDbInitializer : dbInitializerList) {
                serviceDbInitializer.initDb();
            }
            jdbcTemplate.update("SET REFERENTIAL_INTEGRITY TRUE ");
        }

        if(isInitDataPg){
            jdbcTemplate.execute(initialDataPg);
            log.info("Executed {}", initialDataPg);
        }
    }
}
