/*
 * Entropy.java
 * 
 * Juan Recinos                                               jrecinos@rollins.edu
 * CMS495.H1X Spring 2015
 * Dr. Anderson
 * 26 March 2015
 */
public class Entropy {

	double entropy;

	public Entropy() {

	}// end default constructor

	public double calculateEntropy(Examples data) {
		int waits = data.countWillWait();
		int size = data.count();
		double portionThatWaits;
		if (size != 0) {
			portionThatWaits = (double) waits / size;
		} else {
			portionThatWaits = 0;
		}

		return calculateB(portionThatWaits);
	}// end calculateEntropy()

	public double calculateAvgerageEntropy(Examples data, int parentTotal) {
		int waits = data.countWillWait();
		int size = data.count();
		double portionThatWaits;
		double parentChildRatio;
		if (size != 0 && parentTotal != 0) {
			portionThatWaits = (double) waits / size;
			parentChildRatio = (double) size / parentTotal;
		} else {
			portionThatWaits = 0;
			parentChildRatio = 0;
		}

		return calculateAvgerage(portionThatWaits, parentChildRatio);
	}// end calculateAverageEntropy()

	public double calculateB(double x) {
		double b;

		if (x == 0 || x == 1) {
			b = 0;
		} else {
			b = x * (Math.log(x) / Math.log(2)) + (1 - x)
					* (Math.log(1 - x) / Math.log(2));
			b = -1 * b;
		}
		return b;
	}

	public double calculateAvgerage(double x, double y) {
		double avgEntropy;
		avgEntropy = y * calculateB(x);
		return avgEntropy;
	}// end calculateAverage()
}// end class
