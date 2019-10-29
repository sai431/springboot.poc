package com.poc.springboot.Repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc.springboot.entity.SalaryEmployeeEntity;

@Repository
public interface SalRepository extends JpaRepository<SalaryEmployeeEntity, Long> {
	
	List<SalaryEmployeeEntity> findByEmployeeEntityEmpId(Long employeeEntity_empId);
	//Page<SalaryEmployeeEntity> findByEmpId(Long employeeEntity_empId, Pageable pageable);
	// Page<SalaryEmployeeEntity> findByPostId(Long emp_empid, Pageable pageable);

}
