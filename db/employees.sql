-- Create the employees database
DROP DATABASE IF EXISTS employees;
CREATE DATABASE IF NOT EXISTS employees;
USE employees;

-- Create departments table
DROP TABLE IF EXISTS departments;
CREATE TABLE departments (
                             dept_no CHAR(4) NOT NULL,
                             dept_name VARCHAR(40) NOT NULL,
                             PRIMARY KEY (dept_no),
                             UNIQUE KEY (dept_name)
);

-- Create employees table
DROP TABLE IF EXISTS employees;
CREATE TABLE employees (
                           emp_no INT NOT NULL,
                           birth_date DATE NOT NULL,
                           first_name VARCHAR(14) NOT NULL,
                           last_name VARCHAR(16) NOT NULL,
                           gender ENUM('M','F') NOT NULL,
                           hire_date DATE NOT NULL,
                           PRIMARY KEY (emp_no)
);

-- Create dept_manager table
DROP TABLE IF EXISTS dept_manager;
CREATE TABLE dept_manager (
                              emp_no INT NOT NULL,
                              dept_no CHAR(4) NOT NULL,
                              from_date DATE NOT NULL,
                              to_date DATE NOT NULL,
                              FOREIGN KEY (emp_no) REFERENCES employees (emp_no) ON DELETE CASCADE,
                              FOREIGN KEY (dept_no) REFERENCES departments (dept_no) ON DELETE CASCADE,
                              PRIMARY KEY (emp_no,dept_no)
);

-- Create dept_emp table
DROP TABLE IF EXISTS dept_emp;
CREATE TABLE dept_emp (
                          emp_no INT NOT NULL,
                          dept_no CHAR(4) NOT NULL,
                          from_date DATE NOT NULL,
                          to_date DATE NOT NULL,
                          FOREIGN KEY (emp_no) REFERENCES employees (emp_no) ON DELETE CASCADE,
                          FOREIGN KEY (dept_no) REFERENCES departments (dept_no) ON DELETE CASCADE,
                          PRIMARY KEY (emp_no,dept_no)
);

-- Create titles table
DROP TABLE IF EXISTS titles;
CREATE TABLE titles (
                        emp_no INT NOT NULL,
                        title VARCHAR(50) NOT NULL,
                        from_date DATE NOT NULL,
                        to_date DATE,
                        FOREIGN KEY (emp_no) REFERENCES employees (emp_no) ON DELETE CASCADE,
                        PRIMARY KEY (emp_no,title,from_date)
);

-- Create salaries table
DROP TABLE IF EXISTS salaries;
CREATE TABLE salaries (
                          emp_no INT NOT NULL,
                          salary INT NOT NULL,
                          from_date DATE NOT NULL,
                          to_date DATE NOT NULL,
                          FOREIGN KEY (emp_no) REFERENCES employees (emp_no) ON DELETE CASCADE,
                          PRIMARY KEY (emp_no,from_date)
);

-- Now load the data
SOURCE /docker-entrypoint-initdb.d/load_departments.dump;
SOURCE /docker-entrypoint-initdb.d/load_employees.dump;
SOURCE /docker-entrypoint-initdb.d/load_dept_manager.dump;
SOURCE /docker-entrypoint-initdb.d/load_dept_emp.dump;
SOURCE /docker-entrypoint-initdb.d/load_titles.dump;
SOURCE /docker-entrypoint-initdb.d/load_salaries1.dump;
SOURCE /docker-entrypoint-initdb.d/load_salaries2.dump;
SOURCE /docker-entrypoint-initdb.d/load_salaries3.dump;