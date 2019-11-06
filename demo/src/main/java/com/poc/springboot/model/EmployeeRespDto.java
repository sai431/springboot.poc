package com.poc.springboot.model;

public class EmployeeRespDto {
	private EmployeeConversionModel employee;
	private SalaryBreakUpModel salbrkup;

	public SalaryBreakUpModel getSalbrkup() {
		return salbrkup;
	}

	public void setSalbrkup(SalaryBreakUpModel salbrkup) {
		this.salbrkup = salbrkup;
	}

	public EmployeeConversionModel getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeConversionModel employee) {
		this.employee = employee;
	}



	
}
