package com.napier.devops;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Integration tests for App class
 */
final class AppIntegrationTest {
    /**
     * App instance for testing
     */
    private static App app;

    /**
     * Test employee number constant
     */
    private static final int TEST_EMPLOYEE_NUMBER = 255530;

    /**
     * Connection delay in milliseconds
     */
    private static final int CONNECTION_DELAY = 30000;

    /**
     * Private constructor to prevent instantiation
     */
    private AppIntegrationTest() {
        // Utility class
    }

    /**
     * Initialize test instance and connect to database
     */
    @BeforeAll
    static void init() {
        app = new App();
        app.connect("localhost:33060", CONNECTION_DELAY);
    }

    /**
     * Test getEmployee returns non-null
     */
    @Test
    void testGetEmployeeNotNull() {
        final Employee emp = app.getEmployee(TEST_EMPLOYEE_NUMBER);
        assertNotNull(emp, "Employee should not be null");
    }

    /**
     * Test getEmployee returns correct employee number
     */
    @Test
    void testGetEmployeeNumber() {
        final Employee emp = app.getEmployee(TEST_EMPLOYEE_NUMBER);
        assertEquals(TEST_EMPLOYEE_NUMBER, emp.getEmpNo(), "Employee number should match");
    }

    /**
     * Test getEmployee returns correct first name
     */
    @Test
    void testGetEmployeeFirstName() {
        final Employee emp = app.getEmployee(TEST_EMPLOYEE_NUMBER);
        assertEquals("Ronghao", emp.getFirstName(), "First name should match");
    }

    /**
     * Test getEmployee returns correct last name
     */
    @Test
    void testGetEmployeeLastName() {
        final Employee emp = app.getEmployee(TEST_EMPLOYEE_NUMBER);
        assertEquals("Garigliano", emp.getLastName(), "Last name should match");
    }

    /**
     * Test getDepartment returns non-null
     */
    @Test
    void testGetDepartmentNotNull() {
        final Department dept = app.getDepartment("Sales");
        assertNotNull(dept, "Department should not be null");
    }

    /**
     * Test getDepartment returns correct department name
     */
    @Test
    void testGetDepartmentName() {
        final Department dept = app.getDepartment("Sales");
        assertEquals("Sales", dept.getDeptName(), "Department name should match");
    }
}