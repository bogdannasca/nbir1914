package salariati.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import salariati.controller.EmployeeController;
import salariati.enumeration.DidacticFunction;
import salariati.model.Employee;
import salariati.repository.implementations.EmployeeImpl;
import salariati.repository.interfaces.EmployeeRepositoryInterface;
import salariati.repository.mock.EmployeeMock;

import static org.junit.jupiter.api.Assertions.*;
import static salariati.enumeration.DidacticFunction.CONFERENTIAR;

class EmployeeControllerTest {
    private EmployeeRepositoryInterface employeeRepository;
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        employeeRepository = new EmployeeMock();
        employeeController = new EmployeeController(employeeRepository);
    }

    @Test
    void modifyEmployee_TC1() {
        try{
            employeeController.modifyEmployee("123456789087", "lecturer");
            assert false;
        }
        catch (Exception ex){
            assert true;
        }
    }

    @Test
    void modifyEmployee_TC2() {
        try{
            employeeController.modifyEmployee("1234567890871", "lecturerer");
            assert false;
        }
        catch (Exception ex){
            assert true;
        }
    }

    @Test
    void modifyEmployee_TC3() {
        try{
            employeeController.modifyEmployee("1234567890872", "lecturer");
            assertEquals(employeeController.findByCnp("1234567890872"), null);
        }
        catch (Exception ex){
            assert false;
        }
    }

    @Test
    void modifyEmployee_TC4() {
        employeeRepository.addEmployee(new Employee("Puscas", "Marin", "1234567890871", CONFERENTIAR, "2500"));
        try{
            employeeController.modifyEmployee("1234567890871", "lecturer");
            assertEquals(employeeRepository.getEmployeeList().get(6).getFunction(), DidacticFunction.LECTURER);
        }
        catch (Exception ex){
            assert false;
        }
    }

    @Test
    void modifyEmployee_TC5() {
        Employee Ionel   = new Employee("Pacuraru", "Ionel", "1234567890876", DidacticFunction.ASISTENT, "2500");
        Employee Mihai   = new Employee("Dumitrescu", "Mihai","1234567890875", DidacticFunction.LECTURER, "2500");
        employeeRepository.addEmployee(Ionel);
        employeeRepository.addEmployee(Mihai);
        try{
            employeeController.modifyEmployee("1234567890876", "lecturer");
            assertEquals(employeeController.findByCnp("1234567890876").getFunction(), DidacticFunction.LECTURER);
        }
        catch (Exception ex){
            assert false;
        }
    }

}