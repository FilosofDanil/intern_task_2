CREATE TYPE jobs AS ENUM (
    'FRONTEND_DEVELOPER',
    'BACKEND_DEVELOPER',
    'SALES',
    'HIRING_MANAGER',
    'RECRUITER',
    'PROJECT_MANAGER',
    'SQL_DEVELOPER',
    'DEVOPS'
    );

CREATE TABLE companies (

                           id BIGINT NOT NULL,
                           company_name VARCHAR(255),
                           country VARCHAR(255),
                           foundation_date DATE,
                           PRIMARY KEY (id)
);

CREATE TABLE employees (
                           id BIGINT NOT NULL,
                           employee_name VARCHAR(255),
                           employee_surname VARCHAR(255),
                           salary INTEGER,
                           hiring_date DATE,
                           job VARCHAR(255),
                           company_id BIGINT NOT NULL,
                           PRIMARY KEY (id),
                           CONSTRAINT fk_company_id FOREIGN KEY (company_id) REFERENCES companies(id)
);

CREATE SEQUENCE employee_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE company_seq START WITH 1 INCREMENT BY 1;

ALTER TABLE companies
    ALTER COLUMN id SET DEFAULT nextval('company_seq'::regclass);

ALTER TABLE employees
    ALTER COLUMN id SET DEFAULT nextval('employee_seq'::regclass);




