import java.util.Date;
import java.util.Random;

public class Gas extends Report {

	public static class GasFormResponse extends FormResponse {
		public static final int MAX_RESP = 4;
		private static String itchType[] = new String[] { "Olhos", "Pele", "Vias Respiratórias", "Mucosa" };
		private static String smells[] = new String[] { "Peixe", "Pungente", "Cloro", "Outros" };
		private static String cloud[] = new String[] { "Cinza", "Vermelha", "Amarela", "Nenhuma" };

		public static GasFormResponse create(int id, Date date) {
			GasFormResponse resp = new GasFormResponse();
			id = id == -1 ? new Random().nextInt(4) : id;
			switch (id) {
			case 0:
				resp.name = "Você está sentindo alguma irritação?";
				resp.type = "multichoice";
				resp.value = Utils.choicesFrom(String.class, itchType, new Random().nextInt(itchType.length) + 1);
				break;
			case 1:
				resp.name = "Você está sentindo algum cheiro no ar?";
				resp.type = "text";
				resp.value = smells[new Random().nextInt(4)];
				break;
			case 2:
				resp.name = "Há uma nuvem colorida?";
				resp.type = "text";
				resp.value = cloud[new Random().nextInt(4)];
				break;
			case 3:
				resp.name = "Você vê pessoas feridas?";
				resp.type = "text";
				resp.value = Integer.toString(injuredZones[new Random().nextInt(injuredZones.length)]);
				break;
			}
			resp.identifier = Integer.toString(id);
			resp.timestamp = Report.sdf.format(date == null ? new Date() : date);
			return resp;
		}
	}

	public static Gas create() {
		Gas gas = new Gas();
		Date date = new Date();

		gas.generateMedia();

		gas.keyword = "Gas";
		gas.reportIdentifier = Long.toString(new Date().getTime() + (new Random().nextInt(1000) - 500));
		gas.reportSender = ReportSender.create();
		gas.reportPosition = gas.reportSender.personPosition;
		gas.reportTimeStamp = Report.sdf.format(date);
		Integer[] responseChoices = Utils.randomChoices(GasFormResponse.MAX_RESP);
		// int resps = new Random().nextInt(FireFormResponse.MAX_RESP);
		if (responseChoices.length > 0) {
			gas.formResponse = new FormResponse[responseChoices.length];
			for (int i = 0; i < responseChoices.length; i++) {
				gas.formResponse[i] = GasFormResponse.create(responseChoices[i], date);
			}
		} else
			gas.formResponse = new GasFormResponse[] {};
		return gas;

	}

}
