import java.util.Date;
import java.util.Random;

public class Fire extends Report {

	public static class FireFormResponse extends FormResponse {

		public final static int MAX_RESP = 4;

		private static FormAnswer[] size = new FormAnswer[] { new FormAnswer(0, "Grande"), new FormAnswer(1, "Médio"),
				new FormAnswer(2, "Pequeno") };
		private static FormAnswer[] flamesOrSmoke = new FormAnswer[] { new FormAnswer(0, "Só Fumaça"),
				new FormAnswer(1, "Chamas e Fumaça") };
		private static FormAnswer[] colourOfTheSmoke = new FormAnswer[] { new FormAnswer(0, "Cinza Claro"),
				new FormAnswer(1, "Cinza Escuro"), new FormAnswer(2, "Outros") };

		public static FireFormResponse create(int id, Date date) {
			FireFormResponse resp = new FireFormResponse();
			id = id == -1 ? new Random().nextInt(4) : id;
			switch (id) {
			case 0:
				resp.identifier = "0";
				resp.name = "Qual o tamanho do fogo?";
				resp.type = "text";
				resp.singleAnswer = size[new Random().nextInt(3)];
				break;
			case 1:
				resp.identifier = "1";
				resp.name = "Você vê chamas ou fumaça?";
				resp.type = "text";
				resp.singleAnswer = flamesOrSmoke[new Random().nextInt(2)];
				break;
			case 2:
				resp.identifier = "2";

				resp.name = "Qual a cor da fumaça?";
				resp.type = "text";
				resp.singleAnswer = colourOfTheSmoke[new Random().nextInt(3)];
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

	public Fire() {
		keywordIdentifier = "rType1";
	}

	public static Report create() {
		Report fire = new Fire();
		Date date = new Date();

		// TODO: refactor this if at all possible, can static methods be
		// overriden or overrides?
		fire.generateMedia(); // also this call is kinda ugly
		fire.keyword = "Fogo";
		fire.reportIdentifier = Long.toString(new Date().getTime());

		fire.reportSender = ReportSender.create();
		fire.reportPosition = fire.reportSender.personPosition;
		fire.reportTimeStamp = Report.sdf.format(date);
		Integer[] responseChoices = Utils.randomChoices(FireFormResponse.MAX_RESP);
		// int resps = new Random().nextInt(FireFormResponse.MAX_RESP);
		if (responseChoices.length > 0) {
			fire.formResponse = new FormResponse[responseChoices.length];
			for (int i = 0; i < responseChoices.length; i++) {
				fire.formResponse[i] = FireFormResponse.create(responseChoices[i], date);
			}
		} else
			fire.formResponse = new FireFormResponse[] {};
		return fire;

	}

}
