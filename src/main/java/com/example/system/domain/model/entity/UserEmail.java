package com.example.system.domain.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_email")
@Getter
@Setter
public class UserEmail extends BaseEntity {

  @Id
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Id
  @Column(name = "branch")
  private int branch;
  @Column(name = "email_type")
  private char emailType;
  @Column(name = "email_addr")
  private String emailAddr;
}
