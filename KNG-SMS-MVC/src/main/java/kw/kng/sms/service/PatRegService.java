package kw.kng.sms.service;

import java.util.List;

import kw.kng.sms.dto.DepartmentSetupDto;
import kw.kng.sms.dto.PatRegisterDto;

public interface PatRegService
{
	
	PatRegisterDto createPatient(PatRegisterDto patRegisterDto);
	PatRegisterDto getPatientById(Long id);
	PatRegisterDto updatePatientById(Long id, PatRegisterDto patRegisterDto);
	void deletPatientById(Long id);
	List<PatRegisterDto> getPatientListAll();

}
