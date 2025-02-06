package kw.kng.nad.service;

import java.util.List;

import kw.kng.nad.dto.NadKngFoodDBDto;

public interface NadKngFoodDBService 
{
	NadKngFoodDBDto createNadKngFoodDB(NadKngFoodDBDto nadKngFoodDBDto);
	NadKngFoodDBDto getNadKngFoodDBById(Long id);
	NadKngFoodDBDto updateNadKngFoodDBById(Long id, NadKngFoodDBDto nadKngFoodDBDto);
	void deletNadKngFoodDBById(Long id);
	List<NadKngFoodDBDto> getNadKngFoodDBListAll();

}
