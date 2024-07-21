package com.codingshuttle.spingbootwebtutorial.spingbootwebtutorial.Controllers;

import com.codingshuttle.spingbootwebtutorial.spingbootwebtutorial.DTO.EmployeeDTO;
import com.codingshuttle.spingbootwebtutorial.spingbootwebtutorial.Entities.EmployeeEntity;
import com.codingshuttle.spingbootwebtutorial.spingbootwebtutorial.Repositories.EmployeeRepository;
import com.codingshuttle.spingbootwebtutorial.spingbootwebtutorial.Services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {


    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false, name = "inputeAge") Integer age,
                                             @RequestParam(required = false) String soryBy) {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeEntity inputEmployee) {
        return employeeService.createNewEmployee(inputEmployee);
    }

    @PutMapping(path = "/{employeeId}")
    public EmployeeDTO updateEmployeeByID(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeId) {
        return employeeService.updateEmployeeByID(employeeId, employeeDTO);
    }

    @DeleteMapping(path = "/{employeeId}")
    public boolean deleteEmployeeByID(@PathVariable Long employeeId) {
        return employeeService.deleteEmployeeByID(employeeId);
    }
}
