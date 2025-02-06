package kw.kng.sms.service;


import java.util.List;

import kw.kng.sms.dto.ClinicSetupDto;

public interface ClinicService 
{
	
	ClinicSetupDto createSmsClinic(ClinicSetupDto clinicDto);
	ClinicSetupDto getClinicById(Long id);
	ClinicSetupDto updateClinicById(Long id, ClinicSetupDto clinicDto);
	void deletClinicDtoById(Long id);
	List<ClinicSetupDto> getClinicListAll();
	
}
