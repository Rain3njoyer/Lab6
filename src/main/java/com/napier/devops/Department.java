package com.napier.devops;

/**
 * Represents a department in the database
 */
public final class Department {
    /**
     * Department number
     */
    private String deptNo;

    /**
     * Department name
     */
    private String deptName;

    /**
     * Department manager
     */
    private Employee manager;

    /**
     * Default constructor
     */
    public Department() {
        // Default constructor
    }

    /**
     * Gets department number
     * @return department number
     */
    public String getDeptNo() {
        return this.deptNo;
    }

    /**
     * Sets department number
     * @param deptNo department number
     */
    public void setDeptNo(final String deptNo) {
        this.deptNo = deptNo;
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
     * @return manager employee
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
}