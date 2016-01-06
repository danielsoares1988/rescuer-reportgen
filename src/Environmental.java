import java.util.Date;
import java.util.Random;

public class Environmental extends Report {
	public static class EnvironmentalFormResponse extends FormResponse {

		public final static int MAX_RESP = 4;

		private static String itchType[] = new String[] { "Olhos", "Pele", "Vias Respiratórias", "Mucosa" };
		private static String damagesTo[] = { "Pessoas", "Plantas", "Animais", "Nenhum" };
		private static String fluids[] = { "Substância visível", "Nenhuma visível" };

		public static EnvironmentalFormResponse create(int id, Date date) {
			EnvironmentalFormResponse resp = new EnvironmentalFormResponse();
			id = id == -1 ? new Random().nextInt(4) : id;
			switch (id) {
			case 0:
				resp.name = "Você está sentindo alguma irritação?";
				resp.type = "multichoice";
				resp.value = Utils.choicesFrom(String.class, itchType, new Random().nextInt(itchType.length) + 1);
				break;
			case 1:
				resp.name = "Há algum dano?";
				resp.type = "multichoice";
				resp.value = Utils.choicesFrom(String.class, damagesTo, new Random().nextInt(4) + 1);
				break;
			case 2:
				resp.name = "Há algum fluído colorido na água ou no chão?";
				resp.type = "text";
				resp.value = fluids[new Random().nextInt(2)];
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

	public static Environmental create() {
		Environmental environmental = new Environmental();
		Date date = new Date();

		environmental.generateMedia();

		environmental.keyword = "Ambiental";
		environmental.reportIdentifier = Long.toString(new Date().getTime());
		environmental.reportSender = ReportSender.create();
		environmental.reportPosition = environmental.reportSender.personPosition;
		environmental.reportTimeStamp = Report.sdf.format(date);
		Integer[] responseChoices = Utils.randomChoices(EnvironmentalFormResponse.MAX_RESP);
		// int resps = new Random().nextInt(FireFormResponse.MAX_RESP);
		if (responseChoices.length > 0) {
			environmental.formResponse = new FormResponse[responseChoices.length];
			for (int i = 0; i < responseChoices.length; i++) {
				environmental.formResponse[i] = EnvironmentalFormResponse.create(responseChoices[i], date);
			}
		} else
			environmental.formResponse = new EnvironmentalFormResponse[] {};
		return environmental;

	}
}
