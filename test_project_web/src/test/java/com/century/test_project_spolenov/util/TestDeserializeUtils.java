package com.century.test_project_spolenov.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestDeserializeUtils {
    public static final String DESCR_FIELD = "description";
    public static final String STATE_FIELD = "state";
    public static final String TARGET_CONTENT_TYPE = "application/json;charset=UTF-8";

    private static <T> T deserializeJson(String json, Class<T> deserializeAs) throws IOException {
        return new ObjectMapper().readValue(json, deserializeAs);
    }

    public static <T> T deserializeData (String json, Class<T> deserializeAs) throws IOException{
        ObjectNode node = new ObjectMapper().readValue(json, ObjectNode.class);
        return deserializeJson(node.get("data").toString(), deserializeAs);
    }

    public static <T> T deserializeNode (String json, String nodeName, Class<T> deserializeAs) throws IOException{
        ObjectNode node = new ObjectMapper().readValue(json, ObjectNode.class);
        return deserializeJson(node.get(nodeName).toString(), deserializeAs);
    }

    public static <T> T deserializeObject (String json, Class<T> deserializeAs) throws IOException{
        ObjectNode node = new ObjectMapper().readValue(json, ObjectNode.class);
        return deserializeJson(node.toString(), deserializeAs);
    }

    public static JsonNode getNode(JsonNode node, String nodeName){
        return node.get(nodeName);
    }

    public static JsonNode getNode(String json, String nodeName) throws IOException {
        ObjectNode node = new ObjectMapper().readValue(json, ObjectNode.class);
        return node.get(nodeName);
    }

    public static <T> String toJson(T object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

    public static <T> T convertObject(Object object, TypeReference<?> toValueTypeRef) throws IOException {
        return new ObjectMapper()
                .convertValue(object, toValueTypeRef);
    }

    public static void setJson(Collection<String> collection, MvcResult result){
        try {
            collection.add (result.getResponse().getContentAsString());
        } catch (UnsupportedEncodingException e) {
            return;
        }
    }

    public static ResultActions expectCommon(ResultActions input, boolean isError) throws Exception {
        return input.andDo(print())
                .andExpect(content().contentType(TARGET_CONTENT_TYPE))
                .andExpect(jsonPath("data").exists())
                .andExpect(jsonPath(DESCR_FIELD).exists())
                .andExpect(jsonPath(STATE_FIELD).exists())
                .andExpect(isError ? status().isInternalServerError(): status().isOk());
    }
}
