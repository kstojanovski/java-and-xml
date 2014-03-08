package name.stojanok.dzone.javaxml.situation3;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({Apple.class, Pear.class})
public class Fruit {

	private String color;
	private String type;
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
