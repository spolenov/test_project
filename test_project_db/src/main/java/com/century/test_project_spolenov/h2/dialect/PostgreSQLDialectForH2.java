package com.century.test_project_spolenov.h2.dialect;

import org.hibernate.dialect.PostgreSQL10Dialect;

public class PostgreSQLDialectForH2 extends PostgreSQL10Dialect {
    @Override
    public String getCurrentSchemaCommand(){
        return "SELECT 'test' as current_schema";
    }
}
