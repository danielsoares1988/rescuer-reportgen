import java.util.Date;
import java.util.Random;

public class Environmental extends Report {

	public Environmental() {
		keywordIdentifier = "rType4";
	}

	public static class EnvironmentalFormResponse extends FormResponse {

		public final static int MAX_RESP = 4;

		private static FormAnswer itchType[] = { new FormAnswer(0, "Olhos"), new FormAnswer(1, "Pele"),
				new FormAnswer(2, "Vias Respiratórias"), new FormAnswer(3, "Mucosa") };
		private static FormAnswer damagesTo[] = { new FormAnswer(0, "Pessoas"), new FormAnswer(1, "Plantas"),
				new FormAnswer(2, "Animais"), new FormAnswer(3, "Nenhum") };
		private static FormAnswer fluids[] = { new FormAnswer(0, "Substância visível"),
				new FormAnswer(1, "Nenhuma visível") };

		public static EnvironmentalFormResponse create(int id, Date date) {
			EnvironmentalFormResponse resp = new EnvironmentalFormResponse();
			id = id == -1 ? new Random().nextInt(4) : id;
			switch (id) {
			case 0:
				resp.identifier = "4";
				resp.name = "Você está sentindo alguma irritação?";
				resp.type = "text";
				resp.multiAnswer = Utils.choicesFrom(FormAnswer.class, itchType,
						new Random().nextInt(itchType.length) + 1);
				break;
			case 1:
				resp.identifier = "10";
				resp.name = "Há algum dano?";
				resp.type = "text";
				resp.multiAnswer = Utils.choicesFrom(FormAnswer.class, damagesTo, new Random().nextInt(4) + 1);
				break;
			case 2:
				resp.identifier = "11";
				resp.name = "Há algum fluído colorido na água ou no chão?";
				resp.type = "text";
				resp.singleAnswer = fluids[new Random().nextInt(2)];
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

	public static Environmental create() {
		Environmental environmental = new Environmental();
		Date date = new Date();

		environmental.generateMedia();

		environmental.keyword = "Ambiental";
		environmental.reportIdentifier = Long.toString(new Date().getTime() + (new Random().nextInt(1000) - 500));
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
