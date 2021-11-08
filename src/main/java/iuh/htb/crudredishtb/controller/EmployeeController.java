package iuh.htb.crudredishtb.controller;

import iuh.htb.crudredishtb.entity.Employee;
import iuh.htb.crudredishtb.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee) {
        employeeRepository.saveEmployee(employee);
        return employee;
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable("id") long id) {
        return employeeRepository.findById(id);
    }

    @GetMapping
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @PutMapping
    public void update(@RequestBody Employee employee) {
        employeeRepository.update(employee);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        employeeRepository.delete(id);
    }
}
