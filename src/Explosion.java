import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class Explosion extends Report {

	public Explosion() {
		keywordIdentifier = "rType2";
	}

	public static class ExplosionFormResponse extends FormResponse {

		public final static int MAX_RESP = 4;

		private static FormAnswer noise[] = { new FormAnswer(0, "Sim"), new FormAnswer(1, "Não") };
		private static FormAnswer reaction[] = { new FormAnswer(0, "Fogo"), new FormAnswer(1, "Fumaça"),
				new FormAnswer(2, "Outros") };
		private static FormAnswer damages[] = { new FormAnswer(0, "Danos visíveis"), new FormAnswer(1, "Nenhum dano") };

		public static ExplosionFormResponse create(int id, Date date) {
			ExplosionFormResponse resp = new ExplosionFormResponse();
			id = id == -1 ? new Random().nextInt(4) : id;
			switch (id) {
			case 0:
				resp.identifier = "7";
				resp.name = "Você ouviu algo antes da explosão?";
				resp.type = "text";
				resp.singleAnswer = noise[new Random().nextInt(2)];
				break;
			case 1:
				resp.identifier = "8";
				resp.name = "Reação da explosão";
				resp.type = "text";
				resp.multiAnswer = Utils.choicesFrom(FormAnswer.class, reaction, new Random().nextInt(3) + 1);
				break;
			case 2:
				resp.identifier = "9";
				resp.name = "Há algum dano na estrutura física?";
				resp.type = "text";
				resp.singleAnswer = damages[new Random().nextInt(2)];
				break;
			case 3:
				resp.identifier = "3";
				resp.name = "Você vê pessoas feridas?";
				resp.type = "integ";
				resp.singleAnswer = injuredZones[new Random().nextInt(injuredZones.length)];
			}
			resp.timestamp = Report.sdf.format(date == null ? new Date() : date);
			return resp;
		}
	}

	public static Explosion create() {
		Explosion explosion = new Explosion();
		Date date = new Date();

		explosion.generateMedia();

		explosion.keyword = "EXPLOSION";
		explosion.reportIdentifier = UUID.randomUUID().toString();
		explosion.reportSender = ReportSender.create();
		explosion.reportPosition = explosion.reportSender.personPosition;
		explosion.reportTimeStamp = Report.sdf.format(date);
		Integer[] responseChoices = Utils.randomChoices(ExplosionFormResponse.MAX_RESP);
		// int resps = new Random().nextInt(FireFormResponse.MAX_RESP);
		if (responseChoices.length > 0) {
			explosion.formResponse = new FormResponse[responseChoices.length];
			for (int i = 0; i < responseChoices.length; i++) {
				explosion.formResponse[i] = ExplosionFormResponse.create(responseChoices[i], date);
			}
		} else
			explosion.formResponse = new ExplosionFormResponse[] {};
		return explosion;

	}
}
