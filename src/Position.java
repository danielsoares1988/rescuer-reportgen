import java.util.Random;

public class Position {

	/*-12.922412, -38.388985  12.922412, -38.387543
	-12.923432, -38.388954*/
	public float latitude;
	public float longitude;
	public float altitude;

	public static Position create() {
		Position pos = new Position();
		// pos.latitude = -13.003589f + ( -12.998864f - -13.003589f) * new
		// Random().nextFloat();
		// pos.longitude = -38.512623f + (-38.505499f - -38.512623f) * new
		// Random().nextFloat();		
		pos.latitude = Utils.nextFloatBetween(-12.923432f, -12.922412f);// -12.923432f + (-12.922412f - -12.923432f) * new Random().nextFloat();
		pos.longitude = Utils.nextFloatBetween(-38.388985f , -38.387543f);//-38.388985f + (-38.387543f - -38.388985f) * new Random().nextFloat();
		return pos;
	}
}
