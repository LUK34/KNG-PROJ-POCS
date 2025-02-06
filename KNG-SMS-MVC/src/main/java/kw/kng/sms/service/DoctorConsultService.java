package kw.kng.sms.service;

import java.util.List;

import kw.kng.sms.dto.DepartmentSetupDto;
import kw.kng.sms.dto.DoctorConsultDto;

public interface DoctorConsultService 
{
	DoctorConsultDto createDoctorConsult(DoctorConsultDto doctorConsultDto);
	DoctorConsultDto getDoctorConsultById(Long id);
	DoctorConsultDto updateDoctorConsultById(Long id, DoctorConsultDto doctorConsultDto);
	void deletDoctorConsultById(Long id);
	List<DoctorConsultDto> getDoctorConsultListAll();
	List<DoctorConsultDto> getDoctorConsultListSpecificById(Long id);

}
