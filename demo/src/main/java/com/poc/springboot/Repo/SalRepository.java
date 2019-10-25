package com.poc.springboot.Repo;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc.springboot.entity.SalaryEmployeeEntity;

@Repository
public interface SalRepository  extends JpaRepository<SalaryEmployeeEntity, Long>{
	/*
	 * //Page<SalaryEmployeeEntity> findByPostId(Long empId, Pageable pageable);
	 * Optional<SalaryEmployeeEntity> findByIdAndPostId(Long salId, Long empId);
	 */
}
