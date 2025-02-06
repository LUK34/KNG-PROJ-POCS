package kw.kng.sms.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@ToString(exclude={"userId","job"}) //this can cause circular reference to avoid stack overflow error.
//@Table(name="NAD_CLINICS",catalog="nad_mvc")
@Table(name="PAT_CONSULT")
public class DoctorConsult 
{
	
	/*
		1 PatRegister -> MANY PatConsult
	 */
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long consultId;
	
	@Column(name="PAT_NAME_E",length=200)
	private String patNameE;
	
	@Column(name="PAT_NAME_A",length=200,columnDefinition = "NVARCHAR(255)")
	private String patNameA;
	
	@Column(name="DOB")
	private LocalDate dob;
	
	@Column(name="CIVIL_ID", length=12)
	private long civilId;
	
	@Column(name="MILITARY_ID", length=6)
	private long militaryId;
	
	@Column(name="RANK")
	private String rank;
	
	@Column(name="NATIONALITY")
	private long nationality;
	
	@Column(name="IS_VIP",length=1)
	private String isVip;
	
	@Column(name="IS_ENABLE_YN",length=1)
	private String isEnableYn;
	
	
	@Column(name="MOBILE", length=8)
	private long mobile;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="GENDER", length=1)
	private String gender;

	@Column(name="CONSULT_TYPE",length=3)
	private String consultType;
	
	//--------------------------------------------------------------------------------------------------------
									// RELATIONSHIPS are DEFINED here
	
	@ManyToOne
	//@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="REGISTER_ID", referencedColumnName = "id")
	private PatRegister patRegister;
	
	// Many-to-One relationship - `PatConsult` references `ClinicSetup`
	@ManyToOne
	//@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CLINIC_ID", referencedColumnName = "id")
	private ClinicSetup clinicSetup;
	
	// Many-to-One relationship - `PatConsult` references `DepartmentSetup`
	@ManyToOne
	//@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="DEPARTMENT_ID", referencedColumnName = "id")
	private DepartmentSetup departmentSetup;
	
	//--------------------------------------------------------------------------------------------------------
	
	
}
