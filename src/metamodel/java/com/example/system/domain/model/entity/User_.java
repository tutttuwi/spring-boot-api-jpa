package com.example.system.domain.model.entity;

import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ extends com.example.system.domain.model.entity.BaseEntity_ {

	public static volatile ListAttribute<User, UserEmail> userEmailList;
	public static volatile SingularAttribute<User, String> fstName;
	public static volatile SingularAttribute<User, LocalDate> birthDt;
	public static volatile SingularAttribute<User, Long> userId;
	public static volatile SingularAttribute<User, String> lstName;

	public static final String USER_EMAIL_LIST = "userEmailList";
	public static final String FST_NAME = "fstName";
	public static final String BIRTH_DT = "birthDt";
	public static final String USER_ID = "userId";
	public static final String LST_NAME = "lstName";

}

