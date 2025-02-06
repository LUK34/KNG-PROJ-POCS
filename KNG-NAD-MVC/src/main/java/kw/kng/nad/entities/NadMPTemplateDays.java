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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@ToString(exclude = {"nadMPTemplate","nadMPTemplateDayPattern"})
@EqualsAndHashCode(exclude = {"nadMPTemplate","nadMPTemplateDayPattern"})
@Table(name="NAD_MP_TEMPLATE_DAYS")
public class NadMPTemplateDays 
{
	
	/*
 		1 NadMPTemplate -> MANY NadMPTemplateDays
 		1 NadMPTemplateDays -> Many NadMPTemplateDayPattern
	*/
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id; //check
	
	@Column(name="TPL_CODE",length=10)
	private String tplCode; //check
	
	@Column(name="TPL_CRSE_FRE")
	private long tpleCrsFre; //check
	
	@Column(name="TPL_AVL",length=1)
	private String tplAvl; //check
	
	@Column(name="TPL_PUB",length=1)
	private String tplPub; //check
	
	@Column(name="TPL_DAY")
	private Long tplDay;
	
	@Column(name="TPL_DAYS")
	private String tplDays;
	
	
	//@Column(name="TPL_T_EN",precision=7,scale=2)
	@Column(name="TPL_T_EN")
	private BigDecimal tplTEn; //check
		
	@Column(name="TPL_T_FA")
	private BigDecimal tplTFa; //check
		
	@Column(name="TPL_T_CH")
	private BigDecimal tplTCh; //check
		
	@Column(name="TPL_T_PR")
	private BigDecimal tplTPr; //check
		
	@Column(name="TPL_T_CARB")
	private BigDecimal tplTCarb; //check
		
	@Column(name="TPL_T_FIB")
	private BigDecimal tplTFib; //check
		
	@Column(name="TPL_T_SOD")
	private BigDecimal tplTSod; //check
		
	@Column(name="TPL_T_SUG")
	private BigDecimal tplTSug; //check
	
	//--------------------------------------------------------------------------------------------------------
				// RELATIONSHIPS are DEFINED here

	// Many-to-One relationship - `NadMPTemplateDays` references `NadMPTemplate`
	@ManyToOne
	//@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="TPL_ID", referencedColumnName = "id")
	private NadMPTemplate nadMPTemplate;
	
	// One-to-Many relationship - 1 `NadMPTemplateDays` can have MANY `NadMPTemplateDayPattern`
	@OneToMany(mappedBy = "nadMPTemplateDayPattern", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<NadMPTemplateDayPattern> nadMPTemplateDayPattern = new ArrayList<>();

	//--------------------------------------------------------------------------------------------------------



}









