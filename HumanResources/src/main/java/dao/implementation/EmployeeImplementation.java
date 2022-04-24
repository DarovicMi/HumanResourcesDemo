package dao.implementation;

import entity.Employee;

public interface EmployeeImplementation {
    void createEmployee();

    void displayEmployees();

    void updateEmployee();

    void deleteEmployee();

    void updateColumns(String input);

    void buildEmployee(Employee employee);
}
