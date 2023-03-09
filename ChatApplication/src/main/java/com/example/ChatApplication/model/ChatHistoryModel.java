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
@Table(name = "tbl_chat_history")
public class ChatHistoryModel {
    @Id
    @Column(name = "chat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chatId;
    @JoinColumn(name = "to_user_id")
    @ManyToOne
    private UserModel to;
    @JoinColumn(name = "from_user_id")
    @ManyToOne
    private UserModel from;
    private String message;
//    @CreationTimestamp
    @Column(name = "created_time")
    private Timestamp createdDate;
//    @UpdateTimestamp
    @Column(name = "updated_time")
    private Timestamp updatedDate;


}
