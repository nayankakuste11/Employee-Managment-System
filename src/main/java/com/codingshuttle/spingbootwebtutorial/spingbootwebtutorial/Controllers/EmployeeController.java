package com.codingshuttle.spingbootwebtutorial.spingbootwebtutorial.Controllers;

import com.codingshuttle.spingbootwebtutorial.spingbootwebtutorial.DTO.EmployeeDTO;
import com.codingshuttle.spingbootwebtutorial.spingbootwebtutorial.Services.EmployeeService;
import com.codingshuttle.spingbootwebtutorial.spingbootwebtutorial.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long id) {
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    }


    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false, name = "inputeAge") Integer age,
                                                             @RequestParam(required = false) String soryBy) {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee) {
        EmployeeDTO savedEntity = employeeService.createNewEmployee(inputEmployee);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeByID(@RequestBody @Valid EmployeeDTO employeeDTO, @PathVariable Long employeeId) {
        return ResponseEntity.ok(employeeService.updateEmployeeByID(employeeId, employeeDTO));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeByID(@PathVariable Long employeeId) {
        boolean gotDeleted = employeeService.deleteEmployeeByID(employeeId);
        if (gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeByID(@RequestBody Map<String, Object> updates, @PathVariable Long employeeId) {
        EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeByID(employeeId, updates);

        if (employeeDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }
}
