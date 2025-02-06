package kw.kng.nad.entities;

import java.math.BigDecimal;
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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"nadMPTemplateFoodPattern"})
@EqualsAndHashCode(exclude = {"nadMPTemplateFoodPattern"})
@Table(name="NAD_KNG_FOOD_DB")
public class NadKngFoodDB 
{
	/*
	 	1 NadKngFoodDB -> MANY NadMPTemplateFoodPattern
	*/
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@Column(name="FCODE",length=20)
	private String fcode;
	
	@Column(name="FOODNAME",length=100)
	private String foodName;
	
	@Column(name="FOODTYPE",length=100)
	private String foodType;
	
	@Column(name="PORTIONS",length=30)
	private String portions;
	
	@Column(name="MEASUREMENT",length=30)
	private String measurement;
	
	private BigDecimal calories;
	
	private BigDecimal carbohydrates;
	
	private BigDecimal fat;
	
	private BigDecimal protein;
	
	private BigDecimal fiber;
	
	private BigDecimal sodium;
	
	@Column(name="STAT",length=1)
	private String stat;

	
	
	//--------------------------------------------------------------------------------------------------------
		// RELATIONSHIPS are DEFINED here

	// One-to-Many relationship - 1 `NadMPTemplateDayPattern` can have MANY `NadMPTemplateFoodPattern`
	@OneToMany(mappedBy = "nadMPTemplateFoodPattern", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<NadMPTemplateFoodPattern> nadMPTemplateFoodPattern = new ArrayList<>();

	//--------------------------------------------------------------------------------------------------------

}
