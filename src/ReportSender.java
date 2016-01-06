import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ReportSender {

	@JsonIgnore
	private final static int[] roles = { 1, 2 };

	public int personRole;
	public Position personPosition;

	public static ReportSender create() {
		ReportSender sender = new ReportSender();
		sender.personRole = roles[new Random().nextInt(roles.length)];
		sender.personPosition = Position.create();
		return sender;
	}

}
