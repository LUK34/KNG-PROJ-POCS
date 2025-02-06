package kw.kng.nad.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NadMPCategoryDto 
{
	private long id;

	@NotEmpty(message="English Catgeory Name cannot be Empty") //should not be null or empty
	private String ctg;

	@NotEmpty(message="Arabic Catgeory Name cannot be Empty") //should not be null or empty
	private String ctga;

	private String stat="Y";
	
	//1 to Many relationship defined here.
	private List<NadMPTemplateDto> nadMPTemplateDto; // Representing the list of related templates

}
