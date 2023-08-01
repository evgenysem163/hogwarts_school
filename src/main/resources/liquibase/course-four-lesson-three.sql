--liquibase formatted sql

--changeset avtor:1
CREATE INDEX IDX_students_name ON students(name);

--changeset avtor:2
CREATE INDEX IDX_faculties_name_color ON faculties(name, color);