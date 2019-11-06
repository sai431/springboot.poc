package com.poc.springboot.model;

public class Employee {
	private Long empId;
	private String empName;
	private String role;
	private Long salaryCtc;

	public Long getSalaryCtc() {
		return salaryCtc;
	}

	public void setSalaryCtc(Long salaryCtc) {
		this.salaryCtc = salaryCtc;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
