package kw.kng.nad.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import kw.kng.nad.dto.NadClinicDto;
import kw.kng.nad.entities.NadClinics;
import kw.kng.nad.exceptions.ResourceNotFoundException;
import kw.kng.nad.repo.NadClinicRepo;

@Service
public class NadClinicServiceImpl implements NadClinicService 
{
	// ----------------------------------------------------------------------------------------------------------
											// Constructor Injection
	private NadClinicRepo nrepo;
	private ModelMapper modelMapper;
	
	public NadClinicServiceImpl(NadClinicRepo nrepo, ModelMapper modelMapper) 
	{
		this.nrepo = nrepo;
		this.modelMapper = modelMapper;
	}
	// ----------------------------------------------------------------------------------------------------------
	
	
	@Override
	public NadClinicDto createNadClinic(NadClinicDto naClinicDto) 
	{	
		//Transfer DTO to Entity Class
		NadClinics nadClinics = modelMapper.map(naClinicDto, NadClinics.class);
		
		System.out.println("---------------------------------------------- ");
		System.out.println("Service Layer -> NadClinics -> POST -> PASS -> " + nadClinics.getClinicid() +" "+ nadClinics.getCname() +" "+ nadClinics.getCnamea() +" "+ nadClinics.getStat());
		System.out.println("---------------------------------------------- ");
		
		//Save the Clinic details in PERSISTENCE layer
		NadClinics savedNadClinics = nrepo.save(nadClinics);
		
		//Transfer ENTITY to DTO
		return modelMapper.map(savedNadClinics, NadClinicDto.class);
	}

	@Override
	public NadClinicDto getNadClinicById(Long id)
	{
		//Find the specific CLINIC from persistence using ID. If not found throw ResourceNotFoundException
		NadClinics nadClinic = nrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Clinic with id: "+id+" not found in DB !!!"));
		
		return modelMapper.map(nadClinic, NadClinicDto.class);
	}

	@Override
	public NadClinicDto updateNadClinicById(Long id, NadClinicDto nadClinicDto) 
	{	
		//Find the specific CLINIC from persistence using ID. If not found throw ResourceNotFoundException
		NadClinics nadClinic = nrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Clinic with id: "+id+" not found in DB !!!"));
	
		System.out.println("---------------------------------------------- ");
		System.out.println("Service Layer -> NadClinics -> PUT -> PASS -> " + nadClinic.getClinicid() +" "+ nadClinic.getCname() +" "+ nadClinic.getCnamea() +" "+ nadClinic.getStat());
		System.out.println("---------------------------------------------- ");
		
		nadClinic.setClinicid(nadClinicDto.getClinicid());
		nadClinic.setCname(nadClinicDto.getCname());
		nadClinic.setCnamea(nadClinicDto.getCnamea());
		nadClinic.setStat(nadClinicDto.getStat());
		
		NadClinics updateNadClinic=nrepo.save(nadClinic);
		
		return modelMapper.map(updateNadClinic, NadClinicDto.class);
	}

	@Override
	public void deletNadClinicDtoById(Long id) 
	{
		//Find the specific CLINIC from persistence using ID. If not found throw ResourceNotFoundException
		NadClinics nadClinic = nrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Clinic with id: "+id+" not found in DB !!!"));
			
		nrepo.delete(nadClinic);
	}

	@Override
	public List<NadClinicDto> getNadClinicListAll() 
	{
		//Find all the category from PERSISTENCE
		List<NadClinics> nadClinicsList = nrepo.findAllByOrderByIdDesc();
		
		System.out.println("Service Layer -> Nad Clinic List ->" +nadClinicsList);
		
		//Transfer ENTITY to DTO
		return nadClinicsList.stream().map(n -> modelMapper.map(n, NadClinicDto.class)).collect(Collectors.toList());
	}


	
	
	

}
