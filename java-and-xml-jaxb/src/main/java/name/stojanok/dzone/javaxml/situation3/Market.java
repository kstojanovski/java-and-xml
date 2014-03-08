package name.stojanok.dzone.javaxml.situation3;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Market {

	private List<Fruit> fruits;

	@XmlElementWrapper(name = "fruits")
	@XmlElement(name = "fruit")
	public List<Fruit> getFruits() {
		return fruits;
	}

	public void setFruits(List<Fruit> fruits) {
		this.fruits = fruits;
	}
}
