package kw.kng.nad.entities;

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
@ToString(exclude = "nadMPTemplate")
@EqualsAndHashCode(exclude = "nadMPTemplate")
//@ToString(exclude={"userId","job"}) //this can cause circular reference to avoid stack overflow error.
//@Table(name="NAD_CLINICS",catalog="nad_mvc")NadMPCategory
@Table(name="NAD_MP_CATEGORY")
public class NadMPCategory
{
	/*
 		One Meal Plan Category can have MANY meal Plan Templates.
	 */
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;

	@Column(name="CTG",length=200)
	private String ctg;

	@Column(name="CTGA",columnDefinition = "NVARCHAR(255)")
	private String ctga;

	@Column(name="STAT",length=1)
	private String stat;

	// If category is deleted, all associated templates will also be deleted.
	@OneToMany(mappedBy = "nadMPCategory", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<NadMPTemplate> nadMPTemplates = new ArrayList<>();
	

}


/*
	//If categpry deleted. Then all the associated Templates will also be deletd.
	//If template is deleted. Then all the associated categories will not be deleted.
	@OneToOne(mappedBy = "nadMPCategory", cascade = CascadeType.REMOVE)
	private NadMPTemplate nadMPTemplate;

*/