package com.poc.springboot.Controller;



import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc.springboot.Exception.ResourceNotFoundException;
import com.poc.springboot.Repo.EmpRepository;
import com.poc.springboot.Repo.SalRepository;
import com.poc.springboot.entity.SalaryEmployeeEntity;

@RestController
public class SalaryEmployeeController {

	@Autowired
	private EmpRepository employee;

	@Autowired
	private SalRepository salaryEmprepo;

	@PostMapping("/posts/{empId}/comments")
	public SalaryEmployeeEntity createComment(@PathVariable Long empId,
			@Valid @RequestBody SalaryEmployeeEntity comment) {
		return employee.findById(empId).map(post -> {
			comment.setBookCategory(post);
			return salaryEmprepo.save(comment);
		}).orElseThrow(() -> new ResourceNotFoundException("PostId " + empId + " not found"));
	}
	
	
	 @PutMapping("/posts/{empId}/comments/{salId}")
	    public SalaryEmployeeEntity updateComment(@PathVariable  Long empId,
	                                 @PathVariable  Long salId,
	                                 @Valid @RequestBody SalaryEmployeeEntity commentRequest) {
	        if(!employee.existsById(empId)) {
	            throw new ResourceNotFoundException("PostId " + empId + " not found");
	        }

	        return salaryEmprepo.findById(salId).map(comment -> {
	            comment.setBasic(commentRequest.getBasic());
	            return salaryEmprepo.save(comment);
	        }).orElseThrow(() -> new ResourceNotFoundException("salId " + salId + "not found"));
	    }
	 
	/*
	 * @GetMapping("/posts/{empId}/comments") public Page<SalaryEmployeeEntity>
	 * getAllCommentsByPostId(@PathVariable (value = "empId") Long empId, Pageable
	 * pageable) { return salaryEmprepo.findByPostId(empId, pageable); }
	 */
	 
	/*
	 * @DeleteMapping("/posts/{empId}/comments/{salId}") public ResponseEntity<?>
	 * deleteComment(@PathVariable (value = "empId") Long empId,
	 * 
	 * @PathVariable (value = "salId") Long salId) { return
	 * salaryEmprepo.findByIdAndPostId(salId, empId).map(comment -> {
	 * salaryEmprepo.delete(comment); return ResponseEntity.ok().build();
	 * }).orElseThrow(() -> new
	 * ResourceNotFoundException("Comment not found with id " + salId +
	 * " and postId " + empId)); }
	 */

}
