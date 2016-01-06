import java.util.Date;
import java.util.Random;

public class Fire extends Report {

	public static class FireFormResponse extends FormResponse {

		public final static int MAX_RESP = 4;

		private static String size[] = { "Grande", "Médio", "Pequeno" };
		private static String flamesOrSmoke[] = { "Just Smoke", "Flames and Smoke" };
		private static String colourOfTheSmoke[] = { "Light Grey", "Dark Grey", "Other" };

		public static FireFormResponse create(int id, Date date) {
			FireFormResponse resp = new FireFormResponse();
			id = id == -1 ? new Random().nextInt(4) : id;
			switch (id) {
			case 0:
				resp.name = "Qual o tamanho do fogo?";
				resp.type = "text";
				resp.value = size[new Random().nextInt(3)];
				break;
			case 1:
				resp.name = "Você vê chamas ou fumaça?";
				resp.type = "text";
				resp.value = flamesOrSmoke[new Random().nextInt(2)];
				break;
			case 2:
				resp.name = "Qual a cor da fumaça?";
				resp.type = "text";
				resp.value = colourOfTheSmoke[new Random().nextInt(3)];
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

	public static Fire create() {
		Fire fire = new Fire();
		Date date = new Date();

		// TODO: refactor this if at all possible, can static methods be overriden or overrides?
		fire.generateMedia(); //also this call is kinda ugly
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
