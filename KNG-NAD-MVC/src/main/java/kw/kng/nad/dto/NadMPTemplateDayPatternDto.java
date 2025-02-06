package kw.kng.nad.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NadMPTemplateDayPatternDto 
{
	private long id;
	
	/*private long tplId;*/
	
	private long tplDayId;
	
	private String crseNames;
	
	private String crseTime;
	
	private long crseCrsFre;
	
	private String crseAvl="Y";
	
	@NotNull(message="Must not be NULL or EMPTY")
	@Digits(integer = 7, fraction = 2, message="must have up to 7 digits and 2 decimal places.")
	private BigDecimal crseTEn;
	
	@NotNull(message="Must not be NULL or EMPTY")
	@Digits(integer = 7, fraction = 2, message="must have up to 7 digits and 2 decimal places.")
	private BigDecimal crseTFa;
	
	@NotNull(message="Must not be NULL or EMPTY")
	@Digits(integer = 7, fraction = 2, message="must have up to 7 digits and 2 decimal places.")
	private BigDecimal crseTCh;

	@NotNull(message="Must not be NULL or EMPTY")
	@Digits(integer = 7, fraction = 2, message="must have up to 7 digits and 2 decimal places.")
	private BigDecimal crseTPr;

	@NotNull(message="Must not be NULL or EMPTY")
	@Digits(integer = 7, fraction = 2, message="must have up to 7 digits and 2 decimal places.")
	private BigDecimal crseTCarb;
	
	@NotNull(message="Must not be NULL or EMPTY")
	@Digits(integer = 7, fraction = 2, message="must have up to 7 digits and 2 decimal places.")
	private BigDecimal crseTFib;
	
	@NotNull(message="Must not be NULL or EMPTY")
	@Digits(integer = 7, fraction = 2, message="must have up to 7 digits and 2 decimal places.")
	private BigDecimal crseTSod;
	
	@NotNull(message="Must not be NULL or EMPTY")
	@Digits(integer = 7, fraction = 2, message="must have up to 7 digits and 2 decimal places.")
	private BigDecimal crseTSug;
	
	// -------------------------------------------------------------
			// Constructor Injection

			// Injected default value for decimal fields
			private BigDecimal defaultDecimalValue = BigDecimal.valueOf(0.00).setScale(2, RoundingMode.HALF_UP);

			public NadMPTemplateDayPatternDto(BigDecimal defaultDecimalValue) {
				this.defaultDecimalValue = defaultDecimalValue;
				this.crseTEn = defaultDecimalValue; 
				this.crseTCarb = defaultDecimalValue;
				this.crseTFa = defaultDecimalValue;
				this.crseTPr = defaultDecimalValue;
				this.crseTFib = defaultDecimalValue;
				this.crseTSod = defaultDecimalValue;
				this.crseTSug = defaultDecimalValue;
				this.crseTCh = defaultDecimalValue;
			}
		//-------------------------------------------------------------

			public void setId(long id) {
				this.id = id;
			}

			public void setCrseNames(String crseNames) {
				this.crseNames = crseNames;
			}

			public void setCrseCrsFre(long crseCrsFre) {
				this.crseCrsFre = crseCrsFre;
			}

			public void setCrseAvl(String crseAvl) {
				this.crseAvl = crseAvl;
			}
			
			public void setCrseTime(String crseTime) {
				this.crseTime = crseTime;
			}
			
			public void setTplDayId(long tplDayId) {
				this.tplDayId = tplDayId;
			}
		//-------------------------------------------------------------

			public void setCrseTEn(BigDecimal crseTEn) {
				this.crseTEn = crseTEn != null ? crseTEn : defaultDecimalValue;
			}

			public void setCrseTFa(BigDecimal crseTFa) {
				this.crseTFa = crseTFa != null ? crseTFa : defaultDecimalValue;
			}

			public void setCrseTCh(BigDecimal crseTCh) {
				this.crseTCh = crseTCh != null ? crseTCh : defaultDecimalValue;
			}

			public void setCrseTPr(BigDecimal crseTPr) {
				this.crseTPr = crseTPr != null ? crseTPr : defaultDecimalValue;
			}

			public void setCrseTCarb(BigDecimal crseTCarb) {
				this.crseTCarb = crseTCarb != null ? crseTCarb : defaultDecimalValue;
			}

			public void setCrseTFib(BigDecimal crseTFib) {
				this.crseTFib = crseTFib != null ? crseTFib : defaultDecimalValue;
			}

			public void setCrseTSod(BigDecimal crseTSod) {
				this.crseTSod = crseTSod != null ? crseTSod : defaultDecimalValue;
			}

			public void setCrseTSug(BigDecimal crseTSug) {
				this.crseTSug = crseTSug != null ? crseTSug : defaultDecimalValue;
			}
			
		//-------------------------------------------------------------
			
			public void setDefaultDecimalValue(BigDecimal defaultDecimalValue) {
				this.defaultDecimalValue = defaultDecimalValue;
			}

			
	
			
			
			
			
			
			
			
}
