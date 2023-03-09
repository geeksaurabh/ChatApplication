package com.example.ChatApplication.controller;

import com.example.ChatApplication.model.StatusModel;
import com.example.ChatApplication.service.StatusService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app/v1/status")
public class StatusController {
    @Autowired
    StatusService service;
    @PostMapping("create-status")
    public ResponseEntity<String> createStatus(@RequestBody String statusData){
        StatusModel statusModel=setStatus(statusData);
        int statusId=service.saveStatus(statusModel);
        return new ResponseEntity<>("status saved "+statusId, HttpStatus.CREATED);
    }

    private StatusModel setStatus(String statusData) {
        StatusModel statusModel=new StatusModel();
        JSONObject json=new JSONObject(statusData);
        String statusName= json.getString("statusName");
        String statusDescription= json.getString("statusDescription");
        statusModel.setStatusName(statusName);
        statusModel.setStatusDescription(statusDescription);
        return statusModel;
    }
}
