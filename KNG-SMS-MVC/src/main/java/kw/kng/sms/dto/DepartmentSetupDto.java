package kw.kng.sms.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentSetupDto 
{

	private long id;
	
	@NotEmpty(message="Department English Name cannot be Empty")  //should not be null or empty
	private String dname;
	
	@NotEmpty(message="Department Arabic Name cannot be Empty") //should not be null or empty
	private String dnamea;

	private String stat="Y";
	
	//Many to 1 relationship defined here.
	@NotNull(message="Must not be NULL or EMPTY")
	//@Size(max = 1, message = "MAX LENGTH is 1")
	private Long clinicId;

}
