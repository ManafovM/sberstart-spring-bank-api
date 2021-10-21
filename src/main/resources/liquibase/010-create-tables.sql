--liquibase formatted sql
--changeset manafov:create-tables splitStatements:true endDelimiter:;
create table CUSTOMER
(
    ID        BIGINT auto_increment
        primary key,
    FIRSTNAME VARCHAR(255) NOT NULL,
    LASTNAME  VARCHAR(255) NOT NULL,
    ROLE      VARCHAR(255) NOT NULL,
    VERSION BIGINT NOT NULL
);

create table ACCOUNT
(
    ID          BIGINT auto_increment
        primary key,
    AMOUNT      DECIMAL(19, 2) NOT NULL,
    NUMBER      VARCHAR(255) NOT NULL,
    CUSTOMER_ID BIGINT NOT NULL,
    VERSION BIGINT NOT NULL,
    constraint FK_CUSTOMER_ID
        foreign key (CUSTOMER_ID) references CUSTOMER (ID)
);

create table CARD
(
    ID         BIGINT auto_increment
        primary key,
    CVC        VARCHAR(255) NOT NULL,
    EXPIREDATE VARCHAR(255) NOT NULL,
    NUMBER     VARCHAR(255) NOT NULL,
    STATUS     VARCHAR(255) NOT NULL,
    ACCOUNT_ID BIGINT NOT NULL,
    VERSION BIGINT NOT NULL,
    constraint FK_ACCOUNT_ID
        foreign key (ACCOUNT_ID) references ACCOUNT (ID)
);