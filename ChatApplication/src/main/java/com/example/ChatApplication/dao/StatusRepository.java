package com.example.ChatApplication.dao;

import com.example.ChatApplication.model.StatusModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<StatusModel,Integer> {
}
