package clase2.StreamsExcercies.cedesistemas.factory;

import clase2.StreamsExcercies.cedesistemas.model.Employee;
import clase2.StreamsExcercies.cedesistemas.model.Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeFactory {
    List<Employee> employees = new ArrayList<>();

    public List<Employee> getAllEmployee(){
        Project delta = new Project("Delta Model", "Login", "Robert Downey Jr");
        Project beta = new Project("Beta Enhancement", "Authentication", "Chris");
        Project twoFactorAuth = new Project("Two Factor Authentication", "Authentication", "MSD");
        Project commonUI = new Project("Common UI", "UI", "Robert Downey Jr");
        Project pegasus = new Project("Pegasus Model", "Data", "Vikram");
        Project customerOnboarding = new Project("Customer Onboarding", "Ads", "Vedha");
        Project verification = new Project("Source Verification", "Data", "Pablo");
        Project removeUsers = new Project("Remove Invalid User", "Proxy", "Jeetu");
        Project siteReliability = new Project("Site Reliability", "Admin", "Zaheer Khan");
        Project dataTransition = new Project("Data Transition", "Data", "Atif Aslam");
        Project twoPhaseDeployment =new Project("Two Phase Deployment", "Deployment", "Shaktiman");

        employees.add(new Employee("2020Emp0234", "María Luisa", "Pastor", 900000, 1, Arrays.asList(delta, beta)));
        employees.add(new Employee("2012Emp1923", "Cristián", "Martín", 3500000, 3, Arrays.asList(pegasus, customerOnboarding, beta, siteReliability)));
        employees.add(new Employee("2017Emp0721", "Rosalinda", "Reyes", 1800000, 3, Arrays.asList(twoFactorAuth, beta, commonUI)));
        employees.add(new Employee("2017Emp00031", "Yéssica", "Rivero", 2200000, 2, Arrays.asList(delta, twoFactorAuth)));
        employees.add(new Employee("2013Emp0872", "Maite", "Cortés", 2200000, 3, Arrays.asList(pegasus, delta, removeUsers, dataTransition)));
        employees.add(new Employee("2022Emp0087", "Pepito", "Lopez", 900000, 1, List.of(twoFactorAuth)));
        employees.add(new Employee("2019Emp0050", "Vicenta", "Estévez", 1300000, 1, Arrays.asList(removeUsers, commonUI)));
        employees.add(new Employee("2023Emp0934", "Agapito", "Abel", 1100000, 1, List.of(pegasus)));
        employees.add(new Employee("2023Emp1033", "Faustino", "Garcia", 1200000, 0, List.of(delta)));
        employees.add(new Employee("2015Emp0009", "Socorro", "Contreras", 2600000, 2, Arrays.asList(verification, removeUsers, twoPhaseDeployment)));

        return employees;
    }
}
