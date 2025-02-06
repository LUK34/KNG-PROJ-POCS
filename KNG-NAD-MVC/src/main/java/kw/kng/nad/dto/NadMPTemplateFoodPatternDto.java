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
@AllArgsConstructor
@NoArgsConstructor
public class NadMPTemplateFoodPatternDto 
{
	
	private long id;
	
	private long tplDayPatternId;
	
	private long kngFoodId;
	
	private long crseId;
	
	@NotEmpty(message = "Food Id cannot be Empty") // should not be null or empty
	@Size(max = 50, message = "MAX LENGTH is 50")
	private String foodId;
	
	@NotEmpty(message = "Food Id cannot be Empty") // should not be null or empty
	@Size(max = 255, message = "MAX LENGTH is 255")
	private String foodName;
	
	private long crseCrsFre;
	
	private String crseAvl="Y";
	
	@Size(max = 25, message = "MAX LENGTH is 25")
	private String crseCrsDes;
	
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

		public NadMPTemplateFoodPatternDto(BigDecimal defaultDecimalValue) {
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

		public void setTplDayPatternId(long tplDayPatternId) {
			this.tplDayPatternId = tplDayPatternId;
		}

		public void setKngFoodId(long kngFoodId) {
			this.kngFoodId = kngFoodId;
		}

		public void setCrseId(long crseId) {
			this.crseId = crseId;
		}

		public void setFoodId(String foodId) {
			this.foodId = foodId;
		}

		public void setFoodName(String foodName) {
			this.foodName = foodName;
		}

		public void setCrseCrsFre(long crseCrsFre) {
			this.crseCrsFre = crseCrsFre;
		}

		public void setCrseAvl(String crseAvl) {
			this.crseAvl = crseAvl;
		}

		public void setCrseCrsDes(String crseCrsDes) {
			this.crseCrsDes = crseCrsDes;
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
		public void setDefaultDecimalValue(BigDecimal defaultDecimalValue) 
		{
			this.defaultDecimalValue = defaultDecimalValue;
		}

}
