/*
 * Examples.java
 * 
 * Juan Recinos                                               jrecinos@rollins.edu
 * CMS495.H1X Spring 2015
 * Dr. Anderson
 * 26 March 2015
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Used to create Example objects for each line of the csv.
 */
public class Examples {

	private List<Example> examples;
	private HashSet<String> attributeOptions = new HashSet<String>();

	public Examples() {
		examples = new ArrayList<Example>();
	}

	public Examples subset(Examples data, int start, int end) {
		Examples subset = new Examples();
		for (int i = start; i < end; i++) {
			subset.add(examples.get(i));
		}
		return subset;
	}

	public Examples subset(Examples example, String attr, String val) {
		Examples subset = new Examples();
		for (int i = 0; i < example.count(); i++) {
			if (example.getExample(i).getAttributeValue(attr)
					.equalsIgnoreCase(val)) {
				subset.add(examples.get(i));
			}
		}
		return subset;
	}

	public Example getExample(int i) {
		return examples.get(i);
	}

	public void add(Example example) {
		examples.add(example);
	}

	public void add(String exampleNum, String[] attributeNames,
			String[] attributeValues, boolean waits) {
		examples.add(new Example(exampleNum, attributeNames, attributeValues,
				waits));
	}

	public void add(String exampleNum, Map<String, String> attributes,
			boolean waits) {
		examples.add(new Example(exampleNum, attributes, waits));
	}

	public HashSet<String> getUniqueAttributes(String attr) {
		attributeOptions = new HashSet<String>();
		for (int i = 0; i < examples.size(); i++) {
			attributeOptions.add(examples.get(i).getAttributeValue(attr));
		}
		return attributeOptions;
	}

	public Set<String> getAllAttributeKeys() {
		return examples.get(0).getAttributes();
	}

	/**
	 * Returns the number of Example where the attribute equals choice
	 * 
	 * @param attribute
	 * @param choice
	 * @return integer
	 */
	int countChoices(String attribute, String choice) {
		int count = 0;
		for (Example e : examples) {
			if (e.getAttributeValue(attribute).equals(choice))
				count++;
		}
		return count;
	}// end countChoices

	int countWillWait() {
		int count = 0;
		for (Example e : examples) {
			if (e.waits())
				count++;
		}
		return count;
	}

	/**
	 * Returns a map of each attribute
	 * 
	 * @return
	 */
	public Map<String, Set<String>> extractChoices() {
		Map<String, Set<String>> choices = new HashMap<String, Set<String>>();
		for (String attribute : getAttributeNames()) {
			choices.put(attribute, getChoices(attribute));
		}
		return choices;
	}// end extractChoices()

	public int countNegative(String attribute, String choice,
			Map<String, String> attributes) {
		return countWillWait(false, attribute, choice, attributes);
	}

	/**
	 * Returns the count of Examples that match the given value for the
	 * attribute specified
	 * 
	 * @param attribute
	 * @param choice
	 * @param attributes
	 * @return integer
	 */
	public int countPositive(String attribute, String choice,
			Map<String, String> attributes) {
		return countWillWait(true, attribute, choice, attributes);
	}// end countPositive()

	public int countNegative(Map<String, String> attributes) {
		return countWillWait(false, attributes);
	}// end countNegative()

	public int countPositive(Map<String, String> attributes) {
		return countWillWait(true, attributes);
	}

	/**
	 * @param attribute
	 * @param choice
	 * @param attributes
	 * @return
	 */
	public int count(String attribute, String choice,
			Map<String, String> attributes) {
		attributes = new HashMap<String, String>(attributes);
		attributes.put(attribute, choice);
		return count(attributes);
	}// end count()

	/**
	 * @param attributes
	 * @return
	 */
	public int count(Map<String, String> attributes) {
		int count = 0;
		nextExample: for (Example e : examples) {
			for (Map.Entry<String, String> attribute : attributes.entrySet())
				if (!(e.getAttributeValue(attribute.getKey()).equals(attribute
						.getValue())))
					continue nextExample;
			// All of the provided attributes match the example.
			count++;
		}
		return count;
	}// end count()

	/**
	 * @param data
	 * @param attribute
	 * @param value
	 * @return
	 */
	public Examples separateData(Examples data, String attribute, String value) {
		Example example;
		Examples exampleList = new Examples();
		for (int i = 0; i < data.count(); i++) {
			example = data.getExample(i);
			if (example.getAttributeValue(attribute).equals(value)) {
				exampleList.add(example);
			}
		}
		return exampleList;
	}// end separateData()

	/**
	 * @param willWait
	 * @param attributes
	 * @return
	 */
	public int countWillWait(boolean willWait, Map<String, String> attributes) {
		int count = 0;
		nextExample: for (Example e : examples) {
			for (Map.Entry<String, String> attribute : attributes.entrySet())
				if (!(e.getAttributeValue(attribute.getKey()).equals(attribute
						.getValue())))
					continue nextExample;
			// All of the provided attributes match the example.
			// If the example matches the willWait, then include it in the
			// count.
			if (e.matchesWaits(willWait))
				count++;
		}
		return count;
	}// end countWillWait()

	/**
	 * @param willWait
	 * @param attribute
	 * @param choice
	 * @param attributes
	 * @return
	 */
	public int countWillWait(boolean willWait, String attribute, String choice,
			Map<String, String> attributes) {
		attributes = new HashMap<String, String>(attributes);
		attributes.put(attribute, choice);
		return countWillWait(willWait, attributes);
	}// end countWillWait()

	/**
	 * Returns the number of Examples
	 * 
	 * @return integer
	 */
	public int count() {
		return examples.size();
	}

	/*
	 * Returns a set of attribute names used in the examples.
	 */
	/**
	 * Returns a set of attribute names that are used in the Examples
	 * 
	 * @return
	 */
	public Set<String> getAttributeNames() {
		Set<String> attributes = new HashSet<String>();
		for (Example e : examples) {
			attributes.addAll(e.getAttributes());
		}
		return attributes;
	}// end getAttributeNames()

	/**
	 * Returns a set of attribute choices
	 * 
	 * @param attribute
	 * @return
	 */
	private Set<String> getChoices(String attribute) {
		Set<String> choices = new HashSet<String>();
		for (Example e : examples) {
			choices.add(e.getAttributeValue(attribute));
		}
		return choices;
	}// end getChoices()
}// end class
