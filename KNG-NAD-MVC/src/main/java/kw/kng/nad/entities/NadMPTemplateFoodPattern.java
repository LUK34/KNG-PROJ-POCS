package kw.kng.nad.entities;

import java.math.BigDecimal;

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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"nadMPTemplateDayPattern", "nadKngFoodDB"})
@EqualsAndHashCode(exclude = {"nadMPTemplateDayPattern","nadKngFoodDB"})
@Table(name="NAD_MP_TEMPLATE_FOOD_PATTERN")
public class NadMPTemplateFoodPattern 
{
	/*
	 		 MANY NadMPTemplateFoodPattern -> 1 NadMPTemplateDayPattern 
	 		 MANY NadMPTemplateFoodPattern ->  1 NadKngFoodDB 
	 */
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	
	@Column(name="CRSE_ID")
	private long crseId;
	
	@Column(name="FOODID", length=50)
	private String foodId;
	
	@Column(name="FOODNAME", length=255)
	private String foodName;
	
	@Column(name="CRSE_CRS_FRE")
	private long crseCrsFre;
	
	@Column(name="CRSE_AVL", length=1)
	private String crseAvl;
	
	@Column(name="CRSE_CRS_DES", length=25)
	private String crseCrsDes;
	
	private BigDecimal crseTEn;
	
	private BigDecimal crseTFa;
	
	private BigDecimal crseTCh;

	private BigDecimal crseTPr;

	private BigDecimal crseTCarb;
	
	private BigDecimal crseTFib;
	
	private BigDecimal crseTSod;
	
	private BigDecimal crseTSug;
	
	
	//--------------------------------------------------------------------------------------------------------
		// RELATIONSHIPS are DEFINED here

	// Many-to-One relationship - `NadMPTemplateFoodPattern` references `NadMPTemplateDayPattern`
	@ManyToOne
	@JoinColumn(name="TPL_DAY_PATTERN_ID", referencedColumnName = "id")
	private NadMPTemplateDayPattern nadMPTemplateDayPattern;

	// Many to One relationship - `NadKngFoodDb` references `NadMPTemplateFoodPattern`
	@ManyToOne
	@JoinColumn(name="KNG_FOOD_ID", referencedColumnName = "id")
	private NadKngFoodDB nadKngFoodDB;

	//--------------------------------------------------------------------------------------------------------


}
