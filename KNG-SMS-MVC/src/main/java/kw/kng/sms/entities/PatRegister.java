package kw.kng.sms.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@ToString(exclude={"userId","job"}) //this can cause circular reference to avoid stack overflow error.
//@Table(name="NAD_CLINICS",catalog="nad_mvc")
@Table(name="PAT_REGISTER")
public class PatRegister
{
	
	/*
 		1 PatRegister -> MANY PatConsult
	*/
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@Column(name="PAT_NAME_E",length=200)
	private String patNameE;
	
	@Column(name="PAT_NAME_A",length=200,columnDefinition = "NVARCHAR(255)")
	private String patNameA;
	
	@Column(name="REGISTER_DATE")
	private LocalDate registerDate;
	
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
	
	@Column(name="PAT_HISTORY")
	private String patHistory;
	
	@Column(name="PAT_ALLERGY")
	private String patAllergy;
	
	@Column(name="CREATED_BY")
	private long createdBy;
	
	@CreationTimestamp
	private LocalDateTime creationDateTimeStamp;
	
	@Column(name="UPDATED_BY")
	private long updatedBy;
	
	@UpdateTimestamp
	private LocalDateTime updationDateTimeStamp;
	
	@Column(name="PAT_PHOTO")
	private String patPhoto;
	
	@Column(name="ACTIVE_STATUS",length=1)
	private String activeStatus;
	
	
	// If PAT_REGISTER is deleted, all associated CONSULTATIONS will also be deleted.
	@OneToMany(mappedBy = "doctorConsult", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DoctorConsult> doctorConsults = new ArrayList<>();
	
	
}
