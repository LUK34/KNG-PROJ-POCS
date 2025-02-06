package kw.kng.nad.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="NAD_CLINICS")
public class NadClinics 
{
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@Column(name="CLINICID")
	private long clinicid;
	
	@Column(name="CNAME",length=200)
	private String cname;
	
	@Column(name="CNAMEA",columnDefinition = "NVARCHAR(255)")
	private String cnamea;

	@Column(name="STAT",length=1)
	private String stat;

}
