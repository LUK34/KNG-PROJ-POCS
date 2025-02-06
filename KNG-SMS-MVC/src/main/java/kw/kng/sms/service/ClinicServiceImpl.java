package kw.kng.sms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import kw.kng.sms.dto.ClinicSetupDto;
import kw.kng.sms.entities.ClinicSetup;
import kw.kng.sms.exceptions.ResourceNotFoundException;
import kw.kng.sms.repo.ClinicSetupRepo;

@Service
public class ClinicServiceImpl implements ClinicService 
{
	// ----------------------------------------------------------------------------------------------------------
											// Constructor Injection
	private ClinicSetupRepo crepo;
	private ModelMapper modelMapper;
	
	public ClinicServiceImpl(ClinicSetupRepo crepo, ModelMapper modelMapper) 
	{
		this.crepo = crepo;
		this.modelMapper = modelMapper;
	}
	// ----------------------------------------------------------------------------------------------------------
	
	
	@Override
	public ClinicSetupDto createSmsClinic(ClinicSetupDto clinicSetupDto) 
	{	
		//Transfer DTO to Entity Class
		ClinicSetup clinicSetup = modelMapper.map(clinicSetupDto, ClinicSetup.class);
		
		System.out.println("---------------------------------------------- ");
		System.out.println("Service Layer -> Clinics -> POST -> PASS -> " + clinicSetup.getClinicid() +" "+ clinicSetup.getCname() +" "+ clinicSetup.getCnamea() +" "+ clinicSetup.getStat());
		System.out.println("---------------------------------------------- ");
		
		//Save the Clinic details in PERSISTENCE layer
		ClinicSetup savedClinicSetup = crepo.save(clinicSetup);
		
		//Transfer ENTITY to DTO
		return modelMapper.map(savedClinicSetup, ClinicSetupDto.class);
	}

	@Override
	public ClinicSetupDto getClinicById(Long id)
	{
		//Find the specific CLINIC from persistence using ID. If not found throw ResourceNotFoundException
		ClinicSetup clinicSetup = crepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Clinic with id: "+id+" not found in DB !!!"));
		
		return modelMapper.map(clinicSetup, ClinicSetupDto.class);
	}

	@Override
	public ClinicSetupDto updateClinicById(Long id, ClinicSetupDto smsClinicDto) 
	{	
		//Find the specific CLINIC from persistence using ID. If not found throw ResourceNotFoundException
		ClinicSetup clinicSetup = crepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Clinic with id: "+id+" not found in DB !!!"));
	
		System.out.println("---------------------------------------------- ");
		System.out.println("Service Layer -> Clinics -> PUT -> PASS -> " + clinicSetup.getClinicid() +" "+ clinicSetup.getCname() +" "+ clinicSetup.getCnamea() +" "+ clinicSetup.getStat());
		System.out.println("---------------------------------------------- ");
		
		clinicSetup.setClinicid(smsClinicDto.getClinicid());
		clinicSetup.setCname(smsClinicDto.getCname());
		clinicSetup.setCnamea(smsClinicDto.getCnamea());
		clinicSetup.setStat(smsClinicDto.getStat());
		
		ClinicSetup updateSmsClinic=crepo.save(clinicSetup);
		
		return modelMapper.map(updateSmsClinic, ClinicSetupDto.class);
	}

	@Override
	public void deletClinicDtoById(Long id) 
	{
		//Find the specific CLINIC from persistence using ID. If not found throw ResourceNotFoundException
		ClinicSetup clinicSetup = crepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Clinic with id: "+id+" not found in DB !!!"));
			
		crepo.delete(clinicSetup);
	}

	@Override
	public List<ClinicSetupDto> getClinicListAll() 
	{
		//Find all the category from PERSISTENCE
		List<ClinicSetup> clinicsList = crepo.findAllByOrderByIdDesc();
		
		System.out.println("Service Layer -> Clinic List ->" +clinicsList);
		
		//Transfer ENTITY to DTO
		return clinicsList.stream().map(n -> modelMapper.map(n, ClinicSetupDto.class)).collect(Collectors.toList());
	}


	
	
	

}
