--liquibase formatted sql
--changeset manafov:create-tables splitStatements:true endDelimiter:;
create table CUSTOMER
(
    ID        BIGINT auto_increment
        primary key,
    FIRSTNAME VARCHAR(255),
    LASTNAME  VARCHAR(255),
    ROLE      VARCHAR(255)
);

create table ACCOUNT
(
    ID          BIGINT auto_increment
        primary key,
    AMOUNT      DECIMAL(19, 2),
    NUMBER      VARCHAR(255),
    CUSTOMER_ID BIGINT,
    constraint FK_CUSTOMER_ID
        foreign key (CUSTOMER_ID) references CUSTOMER (ID)
);

create table CARD
(
    ID         BIGINT auto_increment
        primary key,
    CVC        VARCHAR(255),
    EXPIREDATE VARCHAR(255),
    NUMBER     VARCHAR(255),
    STATUS     VARCHAR(255),
    ACCOUNT_ID BIGINT,
    constraint FK_ACCOUNT_ID
        foreign key (ACCOUNT_ID) references ACCOUNT (ID)
);