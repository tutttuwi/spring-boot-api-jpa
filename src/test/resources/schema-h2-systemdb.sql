/*
--ユーザーの作成
--CREATE USER docker;
--DBの作成
--CREATE DATABASE docker;
--ユーザーにDBの権限をまとめて付与
--GRANT ALL PRIVILEGES ON DATABASE docker TO docker;
--ユーザーを切り替え
--\c docker
*/

-- Table : user_info
drop table IF EXISTS public.user_info cascade;

create table public.user_info (
  user_id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY
  , fst_name varchar(255) not null
  , lst_name varchar(255) not null
  , birth_dt date 
  , create_user varchar(128) not null
  , create_dt timestamp(6) without time zone not null
  , update_user varchar(128) not null
  , update_dt timestamp(6) without time zone not null
);

-- create unique index account_connection_rank on account_connection(user_id,provider_id,rank);

--INSERT INTO public.user_info VALUES(1,'taro','tanaka','1990-01-01',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
--INSERT INTO public.user_info VALUES(2,'hanako','yamada','1990-01-01',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
--INSERT INTO public.user_info VALUES(3,'ichiro','yoshida','1990-01-01',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
--INSERT INTO public.user_info VALUES(4,'jiro','nishida','1990-01-01',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
--INSERT INTO public.user_info VALUES(5,'kyoko','hashida','1990-01-01',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
--INSERT INTO public.user_info VALUES(6,'kohei','nemoto','1990-01-01',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
--INSERT INTO public.user_info VALUES(7,'masashi','nishikawa','1990-01-01',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
--INSERT INTO public.user_info VALUES(8,'kakeru','yoshikawa','1990-01-01',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
--INSERT INTO public.user_info VALUES(9,'kai','yamamoto','1990-01-01',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
--INSERT INTO public.user_info VALUES(10,'riku','higashi','1990-01-01',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
--
--INSERT INTO public.user_info(fst_name,lst_name,birth_dt,create_user,create_dt,update_user,update_dt) VALUES('taro','tanaka','1990-01-01','INIT_USER',CURRENT_TIMESTAMP,'INIT_USER',CURRENT_TIMESTAMP);
--INSERT INTO public.user_info(fst_name,lst_name,birth_dt,create_user,create_dt,update_user,update_dt) VALUES('hanako','yamada','1990-01-01','INIT_USER',CURRENT_TIMESTAMP,'INIT_USER',CURRENT_TIMESTAMP);
--INSERT INTO public.user_info(fst_name,lst_name,birth_dt,create_user,create_dt,update_user,update_dt) VALUES('ichiro','yoshida','1990-01-01','INIT_USER',CURRENT_TIMESTAMP,'INIT_USER',CURRENT_TIMESTAMP);
--INSERT INTO public.user_info(fst_name,lst_name,birth_dt,create_user,create_dt,update_user,update_dt) VALUES('jiro','nishida','1990-01-01','INIT_USER',CURRENT_TIMESTAMP,'INIT_USER',CURRENT_TIMESTAMP);
--INSERT INTO public.user_info(fst_name,lst_name,birth_dt,create_user,create_dt,update_user,update_dt) VALUES('kyoko','hashida','1990-01-01','INIT_USER',CURRENT_TIMESTAMP,'INIT_USER',CURRENT_TIMESTAMP);
--INSERT INTO public.user_info(fst_name,lst_name,birth_dt,create_user,create_dt,update_user,update_dt) VALUES('kohei','nemoto','1990-01-01','INIT_USER',CURRENT_TIMESTAMP,'INIT_USER',CURRENT_TIMESTAMP);
--INSERT INTO public.user_info(fst_name,lst_name,birth_dt,create_user,create_dt,update_user,update_dt) VALUES('masashi','nishikawa','1990-01-01','INIT_USER',CURRENT_TIMESTAMP,'INIT_USER',CURRENT_TIMESTAMP);
--INSERT INTO public.user_info(fst_name,lst_name,birth_dt,create_user,create_dt,update_user,update_dt) VALUES('kakeru','yoshikawa','1990-01-01','INIT_USER',CURRENT_TIMESTAMP,'INIT_USER',CURRENT_TIMESTAMP);
--INSERT INTO public.user_info(fst_name,lst_name,birth_dt,create_user,create_dt,update_user,update_dt) VALUES('kai','yamamoto','1990-01-01','INIT_USER',CURRENT_TIMESTAMP,'INIT_USER',CURRENT_TIMESTAMP);
--INSERT INTO public.user_info(fst_name,lst_name,birth_dt,create_user,create_dt,update_user,update_dt) VALUES('riku','higashi','1990-01-01','INIT_USER',CURRENT_TIMESTAMP,'INIT_USER',CURRENT_TIMESTAMP);

--INSERT INTO public.user_info(fst_name,lst_name,birth_dt,create_user,create_dt,update_user,update_dt) VALUES('taro','tanaka','1990-01-01','INIT_USER',CURRENT_TIMESTAMP,'INIT_USER',CURRENT_TIMESTAMP);
--INSERT INTO public.user_info(fst_name,lst_name,birth_dt,create_user,create_dt,update_user,update_dt) VALUES('hanako','yamada','1990-01-01','INIT_USER',CURRENT_TIMESTAMP,'INIT_USER',CURRENT_TIMESTAMP);
--INSERT INTO public.user_info(fst_name,lst_name,birth_dt,create_user,create_dt,update_user,update_dt) VALUES('ichiro','yoshida','1990-01-01','INIT_USER',CURRENT_TIMESTAMP,'INIT_USER',CURRENT_TIMESTAMP);
--INSERT INTO public.user_info(fst_name,lst_name,birth_dt,create_user,create_dt,update_user,update_dt) VALUES('jiro','nishida','1990-01-01','INIT_USER',CURRENT_TIMESTAMP,'INIT_USER',CURRENT_TIMESTAMP);
--INSERT INTO public.user_info(fst_name,lst_name,birth_dt,create_user,create_dt,update_user,update_dt) VALUES('kyoko','hashida','1990-01-01','INIT_USER',CURRENT_TIMESTAMP,'INIT_USER',CURRENT_TIMESTAMP);
--INSERT INTO public.user_info(fst_name,lst_name,birth_dt,create_user,create_dt,update_user,update_dt) VALUES('kohei','nemoto','1990-01-01','INIT_USER',CURRENT_TIMESTAMP,'INIT_USER',CURRENT_TIMESTAMP);
--INSERT INTO public.user_info(fst_name,lst_name,birth_dt,create_user,create_dt,update_user,update_dt) VALUES('masashi','nishikawa','1990-01-01','INIT_USER',CURRENT_TIMESTAMP,'INIT_USER',CURRENT_TIMESTAMP);
--INSERT INTO public.user_info(fst_name,lst_name,birth_dt,create_user,create_dt,update_user,update_dt) VALUES('kakeru','yoshikawa','1990-01-01','INIT_USER',CURRENT_TIMESTAMP,'INIT_USER',CURRENT_TIMESTAMP);
--INSERT INTO public.user_info(fst_name,lst_name,birth_dt,create_user,create_dt,update_user,update_dt) VALUES('kai','yamamoto','1990-01-01','INIT_USER',CURRENT_TIMESTAMP,'INIT_USER',CURRENT_TIMESTAMP);
--INSERT INTO public.user_info(fst_name,lst_name,birth_dt,create_user,create_dt,update_user,update_dt) VALUES('riku','higashi','1990-01-01','INIT_USER',CURRENT_TIMESTAMP,'INIT_USER',CURRENT_TIMESTAMP);

-- Table : user_email
drop table IF EXISTS public.user_email cascade;

create table public.user_email (
  user_id bigint not null
  , branch int not null
  , email_type char(1) not null -- 1: 自宅, 2:会社
  , email_addr varchar(255) not null
  , create_user varchar(128) not null
  , create_dt timestamp(6) without time zone not null
  , update_user varchar(128) not null
  , update_dt timestamp(6) without time zone not null
  , CONSTRAINT user_email_pk PRIMARY KEY(user_id,branch)
);

--INSERT INTO public.user_email VALUES(1,1,'1','abcdefg123456@example.com','INIT_USER',CURRENT_TIMESTAMP,'INIT_USER',CURRENT_TIMESTAMP);
--INSERT INTO public.user_email VALUES(1,3,'2','abcdefg123456@example.com','INIT_USER',CURRENT_TIMESTAMP,'INIT_USER',CURRENT_TIMESTAMP);
--INSERT INTO public.user_email VALUES(2,1,'1','abcdefg123456@example.com','INIT_USER',CURRENT_TIMESTAMP,'INIT_USER',CURRENT_TIMESTAMP);
--INSERT INTO public.user_email VALUES(1,2,'1','abcdefg123456@example.com','INIT_USER',CURRENT_TIMESTAMP,'INIT_USER',CURRENT_TIMESTAMP);
--INSERT INTO public.user_email VALUES(3,1,'1','abcdefg123456@example.com','INIT_USER',CURRENT_TIMESTAMP,'INIT_USER',CURRENT_TIMESTAMP);
--INSERT INTO public.user_email VALUES(4,1,'1','abcdefg123456@example.com','INIT_USER',CURRENT_TIMESTAMP,'INIT_USER',CURRENT_TIMESTAMP);
--INSERT INTO public.user_email VALUES(5,1,'1','abcdefg123456@example.com','INIT_USER',CURRENT_TIMESTAMP,'INIT_USER',CURRENT_TIMESTAMP);
--INSERT INTO public.user_email VALUES(6,1,'1','abcdefg123456@example.com','INIT_USER',CURRENT_TIMESTAMP,'INIT_USER',CURRENT_TIMESTAMP);
--INSERT INTO public.user_email VALUES(7,1,'1','abcdefg123456@example.com','INIT_USER',CURRENT_TIMESTAMP,'INIT_USER',CURRENT_TIMESTAMP);
--INSERT INTO public.user_email VALUES(8,1,'1','abcdefg123456@example.com','INIT_USER',CURRENT_TIMESTAMP,'INIT_USER',CURRENT_TIMESTAMP);
--INSERT INTO public.user_email VALUES(9,1,'1','abcdefg123456@example.com','INIT_USER',CURRENT_TIMESTAMP,'INIT_USER',CURRENT_TIMESTAMP);

