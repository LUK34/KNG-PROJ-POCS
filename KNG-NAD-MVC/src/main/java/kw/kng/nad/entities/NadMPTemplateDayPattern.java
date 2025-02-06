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
@ToString(exclude = { "nadMPTemplateDays", "nadMPTemplateFoodPattern" })
@EqualsAndHashCode(exclude = { "nadMPTemplateDays", "nadMPTemplateFoodPattern" })
@Table(name = "NAD_MP_TEMPLATE_DAY_PATTERN")
public class NadMPTemplateDayPattern 
{
		
	/*
	 * 1 NadMPTemplateDays -> MANY NadMPTemplateDayPattern. 1
	 * NadMPTemplateDayPattern -> MANY NadMPTemplateFoodPattern.
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; // check

	/*@Column(name = "TPL_ID")
	private long tplId;*/
	
	@Column(name = "CRSE_TIME")
	private String crseTime;

	@Column(name = "CRSE_CRS_FRE")
	private long crseCrsFre;

	@Column(name = "CRSE_AVL", length = 1)
	private String crseAvl;

	private BigDecimal crseTEn;

	private BigDecimal crseTFa;

	private BigDecimal crseTCh;

	private BigDecimal crseTPr;

	private BigDecimal crseTCarb;

	private BigDecimal crseTFib;

	private BigDecimal crseTSod;

	private BigDecimal crseTSug;

	@Column(name = "CRSE_NAMES", length = 100)
	private String crseNames;
	
		
//--------------------------------------------------------------------------------------------------------
	// RELATIONSHIPS are DEFINED here

// Many-to-One relationship - `NadMPTemplateDayPattern` references `NadMPTemplateDays`
	@ManyToOne
	@JoinColumn(name = "TPL_DAY_ID", referencedColumnName = "id")
	private NadMPTemplateDays nadMPTemplateDays;

// One-to-Many relationship - 1 `NadMPTemplateDayPattern` can have MANY `NadMPTemplateFoodPattern`
	@OneToMany(mappedBy = "nadMPTemplateFoodPattern", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<NadMPTemplateFoodPattern> nadMPTemplateFoodPattern = new ArrayList<>();

//--------------------------------------------------------------------------------------------------------

}
