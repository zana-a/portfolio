import java.util.ArrayList;
import java.util.List;


public class DataAnalysis<E extends Measurable> {

	private List<E> objects;

	public DataAnalysis() {
		objects = new ArrayList<>();
	}

	public void addMeasurable(E m) {
		objects.add(m);
	}

	public int sum() {
		return objects.stream().mapToInt(m -> m.getMeasure()).sum();
	}
	
	public String toString() {
		return "DataAnalysis[objects= " + objects + "]";
	}
}
