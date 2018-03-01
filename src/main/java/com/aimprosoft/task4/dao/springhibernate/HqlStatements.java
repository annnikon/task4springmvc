package com.aimprosoft.task4.dao.springhibernate;

public interface HqlStatements {

    String DEPARTMENTS_ALL_HQL = "from Department ";
    String EMPLOYEES_ALL_HQL = "from Employee ";
    String NAME_CONDITION = " where name = :name";
    String EMAIL_CONDITION = " where email = :email";
    String DEPARTMENT_CONDITION = " where department = :department";

}
