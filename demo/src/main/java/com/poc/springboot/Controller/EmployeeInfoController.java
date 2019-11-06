package com.poc.springboot.Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poc.springboot.Exception.EmployeeNotFoundException;
import com.poc.springboot.Repo.EmployeeInfoRepo;
import com.poc.springboot.Repo.SalBreakUpRepo;
import com.poc.springboot.Repo.SalBrkUpEmp;
import com.poc.springboot.entity.EmpLoyeeInfoEntity;
import com.poc.springboot.entity.SalaryBreakupEntity;
import com.poc.springboot.model.Employee;
import com.poc.springboot.model.EmployeeConversionModel;
import com.poc.springboot.model.EmployeeRespDto;
import com.poc.springboot.model.SalaryBreakUpModel;

@RestController
public class EmployeeInfoController {

	@Autowired
	private SalBreakUpRepo breakUpRepo;

	@Autowired
	private EmployeeInfoRepo empInfoRepo;

	@Autowired
	private SalBrkUpEmp salBrkUp;

	@PostMapping("/employee/saveDet")
	public EmployeeRespDto createEmployeeInfo(@RequestBody Employee employeeEntity) {

		EmpLoyeeInfoEntity empInfoo = new EmpLoyeeInfoEntity();
		empInfoo.setEmpId(employeeEntity.getEmpId());
		empInfoo.setEmpName(employeeEntity.getEmpName());
		empInfoo.setRole(employeeEntity.getRole());

		EmpLoyeeInfoEntity empConv = empInfoRepo.save(empInfoo);
		SalaryBreakupEntity salbrkup = salaryEmployee(employeeEntity.getSalaryCtc(), empInfoo);
		SalaryBreakupEntity salBrkDet = breakUpRepo.save(salbrkup);

		EmployeeConversionModel empmodel = new EmployeeConversionModel();
		empmodel.setEmpId(empConv.getEmpId());
		empmodel.setEmpName(empConv.getEmpName());
		empmodel.setRole(empConv.getRole());

		SalaryBreakUpModel salaryBreakUpModel = new SalaryBreakUpModel();
		salaryBreakUpModel.setBasic(salBrkDet.getBasic());
		salaryBreakUpModel.setCityCompAllow(salBrkDet.getCityCompAllow());
		salaryBreakUpModel.setHra(salBrkDet.getHra());
		// salaryBreakUpModel.setSalId(salBrkDet.getSalId());
		salaryBreakUpModel.setConveyance(salBrkDet.getConveyance());
		salaryBreakUpModel.setGrossSalary(salBrkDet.getGrossSalary());
		salaryBreakUpModel.setEducation(salBrkDet.getEducation());
		salaryBreakUpModel.setLta(salBrkDet.getLta());
		salaryBreakUpModel.setMedical(salBrkDet.getMedical());
		

		EmployeeRespDto empresp = new EmployeeRespDto();
		empresp.setSalbrkup(salaryBreakUpModel);
		empresp.setEmployee(empmodel);
		return empresp;

	}

	@PutMapping("/update/test")
	public EmpLoyeeInfoEntity updateemp(@RequestBody Employee employeeEntity) {
		if (empInfoRepo.existsById(employeeEntity.getEmpId())) {
			Optional<EmpLoyeeInfoEntity> empinfo = empInfoRepo.findById(employeeEntity.getEmpId());
			EmpLoyeeInfoEntity embobj = empinfo.get();
			embobj.setEmpName(employeeEntity.getEmpName());
			embobj.setRole(employeeEntity.getRole());
			embobj.setSalaryCtc(employeeEntity.getSalaryCtc());
			EmpLoyeeInfoEntity empConv = empInfoRepo.save(embobj);

			SalaryBreakupEntity salbrkup = salaryEmployee(employeeEntity.getSalaryCtc(), empConv);
			SalaryBreakupEntity salBrkDet = breakUpRepo.save(salbrkup);
			EmployeeConversionModel empmodel = new EmployeeConversionModel();
			empmodel.setEmpId(empConv.getEmpId());
			empmodel.setEmpName(empConv.getEmpName());
			empmodel.setRole(empConv.getRole());

			SalaryBreakUpModel salaryBreakUpModel = new SalaryBreakUpModel();
			salaryBreakUpModel.setBasic(salBrkDet.getBasic());
			salaryBreakUpModel.setCityCompAllow(salBrkDet.getCityCompAllow());
			salaryBreakUpModel.setConveyance(salBrkDet.getConveyance());
			salaryBreakUpModel.setGrossSalary(salBrkDet.getGrossSalary());

			EmployeeRespDto empresp = new EmployeeRespDto();
			empresp.setSalbrkup(salaryBreakUpModel);
			empresp.setEmployee(empmodel);

			return empConv;
		}

		return null;
	}

	@GetMapping("/all")
	
	public List<EmployeeRespDto> getallEmp() {
		List<SalaryBreakupEntity> sal = breakUpRepo.findAll();
		List<EmpLoyeeInfoEntity> emp = empInfoRepo.findAll();
		
		
		
		
		
		List<EmployeeRespDto> empresplist = new ArrayList<EmployeeRespDto>();
		List<EmployeeConversionModel> empconvlist = new ArrayList<EmployeeConversionModel>();
		List<SalaryBreakUpModel> salemplist = new ArrayList<SalaryBreakUpModel>();
		List<EmployeeRespDto> emprespList = new ArrayList<EmployeeRespDto>();
		for (EmpLoyeeInfoEntity empLoyeeInfoEntity : emp) {
			EmployeeRespDto empresp = new EmployeeRespDto();
			EmployeeConversionModel empconv = new EmployeeConversionModel();
			empconv.setEmpId(empLoyeeInfoEntity.getEmpId());
			empconv.setEmpName(empLoyeeInfoEntity.getEmpName());
			empconv.setRole(empLoyeeInfoEntity.getRole());
			empresp.setEmployee(empconv);
			empconvlist.add(empconv);
			
			empresplist.add(empresp);
			

			 
			 
           
		}
		//
		
		
	
		
		 for (SalaryBreakupEntity salEntity : sal) {
			  SalaryBreakUpModel salemp = new SalaryBreakUpModel();
			 EmployeeRespDto empresp = new EmployeeRespDto();
		  salemp.setGrossSalary(salEntity.getGrossSalary());
		  salemp.setHra(salEntity.getHra());
		  salemp.setCityCompAllow(salEntity.getConveyance());
		  salemp.setBasic(salEntity.getBasic());
		  empresp.setSalbrkup(salemp);
		  empresplist.add(empresp);
		//empresplist.add(empresplist);
		}
		
		

		
		
		
		return empresplist;

	}

	//@GetMapping("/alls/empName")
	@RequestMapping(value="/alls")
	public EmployeeRespDto getAllDetails(@RequestParam String empName) {
		List<EmpLoyeeInfoEntity> brkInfo = empInfoRepo.findByEmpName(empName);
		
		  EmployeeRespDto empRes=new EmployeeRespDto(); EmployeeConversionModel ecm
		  =new EmployeeConversionModel(); SalaryBreakUpModel breakUpModel=new
		  SalaryBreakUpModel(); for (EmpLoyeeInfoEntity empLoyeeInfoEntity : brkInfo) {
		  ecm.setEmpName(empLoyeeInfoEntity.getEmpName());
		  ecm.setRole(empLoyeeInfoEntity.getRole());
		  breakUpModel.setBasic(empLoyeeInfoEntity.getSalaryBreakupEntity().getBasic())
		  ; breakUpModel.setCityCompAllow(empLoyeeInfoEntity.getSalaryBreakupEntity().
		  getCityCompAllow());
		  breakUpModel.setConveyance(empLoyeeInfoEntity.getSalaryBreakupEntity().
		  getConveyance());
		  breakUpModel.setGrossSalary(empLoyeeInfoEntity.getSalaryBreakupEntity().
		  getGrossSalary());
		  breakUpModel.setHra(empLoyeeInfoEntity.getSalaryBreakupEntity().getHra());
		  empRes.setEmployee(ecm); empRes.setSalbrkup(breakUpModel); }
		  
		  System.out.println("EmpSalaryBrkUpEntity :" + brkInfo.toString());
		 
		return  empRes;
	}

	@DeleteMapping("/emp/{empId}")
	public void deleteEmpWithsal(@PathVariable(value = "empId") Long empId) {
		empInfoRepo.deleteAll();
	}

	public static SalaryBreakupEntity salaryEmployee(Long ctc, @RequestBody EmpLoyeeInfoEntity empInfoo) {
		double basic = 0.65 * ctc;

		double hra = 0.1 * ctc;
		double cityCompall = 0.5 * ctc;
		double conveyance = 0.5 * ctc;
		double medical = 0.5 * ctc;
		double education = 0.5 * ctc;
		double ltc =0.5*ctc;

		double grosssalary = hra + cityCompall + conveyance + medical + education+ltc;

		SalaryBreakupEntity breakupEntity = new SalaryBreakupEntity();
		breakupEntity.setBasic(basic);
		breakupEntity.setHra(hra);
		breakupEntity.setCityCompAllow(cityCompall);

		breakupEntity.setConveyance(conveyance);

		breakupEntity.setMedical(medical);

		breakupEntity.setEducation(education);

		breakupEntity.setEmpLoyeeInfoEntity(empInfoo);

		breakupEntity.setGrossSalary(grosssalary);
		breakupEntity.setLta(ltc);

		return breakupEntity;

	}

	@PutMapping("/emps/{empId}/sal/{salId}")
	public EmployeeRespDto updateEmpResp(@PathVariable(value = "empId") Long empId, @PathVariable(value = "salId") Long salId,
			@RequestBody Employee commentRequest) {
		if (!breakUpRepo.existsById(empId)) {
			throw new EmployeeNotFoundException("empId " + empId + " not found");
		} else {
			breakUpRepo.findById(salId);

			EmpLoyeeInfoEntity empInfoo = new EmpLoyeeInfoEntity();
			empInfoo.setEmpId(commentRequest.getEmpId());
			empInfoo.setEmpName(commentRequest.getEmpName());
			empInfoo.setRole(commentRequest.getRole());
			empInfoo.setSalaryCtc(commentRequest.getSalaryCtc());

			SalaryBreakupEntity salbrkupEnt = EmployeeInfoController.salaryEmployee(commentRequest.getSalaryCtc(),
					empInfoo);
			EmpLoyeeInfoEntity ErespMod=empInfoRepo.save(empInfoo);
			 SalaryBreakupEntity salBrkModel=  breakUpRepo.save(salbrkupEnt);
			EmployeeRespDto empRes=new EmployeeRespDto();
			EmployeeConversionModel ecm =new EmployeeConversionModel();
			ecm.setEmpName(ErespMod.getEmpName());
			ecm.setRole(ErespMod.getRole());
			empRes.setEmployee(ecm);
			SalaryBreakUpModel salaryBreakUpModel = new SalaryBreakUpModel();
			salaryBreakUpModel.setBasic(salBrkModel.getBasic());
			salaryBreakUpModel.setCityCompAllow(salBrkModel.getCityCompAllow());
			salaryBreakUpModel.setHra(salBrkModel.getHra());
	
			salaryBreakUpModel.setConveyance(salBrkModel.getConveyance());
			salaryBreakUpModel.setGrossSalary(salBrkModel.getGrossSalary());
			empRes.setSalbrkup(salaryBreakUpModel);
			return empRes;
			
			
			
		}

	}

	/*
	 * @GetMapping("/alls") public EmployeeResponseModel getAllDetails(@RequestParam
	 * String empName) { List<EmpLoyeeInfoEntity> brkInfo =
	 * empInfoRepo.findByEmpName(empName);
	 * System.out.println("EmpSalaryBrkUpEntity :" + brkInfo); EmployeeResponseModel
	 * empmodel = new EmployeeResponseModel(); for (EmpLoyeeInfoEntity
	 * empLoyeeInfoEntity : brkInfo) {
	 * empmodel.getEmployee().setEmpName(empLoyeeInfoEntity.getEmpName());
	 * empmodel.getEmployee().setRole(empLoyeeInfoEntity.getRole());
	 * empmodel.getSalmodel().setBasic(empLoyeeInfoEntity.getSalaryBreakupEntity().
	 * getBasic()); empmodel.getSalmodel().setCityCompAllow(empLoyeeInfoEntity.
	 * getSalaryBreakupEntity().getCityCompAllow());
	 * empmodel.getSalmodel().setConveyance(empLoyeeInfoEntity.
	 * getSalaryBreakupEntity().getConveyance());
	 * empmodel.getSalmodel().setGrossSalary(empLoyeeInfoEntity.
	 * getSalaryBreakupEntity().getGrossSalary());
	 * empmodel.getSalmodel().setHra(empLoyeeInfoEntity.getSalaryBreakupEntity().
	 * getHra()); } return empmodel; }
	 */

}
