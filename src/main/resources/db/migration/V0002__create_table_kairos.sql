
CREATE SCHEMA IF NOT EXISTS checklist;

/*pack pending docs*/ /*TPEND007*/
DROP TABLE IF EXISTS checklist.pack  CASCADE;
CREATE TABLE IF NOT EXISTS checklist.pack
(
    id              	 SERIAL PRIMARY KEY,/*TPEND008*/
    description     	 VARCHAR(20) NOT NULL,
    product_code         CHAR(1)     NOT NULL,
    business_code        CHAR(1)    DEFAULT 'NULL',
    client_type_code     CHAR(1)    DEFAULT 'NULL',
    bail_flag            NUMERIC(1) DEFAULT NULL, /*livrança*/
    insurance_flag       NUMERIC(1) DEFAULT NULL,
    reservation_type     CHAR(1)    DEFAULT 'NULL',
    vehicle_type         CHAR(1)    DEFAULT 'NULL'
    );


/*Multiples*/ /*TPEND009*/
DROP TABLE IF EXISTS checklist.multiple CASCADE;
CREATE TABLE IF NOT EXISTS checklist.multiple (

    id CHAR(1)          NOT NULL PRIMARY KEY,
    description  	CHAR(15)             NOT NULL,
    active_flag  	NUMERIC(1) DEFAULT 0 NOT NULL
    );

/*Category*/ /*TPEND003*/
DROP TABLE IF EXISTS checklist.category CASCADE;
CREATE TABLE IF NOT EXISTS checklist.category (

    id CHAR(1)   NOT NULL PRIMARY KEY,
    description  VARCHAR(35)          NOT NULL,
    active_flag  NUMERIC(1) DEFAULT 0 NOT NULL

    );

/*Sub Types ?*/ /*TPEND002*/
DROP TABLE IF EXISTS checklist.document_type CASCADE;
CREATE TABLE IF NOT EXISTS checklist.document_type(

    id CHAR(1)              NOT NULL PRIMARY KEY,
    description  VARCHAR(35)          NOT NULL,
    active_flag  NUMERIC(1) DEFAULT 0 NOT NULL

    );

/*possible documents*/  /*TPEND001*/
DROP TABLE IF EXISTS checklist.document CASCADE;
CREATE TABLE IF NOT EXISTS checklist.document (

    id         SERIAL PRIMARY KEY,
    description  VARCHAR(80)          NOT NULL,
    document_type_id CHAR(1)         NOT NULL /*TPEND002*/ REFERENCES checklist.document_type (id),
    category_id CHAR(1)              NOT NULL  /*TPEND003*/ REFERENCES checklist.category (id),
    multiple_id CHAR(1)              NOT NULL  /*TPEND009*/ REFERENCES checklist.multiple (id),
    authorization_flag NUMERIC(1)    NOT NULL,
    active_flag NUMERIC(1) DEFAULT 0 NOT NULL
    );

/*pack pending docs related*/ /*TPEND008*/
DROP TABLE IF EXISTS checklist.document_pack;
CREATE TABLE IF NOT EXISTS checklist.document_pack
(
    pack_id integer NOT NULL/*TPEND007*/ REFERENCES checklist.pack(id),
    document_id  integer  NOT NULL /*TPEND001*/ REFERENCES checklist.document(id)
    );


/*vendor notifications*/ /*TPEND010*/
DROP TABLE IF EXISTS checklist.vendor_notification;
CREATE TABLE IF NOT EXISTS checklist.vendor_notification
(
    vendor_id 	  	CHAR(11)             NOT NULL,
    document_id   	integer           NOT NULL REFERENCES checklist.document(id),
    notification_value  NUMERIC(5)           NOT NULL,
    active_flag  	NUMERIC(1) DEFAULT 0 NOT NULL
    );

/*reason*/ /*TPEND005*/
DROP TABLE IF EXISTS checklist.reason CASCADE;
CREATE TABLE IF NOT EXISTS checklist.reason
(
    id SERIAL PRIMARY KEY,
    description  VARCHAR(80)          NOT NULL,
    active_flag  NUMERIC(1) DEFAULT 0 NOT NULL
    );

DROP TABLE IF EXISTS checklist.owner CASCADE;
/*owner do documento */ /*FTB0202F + Enum das entidades*/
CREATE TABLE IF NOT EXISTS checklist.owner(

    id CHAR(3) PRIMARY KEY,
    description  VARCHAR(35)          NOT NULL,
    active_flag  NUMERIC(1) DEFAULT 0 NOT NULL
    );

DROP TABLE IF EXISTS checklist.status CASCADE;
/*status*/ /*TPEND004*/
CREATE TABLE IF NOT EXISTS checklist.status(

    id SERIAL PRIMARY KEY,
    description  VARCHAR(35)          NOT NULL,
    active_flag  NUMERIC(1) DEFAULT 0 NOT NULL
    );

/*FPEND001*/
DROP TABLE IF EXISTS checklist.pending_document;
CREATE TABLE IF NOT EXISTS checklist.pending_document (

    id        SERIAL PRIMARY KEY,
    proposal_id    NUMERIC(11)  NOT NULL, /*FPEND002*/
    brand          CHAR(10)     NOT NULL, /*FPEND002*/
    document_id    integer    NOT NULL /*TPEND001*/ REFERENCES checklist.document(id),
    status_id  integer    NOT NULL /*TPEND004*/ REFERENCES checklist.status(id),
    reason_id  integer    DEFAULT NULL /*TPEND005*/ REFERENCES checklist.reason(id),
    owner_id   CHAR(3)      DEFAULT 'NULL' REFERENCES checklist.owner (id),
    manual_flag   NUMERIC(1)   DEFAULT 0 NOT NULL,
    update_user CHAR(10)     DEFAULT 'NULL',
    gdms_guid   CHAR(40)     DEFAULT 'NULL',
    creation_timestamp        TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_timestamp       TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP NOT NULL
    );

/*reason*/ /*TPEND006*/
DROP TABLE IF EXISTS REASON_CATEGORY;
CREATE TABLE REASON_CATEGORY
(
    REASON_ID    NUMERIC(3) not null,
    CATEGORY_ID  CHAR(1)    not null
);


/*internal/external notes and authorizer*/  /*FPEND002*/
DROP TABLE IF EXISTS checklist.documentation_note;
CREATE TABLE IF NOT EXISTS checklist.documentation_note(

    proposal_id       NUMERIC(11)  NOT NULL PRIMARY KEY,
    brand        CHAR(10)     NOT NULL,
    internal_note    CHAR(300) DEFAULT 'NULL',
    external_note    CHAR(300) DEFAULT 'NULL',
    update_user      CHAR(10)  DEFAULT 'NULL',
    authorization_user CHAR(10)  DEFAULT 'NULL'

    );


/*TGEN020*/
DROP TABLE IF EXISTS checklist.history_field;
create table checklist.history_field
(
    SCHEMA_NAME CHAR(10) default ''  not null,
    TABLE_NAME CHAR(10) default ''  not null,
    FIELD_NAME CHAR(10) default ''  not null,
    SHOW_FLAG CHAR(1) default ''  not null,
    SHORT_TEXT CHAR(25) default ''  not null,
    LONG_TEXT CHAR(50) default '' not null

);

/*HGENE001*/
DROP TABLE IF EXISTS checklist.history;
CREATE TABLE IF NOT EXISTS checklist.history
(
    id        SERIAL PRIMARY KEY,
    schema_name   CHAR(10)     DEFAULT ''                NOT NULL,
    table_name  CHAR(10)     DEFAULT ''                NOT NULL,/*TGEN020*/
    key_1      VARCHAR(30)  DEFAULT ''                NOT NULL,/*TGEN020*/
    key_2      VARCHAR(30)  DEFAULT ''                NOT NULL,
    key_3      VARCHAR(30)  DEFAULT ''                NOT NULL,
    field_name CHAR(10)     DEFAULT ''                NOT NULL,/*TGEN020*/
    action    CHAR(1)      DEFAULT ''                NOT NULL,
    image     CHAR(1)      DEFAULT ''                NOT NULL,
    value     VARCHAR(100),
    creation_timestamp TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_user CHAR(10)     DEFAULT ''                NOT NULL
    );







/*
/*pack pending docs*/ /*TPEND007*/
DROP TABLE IF EXISTS PENDDOCPACK;
CREATE TABLE IF NOT EXISTS PENDDOCPACK
(
    IDPACK          SERIAL PRIMARY KEY,/*TPEND008*/
    DESCRIPTION     VARCHAR(20) NOT NULL,
    PRODUCT         CHAR(1)     NOT NULL,
    BUSINESS        CHAR(1)    DEFAULT 'NULL',
    CLIENTTYPE      CHAR(1)    DEFAULT 'NULL',
    LIVRANCA        NUMERIC(1) DEFAULT NULL,
    INSURANCE       NUMERIC(1) DEFAULT NULL,
    RESERVATIONTYPE CHAR(1)    DEFAULT 'NULL',
    VEHICLETYPE     CHAR(1)    DEFAULT 'NULL'
);

/*pack pending docs related*/ /*TPEND008*/
DROP TABLE IF EXISTS PENDDOCPACKPENDING;
CREATE TABLE IF NOT EXISTS PENDDOCPACKPENDING
(
    IDPACK NUMERIC(9) NOT NULL,/*TPEND007*/
    IDDOC  NUMERIC(3) NOT NULL /*TPEND001*/
);


/*vendor notifications*/ /*TPEND010*/
DROP TABLE IF EXISTS PENDDOCVENDORNOTIFICATION;
CREATE TABLE IF NOT EXISTS PENDDOCVENDORNOTIFICATION
(
    VNDVEND CHAR(11)             NOT NULL,
    IDDOC   NUMERIC(3)           NOT NULL,
    VALUE   NUMERIC(5)           NOT NULL,
    ACTIVE  NUMERIC(1) DEFAULT 0 NOT NULL
);


/*Multiples*/ /*TPEND009*/
DROP TABLE IF EXISTS PENDDOCMULTIPLES CASCADE;
CREATE TABLE IF NOT EXISTS PENDDOCMULTIPLES (

    IDMULTIPLE   CHAR(1)              NOT NULL PRIMARY KEY,
    DESCRIPTION  CHAR(15)             NOT NULL,
    ACTIVE       NUMERIC(1) DEFAULT 0 NOT NULL
);

/*Category*/ /*TPEND003*/
DROP TABLE IF EXISTS PENDDOCCATEGORY CASCADE;
CREATE TABLE IF NOT EXISTS PENDDOCCATEGORY (

    IDCATEGORY  CHAR(1)     NOT NULL PRIMARY KEY,
    DESCRIPTION VARCHAR(35)          NOT NULL,
    ACTIVE      NUMERIC(1) DEFAULT 0 NOT NULL

);

/*Sub Types ?*/ /*TPEND002*/
DROP TABLE IF EXISTS PENDDOCSUBTYPE CASCADE;
CREATE TABLE IF NOT EXISTS PENDDOCSUBTYPE(

    IDDOCTYPE   CHAR(1)              NOT NULL PRIMARY KEY,
    DESCRIPTION VARCHAR(15)          NOT NULL,
    ACTIVE      NUMERIC(1) DEFAULT 0 NOT NULL

);


/*FPEND001*/
DROP TABLE IF EXISTS PENDINGDOCUMENTATION;
CREATE TABLE IF NOT EXISTS PENDINGDOCUMENTATION (

    IDPEND        SERIAL PRIMARY KEY,
    PENDNOTEID    NUMERIC(11)  NOT NULL, /*FPEND002*/
    PENDBRAND     CHAR(10)     NOT NULL, /*FPEND002*/
    PENDDOCID     NUMERIC(3)   NOT NULL, /*TPEND001*/
    PENDSTATUSID  NUMERIC(2)   NOT NULL, /*TPEND004*/
    PENDREASONID  NUMERIC(3)   DEFAULT NULL, /*TPEND005*/
    PENDOWNERTYPE CHAR(3)      DEFAULT 'NULL',
    PENDDOCTYPE   NUMERIC(1)   DEFAULT 0 NOT NULL,
    ALTUSER       CHAR(10)     DEFAULT 'NULL',
    DOCALFRESCO   CHAR(40)     DEFAULT 'NULL',
    TSTAMP        TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP NOT NULL,
    ALTDATE       TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP NOT NULL
    );

/*reason*/ /*TPEND005*/
DROP TABLE IF EXISTS PENDDOCREASON;
CREATE TABLE IF NOT EXISTS PENDDOCREASON
(
    IDREASON    SERIAL PRIMARY KEY,
    DESCRIPTION VARCHAR(80)          NOT NULL,
    ACTIVE      NUMERIC(1) default 0 NOT NULL
);

/*reason*/ /*TPEND006*/
DROP TABLE IF EXISTS PENDDOCREASONCATEGORY;
CREATE TABLE PENDDOCREASONCATEGORY
(
    IDREASON    NUMERIC(3) not null,
    IDCATEGORY  CHAR(1)    not null
);


DROP TABLE IF EXISTS PENDDOCSTATUS;
/*status*/ /*TPEND004*/
CREATE TABLE IF NOT EXISTS PENDDOCSTATUS (

    IDSTATUS     SERIAL PRIMARY KEY,
    DESCRIPTION  VARCHAR(20)            NOT NULL,
    ACTIVE       NUMERIC(1) default 0   NOT NULL
    );

/*possible documents*/  /*TPEND001*/
DROP TABLE IF EXISTS POSSIBLEDOCUMENT ;
CREATE TABLE IF NOT EXISTS POSSIBLEDOCUMENT (

    IDDOC         SERIAL PRIMARY KEY,
    DESCRIPTION   VARCHAR(80)          NOT NULL,
    IDDOCTYPE     CHAR(1)              NOT NULL /*TPEND002*/
    REFERENCES checklist.PENDDOCSUBTYPE (IDDOCTYPE),
    IDCATEGORY   CHAR(1)              NOT NULL  /*TPEND003*/
    REFERENCES checklist.PENDDOCCATEGORY (IDCATEGORY),
    MULTIPLE     CHAR(1)              NOT NULL  /*TPEND009*/
    REFERENCES checklist.PENDDOCMULTIPLES (IDMULTIPLE),
    AUTHLEVEL     NUMERIC(1)           NOT NULL,
    ACTIVE        NUMERIC(1) DEFAULT 0 NOT NULL
    );


/*internal/external notes and authorizer*/  /*FPEND002*/
DROP TABLE IF EXISTS PENDDOCUMENTATIONOTES;
CREATE TABLE IF NOT EXISTS PENDDOCUMENTATIONOTES (

    PENDNOTEID       NUMERIC(11)  NOT NULL PRIMARY KEY,
    PENDBRAND        CHAR(10)     NOT NULL,
    PENDINTERNOTE    CHAR(300) DEFAULT 'NULL',
    PENDEXTERNOTE    CHAR(300) DEFAULT 'NULL',
    ALTUSER          CHAR(10)  DEFAULT 'NULL',
    AUTZUSER         CHAR(10)  DEFAULT 'NULL'

    );

/*HGENE001*/
DROP TABLE IF EXISTS PENDDOCHISTORY;
CREATE TABLE IF NOT EXISTS PENDDOCHISTORY
(
    ID        SERIAL PRIMARY KEY,
    LIBNAME   CHAR(10)     DEFAULT ''                NOT NULL,
    FILENAME  CHAR(10)     DEFAULT ''                NOT NULL,
    KEY1      VARCHAR(30)  DEFAULT ''                NOT NULL,
    KEY2      VARCHAR(30)  DEFAULT ''                NOT NULL,
    KEY3      VARCHAR(30)  DEFAULT ''                NOT NULL,
    FIELDNAME CHAR(10)     DEFAULT ''                NOT NULL,
    ACTION    CHAR(1)      DEFAULT ''                NOT NULL,
    IMAGE     CHAR(1)      DEFAULT ''                NOT NULL,
    VALUE     VARCHAR(100),
    TSTAMP    TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP NOT NULL,
    ALTUSER   CHAR(10)     DEFAULT ''                NOT NULL
);
*/