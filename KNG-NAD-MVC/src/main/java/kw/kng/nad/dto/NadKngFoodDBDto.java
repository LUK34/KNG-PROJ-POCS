package kw.kng.nad.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Column;
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
public class NadKngFoodDBDto {
	/*
	 * 1 NadKngFoodDB -> MANY NadMPTemplateFoodPattern
	 */

	private long id;

	@NotEmpty(message = "Food Code cannot be Empty") // should not be null or empty
	@Size(max = 20, message = "MAX LENGTH is 20")
	private String fcode;

	@NotEmpty(message = "Food Name cannot be Empty") // should not be null or empty
	@Size(max = 100, message = "MAX LENGTH is 100")
	private String foodName;

	@NotEmpty(message = "Food Type cannot be Empty") // should not be null or empty
	@Size(max = 100, message = "MAX LENGTH is 100")
	private String foodType;

	@NotEmpty(message = "Food Portions cannot be Empty") // should not be null or empty
	@Size(max = 30, message = "MAX LENGTH is 30")
	private String portions;

	@NotEmpty(message = "Measurements cannot be Empty") // should not be null or empty
	@Size(max = 30, message = "MAX LENGTH is 30")
	private String measurement;

	@NotNull(message = "Must not be NULL or EMPTY")
	@Digits(integer = 7, fraction = 2, message = "must have up to 7 digits and 2 decimal places.")
	private BigDecimal calories;

	@NotNull(message = "Must not be NULL or EMPTY")
	@Digits(integer = 7, fraction = 2, message = "must have up to 7 digits and 2 decimal places.")
	private BigDecimal carbohydrates;

	@NotNull(message = "Must not be NULL or EMPTY")
	@Digits(integer = 7, fraction = 2, message = "must have up to 7 digits and 2 decimal places.")
	private BigDecimal fat;

	@NotNull(message = "Must not be NULL or EMPTY")
	@Digits(integer = 7, fraction = 2, message = "must have up to 7 digits and 2 decimal places.")
	private BigDecimal protein;

	@NotNull(message = "Must not be NULL or EMPTY")
	@Digits(integer = 7, fraction = 2, message = "must have up to 7 digits and 2 decimal places.")
	private BigDecimal fiber;

	@NotNull(message = "Must not be NULL or EMPTY")
	@Digits(integer = 7, fraction = 2, message = "must have up to 7 digits and 2 decimal places.")
	private BigDecimal sodium;

	@Column(name = "STAT", length = 1)
	private String stat;

	// -------------------------------------------------------------
	// Constructor Injection

	// Injected default value for decimal fields
	private BigDecimal defaultDecimalValue = BigDecimal.valueOf(0.00).setScale(2, RoundingMode.HALF_UP);

	public NadKngFoodDBDto(BigDecimal defaultDecimalValue) {
		this.defaultDecimalValue = defaultDecimalValue;
		this.calories = defaultDecimalValue;
		this.carbohydrates = defaultDecimalValue;
		this.fat = defaultDecimalValue;
		this.protein = defaultDecimalValue;
		this.fiber = defaultDecimalValue;
		this.sodium = defaultDecimalValue;

	}
	//-------------------------------------------------------------

	public void setId(long id) {
		this.id = id;
	}

	public void setFcode(String fcode) {
		this.fcode = fcode;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	public void setPortions(String portions) {
		this.portions = portions;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	public void setFiber(BigDecimal fiber) {
		this.fiber = fiber;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}
	//-------------------------------------------------------------
	
	public void setCalories(BigDecimal calories) {
		this.calories = calories != null ? calories : defaultDecimalValue;
	}

	public void setCarbohydrates(BigDecimal carbohydrates) {
		this.carbohydrates = carbohydrates != null ? carbohydrates: defaultDecimalValue;
	}

	public void setProtein(BigDecimal protein) {
		this.protein = protein != null ? protein: defaultDecimalValue;
	}

	public void setSodium(BigDecimal sodium) {
		this.sodium = sodium != null ? sodium: defaultDecimalValue;
	}

	public void setFat(BigDecimal fat) {
		this.fat = fat != null ? fat: defaultDecimalValue;
	}
	//-------------------------------------------------------------
	
	
	public void setDefaultDecimalValue(BigDecimal defaultDecimalValue) 
	{
		this.defaultDecimalValue = defaultDecimalValue;
	}
	
}
