package com.napier.devops;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Main application class for Employee Database
 */
public final class App {
    /**
     * Logger for the application
     */
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    /**
     * Maximum retry attempts for database connection
     */
    private static final int MAX_RETRIES = 10;

    /**
     * Connection to MySQL database.
     */
    private transient Connection con;

    /**
     * Constructor
     */
    public App() {
        this.con = null;
    }

    /**
     * Connect to the MySQL database.
     * @param location the database location
     * @param delay the delay in milliseconds before connecting
     */
    public void connect(final String location, final int delay) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Could not load SQL driver", e);
            return;
        }

        boolean connected = false;
        for (int i = 0; i < MAX_RETRIES && !connected; ++i) {
            LOGGER.log(Level.INFO, "Connecting to database... Attempt {0}", i);
            try {
                Thread.sleep(delay);
                this.con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/employees?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "college");
                LOGGER.info("Successfully connected");
                connected = true;
            } catch (SQLException sqle) {
                LOGGER.log(Level.WARNING, "Failed to connect to database attempt " + i, sqle);
            } catch (InterruptedException ie) {
                LOGGER.log(Level.WARNING, "Thread interrupted", ie);
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (this.con != null) {
            try {
                this.con.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error closing connection to database", e);
            }
        }
    }

    /**
     * Gets all the current employees and salaries.
     *
     * @return A list of all employees and salaries, or empty list if error.
     */
    public List<Employee> getAllSalaries() {
        final String query =
                "SELECT employees.emp_no, employees.first_name, employees.last_name, salaries.salary "
                        + "FROM employees, salaries "
                        + "WHERE employees.emp_no = salaries.emp_no "
                        + "AND salaries.to_date = '9999-01-01' "
                        + "ORDER BY employees.emp_no ASC";

        final List<Employee> employees = new ArrayList<>();

        try (Statement stmt = this.con.createStatement();
             ResultSet rset = stmt.executeQuery(query)) {

            while (rset.next()) {
                final Employee emp = createEmployeeFromResultSet(rset,
                        "employees.emp_no", "employees.first_name", "employees.last_name", "salaries.salary");
                employees.add(emp);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to get salary details", e);
        }

        return employees;
    }

    /**
     * Gets salaries for employees with a given role.
     * @param title the job title to search for
     * @return A list of all employees with the given title, or empty list if error.
     */
    public List<Employee> getSalariesByRole(final String title) {
        if (title == null || title.isEmpty()) {
            return new ArrayList<>();
        }

        final String query =
                "SELECT employees.emp_no, employees.first_name, employees.last_name, "
                        + "salaries.salary, titles.title "
                        + "FROM employees, salaries, titles "
                        + "WHERE employees.emp_no = salaries.emp_no "
                        + "AND employees.emp_no = titles.emp_no "
                        + "AND salaries.to_date = '9999-01-01' "
                        + "AND titles.to_date = '9999-01-01' "
                        + "AND titles.title = '" + title + "' "
                        + "ORDER BY employees.emp_no ASC";

        final List<Employee> employees = new ArrayList<>();

        try (Statement stmt = this.con.createStatement();
             ResultSet rset = stmt.executeQuery(query)) {

            while (rset.next()) {
                final Employee emp = createEmployeeFromResultSet(rset,
                        "employees.emp_no", "employees.first_name", "employees.last_name", "salaries.salary");
                emp.setTitle(rset.getString("titles.title"));
                employees.add(emp);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to get salary details", e);
        }

        return employees;
    }

    /**
     * Gets a department by name
     *
     * @param deptName the department name to search for
     * @return Department object or null if not found
     */
    public Department getDepartment(final String deptName) {
        if (deptName == null || deptName.isEmpty()) {
            return null;
        }

        final String query =
                "SELECT dept_no, dept_name "
                        + "FROM departments "
                        + "WHERE dept_name = '" + deptName + "'";

        Department result = null;

        try (Statement stmt = this.con.createStatement();
             ResultSet rset = stmt.executeQuery(query)) {

            if (rset.next()) {
                final Department dept = new Department();
                dept.setDeptNo(rset.getString("dept_no"));
                dept.setDeptName(rset.getString("dept_name"));
                result = dept;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to get department details", e);
        }

        return result;
    }

    /**
     * Gets all employees in a department
     *
     * @param dept the department to get employees for
     * @return list of employees in the department
     */
    public List<Employee> getSalariesByDepartment(final Department dept) {
        if (dept == null) {
            return new ArrayList<>();
        }

        final String query =
                "SELECT employees.emp_no, employees.first_name, employees.last_name, salaries.salary "
                        + "FROM employees, salaries, dept_emp, departments "
                        + "WHERE employees.emp_no = salaries.emp_no "
                        + "AND employees.emp_no = dept_emp.emp_no "
                        + "AND dept_emp.dept_no = departments.dept_no "
                        + "AND salaries.to_date = '9999-01-01' "
                        + "AND departments.dept_no = '" + dept.getDeptNo() + "' "
                        + "ORDER BY employees.emp_no ASC";

        final List<Employee> employees = new ArrayList<>();

        try (Statement stmt = this.con.createStatement();
             ResultSet rset = stmt.executeQuery(query)) {

            while (rset.next()) {
                final Employee emp = createEmployeeFromResultSet(rset,
                        "employees.emp_no", "employees.first_name", "employees.last_name", "salaries.salary");
                employees.add(emp);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to get salary details", e);
        }

        return employees;
    }

    /**
     * Gets a single employee by employee number
     *
     * @param empNo the employee number
     * @return Employee object or null if not found
     */
    public Employee getEmployee(final int empNo) {
        final String query =
                "SELECT emp_no, first_name, last_name "
                        + "FROM employees "
                        + "WHERE emp_no = " + empNo;

        Employee result = null;

        try (Statement stmt = this.con.createStatement();
             ResultSet rset = stmt.executeQuery(query)) {

            if (rset.next()) {
                final Employee emp = new Employee();
                emp.setEmpNo(rset.getInt("emp_no"));
                emp.setFirstName(rset.getString("first_name"));
                emp.setLastName(rset.getString("last_name"));
                result = emp;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to get employee details", e);
        }

        return result;
    }

    /**
     * Adds a new employee to the database
     *
     * @param emp the employee to add
     */
    public void addEmployee(final Employee emp) {
        if (emp == null) {
            return;
        }

        final String query =
                "INSERT INTO employees (emp_no, first_name, last_name, birth_date, gender, hire_date) " +
                        "VALUES (" + emp.getEmpNo() + ", '" + emp.getFirstName() + "', '"
                        + emp.getLastName() + "', '9999-01-01', 'M', '9999-01-01')";

        try (Statement stmt = this.con.createStatement()) {
            stmt.execute(query);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to add employee", e);
        }
    }

    /**
     * Gets salaries by department name (convenience method)
     *
     * @param deptName the department name
     * @return list of employees in the department
     */
    public List<Employee> getSalariesByDept(final String deptName) {
        final Department dept = getDepartment(deptName);
        if (dept == null) {
            return new ArrayList<>();
        }
        return getSalariesByDepartment(dept);
    }

    /**
     * Creates an Employee from a ResultSet
     * @param rset the result set
     * @param empNoCol employee number column name
     * @param firstNameCol first name column name
     * @param lastNameCol last name column name
     * @param salaryCol salary column name
     * @return created Employee object
     * @throws SQLException if database access error occurs
     */
    private Employee createEmployeeFromResultSet(final ResultSet rset,
                                                 final String empNoCol, final String firstNameCol,
                                                 final String lastNameCol, final String salaryCol) throws SQLException {
        final Employee emp = new Employee();
        emp.setEmpNo(rset.getInt(empNoCol));
        emp.setFirstName(rset.getString(firstNameCol));
        emp.setLastName(rset.getString(lastNameCol));
        emp.setSalary(rset.getInt(salaryCol));
        return emp;
    }

    /**
     * Prints a list of employees.
     *
     * @param employees The list of employees to print.
     */
    @SuppressWarnings("PMD.SystemPrintln")
    public void printSalaries(final List<Employee> employees) {
        if (employees == null || employees.isEmpty()) {
            System.out.println("No employees");
            return;
        }

        final String headerFormat = "%-10s %-15s %-20s %-8s";
        System.out.println(String.format(headerFormat,
                "Emp No", "First Name", "Last Name", "Salary"));

        for (final Employee emp : employees) {
            if (emp != null) {
                final String empFormat = "%-10s %-15s %-20s %-8s";
                final String empString = String.format(empFormat,
                        emp.getEmpNo(), emp.getFirstName(), emp.getLastName(), emp.getSalary());
                System.out.println(empString);
            }
        }
    }

    /**
     * Prints employee details
     *
     * @param emp Employee to display
     */
    @SuppressWarnings("PMD.SystemPrintln")
    public void displayEmployee(final Employee emp) {
        if (emp == null) {
            System.out.println("No employee provided");
            return;
        }

        final StringBuilder output = new StringBuilder();
        output.append(emp.getEmpNo()).append(" ")
                .append(emp.getFirstName()).append(" ")
                .append(emp.getLastName()).append("\n")
                .append(emp.getTitle()).append("\n")
                .append("Salary:").append(emp.getSalary()).append("\n");

        final Department empDept = emp.getDept();
        if (empDept != null) {
            output.append(empDept.getDeptName()).append("\n");
        } else {
            output.append("No department assigned\n");
        }

        final Employee empManager = emp.getManager();
        if (empManager != null) {
            output.append("Manager: ").append(empManager.getFirstName())
                    .append(" ").append(empManager.getLastName()).append("\n");
        } else {
            output.append("No manager assigned\n");
        }

        System.out.println(output.toString());
    }

    /**
     * Main method
     * @param args command line arguments
     */
    @SuppressWarnings("PMD.SystemPrintln")
    public static void main(final String[] args) {
        final App application = new App();
        final int defaultDelay = 30000;
        final int maxSampleSize = 5;

        if (args.length < 1) {
            application.connect("localhost:33060", defaultDelay);
        } else {
            application.connect(args[0], Integer.parseInt(args[1]));
        }

        final Department dept = application.getDepartment("Sales");
        if (dept != null) {
            final List<Employee> employees = application.getSalariesByDepartment(dept);
            if (!employees.isEmpty()) {
                System.out.println("Sales Department - First 5 employees:");
                final List<Employee> sample = new ArrayList<>();
                final int limit = Math.min(maxSampleSize, employees.size());
                for (int i = 0; i < limit; i++) {
                    sample.add(employees.get(i));
                }
                application.printSalaries(sample);
            }
        }

        application.disconnect();
        System.out.println("Application finished.");
    }
}