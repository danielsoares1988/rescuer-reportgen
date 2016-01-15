import java.text.ParseException;
import java.util.Date;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

	public static void main(String[] args) throws JsonProcessingException {

		Report r = null;
		for (int i = 0; i < 2; i++) {
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
			System.out.println(new ObjectMapper().setSerializationInclusion(Include.NON_NULL).writeValueAsString(r) + ",");		
			
			String updatedTimestamp = "";
			try {
				updatedTimestamp = Report.sdf.format(new Date((Report.sdf.parse(r.reportTimeStamp).getTime()+1000)));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}					
			r.reportTimeStamp = updatedTimestamp;			
			System.out.println(new ObjectMapper().setSerializationInclusion(Include.NON_NULL).writeValueAsString(r) + ",");
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
