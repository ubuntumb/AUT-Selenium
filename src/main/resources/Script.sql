--<ScriptOptions statementTerminator=";"/>

CREATE TABLE TEST_DATA (
		ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_E94D9CD6_864D_4EE1_8DC5_255CD996D5C8) NOT NULL,
		CLASS_NAME VARCHAR(255),
		ELEMENT_NAME VARCHAR(255),
		ELEMENT_INPUT VARCHAR(512),
		ELEMENT_OUPUT VARCHAR(512)
	);

CREATE UNIQUE INDEX PRIMARY_KEY_6 ON TEST_DATA (ID ASC);

ALTER TABLE TEST_DATA ADD CONSTRAINT CONSTRAINT_6 PRIMARY KEY (ID);

