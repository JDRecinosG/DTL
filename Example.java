/*
 * Example.java
 * 
 * Juan Recinos                                               jrecinos@rollins.edu
 * CMS495.H1X Spring 2015
 * Dr. Anderson
 * 26 March 2015
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Example {
	private String exampleValue;
	private Map<String, String> values;
	private boolean waits;

	public Example(String exampleNum, String[] attributeNames,
			String[] attributeValues, boolean willWait) {
		exampleValue = exampleNum;
		assert (attributeNames.length == attributeValues.length);
		values = new HashMap<String, String>();
		for (int i = 0; i < attributeNames.length; i++) {
			values.put(attributeNames[i], attributeValues[i]);
		}
		this.waits = willWait;
	}

	public Example(String exampleNum, Map<String, String> attributes,
			boolean willWait) {
		this.exampleValue = exampleNum;
		this.waits = willWait;
		this.values = attributes;
	}

	public String getExampleNum() {
		return exampleValue;
	}

	public Set<String> getAttributes() {
		return values.keySet();
	}

	public String getAttributeValue(String attribute) {
		return values.get(attribute);
	}

	public boolean matchesWaits(boolean willWait) {
		return willWait == this.waits;
	}

	public boolean waits() {
		return waits;
	}
}