import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Report {

	public final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSXXX");

	public String reportTimeStamp;
	public ReportSender reportSender;
	public String keyword;
	public Position reportPosition;
	public String reportIdentifier;
	public FormResponse[] formResponse;

	public MediaPhoto[] mediaPhoto;
	public MediaVideo[] mediaVideo;

	// TODO: Replace with an ENUM, for now this is:
	// 0 = text only, 1 = image, 2 = video, 3 = image and video (NYI)
	@JsonIgnore
	public int mediaType = -1;

	public void generateMedia() {
		this.mediaType = new Random().nextInt(3);
		// TODO: These procedures for image and video be generalized
		// MediaVideo and MediaPhoto likely should be the same class, though
		if (mediaType == 1) {
			this.mediaPhoto = new MediaPhoto[1];
			MediaPhoto photo = new MediaPhoto();
			photo.mediaPath = "https://s3-eu-west-1.amazonaws.com/rescuer.media.files/images/473159ae-8207-51c1-6b92-f99a2a6f7035.jpg";
			photo.mediaIdentifier = "0";
			photo.mediaTimestamp = Report.sdf.format(new Date());
			photo.type = "image/jpg";
			photo.location = Position.create();
			this.mediaPhoto[0] = photo;
		} else if (mediaType == 2) {
			this.mediaVideo = new MediaVideo[1];
			MediaVideo video = new MediaVideo();
			video.mediaPath = "https://s3-sa-east-1.amazonaws.com/drone.stuff/fire_favelas.mp4";
			video.mediaIdentifier = "0";
			video.mediaTimestamp = Report.sdf.format(new Date());
			video.type = "video/mp4";
			video.location = Position.create();
			this.mediaVideo[0] = video;
		} else if (mediaType == 3) {
			throw new UnsupportedOperationException();
		} else if (mediaType != 0) {
			throw new IllegalArgumentException();
		}
	}

}
