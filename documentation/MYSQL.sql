DROP DATABASE roadmappdb;

CREATE DATABASE roadmappdb;

use roadmappdb;

CREATE TABLE USER(
	id INT(11) NOT NULL AUTO_INCREMENT,
	email VARCHAR(50) NOT NULL UNIQUE,
	username VARCHAR(50) NOT NULL,
	password VARCHAR(60) NOT NULL,
	role VARCHAR(60) NOT NULL,
	enabled TINYINT(4) NOT NULL DEFAULT 1,
	PRIMARY KEY (id)
);

INSERT INTO USER (email, password, enabled, username, role)
VALUES ('yo@admin.com','$2a$10$lKYldB/h09WdC5w.tfP4Ze8WLCF1jrAke7l9XS9IdpQHyJMbZJwWS', true, 'yo-admin', 'ADMIN');
INSERT INTO USER (email, password, enabled, username, role)
VALUES ('yo@user.com','$2a$10$mVyMMgu8ny7boWgC1QU3XOQvp9QkU4rNKEijBsLoMQCjpSU9qgOBa', true, 'yo-user', 'USER');


CREATE TABLE ROADMAPP(
  roadmapp_id INT NOT NULL auto_increment,
  user_id INT NOT NULL,
  title VARCHAR(50) NOT NULL,
  description VARCHAR(100) NOT NULL,
  PRIMARY KEY (roadmapp_id)
);

INSERT INTO ROADMAPP(title, user_id, description)
VALUES ('Mexican Trip', 1, 'Road trip with my best friends planning for summer 2016.');
INSERT INTO ROADMAPP(title, user_id, description)
VALUES ('Asia Discovery', 2, 'My girl friend and I want to discover the asian culture.');

CREATE TABLE MARKER(
  marker_id INT NOT NULL auto_increment,
  roadmapp_id INT NOT NULL,
  lat FLOAT NOT NULL,
  lng FLOAT NOT NULL,
  marker_index INT NOT NULL,
  title VARCHAR(40) NOT NULL,
  PRIMARY KEY (marker_id)
);

CREATE TABLE ROADMAPP_MARKERS (
  id INT NOT NULL auto_increment,
  marker_id INT NOT NULL,
  roadmapp_id INT NOT NULL,
  PRIMARY KEY (id)
);