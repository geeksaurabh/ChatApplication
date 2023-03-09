package com.example.ChatApplication.dao;

import com.example.ChatApplication.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserModel,Integer> {
    @Query(value = "Select * from tbl_user where username = :username and status_id = 1", nativeQuery = true)
    public List<UserModel> findByUsername(String username);

    @Query(value = "select * from tbl_user where user_id = :userId and status_id = 1", nativeQuery = true)
    public List<UserModel> getUserByUserId(int userId);

    @Query(value = "select * from tbl_user where status_id = 1", nativeQuery = true)
    public List<UserModel> getAllUsers();
}
