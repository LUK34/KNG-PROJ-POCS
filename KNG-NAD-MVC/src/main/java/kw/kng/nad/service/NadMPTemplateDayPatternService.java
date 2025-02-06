package kw.kng.nad.service;

import java.util.List;

import kw.kng.nad.dto.NadMPTemplateDayPatternDto;


public interface NadMPTemplateDayPatternService 
{
	NadMPTemplateDayPatternDto createNadMPTemplateDayPatternDto(NadMPTemplateDayPatternDto nadMPTemplateDayPatternDto);
	NadMPTemplateDayPatternDto getNadMPTemplateDayPatternDtoById(Long id);
	NadMPTemplateDayPatternDto updateNadMPTemplateDayPatternDtoById(Long id, NadMPTemplateDayPatternDto nadMPTemplateDayPatternDto);
	void deletNadMPTemplateDayPatternDtoById(Long id);
	List<NadMPTemplateDayPatternDto> getNadMPTemplateDayPatternDtoListAll();
	List<NadMPTemplateDayPatternDto> getNadMPTemplateDayPatternDtoListSpecificById(Long id);

}
