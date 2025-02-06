package kw.kng.nad.service;

import java.util.List;

import kw.kng.nad.dto.NadLogDto;

public interface NadLogService 
{

	NadLogDto createNadLog(NadLogDto nadLogDto);
	NadLogDto getNadLogById(Long id);
	NadLogDto updateNadClinicById(Long id, NadLogDto nadLogDto);
	void deletNadLogoById(Long id);
	List<NadLogDto> getNadLogListAll();
	
}
