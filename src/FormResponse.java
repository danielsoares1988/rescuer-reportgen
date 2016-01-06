import com.fasterxml.jackson.annotation.JsonIgnore;

public class FormResponse {
	@JsonIgnore
	protected static int injuredZones[] = new int[] { 0, 1, 5, 10, 50 };

	public String identifier;
	public String name;
	public Object value;
	public String type;
	public String timestamp;
}
