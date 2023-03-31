package com.example.system.domain.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * テーブルの共通項目を定義したクラスです。</br>
 * 全てのEntityクラスはこのクラスを継承して作成します。</br>
 * テーブル毎の共通カラムを必要に応じて定義してください。
 */
@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class) // SpringDataが提供している監査記録用のEventListener
public class BaseEntity {

    /**
     * データ登録日時
     */
    @CreatedDate
    @Column(name = "createDt")
    private LocalDateTime createDt;

    /**
     * データ登録ユーザ名
     */
    @CreatedBy
    @Column(name = "createUser")
    private String createUser;

    /**
     * データ更新日時
     */
    @LastModifiedDate
    @Column(name = "updateDt")
    private LocalDateTime updateDt;

    /**
     * データ更新ユーザ名
     */
    @LastModifiedBy
    @Column(name = "updateUser")
    private String updateUser;

//    /**
//     * データ登録前に共通的に実行されるメソッド
//     */
//    @PrePersist
//    public void preInsert() {
//        setCreateDt(LocalDateTime.now());
//        setUpdateDt(LocalDate.now());
//    }
//
//    /**
//     * データ更新前に共通的に実行されるメソッド
//     */
//    @PreUpdate
//    public void preUpdate() {
//        setUpdateDt(LocalDate.now());
//    }

}
