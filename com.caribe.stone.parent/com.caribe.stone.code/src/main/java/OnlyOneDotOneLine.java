import java.util.List;

/**
 * 一行代码只有一个.运算符
 * 
 * @author bsnpbag
 * 
 */
public class OnlyOneDotOneLine {
	class Price {
		String representation;

		void addTo(StringBuffer buffer) {
			buffer.append(character());
		}

		private String character() {
			return representation.substring(0, 1);
		}
	}

	class Location {
		Price current;
	}

	private String boardRepresentationBad() {
		StringBuffer buffer = new StringBuffer();
		for (Location location : squares()) {
			//很多"."
			location.current.representation.substring(0, 1);
		}
		return buffer.toString();
	}

	private String boardRepresentation() {
		StringBuffer buffer = new StringBuffer();
		for (Location location : squares()) {
			Price price = location.current;
			price.addTo(buffer);
		}
		return buffer.toString();
	}

	private List<Location> squares() {
		return null;
	}
}
