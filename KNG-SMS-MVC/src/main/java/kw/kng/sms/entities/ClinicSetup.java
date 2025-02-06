package kw.kng.sms.entities;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@ToString(exclude={"userId","job"}) //this can cause circular reference to avoid stack overflow error.
//@Table(name="NAD_CLINICS",catalog="nad_mvc")
@Table(name="CLINIC_SETUP")
public class ClinicSetup 
{
			/*
			 		1 ClinicSetup -> Many DepartmentSetup
			*/
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@Column(name="CID")
	private long clinicid;
	
	@Column(name="CLINIC_NAME",length=200)
	private String cname;
	
	@Column(name="CLINIC_NAMEA",columnDefinition = "NVARCHAR(255)")
	private String cnamea;

	@Column(name="STAT",length=1)
	private String stat;
	
	// --------------------------------------------------------------------------------------------
								/* ENTITY RELATIONSHIPS */
		
	// If CLINIC is deleted, all associated DEPARTMENTS will also be deleted.
	@OneToMany(mappedBy = "clinicSetup", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DepartmentSetup> departmentSetups = new ArrayList<>();

	
	// --------------------------------------------------------------------------------------------
}
