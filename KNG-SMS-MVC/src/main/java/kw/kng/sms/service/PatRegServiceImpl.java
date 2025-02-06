package kw.kng.sms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import kw.kng.sms.dto.ClinicSetupDto;
import kw.kng.sms.dto.PatRegisterDto;
import kw.kng.sms.entities.ClinicSetup;
import kw.kng.sms.entities.PatRegister;
import kw.kng.sms.exceptions.ResourceNotFoundException;
import kw.kng.sms.repo.ClinicSetupRepo;
import kw.kng.sms.repo.DepartmentSetupRepo;
import kw.kng.sms.repo.PatientRegisterRepo;

@Service
public class PatRegServiceImpl implements PatRegService 
{
	
// ----------------------------------------------------------------------------------------------------------
						// Constructor Injection
	
	private ClinicSetupRepo crepo;
	private DepartmentSetupRepo drepo;
	private PatientRegisterRepo prepo;
	private ModelMapper modelMapper;

	public PatRegServiceImpl(ClinicSetupRepo crepo, DepartmentSetupRepo drepo, PatientRegisterRepo prepo, ModelMapper modelMapper) 
	{
		this.crepo = crepo;
		this.drepo=drepo;
		this.prepo=prepo;
		this.modelMapper = modelMapper;
	}
// ----------------------------------------------------------------------------------------------------------

	@Override
	public PatRegisterDto createPatient(PatRegisterDto patRegisterDto)
	{
		//Transfer DTO to Entity Class
		PatRegister patRegister = modelMapper.map(patRegisterDto, PatRegister.class);
		
		System.out.println("---------------------------------------------- ");
		System.out.println("Service Layer -> Patient Register -> POST -> PASS -> " +
		patRegister.getId()+" "+
		patRegister.getPatNameA()+" "+
		patRegister.getPatNameA()+" "+
		patRegister.getCivilId()+" "+
		patRegister.getMilitaryId()+" "+
		patRegister.getMobile()+" "+
		patRegister.getEmail()+" "+
		patRegister.getGender()+" "+
		patRegister.getRank()+" "+
		patRegister.getPatPhoto()+" "+
		patRegister.getIsEnableYn()+" "+
		patRegister.getIsVip()+" "+
		patRegister.getNationality()+" "+
		patRegister.getPatAllergy()+" "+
		patRegister.getPatHistory()+" "+
		patRegister.getCreationDateTimeStamp()+" "+
		patRegister.getUpdatedBy()+" "+
		patRegister.getUpdationDateTimeStamp()+" "+
		patRegister.getActiveStatus()
		);
		System.out.println("---------------------------------------------- ");
				
		//Save the Clinic details in PERSISTENCE layer
		PatRegister savedPatientRegister = prepo.save(patRegister);
				
		//Transfer ENTITY to DTO
		return modelMapper.map(savedPatientRegister, PatRegisterDto.class);
	}

	@Override
	public PatRegisterDto getPatientById(Long id) 
	{
		//Find the specific PATIENT REGISTRATION from persistence using ID. If not found throw ResourceNotFoundException
		PatRegister patRegister = prepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient with id: "+id+" not found in DB !!!"));
				
		return modelMapper.map(patRegister, PatRegisterDto.class);
	}

	@Override
	public PatRegisterDto updatePatientById(Long id, PatRegisterDto patRegisterDto) 
	{
		//Find the specific PATIENT from persistence using ID. If not found throw ResourceNotFoundException
		PatRegister patRegister = prepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient with id: "+id+" not found in DB !!!"));
			
		System.out.println("---------------------------------------------- ");
		System.out.println("Service Layer -> Patient Register -> PUT -> PASS -> " +
		patRegister.getId()+" "+
		patRegister.getPatNameE()+" "+
		patRegister.getPatNameA()+" "+
		patRegister.getCivilId()+" "+
		patRegister.getMilitaryId()+" "+
		patRegister.getMobile()+" "+
		patRegister.getEmail()+" "+
		patRegister.getGender()+" "+
		patRegister.getRank()+" "+
		patRegister.getPatPhoto()+" "+
		patRegister.getIsEnableYn()+" "+
		patRegister.getIsVip()+" "+
		patRegister.getNationality()+" "+
		patRegister.getPatAllergy()+" "+
		patRegister.getPatHistory()+" "+
		patRegister.getCreationDateTimeStamp()+" "+
		patRegister.getUpdatedBy()+" "+
		patRegister.getUpdationDateTimeStamp()+" "+
		patRegister.getActiveStatus()
		);
		System.out.println("---------------------------------------------- ");
				
		patRegister.setId(patRegisterDto.getId());
		patRegister.setPatNameE(patRegisterDto.getPatNameE());
		patRegister.setPatNameA(patRegisterDto.getPatNameA());
		patRegister.setCivilId(patRegisterDto.getCivilId());
		patRegister.setMilitaryId(patRegisterDto.getMilitaryId());
		patRegister.setMobile(patRegisterDto.getMobile());
		patRegister.setEmail(patRegisterDto.getEmail());
		patRegister.setGender(patRegisterDto.getGender());
		patRegister.setRank(patRegisterDto.getRank());
		patRegister.setPatPhoto(patRegisterDto.getPatPhoto());
		patRegister.setIsEnableYn(patRegisterDto.getIsEnableYn());
		patRegister.setIsVip(patRegisterDto.getIsVip());
		patRegister.setNationality(patRegisterDto.getNationality());
		patRegister.setPatAllergy(patRegisterDto.getPatAllergy());
		patRegister.setPatHistory(patRegisterDto.getPatHistory());
		//patRegister.setCreationDateTimeStamp(patRegisterDto.geCr);
		patRegister.setUpdatedBy(patRegisterDto.getUpdatedBy());
		//patRegister.setUpdationDateTimeStamp();
		patRegister.setActiveStatus(patRegisterDto.getActiveStatus());
		
		
		PatRegister updatePatRegister=prepo.save(patRegister);
				
		return modelMapper.map(updatePatRegister, PatRegisterDto.class);
	}

	@Override
	public void deletPatientById(Long id) 
	{
		//Find the specific PATIENT from persistence using ID. If not found throw ResourceNotFoundException
		PatRegister patRegister = prepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient with id: "+id+" not found in DB !!!"));
					
		prepo.delete(patRegister);
		
	}

	@Override
	public List<PatRegisterDto> getPatientListAll() 
	{
		//Find all the PATIENT from PERSISTENCE
		List<PatRegister> patientRegisterList = prepo.findAllByOrderByIdDesc();
				
		System.out.println("Service Layer -> Patient Register List ->" +patientRegisterList);
				
		//Transfer ENTITY to DTO
		return patientRegisterList.stream().map(n -> modelMapper.map(n, PatRegisterDto.class)).collect(Collectors.toList());
	}

	

}
