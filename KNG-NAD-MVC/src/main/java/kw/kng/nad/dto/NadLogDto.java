package kw.kng.nad.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NadLogDto 
{
	private long id;
	
	@Column(name="SESID", length=25)
	@NotEmpty(message="Session Id cannot be Empty") //should not be null or empty
	private String sesid;
	
	@Column(name="LOG_INDATE", nullable = false, updatable = false)
	private LocalDateTime logIndate;
	
	@Column(name="LOG_ID")
	@Digits(integer = 12, fraction = 0, message="must have up to 12 digits and 0 decimal places.")
	@NotNull(message="Clinic Id must not be NULL or EMPTY")
	private long logId;
	
	@Column(name="LOG_IP", length=64)
	@NotEmpty(message="IP Address cannot be Empty") //should not be null or empty
	private String logIp;
	
	@Column(name="LOG_LOC", length=5)
	@NotEmpty(message="Location cannot be Empty") //should not be null or empty
	private String logLoc;
	
	@Column(name="LOG_OUTDATE", nullable = false, updatable = false)
	private LocalDateTime logOutdate;
	
	@Column(name="STAT", length=1)
	private String stat="N";
	
	@Column(name="LOG_CLINIC")
	@NotNull(message="Clinic Id must not be NULL or EMPTY")
	private long logClinic;

}


