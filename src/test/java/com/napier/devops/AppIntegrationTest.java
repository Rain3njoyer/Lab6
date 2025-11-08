package com.napier.devops;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 30000);
    }

    @Test
    void testGetEmployee()
    {
        Employee emp = app.getEmployee(255530);
        assertNotNull(emp, "Employee should not be null");
        assertEquals(255530, emp.emp_no);
        assertEquals("Ronghao", emp.first_name);
        assertEquals("Garigliano", emp.last_name);
    }

    @Test
    void testGetDepartment()
    {
        Department dept = app.getDepartment("Sales");
        assertNotNull(dept, "Department should not be null");
        assertEquals("Sales", dept.dept_name);
    }
}