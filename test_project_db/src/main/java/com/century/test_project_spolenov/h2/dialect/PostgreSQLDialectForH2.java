package com.century.test_project_spolenov.h2.dialect;

import org.hibernate.dialect.PostgreSQL95Dialect;

public class PostgreSQLDialectForH2 extends PostgreSQL95Dialect {
    @Override
    public String getCurrentSchemaCommand(){
        return "SELECT 'test' as current_schema";
    }
}
