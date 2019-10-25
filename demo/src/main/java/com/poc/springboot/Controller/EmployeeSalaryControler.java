package com.poc.springboot.Controller;

import java.awt.print.Pageable;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.poc.springboot.Exception.EmployeeNotFoundException;
import com.poc.springboot.Repo.EmpRepository;
import com.poc.springboot.entity.EmployeeEntity;
import com.poc.springboot.entity.SalaryEmployeeEntity;

@RestController
public class EmployeeSalaryControler {

	@Autowired
	private EmpRepository employee;

	@PostMapping("/employee/save")
	public EmployeeEntity createStudent(@RequestBody EmployeeEntity student) {
		EmployeeEntity savedEmployee = employee.save(student);
		return savedEmployee;

	}

	@PutMapping("/employee/{empId}")
	public ResponseEntity<EmployeeEntity> updateStudent(@RequestBody EmployeeEntity student, @PathVariable long id) {

		java.util.Optional<EmployeeEntity> studentOptional = employee.findById(id);

		if (!studentOptional.isPresent())
			return ResponseEntity.notFound().build();

		student.setEmpId(id);

		employee.save(student);

		return ResponseEntity.noContent().build();
	}

	
	
	/*
	 * @GetMapping("/posts") public Page<EmployeeEntity> getAllPosts(Pageable
	 * pageable) { return employee.findAll(pageable); }
	 */
	@GetMapping("/employee/{empId}")
	public EmployeeEntity retrieveStudent(@PathVariable(value = "id") long empId) {
		java.util.Optional<EmployeeEntity> student = employee.findById(empId);

		if (!student.isPresent())
			throw new EmployeeNotFoundException("id-" + empId);

		return student.get();
	}

	@DeleteMapping("/employee/{empId}")
	public void deleteStudent(@PathVariable long empId) {
		employee.deleteById(empId);
	}

}
