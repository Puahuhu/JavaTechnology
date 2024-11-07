package com.example.Ex2;

import com.example.Ex2.model.Employee;
import com.example.Ex2.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ex2Application implements CommandLineRunner {

	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(Ex2Application.class, args);
	}

	public void run(String... args) {
		employeeRepository.save(new Employee("John Doe", "john@example.com", "123 Main St", "123-456-7890"));
		employeeRepository.save(new Employee("Jane Smith", "jane@example.com", "456 Elm St", "098-765-4321"));
		employeeRepository.save(new Employee("Alice Johnson", "alice@example.com", "789 Oak St", "111-222-3333"));
		employeeRepository.save(new Employee("Bob Brown", "bob@example.com", "321 Maple St", "444-555-6666"));
		employeeRepository.save(new Employee("Charlie Green", "charlie@example.com", "654 Pine St", "777-888-9999"));
		employeeRepository.save(new Employee("Daisy Blue", "daisy@example.com", "987 Cedar St", "000-123-4567"));
		employeeRepository.save(new Employee("Ethan White", "ethan@example.com", "234 Birch St", "456-789-0123"));
		employeeRepository.save(new Employee("Fiona Black", "fiona@example.com", "345 Spruce St", "678-901-2345"));
		employeeRepository.save(new Employee("George Gray", "george@example.com", "123 Willow St", "345-678-9012"));
		employeeRepository.save(new Employee("Hannah Gold", "hannah@example.com", "567 Redwood St", "890-123-4567"));
	}
}
