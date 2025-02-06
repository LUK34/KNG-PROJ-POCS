package kw.kng.nad.service;

import java.util.List;

import kw.kng.nad.dto.NadMPTemplateDaysDto;

public interface NadMPTemplateDaysService 
{
	NadMPTemplateDaysDto createNadMPTemplateDays(NadMPTemplateDaysDto nadMPTemplateDaysDto);
	NadMPTemplateDaysDto getNadMPTemplateDaysById(Long id);
	NadMPTemplateDaysDto updateNadMPTemplateDaysById(Long id, NadMPTemplateDaysDto nadMPTemplateDaysDto);
	void deletNadMPTemplateDaysById(Long id);
	List<NadMPTemplateDaysDto> getNadMPTemplateDaysListAll();
	List<NadMPTemplateDaysDto> getNadMPTemplateDaysListSpecificById(Long id);

}
