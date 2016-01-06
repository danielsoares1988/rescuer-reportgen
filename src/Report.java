import java.text.SimpleDateFormat;

public class Report {

	public final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSXXX");

	public String reportTimeStamp;
	public ReportSender reportSender;
	public String keyword;
	public Position reportPosition;
	public String reportIdentifier;
	public FormResponse[] formResponse;
}
