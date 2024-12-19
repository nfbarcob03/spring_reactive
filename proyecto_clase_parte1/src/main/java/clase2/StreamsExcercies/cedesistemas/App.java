package clase2.StreamsExcercies.cedesistemas;


import clase2.StreamsExcercies.cedesistemas.factory.EmployeeFactory;
import clase2.StreamsExcercies.cedesistemas.model.Employee;
import clase2.StreamsExcercies.cedesistemas.model.Project;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    static List<Employee> employeeList = new ArrayList<>();

    public static void main(String[] args) {
        EmployeeFactory employeeFactory = new EmployeeFactory();
        employeeList = employeeFactory.getAllEmployee();

        System.out.println("-------------------Punto 1 ----------------");
        System.out.println();

        System.out.println("-------------------Punto 2 ----------------");
        System.out.println(employeeList.stream().filter(employee -> employee.getFirstName().startsWith("A"))
                                   .map(employee -> employee.getFirstName() + " " +employee.getLastName())
                                   .collect(Collectors.toList()));

        System.out.println("-------------------Punto 3 ----------------");
        System.out.println(employeeList.stream().filter(employee -> employee.getId().substring(0,4).equals("2023")).collect(
                Collectors.toList()));
        System.out.println("-------------------Punto 4.1 Orden por primer nombre y salario ----------------");

        System.out.println(employeeList.stream().sorted(Comparator.comparing
                (Employee::getFirstName).thenComparing(Employee::getSalary))
                                   .map(employee -> employee.getFirstName() + " " + employee.getSalary())
                                   .collect(Collectors.toList()));

        System.out.println("-------------------Punto 4.2 Orden por primer nombre ----------------");

        System.out.println(employeeList.stream().sorted(Comparator.comparing
                        (Employee::getFirstName))
                                   .map(employee -> employee.getFirstName() + " " + employee.getSalary())
                                   .collect(Collectors.toList()));
        System.out.println("-------------------Punto 4.3 Orden por salario ----------------");

        System.out.println(employeeList.stream().sorted(Comparator.comparing(Employee::getSalary))
                                   .map(employee -> employee.getFirstName() + " " + employee.getSalary())
                                   .collect(Collectors.toList()));

        System.out.println("-------------------Punto 5  ----------------");
        var n = 3;
        System.out.println("------------los "+ n + " salarios mas altos----------------");
        System.out.println(employeeList.stream().sorted(Comparator.comparing(Employee::getSalary))
                                   .limit(n)
                                   .map(Employee::getFirstName)
                                   .collect(Collectors.toList()));

        System.out.println("-------------------Punto 6 ----------------");
        System.out.println(employeeList.stream()
                                   .min(Comparator.comparing(Employee::getSalary))
                                   .map(Employee::getSalary)
                                   .orElse(null));
        System.out.println("-------------------Punto 7 ----------------");
        var minimo = employeeList.stream()
                .min(Comparator.comparing(Employee::getSalary))
                .map(Employee::getSalary)
                .orElse(null);
        System.out.println(employeeList.stream()
                                   .filter(employee -> employee.getSalary() == minimo)
                                   .map(App::getFullNameEmployee)
                                   .collect(Collectors.toList()));

        System.out.println("-------------------Punto 8 ----------------");
        System.out.println(employeeList.stream()
                                   .filter(employee -> employee.getProjects().size()>2)
                                   .map(App::getFullNameEmployee)
                                   .collect(Collectors.toList()));

        System.out.println("-------------------Punto 9 ----------------");
        System.out.println(employeeList.stream()
                                   .mapToInt(Employee::getTotalLaptopsAssigned)
                                   .sum());

        System.out.println("-------------------Punto 10 ----------------");
        System.out.println(getProjectByManagerName("Robert Downey Jr").size());

        System.out.println("-------------------Punto 11 ----------------");
        System.out.println(getProjectByManagerName("Robert Downey Jr").stream().map(Project::getName).collect(Collectors.toList()));

        System.out.println("-------------------Punto 12 ----------------");
        var projectNameRobert = getProjectByManagerName("Robert Downey Jr").stream().map(Project::getName).collect(
                Collectors.toList());
        System.out.println(employeeList.stream()
                                   .filter(employee -> employee.getProjects().stream()
                                           .filter(project -> projectNameRobert.contains(project.getName()))
                                           .collect(Collectors.toList()).size()>=1)
                                   .map(employee -> getFullNameEmployee(employee))
                                   .collect(Collectors.toList()));

        System.out.println("-------------------Punto 13 ----------------");
        System.out.println(employeeList.stream().collect(Collectors
                                                                 .groupingBy(employee -> employee.getId()
                                                                         .substring(0,4),
                                                                             Collectors.mapping(employee -> getFullNameEmployee(employee),
                                                                                                Collectors.toList()))));


        System.out.println("-------------------Punto 14 ----------------");
        System.out.println(employeeList.stream().collect(Collectors
                                                                 .groupingBy(employee -> employee.getId()
                                                                                     .substring(0,4), Collectors.counting())));


    }
    public static
    String getFullNameEmployee(Employee employee){
        return employee.getFirstName() + " " + employee.getLastName();
    }

    public static List<Project> getProjectByManagerName(String managerName){
        return employeeList.stream().flatMap(employee -> employee.getProjects().stream())
                .filter(project -> project.getProjectManager().equals(managerName))
                .collect(Collectors.toList());
    }
}
