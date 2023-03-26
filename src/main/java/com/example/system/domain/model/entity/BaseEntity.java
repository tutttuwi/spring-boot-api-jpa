package com.example.system.domain.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "user_info")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;
    @Column(name = "fstName")
    private String fstName;
    @Column(name = "lstName")
    private String lstName;
    @Column(name = "birthDt")
    private LocalDate birthDt;

    @Column(name = "createDt")
    private LocalDate createDt = LocalDate.now();
    @Column(name = "updateDt")
    private LocalDate updateDt = LocalDate.now();

    /**
     * データ登録前に共通的に実行されるメソッド
     */
    @PrePersist
    public void preInsert() {
        setCreateDt(LocalDate.now());
        setUpdateDt(LocalDate.now());
    }

    /**
     * データ更新前に共通的に実行されるメソッド
     */
    @PreUpdate
    public void preUpdate() {
        setUpdateDt(LocalDate.now());
    }

}
