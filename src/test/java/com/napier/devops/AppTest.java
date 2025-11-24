package com.napier.devops;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;

/**
 * Unit tests for App class
 */
final class AppTest {
    /**
     * App instance for testing
     */
    private static App app;

    /**
     * Test employee number
     */
    private static final int TEST_EMP_NO = 1;

    /**
     * Test salary
     */
    private static final int TEST_SALARY = 55000;

    /**
     * Private constructor to prevent instantiation
     */
    private AppTest() {
        // Utility class
    }

    /**
     * Initialize test instance
     */
    @BeforeAll
    static void init() {
        app = new App();
    }

    /**
     * Test printSalaries with null input
     */
    @Test
    void printSalariesTestNull() {
        app.printSalaries(null);
    }

    /**
     * Test printSalaries with empty list
     */
    @Test
    void printSalariesTestEmpty() {
        final List<Employee> employees = new ArrayList<>();
        app.printSalaries(employees);
    }

    /**
     * Test printSalaries with list containing null
     */
    @Test
    void printSalariesTestContainsNull() {
        final List<Employee> employees = new ArrayList<>();
        employees.add(null);
        app.printSalaries(employees);
    }

    /**
     * Test printSalaries with valid employee
     */
    @Test
    void printSalaries() {
        final List<Employee> employees = new ArrayList<>();
        final Employee emp = createTestEmployee();
        employees.add(emp);
        app.printSalaries(employees);
    }

    /**
     * Test displayEmployee with null input
     */
    @Test
    void displayEmployeeTestNull() {
        app.displayEmployee(null);
    }

    /**
     * Test displayEmployee with valid employee
     */
    @Test
    void displayEmployee() {
        final Employee emp = createTestEmployee();
        app.displayEmployee(emp);
    }

    /**
     * Creates a test employee
     * @return test employee
     */
    private static Employee createTestEmployee() {
        final Employee emp = new Employee();
        emp.setEmpNo(TEST_EMP_NO);
        emp.setFirstName("Kevin");
        emp.setLastName("Chalmers");
        emp.setTitle("Engineer");
        emp.setSalary(TEST_SALARY);
        return emp;
    }
}