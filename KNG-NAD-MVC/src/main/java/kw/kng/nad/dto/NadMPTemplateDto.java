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
public class NadMPTemplateDto
{
	/*
 			One Meal Plan Template belongs to One Meal Plan Category so ONE is to ONE Relationship.
	 */
	
	private long id;
	
	@NotEmpty(message="Meal Plan Template Name must not be NULL or EMPTY")
	private String tplName;
	
	//@NotEmpty(message="Meal Plan Template Code ust not be NULL or EMPTY")
	//@Size(max = 10, message = "MAX LENGTH is 10")
	private String tplCode;
	
	private long tpleCrsFre;
	
	@NotEmpty(message="Must not be NULL or EMPTY")
	@Size(max = 1, message = "MAX LENGTH is 1")
	private String tplAvl="Y";
	
	@NotEmpty(message="Must not be NULL or EMPTY")
	@Size(max = 1, message = "MAX LENGTH is 1")
	private String tplPub="Y";

	private String tplObj;
	
	private String tplPlan;

	//Many to 1 relationship defined here.
	@NotNull(message="Must not be NULL or EMPTY")
	//@Size(max = 1, message = "MAX LENGTH is 1")
	private Long tplCat;
	
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
	
    
	 public NadMPTemplateDto(BigDecimal defaultDecimalValue)
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
	
	 
	//-------------------------------------------------------------
	 			//Setters defined not via lombok
	 
	 
	 	

		public void setId(long id) {
			this.id = id;
		}

		public void setTplName(String tplName) {
			this.tplName = tplName;
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
		
		public void setTplObj(String tplObj) {
			this.tplObj = tplObj;
		}

		public void setTplPlan(String tplPlan) {
			this.tplPlan = tplPlan;
		}

		public void setTplCat(Long tplCat) {
			this.tplCat = tplCat;
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

		

	
		 
    //-------------------------------------------------------------
	
	


}


/*
 
 This code defines a constructor injection using the @Autowired annotation in Spring. Here's what it does:

How It Works:
@Autowired Annotation:

Tells Spring to inject a value into the NadMPTemplateDTO constructor when the object is created by the Spring container.
Spring looks for a BigDecimal bean defined in the Spring context (like one created using @Value or @Bean).
Parameter Injection (BigDecimal defaultDecimalValue):

When Spring creates an instance of NadMPTemplateDTO, it looks for a BigDecimal bean named defaultDecimalValue (created using @Value or in a configuration class).
Spring injects the value into the constructor parameter defaultDecimalValue.



 */