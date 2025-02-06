package kw.kng.nad.service;


import java.util.List;

import kw.kng.nad.dto.NadClinicDto;

public interface NadClinicService 
{
	
	NadClinicDto createNadClinic(NadClinicDto naClinicDto);
	NadClinicDto getNadClinicById(Long id);
	NadClinicDto updateNadClinicById(Long id, NadClinicDto nadClinicDto);
	void deletNadClinicDtoById(Long id);
	List<NadClinicDto> getNadClinicListAll();
	
}
