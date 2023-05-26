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
    CONSTRAINT fk_elevators_building_id FOREIGN KEY (building_id) REFERENCES buildings(id) ON DELETE CASCADE
)
/

CREATE TABLE admin_rights (
    account_id INT NOT NULL,
    building_id INT NOT NULL,
    CONSTRAINT fk_admin_rights_account_id FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE,
    CONSTRAINT fk_aadmin_rights_building_id FOREIGN KEY (building_id) REFERENCES buildings(id) ON DELETE CASCADE
)
/
COMMIT;




create or replace trigger delete_accounts
for delete on accounts
compound trigger
    v_min_id int := 0;
    v_max_id int := 0;
    v_id int;
    cursor list_accounts is select id from accounts where id>v_max_id;
    after each row is
    begin
        if v_min_id=0 then
            v_min_id:=:OLD.id;
        end if;
        v_max_id := :OLD.id;
    end after each row;
    after statement is
    begin
        open list_accounts;
        loop
            fetch list_accounts into v_id;
            exit when list_accounts%NOTFOUND;
            UPDATE accounts set id=v_min_id+1 where id=v_id;
        end loop;        
    end after statement;
end delete_accounts;

create or replace trigger delete_buildings
for delete on buildings
compound trigger
    v_min_id int := 0;
    v_max_id int := 0;
    v_id int;
    cursor list_buildings is select id from buildings where id>v_max_id;
    after each row is
    begin
        if v_min_id=0 then
            v_min_id:=:OLD.id;
        end if;
        v_max_id := :OLD.id;
    end after each row;
    after statement is
    begin
        open list_buildings;
        loop
            fetch list_buildings into v_id;
            exit when list_buildings%NOTFOUND;
            UPDATE buildings set id=v_min_id+1 where id=v_id;
        end loop;        
    end after statement;
end delete_buildings;

create or replace trigger delete_elevators
for delete on elevators
compound trigger
    v_min_id int := 0;
    v_max_id int := 0;
    v_id int;
    cursor list_elevators is select id from elevators where id>v_max_id;
    after each row is
    begin
        if v_min_id=0 then
            v_min_id:=:OLD.id;
        end if;
        v_max_id := :OLD.id;
    end after each row;
    after statement is
    begin
        open list_elevators;
        loop
            fetch list_elevators into v_id;
            exit when list_elevators%NOTFOUND;
            UPDATE elevators set id=v_min_id+1 where id=v_id;
        end loop;        
    end after statement;
end delete_elevators;

create or replace trigger update_id_accounts
after update of id on accounts
for each row
begin
    update admin_rights set account_id = :new.id where account_id = :old.id;
end;

create or replace trigger update_id_buildings
after update of id on buildings
for each row
begin
    update admin_rights set building_id = :new.id where building_id = :old.id;
    update elevators set building_id = :new.id where building_id = :old.id;
end;

create or replace trigger add_overseer_rights
after insert on buildings
for each row
begin
    insert into admin_rights values (1,:new.id);
end;
