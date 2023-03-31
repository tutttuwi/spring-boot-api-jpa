package com.example.system.domain.model.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserEmail.class)
public abstract class UserEmail_ extends com.example.system.domain.model.entity.BaseEntity_ {

	public static volatile SingularAttribute<UserEmail, Character> emailType;
	public static volatile SingularAttribute<UserEmail, String> emailAddr;
	public static volatile SingularAttribute<UserEmail, User> user;
	public static volatile SingularAttribute<UserEmail, Integer> branch;

	public static final String EMAIL_TYPE = "emailType";
	public static final String EMAIL_ADDR = "emailAddr";
	public static final String USER = "user";
	public static final String BRANCH = "branch";

}

