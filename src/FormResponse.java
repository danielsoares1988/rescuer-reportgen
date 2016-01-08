import java.text.Normalizer.Form;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FormResponse {
	@JsonIgnore
	protected static FormAnswer injuredZones[] = new FormAnswer[] { new FormAnswer(0,0), new FormAnswer(1,1), new FormAnswer(2,5), new FormAnswer(3,10), new FormAnswer(4,50) };

	public String identifier;
	public String name;
	public FormAnswer[] multiAnswer;
	public FormAnswer singleAnswer;
	//public String type;
	public String timestamp;
}
