DROP TABLE accounts CASCADE CONSTRAINTS;
DROP TABLE admin_rights CASCADE CONSTRAINTS;
DROP TABLE buildings CASCADE CONSTRAINTS;
DROP TABLE elevators CASCADE CONSTRAINTS;

CREATE TABLE accounts (
    id INT NOT NULL PRIMARY KEY,
    name VARCHAR2(100) NOT NULL UNIQUE,
    password VARCHAR2(100) NOT NULL,
    type varchar2(15) NOT NULL
)
/

CREATE TABLE buildings (
    id INT NOT NULL PRIMARY KEY,
    name VARCHAR2(100) NOT NULL UNIQUE
)
/

CREATE TABLE elevators (
    id INT NOT NULL PRIMARY KEY,
    building_id INT NOT NULL,
    lowest_floor INT NOT NULL,
    highest_floor INT NOT NULL,
    status VARCHAR2(15) NOT NULL,
    current_floor INT NOT NULL,
    max_people INT,
    max_weight INT,
    CONSTRAINT fk_elevators_building_id FOREIGN KEY (building_id) REFERENCES buildings(id)
)
/

CREATE TABLE admin_rights (
    account_id INT NOT NULL,
    building_id INT NOT NULL,
    CONSTRAINT fk_admin_rights_account_id FOREIGN KEY (account_id) REFERENCES accounts(id),
    CONSTRAINT fk_aadmin_rights_building_id FOREIGN KEY (building_id) REFERENCES buildings(id)
)
/
COMMIT;
