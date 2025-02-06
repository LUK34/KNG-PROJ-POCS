package kw.kng.sms.service;

import java.util.List;

import kw.kng.sms.dto.DepartmentSetupDto;

public interface DepartmentService 
{
	DepartmentSetupDto createDepartment(DepartmentSetupDto departmentSetupDto);
	DepartmentSetupDto getDepartmentById(Long id);
	DepartmentSetupDto updateDepartmentById(Long id, DepartmentSetupDto departmentSetupDto);
	void deletDepartmentById(Long id);
	List<DepartmentSetupDto> getDepartmentListAll();
	List<DepartmentSetupDto> getDepartmentListSpecificById(Long id);

}
