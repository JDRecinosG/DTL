/*
 * Node.java
 * 
 * Juan Recinos                                               jrecinos@rollins.edu
 * CMS495.H1X Spring 2015
 * Dr. Anderson
 * 26 March 2015
 */
import java.util.ArrayList;

public class Node {
	private Node parent;
	private Node[] children;
	private Examples data;
	private double entropy;
	private boolean isUsed;
	private boolean isLeaf;
	private String daValue;
	private String determAttr;
	private boolean isHeadRoot;

	private ArrayList<String> value = new ArrayList<String>();
	private ArrayList<String> determineAttribute = new ArrayList<String>();

	public Node() {
		this.data = new Examples();
		setHeadRoot(false);
		setLeaf(false);
		setEntropy(0.0);
		setParent(null);
		setChildren(null);
		setUsed(false);
		addValue("");
		addDetermineAttribute("Target");
	}

	public void setLeaf(boolean leaf) {
		this.isLeaf = leaf;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void addValue(String val) {
		this.value.add(val);
	}

	public void setValueList(ArrayList<String> val) {
		this.value = val;
	}

	public ArrayList<String> getValueList() {
		return value;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Node getParent() {
		return parent;
	}

	public void setData(Examples attributes) {
		this.data = attributes;
	}

	public Examples getData() {
		return data;
	}

	public void setEntropy(double entropy) {
		this.entropy = entropy;
	}

	public double getEntropy() {
		return entropy;
	}

	public void setChildren(Node[] children) {
		this.children = children;
	}

	public Node[] getChildren() {
		return children;
	}

	public void setHeadRoot(boolean isHead) {
		this.isHeadRoot = isHead;
	}

	public boolean getHeadRoot() {
		return isHeadRoot;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setDetermineAttributeList(ArrayList<String> determineAttr) {
		this.determineAttribute = determineAttr;
	}

	public void addDetermineAttribute(String determineAttribute) {
		this.determineAttribute.add(determineAttribute);
	}

	public ArrayList<String> getDetermineAttribute() {
		return determineAttribute;
	}

	public void setValue(String val) {
		this.daValue = val;
	}

	public String getValue() {
		return daValue;
	}

	public void setAttr(String attr) {
		this.determAttr = attr;
	}

	public String getAttr() {
		return determAttr;
	}
}
