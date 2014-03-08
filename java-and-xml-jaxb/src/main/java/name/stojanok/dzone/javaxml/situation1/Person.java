package name.stojanok.dzone.javaxml.situation1;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(namespace = "name.stojanok.dzone")
@XmlType(propOrder = { "id", "firstname", "lastname", "additionalInformation",
		"extraInformation" })
public class Person {

	private String id;
	private String firstname;
	private String lastname;
	private AdditionalInformation additionalInformation;
	private ExtraInformation extraInformation;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlElement(name = "addInfo")
	public AdditionalInformation getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(
			AdditionalInformation additionalInformations) {
		this.additionalInformation = additionalInformations;
	}

	public ExtraInformation getExtraInformation() {
		return extraInformation;
	}

	public void setExtraInformation(ExtraInformation extraInformation) {
		this.extraInformation = extraInformation;
	}

}
