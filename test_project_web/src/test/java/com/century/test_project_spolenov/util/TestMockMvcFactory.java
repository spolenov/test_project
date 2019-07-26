package com.century.test_project_spolenov.util;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class TestMockMvcFactory {
    public static<T> MockMvc getMockMvc(T controller){
        return standaloneSetup(controller).
                defaultRequest(get("/").servletPath("/rest")).
                build();
    }
}
