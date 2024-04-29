
INSERT INTO companies (id, company_name, country, foundation_date)
VALUES
    (1, 'ABC Corporation', 'USA', '2000-01-01'),
    (2, 'XYZ Ltd', 'UK', '1995-05-12'),
    (3, '123 Inc', 'Canada', '2010-10-20');

INSERT INTO employees (id, employee_name, employee_surname, salary, hiring_date, job, company_id)
VALUES
    (1, 'John', 'Doe', 50000, '2010-01-01', 'FRONTEND_DEVELOPER', 1),
    (2, 'Alice', 'Smith', 60000, '2012-03-15', 'BACKEND_DEVELOPER', 1),
    (3, 'Bob', 'Johnson', 70000, '2015-06-30', 'SALES', 2),
    (4, 'Emily', 'Williams', 55000, '2017-09-20', 'HIRING_MANAGER', 2),
    (5, 'Michael', 'Brown', 65000, '2018-11-10', 'RECRUITER', 3),
    (6, 'Emma', 'Jones', 75000, '2019-12-25', 'PROJECT_MANAGER', 3),
    (7, 'David', 'Miller', 80000, '2020-02-28', 'SQL_DEVELOPER', 3),
    (8, 'Olivia', 'Davis', 70000, '2021-04-15', 'DEVOPS', 3);