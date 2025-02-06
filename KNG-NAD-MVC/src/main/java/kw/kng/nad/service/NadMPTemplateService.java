package kw.kng.nad.service;

import java.util.List;

import kw.kng.nad.dto.NadMPCategoryDto;
import kw.kng.nad.dto.NadMPTemplateDto;

public interface NadMPTemplateService 
{
	NadMPTemplateDto createNadMealPlanTemplate(NadMPTemplateDto nadMPTemplateDto);
	NadMPTemplateDto getNadMealPlanTemplateById(Long id);
	NadMPTemplateDto updateNadMealPlanTemplateById(Long id, NadMPTemplateDto nadMPTemplateDto);
	void deletNadMealPlanTemplateDtoById(Long id);
	List<NadMPTemplateDto> getNadMealPlanTemplateListAll();
	List<NadMPTemplateDto> getNadMealPlanTemplateListSpecificById(Long id);

}
