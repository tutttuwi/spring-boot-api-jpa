package com.example.system.domain.model.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BaseEntity.class)
public abstract class BaseEntity_ {

	public static volatile SingularAttribute<BaseEntity, String> updateUser;
	public static volatile SingularAttribute<BaseEntity, String> createUser;
	public static volatile SingularAttribute<BaseEntity, LocalDateTime> updateDt;
	public static volatile SingularAttribute<BaseEntity, LocalDateTime> createDt;

	public static final String UPDATE_USER = "updateUser";
	public static final String CREATE_USER = "createUser";
	public static final String UPDATE_DT = "updateDt";
	public static final String CREATE_DT = "createDt";

}

