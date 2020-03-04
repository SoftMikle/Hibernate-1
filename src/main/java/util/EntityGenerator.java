package util;

import entity.Employee;

import com.github.javafaker.Faker;
import entity.EmployeeDetails;

import java.util.ArrayList;
import java.util.List;

public class EntityGenerator {
    public static List<Employee> generateEntities(int quantity){
        List<Employee> list = new ArrayList<>(quantity);
        Faker faker = new Faker();
        EmployeeDetails employeeDetails = new EmployeeDetails();
        for (int i = 0; i < quantity; i++) {
            Employee employee = new Employee();
            employee.setName(faker.name().firstName());
            employee.setLastName(faker.name().lastName());
            employeeDetails.setAddress(faker.address().streetAddressNumber());
            employeeDetails.setDepartment(faker.company().profession());
            employeeDetails.setEmail(faker.internet().emailAddress());
            employee.setEmployeeDetails(employeeDetails);
            list.add(employee);
        }
        return  list;
    }
}
