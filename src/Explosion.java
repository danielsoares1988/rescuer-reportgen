import java.util.Date;
import java.util.Random;

public class Explosion extends Report {
	public static class ExplosionFormResponse extends FormResponse {

		public final static int MAX_RESP = 4;

		private static String noise[] = { "Sim", "Não" };
		private static String reaction[] = { "Fogo", "Fumaça", "Outros" };
		private static String damages[] = { "Danos visíveis", "Nenhum dano" };

		public static ExplosionFormResponse create(int id, Date date) {
			ExplosionFormResponse resp = new ExplosionFormResponse();
			id = id == -1 ? new Random().nextInt(4) : id;
			switch (id) {
			case 0:
				resp.name = "Você ouviu algo antes da explosão?";
				resp.type = "text";
				resp.value = noise[new Random().nextInt(2)];
				break;
			case 1:
				resp.name = "Reação da explosão";
				resp.type = "multichoice";
				resp.value = Utils.choicesFrom(String.class, reaction, new Random().nextInt(3) + 1);
				break;
			case 2:
				resp.name = "Há algum dano na estrutura física?";
				resp.type = "text";
				resp.value = damages[new Random().nextInt(2)];
				break;
			case 3:
				resp.name = "Você vê pessoas feridas?";
				resp.type = "text";
				resp.value = Integer.toString(injuredZones[new Random().nextInt(injuredZones.length)]);
			}
			resp.identifier = Integer.toString(id);
			resp.timestamp = Report.sdf.format(date == null ? new Date() : date);
			return resp;
		}
	}

	public static Explosion create() {
		Explosion explosion = new Explosion();
		Date date = new Date();

		explosion.generateMedia();

		explosion.keyword = "Explosão";
		explosion.reportIdentifier = Long.toString(new Date().getTime());
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
