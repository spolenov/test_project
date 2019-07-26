package com.century.test_project_spolenov.h2.dialect;

import org.hibernate.dialect.PostgreSQL93Dialect;

public class PostgreSQLDialectForH2 extends PostgreSQL93Dialect {
    @Override
    public String getCurrentSchemaCommand(){
        return "SELECT 'test' as current_schema";
    }
}
