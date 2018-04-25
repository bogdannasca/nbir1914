package salariati.test;

import org.junit.Before;
import org.junit.Test;
import salariati.controller.EmployeeController;
import salariati.enumeration.DidacticFunction;
import salariati.model.Employee;
import salariati.repository.interfaces.EmployeeRepositoryInterface;
import salariati.repository.mock.EmployeeMock;
import salariati.validator.EmployeeValidator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class lab2Test
{
    private EmployeeRepositoryInterface employeeRepository;
    private EmployeeController controller;
    private EmployeeValidator employeeValidator;

    @Before
    public void setUp() {
        employeeRepository = new EmployeeMock();
        controller         = new EmployeeController(employeeRepository);
        employeeValidator  = new EmployeeValidator();
    }

    @Test
    public void testAddValid() {
        Employee newEmployee = new Employee("Marin","ValidLastName", "1910509055057", DidacticFunction.ASISTENT, "3000");
        assertTrue(employeeValidator.isValid(newEmployee));
        controller.addEmployee(newEmployee);
        assertEquals(7, controller.getEmployeesList().size());
        assertTrue(newEmployee.equals(controller.getEmployeesList().get(controller.getEmployeesList().size() - 1)));
    }

    @Test
    public void testFirstNameNotValid() {
        Employee newEmployee = new Employee("M","ValidLastName", "1910509055057", DidacticFunction.ASISTENT, "3000");
        assertFalse(employeeValidator.isValid(newEmployee));
    }

    @Test
    public void testLastNameNotValid() {
        Employee newEmployee = new Employee("Valid","I", "1910509055057", DidacticFunction.ASISTENT, "3000");
        assertFalse(employeeValidator.isValid(newEmployee));
    }

    @Test
    public void testCNPNotValid() {
        Employee newEmployee = new Employee("Valid","Valid", "19100509055057", DidacticFunction.ASISTENT, "3000");
        assertFalse(employeeValidator.isValid(newEmployee));
    }

    @Test
    public void testDidacticNotValid() {
        Employee newEmployee = new Employee("Valid","Valid", "1910509055057", DidacticFunction.CONFERENTIAR, "3000");
        assertFalse(employeeValidator.isValid(newEmployee));
    }

    @Test
    public void testSalarZeroNotValid() {
        Employee newEmployee = new Employee("Valid","Valid", "1910509055057", DidacticFunction.LECTURER, "0");
        assertFalse(employeeValidator.isValid(newEmployee));
    }

    @Test
    public void testSalarNegativeNotValid() {
        Employee newEmployee = new Employee("Valid","Valid", "1910509055057", DidacticFunction.LECTURER, "-1000");
        assertFalse(employeeValidator.isValid(newEmployee));
    }

    @Test
    public void testCNPContainsLetters() {
        Employee newEmployee = new Employee("Valid","Valid", "1910509a55057", DidacticFunction.LECTURER, "1000");
        assertFalse(employeeValidator.isValid(newEmployee));
    }

    @Test
    public void testCNPLessLenght() {
        Employee newEmployee = new Employee("Valid","Valid", "19105055057", DidacticFunction.LECTURER, "1000");
        assertFalse(employeeValidator.isValid(newEmployee));
    }


}
