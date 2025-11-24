package com.napier.devops;

/**
 * Represents an employee in the database
 */
public final class Employee {
    /**
     * Employee number
     */
    private int empNo;

    /**
     * Employee first name
     */
    private String firstName;

    /**
     * Employee last name
     */
    private String lastName;

    /**
     * Employee job title
     */
    private String title;

    /**
     * Employee salary
     */
    private int salary;

    /**
     * Employee department
     */
    private Department dept;

    /**
     * Department name (fallback if dept object not used)
     */
    private String deptName;

    /**
     * Employee manager
     */
    private Employee manager;

    /**
     * Manager name (fallback if manager object not used)
     */
    private String managerName;

    /**
     * Default constructor
     */
    public Employee() {
        // Default constructor
    }

    /**
     * Gets employee number
     * @return employee number
     */
    public int getEmpNo() {
        return this.empNo;
    }

    /**
     * Sets employee number
     * @param empNo employee number
     */
    public void setEmpNo(final int empNo) {
        this.empNo = empNo;
    }

    /**
     * Gets first name
     * @return first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Sets first name
     * @param firstName first name
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name
     * @return last name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Sets last name
     * @param lastName last name
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets title
     * @return title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets title
     * @param title job title
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Gets salary
     * @return salary
     */
    public int getSalary() {
        return this.salary;
    }

    /**
     * Sets salary
     * @param salary salary amount
     */
    public void setSalary(final int salary) {
        this.salary = salary;
    }

    /**
     * Gets department
     * @return department
     */
    public Department getDept() {
        return this.dept;
    }

    /**
     * Sets department
     * @param dept department
     */
    public void setDept(final Department dept) {
        this.dept = dept;
    }

    /**
     * Gets department name
     * @return department name
     */
    public String getDeptName() {
        return this.deptName;
    }

    /**
     * Sets department name
     * @param deptName department name
     */
    public void setDeptName(final String deptName) {
        this.deptName = deptName;
    }

    /**
     * Gets manager
     * @return manager
     */
    public Employee getManager() {
        return this.manager;
    }

    /**
     * Sets manager
     * @param manager manager employee
     */
    public void setManager(final Employee manager) {
        this.manager = manager;
    }

    /**
     * Gets manager name
     * @return manager name
     */
    public String getManagerName() {
        return this.managerName;
    }

    /**
     * Sets manager name
     * @param managerName manager name
     */
    public void setManagerName(final String managerName) {
        this.managerName = managerName;
    }
}