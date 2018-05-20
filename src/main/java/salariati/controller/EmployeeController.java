package salariati.controller;

import java.util.List;

import salariati.enumeration.DidacticFunction;
import salariati.model.Employee;
import salariati.repository.interfaces.EmployeeRepositoryInterface;

public class EmployeeController {
	
	private EmployeeRepositoryInterface employeeRepository;
	
	public EmployeeController(EmployeeRepositoryInterface employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public void addEmployee(Employee employee) {
		employeeRepository.addEmployee(employee);
	}
	
	public List<Employee> getEmployeesList() {
		return employeeRepository.getEmployeeList();
	}

	public void modifyEmployee(String cnp, String function) throws Exception {
		if(!cnp.matches("[0-9]+") || (cnp.length() != 13))
			throw new Exception("Provided CNP is not correct!");
		else if(!(function.toUpperCase().equals("ASISTENT")
				|| function.toUpperCase().equals("CONFERENTIAR")
				|| function.toUpperCase().equals("LECTURER")
				|| function.toUpperCase().equals("TEACHER")))
			throw new Exception("Provided didactic function is not correct!");
		else {
			for (Employee employee : getEmployeesList()) {
				if (employee.getCnp().equals(cnp)) {
					Employee newEmployee = new Employee(employee.getLastName(),
							employee.getFirstName(),
							employee.getCnp(),
							DidacticFunction.valueOf(function.toUpperCase()),
							employee.getSalary());
					employeeRepository.modifyEmployee(employee, newEmployee);
				}
			}
		}
	}

	public Employee findByCnp(String cnp){
		for (Employee employee : getEmployeesList()
				) {
			if (employee.getCnp().equals(cnp)) {
				return employee;
			}
		}

		return null;
	}

	public void deleteEmployee(Employee employee) {
		employeeRepository.deleteEmployee(employee);
	}
	
}
