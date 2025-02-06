package kw.kng.sms.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
//@NoArgsConstructor
@AllArgsConstructor
public class PatRegisterDto 
{
	private long id;
	
	@NotNull(message="Patient Name English must not be NULL or EMPTY")
	private String patNameE;
	
	@NotNull(message="Patient Name Arabic must not be NULL or EMPTY")
	private String patNameA;
	
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private LocalDate registerDate;
	
	@NotNull(message="DOB must not be NULL or EMPTY")
	private LocalDate dob;
	
	@NotNull(message="CIVIL ID must not be NULL or EMPTY")
	@Pattern(regexp = "\\d{12}", message = "The Civil ID must contain exactly 12 digits, no letters nor special characters")
	private long civilId;
	
	@NotNull(message="MILITARY Number must not be NULL or EMPTY")
	@Pattern(regexp = "\\d{6}", message = "The MILITARY Number must contain exactly 6 digits, no letters nor special characters")
	private long militaryId;
	
	@NotNull(message="RANK must not be NULL or EMPTY")
	private String rank;
	
	@NotNull(message="NATIONALITY must not be NULL or EMPTY")
	private long nationality;
	
	private String isVip;
	
	private String isEnableYn="Y";
	
	@NotNull(message="MOBILE must not be NULL or EMPTY")
	@Pattern(regexp = "\\d{8}", message = "The Mobile Number must contain exactly 8 digits, no letters nor special characters")
	private long mobile;

	@NotNull(message="GENDER must not be NULL or EMPTY")
	private String gender;
	
	@Column(name="EMAIL")
	private String email;
	
	private String patHistory;
	
	private String patAllergy;
	
	private long createdBy;
	
	private long updatedBy;
	
	private String patPhoto;
	
	private String activeStatus="Y";
	
	public PatRegisterDto()
	{
	    this.registerDate = LocalDate.now(); // TODAYS Date DEFAULT set here.
	}
	/*
	public PatRegisterDto()
	{
		 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	     LocalDate currentDate = sdf1.format(new Date());
	     this.registerDate = currentDate;
	}
	*/
	
}
