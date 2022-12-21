package dataLayer;

// 1 navrhovy vzor - I identity
public interface Identifiable {
	public int getId();

	public String toString();

	public default void print() {
		if (additional.Constants.printInfo == true) {			
			System.out.println(toString());
		}
	}

	public String toStringAll();

	public default void printAll() {
		if (additional.Constants.printInfo == true) {			
			System.out.println(toStringAll());
		}
	}
}
