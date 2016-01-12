import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {

	public static <T> T[] choicesFrom(Class<T> clazz, T[] toChoose, int quantity) {
		T[] choices = (T[]) Array.newInstance(clazz, quantity);

		for (int i = 0; i < quantity; i++) {
			T value = toChoose[new Random().nextInt(toChoose.length)];
			while (hasValue(choices, value))
				value = toChoose[new Random().nextInt(toChoose.length)];
			choices[i] = value;
		}
		return choices;
	}

	public static Integer[] randomChoices(int possibilities) {
		List<Integer> l = new ArrayList<>();
		int quantity = new Random().nextInt(possibilities);
		for (int i = 0; i < quantity; i++) {
			Integer choice = new Random().nextInt(possibilities);
			while (l.contains(choice))
				choice = new Random().nextInt(quantity);
			l.add(choice);
		}
		return l.toArray(new Integer[0]);
	}

	private static <T> boolean hasValue(T[] choices, T value) {
		for (T t : choices)
			if (t == value)
				return true;
		return false;
	}

	public static float nextFloatBetween(float min, float max) {
		if (min < max)
			return min + (max - min) * new Random().nextFloat();
		else
			throw new IllegalArgumentException();
	}
}
