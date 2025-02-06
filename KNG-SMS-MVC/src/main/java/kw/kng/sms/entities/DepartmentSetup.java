package kw.kng.sms.entities;

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
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude={"clinicSetup"}) //this can cause circular reference to avoid stack overflow error.
//@Table(name="NAD_CLINICS",catalog="nad_mvc")
@Table(name="DEPARTMENT_SETUP")
public class DepartmentSetup
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@Column(name="DEPARTMENT_NAME",length=200)
	private String dname;
	
	@Column(name="DEPARTMENT_NAMEA",columnDefinition = "NVARCHAR(255)")
	private String dnamea;

	@Column(name="STAT",length=1)
	private String stat;
	
	// --------------------------------------------------------------------------------------------
									/* ENTITY RELATIONSHIPS */

	// Many-to-One relationship - `DepartmentSetup` references `ClinicSetup`
	@ManyToOne
	//@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CLINIC_ID", referencedColumnName = "id")
	private ClinicSetup clinicSetup;

	// --------------------------------------------------------------------------------------------
}
