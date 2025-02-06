package kw.kng.nad.service;

import java.util.List;

import kw.kng.nad.dto.NadMPCategoryDto;

public interface NadMPCategoryService 
{
	NadMPCategoryDto createNadMealPlanCategory(NadMPCategoryDto nadMPCategoryDto);
	NadMPCategoryDto getNadMealPlanCategoryById(Long id);
	NadMPCategoryDto updateNadMealPlanCategoryById(Long id, NadMPCategoryDto nadMPCategoryDto);
	void deletNadMealPlanCategoryDtoById(Long id);
	List<NadMPCategoryDto> getNadMealPlanCategoryListAll();
	List<NadMPCategoryDto> getNadClinicListAll_nameAsc();

}
