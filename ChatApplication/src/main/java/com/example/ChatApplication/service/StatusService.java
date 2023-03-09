package com.example.ChatApplication.service;

import com.example.ChatApplication.dao.StatusRepository;
import com.example.ChatApplication.model.StatusModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {
    @Autowired
    StatusRepository statusRepository;
    public int saveStatus(StatusModel statusModel) {
      StatusModel statusObj=statusRepository.save(statusModel);
      return statusObj.getStatusId();
    }
}
