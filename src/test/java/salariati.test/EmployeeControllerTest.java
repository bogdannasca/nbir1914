package salariati.test;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import salariati.controller.EmployeeController;
import salariati.enumeration.DidacticFunction;
import salariati.model.Employee;
import salariati.repository.interfaces.EmployeeRepositoryInterface;
import salariati.repository.mock.EmployeeMock;

import static org.junit.jupiter.api.Assertions.*;
import static salariati.enumeration.DidacticFunction.CONFERENTIAR;

public class EmployeeControllerTest {
    private EmployeeRepositoryInterface employeeRepository;
    private EmployeeController employeeController;

    @BeforeEach
    public void setUp() {
        employeeRepository = new EmployeeMock();
        employeeController = new EmployeeController(employeeRepository);
    }

    @Test
    public void modifyEmployee_TC1() {
        try{
            employeeController.modifyEmployee("123456789087", "lecturer");
            assert false;
        }
        catch (Exception ex){
            assert true;
        }
    }

    @Test
    public void modifyEmployee_TC2() {
        try{
            employeeController.modifyEmployee("1234567890871", "lecturerer");
            assert false;
        }
        catch (Exception ex){
            assert true;
        }
    }

    @Test
    public void modifyEmployee_TC3() {
        try{
            employeeController.modifyEmployee("1234567890872", "lecturer");
            assertEquals(employeeController.findByCnp("1234567890872"), null);
        }
        catch (Exception ex){
            assert false;
        }
    }

    @Test
    public void modifyEmployee_TC4() {
        employeeRepository.addEmployee(new Employee("Puscas", "Marin", "1234567890871", DidacticFunction.CONFERENTIAR, "2500"));
        try{
            employeeController.modifyEmployee("1234567890871", "lecturer");
            assertEquals(employeeRepository.getEmployeeList().get(6).getFunction(), DidacticFunction.LECTURER);
        }
        catch (Exception ex){
            assert false;
        }
    }

    @Test
    public void modifyEmployee_TC5() {
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