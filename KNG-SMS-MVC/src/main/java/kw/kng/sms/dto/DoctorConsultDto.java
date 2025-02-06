package kw.kng.sms.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
public class DoctorConsultDto 
{
	
	private long consultId;
	
	@NotEmpty(message="Patient English Name cannot be Empty")  //should not be null or empty
	private String patNameE;
	
	@NotEmpty(message="Patient Arabic Name cannot be Empty")  //should not be null or empty
	private String patNameA;
	
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
	
	@Column(name="IS_ENABLE_YN",length=1)
	private String isEnableYn;
	
	private String isVip;
	
	@NotNull(message="MOBILE must not be NULL or EMPTY")
	@Pattern(regexp = "\\d{8}", message = "The Mobile Number must contain exactly 8 digits, no letters nor special characters")
	private long mobile;
	
	
	@NotNull(message="GENDER must not be NULL or EMPTY")
	private String gender;

	@Column(name="CONSULT_TYPE",length=3)
	private String consultType;

	@Column(name="EMAIL")
	private String email;
	
	private Long registerId;
	
	private Long clinicId;
	
	private Long departmentId;
	
	
	
}
