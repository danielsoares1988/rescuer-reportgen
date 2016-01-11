import java.util.Date;
import java.util.Random;

public class Gas extends Report {

	public Gas(){
		keywordIdentifier = "rType3";
	}
	
	public static class GasFormResponse extends FormResponse {
		public static final int MAX_RESP = 4;
		private static FormAnswer itchType[] = new FormAnswer[] { new FormAnswer(0, "Olhos"), new FormAnswer(1, "Pele"), new FormAnswer(2,"Vias Respiratórias"),
				new FormAnswer(3, "Mucosa")};
		private static FormAnswer smells[] = new FormAnswer[] { new FormAnswer(0,"Peixe"), new FormAnswer(1,"Pungente"), new FormAnswer(2, "Cloro"),
				new FormAnswer(3,"Outros") };
		private static FormAnswer cloud[] = new FormAnswer[] { new FormAnswer(0, "Cinza"),new FormAnswer(1, "Vermelha"),
				new FormAnswer(2, "Amarela"), new FormAnswer(3, "Nenhuma") };

		public static GasFormResponse create(int id, Date date) {
			GasFormResponse resp = new GasFormResponse();
			id = id == -1 ? new Random().nextInt(4) : id;
			switch (id) {
			case 0:
				resp.identifier = "4";
				resp.name = "Você está sentindo alguma irritação?";
				resp.type = "text";
				resp.multiAnswer = Utils.choicesFrom(FormAnswer.class, itchType, new Random().nextInt(itchType.length) + 1);
				break;
			case 1:
				resp.identifier = "5";
				resp.name = "Você está sentindo algum cheiro no ar?";
				resp.type = "text";
				resp.singleAnswer = smells[new Random().nextInt(4)];
				break;
			case 2:
				resp.identifier = "6";
				resp.name = "Há uma nuvem colorida?";
				resp.type = "text";
				resp.singleAnswer = cloud[new Random().nextInt(4)];
				break;
			case 3:
				resp.identifier = "3";
				resp.name = "Você vê pessoas feridas?";
				resp.type = "integ";
				resp.singleAnswer = injuredZones[new Random().nextInt(injuredZones.length)];
				break;
			}
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
