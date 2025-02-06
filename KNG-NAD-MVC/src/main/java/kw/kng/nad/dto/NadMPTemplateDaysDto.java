package kw.kng.nad.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NadMPTemplateDaysDto 
{
	/*
		One Meal Plan Template can have MANY Meal Plan Template Days
	*/
	
	private long id;
	
	//Many to 1 relationship defined here.
	@NotNull(message="Must not be NULL or EMPTY")
	//@Size(max = 1, message = "MAX LENGTH is 1")
	private long tplId;
		
	private String tplCode;
	
	private long tpleCrsFre;
	
	@NotEmpty(message="Must not be NULL or EMPTY")
	@Size(max = 1, message = "MAX LENGTH is 1")
	private String tplAvl="Y";
	
	@NotEmpty(message="Must not be NULL or EMPTY")
	@Size(max = 1, message = "MAX LENGTH is 1")
	private String tplPub="Y";

	//@NotNull(message="Must not be NULL or EMPTY")
	private Long tplDay;
	
	@NotEmpty(message="Template Day name must not be NULL or EMPTY")
	private String tplDays;
	

	//@Column(name="TPL_T_EN",precision=7,scale=2)
		@NotNull(message="Must not be NULL or EMPTY")
		@Digits(integer = 7, fraction = 2, message="must have up to 7 digits and 2 decimal places.")
		private BigDecimal tplTEn;
		
		@NotNull(message="Must not be NULL or EMPTY")
		@Digits(integer = 7, fraction = 2, message="must have up to 7 digits and 2 decimal places.")
		private BigDecimal tplTFa;
		
		//@NotNull(message="Must not be NULL or EMPTY")
		@Digits(integer = 7, fraction = 2, message="must have up to 7 digits and 2 decimal places.")
		private BigDecimal tplTCh;
		
		@NotNull(message="Must not be NULL or EMPTY")
		@Digits(integer = 7, fraction = 2, message="must have up to 7 digits and 2 decimal places.")
		private BigDecimal tplTPr;
		
		@NotNull(message="Must not be NULL or EMPTY")
		@Digits(integer = 7, fraction = 2, message="must have up to 7 digits and 2 decimal places.")
		private BigDecimal tplTCarb;
		
		@NotNull(message="Must not be NULL or EMPTY")
		@Digits(integer = 7, fraction = 2, message="must have up to 7 digits and 2 decimal places.")
		private BigDecimal tplTFib;
		
		@NotNull(message="Must not be NULL or EMPTY")
		@Digits(integer = 7, fraction = 2, message="must have up to 7 digits and 2 decimal places.")
		private BigDecimal tplTSod;
		
		//@NotNull(message="Must not be NULL or EMPTY")
		@Digits(integer = 7, fraction = 2, message="must have up to 7 digits and 2 decimal places.")
		private BigDecimal tplTSug;
	
		
		//-------------------------------------------------------------
				//Constructor Injection
		
		// Injected default value for decimal fields
	    private BigDecimal defaultDecimalValue = BigDecimal.valueOf(0.00).setScale(2,RoundingMode.HALF_UP);
		
	    
		 public NadMPTemplateDaysDto(BigDecimal defaultDecimalValue)
		 {
			 	this.defaultDecimalValue = defaultDecimalValue;
		        this.tplTEn = defaultDecimalValue;
		        this.tplTFa = defaultDecimalValue;
		        this.tplTCh = defaultDecimalValue;
		        this.tplTPr = defaultDecimalValue;
		        this.tplTCarb = defaultDecimalValue;
		        this.tplTFib = defaultDecimalValue;
		        this.tplTSod = defaultDecimalValue;
		        this.tplTSug = defaultDecimalValue;
		 }

		//-------------------------------------------------------------
		

			public void setId(long id) {
				this.id = id;
			}

			public void setTplId(Long tplId) {
				this.tplId = tplId;
			}

			public void setTplCode(String tplCode) {
				this.tplCode = tplCode;
			}

			public void setTpleCrsFre(long tpleCrsFre) {
				this.tpleCrsFre = tpleCrsFre;
			}

			public void setTplAvl(String tplAvl) {
				this.tplAvl = tplAvl;
			}

			public void setTplPub(String tplPub) {
				this.tplPub = tplPub;
			}

			public void setTplDay(Long tplDay) {
				this.tplDay = tplDay;
			}

			public void setTplDays(String tplDays) {
				this.tplDays = tplDays;
			}
		 
		 
		 
			public void setTplTEn(BigDecimal tplTEn) {
				this.tplTEn = tplTEn != null ? tplTEn : defaultDecimalValue;
			}

			public void setTplTFa(BigDecimal tplTFa) {
				this.tplTFa = tplTFa != null ? tplTFa : defaultDecimalValue;
			}

			public void setTplTCh(BigDecimal tplTCh) {
				this.tplTCh = tplTCh != null ? tplTCh : defaultDecimalValue;
			}

			public void setTplTPr(BigDecimal tplTPr) {
				this.tplTPr = tplTPr != null ? tplTPr : defaultDecimalValue;
			}

			public void setTplTCarb(BigDecimal tplTCarb) {
				this.tplTCarb = tplTCarb != null ? tplTCarb : defaultDecimalValue;
			}

			public void setTplTFib(BigDecimal tplTFib) {
				this.tplTFib = tplTFib != null ? tplTFib : defaultDecimalValue;
			}

			public void setTplTSod(BigDecimal tplTSod) {
				this.tplTSod = tplTSod != null ? tplTSod : defaultDecimalValue;
			}

			public void setTplTSug(BigDecimal tplTSug) {
				this.tplTSug = tplTSug != null ? tplTSug : defaultDecimalValue;
			}

			public void setDefaultDecimalValue(BigDecimal defaultDecimalValue) {
				this.defaultDecimalValue = defaultDecimalValue;
			}

}
