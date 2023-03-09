package com.example.ChatApplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_user")
public class UserModel {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int userId;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "gender")
    private  String gender;
    @Column(name = "age")
    private int age;
    @Column(name = "phone_number")
    private String phoneNumber;
//    @CreationTimestamp
    @Column(name = "created_time")
    private Timestamp createdDate;
//    @UpdateTimestamp
    @Column(name = "updated_time")
    private Timestamp updatedDate;
    @JoinColumn(name = "status_id")
    @ManyToOne
    private StatusModel statusId;
}
