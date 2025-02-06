package kw.kng.sms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import kw.kng.sms.dto.DepartmentSetupDto;
import kw.kng.sms.entities.ClinicSetup;
import kw.kng.sms.entities.DepartmentSetup;
import kw.kng.sms.exceptions.ResourceNotFoundException;
import kw.kng.sms.repo.ClinicSetupRepo;
import kw.kng.sms.repo.DepartmentSetupRepo;

@Service
public class DepartmentServiceImpl implements DepartmentService 
{
	
	// ----------------------------------------------------------------------------------------------------------
				// Constructor Injection
	
	private ClinicSetupRepo clinicSetupRepo;
	private DepartmentSetupRepo departmentSetupRepo;
	private ModelMapper modelMapper;

	public DepartmentServiceImpl(ClinicSetupRepo clinicSetupRepo, DepartmentSetupRepo departmentSetupRepo, ModelMapper modelMapper) 
	{
		this.clinicSetupRepo=clinicSetupRepo;
		this.departmentSetupRepo = departmentSetupRepo;
		this.modelMapper = modelMapper;
	}
	// ----------------------------------------------------------------------------------------------------------

	@Override
	public DepartmentSetupDto createDepartment(DepartmentSetupDto departmentSetupDto) 
	{
		//Transfer DTO to Entity Class
		System.out.println("Department SERVICE -> CREATE started.");
		DepartmentSetup departmentSetup = modelMapper.map(departmentSetupDto, DepartmentSetup.class);
		
		// The departmentSetup.getClinicSetup().getId() will not be mapped by modelMapper. So we have to do it manually.
		ClinicSetup clinicSetup=clinicSetupRepo.findById(departmentSetupDto.getClinicId()).orElseThrow(() -> new ResourceNotFoundException("Clinic with id: " + departmentSetupDto.getClinicId() + " not found"));
		departmentSetup.setClinicSetup(clinicSetup);
	
		//Save the DEPARTMENT SETUP details in PERSISTENCE layer
		DepartmentSetup savedDepartmentSetup = departmentSetupRepo.save(departmentSetup);
		
		System.out.println("---------------------------------------------- ");
		System.out.println("Service Layer -> Department Setup -> POST -> PASS -> " + 
				departmentSetup.getId()+" "+
				departmentSetup.getDname()+" "+
				departmentSetup.getDnamea()+" "+
				departmentSetup.getStat()+" "+
				departmentSetup.getClinicSetup()
		);
		System.out.println("---------------------------------------------- ");
		
		//Transfer ENTITY to DTO
		return modelMapper.map(savedDepartmentSetup, DepartmentSetupDto.class);
	}

	@Override
	public DepartmentSetupDto getDepartmentById(Long id) 
	{
		//Find the specific DEPARTMENT SETUP from persistence using ID. If not found throw ResourceNotFoundException
		DepartmentSetup departmentSetup = departmentSetupRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department with id: "+id+" not found in DB !!!"));
		
		System.out.println("---------------------------------------------- ");
		System.out.println("Service Layer -> DEPARTMENT SETUP -> GET by Id -> PASS -> " + 
				departmentSetup.getId()+" "+
				departmentSetup.getDname()+" "+
				departmentSetup.getDnamea()+" "+
				departmentSetup.getStat()+" "+
				departmentSetup.getClinicSetup()
		);
		System.out.println("---------------------------------------------- ");
		
		// Custom ModelMapper mapping to include DEPARTMENT SETUP id in the DTO
		DepartmentSetupDto departmentSetupDto = modelMapper.map(departmentSetup, DepartmentSetupDto.class);

	    // Manually map the clinicId from DEPARTMENT SETUP to DTO
		departmentSetupDto.setClinicId(departmentSetup.getClinicSetup().getId());

	    return departmentSetupDto;
	}

	@Override
	public DepartmentSetupDto updateDepartmentById(Long id, DepartmentSetupDto departmentSetupDto) 
	{
		//Find the specific DEPARTMENT SETUP from persistence using ID. If not found throw ResourceNotFoundException
		DepartmentSetup departmentSetup = departmentSetupRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department with id: "+id+" not found in DB !!!"));
		
		System.out.println("---------------------------------------------- ");
		System.out.println("Service Layer -> DEPARTMENT SETUP -> PUT -> PASS -> " + 
				departmentSetupDto.getId()+" "+
				departmentSetupDto.getDname()+" "+
				departmentSetupDto.getDnamea()+" "+
				departmentSetupDto.getStat()+" "+
				departmentSetupDto.getClinicId()
		);
		System.out.println("---------------------------------------------- ");
	
		departmentSetup.setId(departmentSetupDto.getId());
		departmentSetup.setDname(departmentSetupDto.getDname());
		departmentSetup.setDnamea(departmentSetupDto.getDnamea());
		departmentSetup.setStat(departmentSetupDto.getStat());
		
		ClinicSetup clinicSetup=clinicSetupRepo.findById(departmentSetupDto.getClinicId()).orElseThrow(() -> new ResourceNotFoundException("Clinic with id: " + departmentSetupDto.getClinicId() + " not found"));
		departmentSetup.setClinicSetup(clinicSetup);		
		
		DepartmentSetup updateDepartmentSetup=departmentSetupRepo.save(departmentSetup);
		
		return modelMapper.map(updateDepartmentSetup, DepartmentSetupDto.class);
	}

	@Override
	public void deletDepartmentById(Long id) 
	{
		//Find the specific DEPARTMENT from persistence using ID. If not found throw ResourceNotFoundException
		DepartmentSetup departmentSetup = departmentSetupRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department with id: "+id+" not found in DB !!!"));
		departmentSetupRepo.delete(departmentSetup);
		
	}

	@Override
	public List<DepartmentSetupDto> getDepartmentListAll()
	{
		//Find all the DEPARTMENT from PERSISTENCE
		List<DepartmentSetup> departmentSetupList = departmentSetupRepo.findAllByOrderByIdDesc();
		
		// Configure ModelMapper for the custom mapping
	    modelMapper.typeMap(DepartmentSetup.class, DepartmentSetupDto.class)
	               .addMappings(mapper -> mapper.map(src -> src.getClinicSetup().getId(), DepartmentSetupDto::setClinicId));
		
		//Transfer ENTITY to DTO
		return departmentSetupList.stream().map(n -> modelMapper.map(n, DepartmentSetupDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<DepartmentSetupDto> getDepartmentListSpecificById(Long id) 
	{
		//Find the MEAL PLAN TEMPLATE by id from PERSISTENCE
		DepartmentSetup departmentSetup = departmentSetupRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department with id: "+id+" not found in DB !!!"));
		
		// Custom ModelMapper mapping to include NadMPCategory's id in the DTO
		// Configure ModelMapper for the custom mapping
	    modelMapper.typeMap(DepartmentSetup.class, DepartmentSetupDto.class)
	               .addMappings(mapper -> mapper.map(src -> src.getClinicSetup().getId(), DepartmentSetupDto::setClinicId));
		
	    // Transfer ENTITY to DTO
	    DepartmentSetupDto departmentSetupDto = modelMapper.map(departmentSetup, DepartmentSetupDto.class);

	    // Return as a list (if required by your method signature)
	    return List.of(departmentSetupDto);
	}

		
}
