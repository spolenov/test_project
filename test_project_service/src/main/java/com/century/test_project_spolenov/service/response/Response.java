package com.century.test_project_spolenov.service.response;

import java.util.List;

public interface Response <T> {
    List<T> getData();
    String getDescription();
}
