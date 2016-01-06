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

	// TODO: Deixar de ser 1 item pra ser n itens
	public MediaPhoto[] mediaPhoto;
	public MediaVideo[] mediaVideo;

	// TODO: trocar por um enum, por hora: 0 = só texto, 1 = imagem, 2 = vídeo,
	// 3 = imagem e vídeo -- não usar pra geração dessa massa, mas implementar
	// pro futuro

	@JsonIgnore
	public int mediaType = -1;

	public void generateMedia() {
		this.mediaType = new Random().nextInt(4);
		// TODO: These should obviously be generalized
		// MediaVideo and MediaPhoto should likely be the same class
		if (mediaType == 1) {
			this.mediaPhoto = new MediaPhoto[1];
			MediaPhoto photo = new MediaPhoto();
			photo.mediaPath = "https://s3-eu-west-1.amazonaws.com/rescuer.media.filesimages/473159ae-8207-51c1-6b92-f99a2a6f7035.jpg";
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
