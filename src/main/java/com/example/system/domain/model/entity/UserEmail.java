package com.example.system.domain.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_info")
@Getter
@Setter
public class UserEmailEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;
    @Column(name = "branch")
    private int branch;
    @Column(name = "email_type")
    private char emailType;
    @Column(name = "email_addr")
    private String emailAddr;
}
