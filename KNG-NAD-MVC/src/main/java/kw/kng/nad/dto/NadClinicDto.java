package kw.kng.nad.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NadClinicDto 
{
	
	private long id;
	
	@NotNull(message="Clinic Id must not be NULL or EMPTY")
	private long clinicid;
	
	@NotEmpty(message="English Office Name cannot be Empty") //should not be null or empty
	private String cname;
	
	@NotEmpty(message="Arabic Office Name cannot be Empty") //should not be null or empty
	private String cnamea;

	private String stat="Y";

}

/*
 @NotEmpty(message="User Email should not be NULL or EMPTY")//should not be null or empty
	@NotBlank(message="User Email should not be NULL nor should have WHITE SPACE") //should not be null or have white space
	@Email(message="User Email should be VALID") //should have VALID Email
	private String email;
*/