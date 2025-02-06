package kw.kng.nad.entities;




import java.time.LocalDateTime;
import java.util.Date;

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
@Table(name="NAD_LOGS")
public class NadLog 
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@Column(name="SESID", length=25)
	private String sesid;
	
	@Column(name="LOG_INDATE", nullable = false, updatable = false)
	private LocalDateTime logIndate;
	
	@Column(name="LOG_ID")
	private long logId;
	
	@Column(name="LOG_IP", length=64)
	private String logIp;
	
	@Column(name="LOG_LOC", length=5)
	private String logLoc;
	
	@Column(name="LOG_OUTDATE", nullable = false, updatable = false)
	private LocalDateTime logOutdate;
	
	@Column(name="STAT", length=1)
	private String stat;
	
	@Column(name="LOG_CLINIC")
	private long logClinic;
	
}












