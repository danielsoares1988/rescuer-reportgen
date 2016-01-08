import java.util.Random;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

	public static void main(String[] args) throws JsonProcessingException {

		Report r = null;
		for (int i = 0; i < 100; i++) {
			int j = new Random().nextInt(4);
			switch (j) {
			case 0:
				r = Fire.create();
				break;
			case 1:
				r = Gas.create();
				break;
			case 2:
				r = Explosion.create();
				break;
			case 3:
				r = Environmental.create();
				break;
			}
			System.out.println(
					new ObjectMapper().setSerializationInclusion(Include.NON_NULL).writeValueAsString(r) + ",");
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
