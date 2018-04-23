package salariati.main;

import salariati.model.Employee;
import salariati.repository.interfaces.EmployeeRepositoryInterface;
import salariati.repository.mock.EmployeeMock;
import salariati.validator.EmployeeValidator;
import salariati.controller.EmployeeController;
import salariati.enumeration.DidacticFunction;

import java.util.Scanner;

//functionalitati
//i.	 adaugarea unui nou angajat (nume, prenume, CNP, functia didactica, salariul de incadrare);
//ii.	 modificarea functiei didactice (asistent/lector/conferentiar/profesor) a unui angajat;
//iii.	 afisarea salariatilor ordonati descrescator dupa salariu si crescator dupa varsta (CNP).
public class StartApp {
	
	public static void main(String[] args) {
		
		EmployeeRepositoryInterface employeesRepository = new EmployeeMock();
		EmployeeController employeeController = new EmployeeController(employeesRepository);
		
		for(Employee _employee : employeeController.getEmployeesList())
			System.out.println(_employee.toString());
		System.out.println("-----------------------------------------");
		
		Employee employee = new Employee("FirstName","LastName", "1234567894321", DidacticFunction.ASISTENT, "2500");
		employeeController.addEmployee(employee);
		
		/*for(Employee _employee : employeeController.getEmployeesList())
			System.out.println(_employee.toString());
		
		EmployeeValidator validator = new EmployeeValidator();
		System.out.println( validator.isValid(new Employee("FirstName","LastName", "1234567894322", DidacticFunction.TEACHER, "3400")) );
		*/

		boolean program  = true;

        Scanner in = new Scanner(System.in);

		while(program)
        {
            printMeniu();
            Integer numar = 0;
            String sadv = in.nextLine();
            numar = Integer.parseInt(in.nextLine());
            switch (numar)
            {
                case 1:
                    afisareText("Nume : ");
                    String firstName = citireText();
                    afisareText("Prenume : ");
                    String lastName = citireText();
                    afisareText("CNP : ");
                    String cnp = citireText();
                    afisareText("FUNCTIA");
                    afisareText("ASISTENT : 1");
                    afisareText("LECTURER : 2");
                    afisareText("TEACHER : 3");
                    afisareText("CONFERENTIAR : 4");
                    afisareText("Optiune : ");
                    Integer nr = in.nextInt();
                    DidacticFunction functie = DidacticFunction.ASISTENT;
                    switch (nr)
                    {
                        case 1:
                            functie = DidacticFunction.ASISTENT;
                        case 2:
                            functie = DidacticFunction.LECTURER;
                        case 3:
                            functie = DidacticFunction.TEACHER;
                        case 4:
                            functie = DidacticFunction.CONFERENTIAR;
                    }
                    afisareText("Salar : ");
                    String salar = citireText();
                    Employee angajat = new Employee(firstName,lastName,cnp,functie,salar);
                    employeeController.addEmployee(angajat);
                case 2:
                    for(Employee _employee : employeeController.getEmployeesList())
                        System.out.println(_employee.toString());
                case 0: break;
            }
        }


	}

	public static String citireText()
    {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

	public static void afisareText(String text)
    {
        System.out.println(text);
    }

	public static int citireNumar()
    {
        String numar = System.console().readLine();
        return Integer.parseInt(numar);
    }

	public static void printMeniu()
    {
        System.out.println("----MENIU----");
        System.out.println("Adaugare angajat : 1");
        System.out.println("Afosare Angajati: 2");
        System.out.println("Iesire : 0");
        System.out.println("Optiune : ");
    }

}
