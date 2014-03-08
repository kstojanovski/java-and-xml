package name.stojanok.dzone.javaxml.situation1;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class AdditionalInformation {

	private boolean drivingLicense;
	private ArrayList<String> hobbies = new ArrayList<String>();

	public boolean isDrivingLicense() {
		return drivingLicense;
	}

	public void setDrivingLicense(boolean drivingLicense) {
		this.drivingLicense = drivingLicense;
	}

	@XmlElementWrapper(name = "hobbies")
	@XmlElement(name = "hobby")
	public ArrayList<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(ArrayList<String> hobbies) {
		this.hobbies = hobbies;
	}
}
