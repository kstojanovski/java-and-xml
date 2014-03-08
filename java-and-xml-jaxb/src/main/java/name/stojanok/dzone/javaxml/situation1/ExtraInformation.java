package name.stojanok.dzone.javaxml.situation1;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class ExtraInformation {

	private String id;
	private String name;
	private String value;

	// for JAXB
	public ExtraInformation() {
		super();
	}

	public ExtraInformation(String id, String name, String value) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
	}

	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlValue
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
