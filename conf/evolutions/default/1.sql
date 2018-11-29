# PERSON schema

# --- !Ups
CREATE TABLE PERSON (
    ID numeric NOT NULL,
    NAME varchar(255) NOT NULL,
    PRIMARY KEY (ID)
);

# --- !Downs

DROP TABLE PERSON;
