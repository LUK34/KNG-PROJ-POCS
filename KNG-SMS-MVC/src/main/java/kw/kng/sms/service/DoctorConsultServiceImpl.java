package kw.kng.sms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import kw.kng.sms.dto.DoctorConsultDto;
import kw.kng.sms.entities.ClinicSetup;
import kw.kng.sms.entities.DepartmentSetup;
import kw.kng.sms.entities.DoctorConsult;
import kw.kng.sms.entities.PatRegister;
import kw.kng.sms.exceptions.ResourceNotFoundException;
import kw.kng.sms.repo.ClinicSetupRepo;
import kw.kng.sms.repo.DepartmentSetupRepo;
import kw.kng.sms.repo.DoctorConsultRepo;
import kw.kng.sms.repo.PatientRegisterRepo;

@Service
public class DoctorConsultServiceImpl implements DoctorConsultService {

	// ----------------------------------------------------------------------------------------------------------
	// Constructor Injection

	private ClinicSetupRepo csrepo;
	private DepartmentSetupRepo dsrepo;
	private DoctorConsultRepo dcrepo;
	private PatientRegisterRepo prrepo;
	private ModelMapper modelMapper;

	public DoctorConsultServiceImpl(ClinicSetupRepo csrepo, DepartmentSetupRepo dsrepo, 
									DoctorConsultRepo dcrepo, PatientRegisterRepo prrepo,
									ModelMapper modelMapper)
	{
		this.csrepo = csrepo;
		this.dsrepo = dsrepo;
		this.dcrepo = dcrepo;
		this.prrepo=prrepo;
		this.modelMapper = modelMapper;
	}
	// ----------------------------------------------------------------------------------------------------------

	@Override
	public DoctorConsultDto createDoctorConsult(DoctorConsultDto doctorConsultDto) 
	{
		// Transfer DTO to Entity Class
		System.out.println("Doctor Consult SERVICE -> CREATE started.");
		DoctorConsult doctorConsult = modelMapper.map(doctorConsultDto, DoctorConsult.class);

		PatRegister patRegister=prrepo.findById(doctorConsultDto.getRegisterId()).orElseThrow(() -> new ResourceNotFoundException("Register with id: " + doctorConsultDto.getDepartmentId() + " not found"));
		doctorConsult.setPatRegister(patRegister);
		
		ClinicSetup clinicSetup = csrepo.findById(doctorConsultDto.getClinicId()).orElseThrow(() -> new ResourceNotFoundException("Clinic with id: " + doctorConsultDto.getClinicId() + " not found"));
		doctorConsult.setClinicSetup(clinicSetup);
		
		DepartmentSetup departmentSetup=dsrepo.findById(doctorConsultDto.getDepartmentId()).orElseThrow(() -> new ResourceNotFoundException("Department with id: " + doctorConsultDto.getDepartmentId() + " not found"));
		doctorConsult.setDepartmentSetup(departmentSetup);	
		
		// Save the DOCTOR_CONSULT details in PERSISTENCE layer
		DoctorConsult savedDoctorConsult = dcrepo.save(doctorConsult);

		System.out.println("---------------------------------------------- ");
		System.out.println("Service Layer -> Doctor Consult -> POST -> PASS -> "+
		doctorConsult.getConsultId()+" "+
		doctorConsult.getPatNameE()+" "+
		doctorConsult.getPatNameA()+" "+
		doctorConsult.getDob()+" "+
		doctorConsult.getCivilId()+" "+
		doctorConsult.getMilitaryId()+" "+
		doctorConsult.getRank()+" "+
		doctorConsult.getNationality()+" "+
		doctorConsult.getIsEnableYn()+" "+
		doctorConsult.getIsVip()+" "+
		doctorConsult.getMobile()+" "+
		doctorConsult.getGender()+" "+
		doctorConsult.getConsultType()+" "+
		doctorConsult.getEmail()+" "+
		doctorConsult.getPatRegister().getId()+" "+
		doctorConsult.getClinicSetup().getClinicid()+" "+
		doctorConsult.getDepartmentSetup().getId()
		);
		System.out.println("---------------------------------------------- ");

		// Transfer ENTITY to DTO
		return modelMapper.map(savedDoctorConsult, DoctorConsultDto.class);
	}

	@Override
	public DoctorConsultDto getDoctorConsultById(Long id) 
	{
		//Find the specific DOCTOR CONSULT from persistence using ID. If not found throw ResourceNotFoundException
		DoctorConsult doctorConsult = dcrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Consultation with id: "+id+" not found in DB !!!"));
				
		System.out.println("---------------------------------------------- ");
		System.out.println("Service Layer -> Doctor Consult -> get by id -> PASS -> "+
		doctorConsult.getConsultId()+" "+
		doctorConsult.getPatNameE()+" "+
		doctorConsult.getPatNameA()+" "+
		doctorConsult.getDob()+" "+
		doctorConsult.getCivilId()+" "+
		doctorConsult.getMilitaryId()+" "+
		doctorConsult.getRank()+" "+
		doctorConsult.getNationality()+" "+
		doctorConsult.getIsEnableYn()+" "+
		doctorConsult.getIsVip()+" "+
		doctorConsult.getMobile()+" "+
		doctorConsult.getGender()+" "+
		doctorConsult.getConsultType()+" "+
		doctorConsult.getEmail()+" "+
		doctorConsult.getPatRegister().getId()+" "+
		doctorConsult.getClinicSetup().getClinicid()+" "+
		doctorConsult.getDepartmentSetup().getId()
		);
		System.out.println("---------------------------------------------- ");
				
		// Custom ModelMapper mapping to include DOCTOR CONSULT id in the DTO
		DoctorConsultDto doctorConsultDto = modelMapper.map(doctorConsult, DoctorConsultDto.class);

		//Manually map the REGISTER ID to DTO
		doctorConsultDto.setRegisterId(doctorConsult.getPatRegister().getId());
		
		// Manually map the CLINIC ID to DTO
		doctorConsultDto.setClinicId(doctorConsult.getClinicSetup().getClinicid());
		
		// Manually map the DEPARTMENT ID to DTO
		doctorConsultDto.setDepartmentId(doctorConsult.getDepartmentSetup().getId());
		
		return doctorConsultDto;
	}

	@Override
	public DoctorConsultDto updateDoctorConsultById(Long id, DoctorConsultDto doctorConsultDto) 
	{
		//Find the specific DEPARTMENT SETUP from persistence using ID. If not found throw ResourceNotFoundException
		DoctorConsult doctorConsult = dcrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Consultation with id: "+id+" not found in DB !!!"));
				
		System.out.println("---------------------------------------------- ");
		System.out.println("Service Layer -> Doctor Consult -> PUT -> PASS -> "+
		doctorConsultDto.getConsultId()+" "+
		doctorConsultDto.getPatNameE()+" "+
		doctorConsultDto.getPatNameA()+" "+
		doctorConsultDto.getDob()+" "+
		doctorConsultDto.getCivilId()+" "+
		doctorConsultDto.getMilitaryId()+" "+
		doctorConsultDto.getRank()+" "+
		doctorConsultDto.getNationality()+" "+
		doctorConsultDto.getIsEnableYn()+" "+
		doctorConsultDto.getIsVip()+" "+
		doctorConsultDto.getMobile()+" "+
		doctorConsultDto.getGender()+" "+
		doctorConsultDto.getConsultType()+" "+
		doctorConsultDto.getEmail()+" "+
		doctorConsultDto.getRegisterId()+" "+
		doctorConsultDto.getClinicId()+" "+
		doctorConsultDto.getDepartmentId()
		);
		System.out.println("---------------------------------------------- ");
		doctorConsult.setConsultId(doctorConsultDto.getConsultId());
		doctorConsult.setPatNameE(doctorConsultDto.getPatNameE());
		doctorConsult.setPatNameA(doctorConsultDto.getPatNameA());
		doctorConsult.setDob(doctorConsultDto.getDob());
		doctorConsult.setCivilId(doctorConsultDto.getCivilId());
		doctorConsult.setMilitaryId(doctorConsultDto.getMilitaryId());
		doctorConsult.setRank(doctorConsultDto.getRank());
		doctorConsult.setNationality(doctorConsultDto.getNationality());
		doctorConsult.setIsEnableYn(doctorConsultDto.getIsEnableYn());
		doctorConsult.setIsVip(doctorConsultDto.getIsVip());
		doctorConsult.setMobile(doctorConsultDto.getMobile());
		doctorConsult.setGender(doctorConsultDto.getGender());
		doctorConsult.setConsultType(doctorConsultDto.getConsultType());
		doctorConsult.setEmail(doctorConsultDto.getEmail());
		
		PatRegister patRegister=prrepo.findById(doctorConsultDto.getRegisterId()).orElseThrow(() -> new ResourceNotFoundException("Register with id: " + doctorConsultDto.getDepartmentId() + " not found"));
		doctorConsult.setPatRegister(patRegister);
		
		ClinicSetup clinicSetup = csrepo.findById(doctorConsultDto.getClinicId()).orElseThrow(() -> new ResourceNotFoundException("Clinic with id: " + doctorConsultDto.getClinicId() + " not found"));
		doctorConsult.setClinicSetup(clinicSetup);		
		
		DepartmentSetup departmentSetup=dsrepo.findById(doctorConsultDto.getDepartmentId()).orElseThrow(() -> new ResourceNotFoundException("Department with id: " + doctorConsultDto.getDepartmentId() + " not found"));
		doctorConsult.setDepartmentSetup(departmentSetup);
		
		DoctorConsult updateDoctorConsult=dcrepo.save(doctorConsult);
				
		return modelMapper.map(updateDoctorConsult, DoctorConsultDto.class);
	}

	@Override
	public void deletDoctorConsultById(Long id) 
	{
		//Find the specific DEPARTMENT from persistence using ID. If not found throw ResourceNotFoundException
		DoctorConsult doctorConsult = dcrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Consultation with id: "+id+" not found in DB !!!"));
		dcrepo.delete(doctorConsult);
	}

	@Override
	public List<DoctorConsultDto> getDoctorConsultListAll()
	{
		
		List<DoctorConsult> doctorConsultList = dcrepo.findAllByOrderByIdDesc();
				
		// Configure ModelMapper for the custom mapping
		modelMapper.typeMap(DoctorConsult.class, DoctorConsultDto.class)
			               .addMappings(mapper -> 
			               {
			            	   mapper.map(src -> src.getPatRegister().getId(), DoctorConsultDto::setRegisterId);
			                   mapper.map(src -> src.getClinicSetup().getId(), DoctorConsultDto::setClinicId);
			                   mapper.map(src -> src.getDepartmentSetup().getId(), DoctorConsultDto::setDepartmentId);
			               });
				
		//Transfer ENTITY to DTO
		return doctorConsultList.stream().map(n -> modelMapper.map(n, DoctorConsultDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<DoctorConsultDto> getDoctorConsultListSpecificById(Long id) 
	{
		
		DoctorConsult doctorConsult = dcrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Consultation with id: "+id+" not found in DB !!!"));
				
		// Configure ModelMapper for the custom mapping
		modelMapper.typeMap(DoctorConsult.class, DoctorConsultDto.class)
			               .addMappings(mapper -> 
			               {
			            	   mapper.map(src -> src.getPatRegister().getId(), DoctorConsultDto::setRegisterId);
			                   mapper.map(src -> src.getClinicSetup().getId(), DoctorConsultDto::setClinicId);
			                   mapper.map(src -> src.getDepartmentSetup().getId(), DoctorConsultDto::setDepartmentId);
			               });
				
		// Transfer ENTITY to DTO
		DoctorConsultDto doctorConsultDto = modelMapper.map(doctorConsult, DoctorConsultDto.class);

		// Return as a list (if required by your method signature)
		return List.of(doctorConsultDto);
	}

	
	
	
}
