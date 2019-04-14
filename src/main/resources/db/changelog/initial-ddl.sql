
CREATE SEQUENCE PROFILE_ID_SEQ;

CREATE TABLE profile (
                id NUMBER NOT NULL,
                first_name VARCHAR2(50) NOT NULL,
                last_name VARCHAR2(50) NOT NULL,
                date_of_birth DATE NOT NULL,
                email VARCHAR2(100) NOT NULL,
                nationality VARCHAR2(50) NOT NULL,
                sex VARCHAR2(10) NOT NULL,
                mobile_number VARCHAR2(30) NOT NULL,
                occupation VARCHAR2(50) NOT NULL,
                national_id VARCHAR2(30) NOT NULL,
                passport_id VARCHAR2(30) NOT NULL,
                tin_number VARCHAR2(30) NOT NULL,
                updated_date TIMESTAMP NOT NULL,
                created_date TIMESTAMP NOT NULL,
                created_by NUMBER NOT NULL,
                updated_by NUMBER NOT NULL,
                CONSTRAINT PROFILE_PK PRIMARY KEY (id)
);


CREATE SEQUENCE ADDRESS_ID_SEQ;

CREATE TABLE address (
                id NUMBER NOT NULL,
                road_no VARCHAR2(50) NOT NULL,
                house_no VARCHAR2(50) NOT NULL,
                post_code VARCHAR2(50) NOT NULL,
                city VARCHAR2(50) NOT NULL,
                country VARCHAR2(50) NOT NULL,
                contact VARCHAR2(50) NOT NULL,
                address_type VARCHAR2(50) NOT NULL,
                profile_id NUMBER NOT NULL,
                CONSTRAINT ADDRESS_PK PRIMARY KEY (id)
);


CREATE SEQUENCE BANK_PRODUCT_ID_SEQ;

CREATE TABLE bank_product (
                id NUMBER NOT NULL,
                product_name VARCHAR2(50) NOT NULL,
                product_code VARCHAR2(20) NOT NULL,
                product_desc VARCHAR2(100) NOT NULL,
                product_creation_date TIMESTAMP NOT NULL,
                product_active NUMBER NOT NULL,
                CONSTRAINT BANK_PRODUCT_PK PRIMARY KEY (id)
);


CREATE SEQUENCE BRANCH_ID_SEQ;

CREATE TABLE branch (
                id NUMBER NOT NULL,
                branch_name VARCHAR2(50) NOT NULL,
                branch_address VARCHAR2(120) NOT NULL,
                branch_code VARCHAR2(50) NOT NULL,
                branch_contact VARCHAR2(50) NOT NULL,
                branch_routing_number VARCHAR2(30) NOT NULL,
                CONSTRAINT BRANCH_PK PRIMARY KEY (id)
);


CREATE SEQUENCE CUSTOMER_ID_SEQ;

CREATE TABLE customer (
                id NUMBER NOT NULL,
                active RAW NOT NULL,
                branch_id NUMBER NOT NULL,
                locked NUMBER NOT NULL,
                created_by NUMBER NOT NULL,
                created_date TIMESTAMP NOT NULL,
                updated_by NUMBER NOT NULL,
                updated_date TIMESTAMP NOT NULL,
                profile_id_fk NUMBER NOT NULL,
                CONSTRAINT CUSTOMER_PK PRIMARY KEY (id)
);


CREATE TABLE account (
                id NUMBER NOT NULL,
                balance NUMBER NOT NULL,
                branch_id NUMBER NOT NULL,
                customer_id NUMBER NOT NULL,
                enabled RAW NOT NULL,
                account_type VARCHAR2(30) NOT NULL,
                created_by NUMBER NOT NULL,
                updated_by NUMBER NOT NULL,
                updated_date TIMESTAMP NOT NULL,
                created_date TIMESTAMP NOT NULL,
                brank_product_id_fk NUMBER NOT NULL,
                CONSTRAINT ACCOUNT_PK PRIMARY KEY (id)
);


CREATE SEQUENCE ACC_TRANSANC_HISTORY_ID_SEQ;

CREATE TABLE account_transanction_history (
                id NUMBER NOT NULL,
                credit_amount NUMBER NOT NULL,
                transanction_date TIMESTAMP NOT NULL,
                transanction_type VARCHAR2(30) NOT NULL,
                from_account VARCHAR2 NOT NULL,
                to_account VARCHAR2 NOT NULL,
                reference VARCHAR2(200) NOT NULL,
                debit_amount NUMBER NOT NULL,
                account_id NUMBER NOT NULL,
                CONSTRAINT ACCOUNT_TRANSANCTION_HISTOR881 PRIMARY KEY (id)
);


CREATE SEQUENCE BENEFICIAR_ACCOUNT_ID_SEQ;

CREATE TABLE beneficiar_account (
                id NUMBER NOT NULL,
                receiver_name VARCHAR2(100) NOT NULL,
                receiver_account VARCHAR2(20) NOT NULL,
                receiver_bank_name VARCHAR2(120) NOT NULL,
                receiver_branch_name VARCHAR2(60) NOT NULL,
                receiver_branch_district_name VARCHAR2(100) NOT NULL,
                receiver_bank_routing VARCHAR2(30) NOT NULL,
                receiver_alias_name VARCHAR2(100) NOT NULL,
                receiver_is_on_same_bank NUMBER NOT NULL,
                customer_id_fk NUMBER NOT NULL,
                CONSTRAINT BENEFICIAR_ACCOUNT_PK PRIMARY KEY (id)
);


CREATE SEQUENCE USER_ACCOUNT_ID_SEQ;

CREATE TABLE user_account (
                id NUMBER NOT NULL,
                email VARCHAR2(100) NOT NULL,
                password VARCHAR2(128) NOT NULL,
                enabled NUMBER NOT NULL,
                deleted NUMBER NOT NULL,
                profile_id NUMBER NOT NULL,
                locked NUMBER NOT NULL,
                CONSTRAINT USER_ACCOUNT_PK PRIMARY KEY (id)
);


CREATE SEQUENCE USER_ROLE_ID_SEQ;

CREATE TABLE user_role (
                id NUMBER NOT NULL,
                role_name VARCHAR2(30) NOT NULL,
                user_id NUMBER NOT NULL,
                CONSTRAINT USER_ROLE_PK PRIMARY KEY (id)
);


ALTER TABLE customer ADD CONSTRAINT PROFILE_CUSTOMER_FK
FOREIGN KEY (profile_id_fk)
REFERENCES profile (id)
NOT DEFERRABLE;

ALTER TABLE address ADD CONSTRAINT PROFILE_ADDRESS_FK
FOREIGN KEY (profile_id)
REFERENCES profile (id)
NOT DEFERRABLE;

ALTER TABLE account ADD CONSTRAINT BRANK_PRODUCT_ACCOUNT_FK
FOREIGN KEY (brank_product_id_fk)
REFERENCES bank_product (id)
NOT DEFERRABLE;

ALTER TABLE customer ADD CONSTRAINT BRANCH_CUSTOMER_FK
FOREIGN KEY (branch_id)
REFERENCES branch (id)
NOT DEFERRABLE;

ALTER TABLE account ADD CONSTRAINT BRANCH_ACCOUNT_FK
FOREIGN KEY (branch_id)
REFERENCES branch (id)
NOT DEFERRABLE;

ALTER TABLE beneficiar_account ADD CONSTRAINT CUSTOMER_BENEFICIAR_ACCOUNT_FK
FOREIGN KEY (customer_id_fk)
REFERENCES customer (id)
NOT DEFERRABLE;

ALTER TABLE account ADD CONSTRAINT CUSTOMER_ACCOUNT_FK
FOREIGN KEY (customer_id)
REFERENCES customer (id)
NOT DEFERRABLE;

ALTER TABLE account_transanction_history ADD CONSTRAINT ACC_TRANSANCTION_HISTORY_FK
FOREIGN KEY (account_id)
REFERENCES account (id)
NOT DEFERRABLE;

ALTER TABLE user_role ADD CONSTRAINT USER_ACCOUNT_USER_ROLE_FK
FOREIGN KEY (user_id)
REFERENCES user_account (id)
NOT DEFERRABLE;
