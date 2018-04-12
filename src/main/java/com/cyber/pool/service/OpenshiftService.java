package com.cyber.pool.service;

import com.cyber.pool.util.OpenShiftTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class OpenshiftService {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Value("${openshift.url}")
    String openshiftUrl;

    @Value("${openshift.username}")
    String openshiftUsername;

    @Value("${openshift.password}")
    String openshiftPassword;

    public String updateLable(String labelName, String node){
        return this.updateLable(labelName, node, false);
    }

    public String updateLable(String labelName, String node, Boolean removeLabelFlag){
        String urlOcLabel = this.openshiftUrl + "/api/v1/nodes/" + node;
        String body = "{\"metadata\":{\"labels\":{\"" + labelName + "\":\"1\"}}}";
        if(removeLabelFlag){
            body = "{\"metadata\":{\"labels\":{\"" + labelName + "\":null}}}";
        }
        logger.info("updateLable urlOcLabel=" + urlOcLabel);
        logger.info("updateLable body=" + body);
        return this.httpInvoke(urlOcLabel, HttpMethod.PATCH, new HashMap<String, String>(), body);
    }

    public String httpInvoke(String url, HttpMethod httpMethod){
        Map<String, String> params = new HashMap<>();
        return OpenShiftTokenUtil.invokeRestApi(url, httpMethod, params, null, this.getToken());
    }

    public String httpInvoke(String url, HttpMethod httpMethod, Map<String, String> params){
        return OpenShiftTokenUtil.invokeRestApi(url, httpMethod, params, null, this.getToken());
    }

    public String httpInvoke(String url, HttpMethod httpMethod, Map<String, String> params, String jsonObj){
        return OpenShiftTokenUtil.invokeRestApi(url, httpMethod, params, jsonObj, this.getToken());
    }

    /*{
        "username": "admin",
        "password": "123456"
    }*/
    public String getToken() {

        String url = this.openshiftUrl + "/oauth/authorize?response_type=token&client_id=openshift-challenging-client";
        logger.info("getToken url=" + url);

        String token = OpenShiftTokenUtil.getOpenShiftToken(openshiftUsername, openshiftPassword);
        logger.info("getToken token = Bearer " + token);
        return "Bearer " + token;
    }

    public String toJson(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            return null;
        }
    }

    public ObjectNode toObjectNode(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(obj);
            return objectMapper.readValue(json, ObjectNode.class);
        } catch (Exception e) {
            return null;
        }
    }

    public ObjectNode toObjectNode(String json) {
        try {
            return new ObjectMapper().readValue(json, ObjectNode.class);
        } catch (Exception e) {
            return null;
        }
    }


}