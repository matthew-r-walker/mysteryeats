CREATE USER mysteryeats_user@localhost IDENTIFIED BY 'p@$$w0rd';
GRANT ALL ON mysteryeats_db.* TO mysteryeats_user@localhost;


DROP DATABASE mysteryeats_db;


CREATE DATABASE IF NOT EXISTS mysteryeats_db;