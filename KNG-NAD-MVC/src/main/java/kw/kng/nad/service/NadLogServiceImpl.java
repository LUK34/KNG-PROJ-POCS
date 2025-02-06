package kw.kng.nad.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import kw.kng.nad.dto.NadClinicDto;
import kw.kng.nad.dto.NadLogDto;
import kw.kng.nad.entities.NadClinics;
import kw.kng.nad.entities.NadLog;
import kw.kng.nad.exceptions.ResourceNotFoundException;
import kw.kng.nad.repo.NadLogRepo;

@Service
public class NadLogServiceImpl implements NadLogService
{

	// ----------------------------------------------------------------------------------------------------------
				// Constructor Injection
			private NadLogRepo nlrepo;
			private ModelMapper modelMapper;
			
			public NadLogServiceImpl(NadLogRepo nlrepo, ModelMapper modelMapper) 
			{
				this.nlrepo = nlrepo;
				this.modelMapper=modelMapper;
			}
	// ----------------------------------------------------------------------------------------------------------

	@Override
	public NadLogDto createNadLog(NadLogDto nadLogDto) 
	{
		//Transfer DTO to Entity Class
		NadLog nadLog = modelMapper.map(nadLogDto, NadLog.class);
				
		//Save the LOG details in PERSISTENCE layer
		NadLog savedNadLog = nlrepo.save(nadLog);
				
		//Transfer ENTITY to DTO
		return modelMapper.map(savedNadLog, NadLogDto.class);
	}

	@Override
	public NadLogDto getNadLogById(Long id)
	{
		//Find the specific LOG from persistence using ID. If not found throw ResourceNotFoundException
		NadLog nadLog = nlrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Log with id: "+id+" not found in DB !!!"));
				
		return modelMapper.map(nadLog, NadLogDto.class);
	}

	@Override
	public NadLogDto updateNadClinicById(Long id, NadLogDto nadLogDto) 
	{
		//Find the specific LOG from persistence using ID. If not found throw ResourceNotFoundException
		NadLog nadLog = nlrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Log with id: "+id+" not found in DB !!!"));
			
		nadLog.setLogClinic(nadLogDto.getLogClinic());
		nadLog.setLogId(nadLogDto.getLogId());
		nadLog.setLogIndate(nadLogDto.getLogIndate());
		nadLog.setLogIp(nadLogDto.getLogIp());
		nadLog.setLogLoc(nadLogDto.getLogLoc());
		nadLog.setLogOutdate(nadLogDto.getLogOutdate());
		nadLog.setSesid(nadLogDto.getSesid());
		nadLog.setStat(nadLogDto.getStat());
		
				
		NadLog updateNadLog=nlrepo.save(nadLog);
				
		return modelMapper.map(updateNadLog, NadLogDto.class);
	}

	@Override
	public void deletNadLogoById(Long id) 
	{
		//Find the specific LOG from persistence using ID. If not found throw ResourceNotFoundException
		NadLog nadLog = nlrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Log with id: "+id+" not found in DB !!!"));
			
		nlrepo.delete(nadLog);
		
	}

	@Override
	public List<NadLogDto> getNadLogListAll() 
	{
		//Find all the category from PERSISTENCE
		List<NadLog> nadLogList = nlrepo.findAllByOrderByIdDesc();
				
				
		//Transfer ENTITY to DTO
		return nadLogList.stream().map(n -> modelMapper.map(n, NadLogDto.class)).collect(Collectors.toList());
	}
	

}
