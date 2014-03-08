package name.stojanok.dzone.javaxml;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import name.stojanok.dzone.javaxml.situation1.AdditionalInformation;
import name.stojanok.dzone.javaxml.situation1.ExtraInformation;
import name.stojanok.dzone.javaxml.situation1.Person;
import name.stojanok.dzone.javaxml.situation2.Key;
import name.stojanok.dzone.javaxml.situation2.OtherPerson;
import name.stojanok.dzone.javaxml.situation2.Value;
import name.stojanok.dzone.javaxml.situation3.Apple;
import name.stojanok.dzone.javaxml.situation3.Fruit;
import name.stojanok.dzone.javaxml.situation3.Market;
import name.stojanok.dzone.javaxml.situation3.Pear;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class TestJaxb {

	private static final String PATH_SITUATION_ONE = "src\\test\\resources\\situation1.xml";
	private static final String PATH_SITUATION_TWO = "src\\test\\resources\\situation2.xml";
	private static final String PATH_SITUATION_THREE = "src\\test\\resources\\situation3.xml";

	Logger LOGGER = Logger.getLogger(TestJaxb.class);

	private JaxbClient jaxbClient = new JaxbClient();
	private File file1, file2, file3;

	@Before
	public void init() {
		jaxbClient = new JaxbClient();
		file1 = new File(PATH_SITUATION_ONE);
		file2 = new File(PATH_SITUATION_TWO);
		file3 = new File(PATH_SITUATION_THREE);
	}

	@Test
	public void testMarshallerSituation1() {
		Person person = new Person();
		person.setFirstname("John");
		person.setLastname("Doe");
		person.setId("123");
		AdditionalInformation additionalInformations = new AdditionalInformation();
		ArrayList<String> hobbies = new ArrayList<String>(Arrays.asList(
				"fishing", "techblog"));
		additionalInformations.setHobbies(hobbies);
		person.setAdditionalInformation(additionalInformations);
		ExtraInformation extraInformation = new ExtraInformation("987", "type",
				"info");
		person.setExtraInformation(extraInformation);
		jaxbClient.marshaller(person, file1);
	}

	@Test
	public void testUnmarshallerSituation1() {
		Object object = jaxbClient.unmarshaller(file1);
		if (object instanceof Person) {
			LOGGER.trace("id: " + ((Person) object).getId());
			LOGGER.trace("firstname: " + ((Person) object).getFirstname());
			LOGGER.trace("lastname: " + ((Person) object).getLastname());
		}
	}
	
	@Test
	public void testMarshallerSituation2() {
		OtherPerson otherPerson = new OtherPerson();
		Map<String, String> map = new HashMap<String, String>();
		map.put("prperty0", "value0");
		map.put("prperty1", "value1");
		otherPerson.setMap0(map);
		otherPerson.setMap1(map);
		
		Map<Key, Value> map0 = new HashMap<Key, Value>();
		Key key = new Key(); Value value = new Value();
		key.setKey("key0"); value.setValue("value0");
		map0.put(key, value);
		Key key0 = new Key(); Value value0 = new Value();
		key0.setKey("key1"); value0.setValue("value2");
		map0.put(key0, value0);
		otherPerson.setMap3(map0);
		otherPerson.setCdata("cdata conent <html></html>");
		otherPerson.setSomeData("a & b");
		jaxbClient.marshaller(otherPerson, file2);
	}
	
	@Test
	public void testUnmarshallerSituation2() {
		Object object = jaxbClient.unmarshaller(file2);
		if (object instanceof OtherPerson) {
			OtherPerson otherPerson = (OtherPerson)object;
			for (Entry<String, String> entry : otherPerson.getMap0().entrySet()) {
				LOGGER.trace(entry);
			}
			for (Entry<String, String> entry : otherPerson.getMap1().entrySet()) {
				LOGGER.trace(entry);
			}
			LOGGER.trace(otherPerson.getCdata());
		}
	}

	@Test
	public void testMarshallerSituation3() {
		Market market = new Market();
		List<Fruit> fruits = new ArrayList<Fruit>();
		Apple apple = new Apple(); apple.setColor("red"); apple.setType("type1"); apple.setAppleProperty("some");
		fruits.add(apple);
		Pear pear = new Pear(); pear.setColor("yellow"); pear.setType("type2"); pear.setPearProperty("some");
		fruits.add(pear);
		market.setFruits(fruits);
		
		jaxbClient.marshaller(market, file3);		
	}
	
	@Test
	public void testUnmarshallerSituation3() {
		Object object = jaxbClient.unmarshaller(file3);
		if (object instanceof Market) {
			Market market = ((Market)object);
			for (Fruit fruit : market.getFruits()) {
				if (fruit instanceof Apple) {
					LOGGER.trace("apple: " + fruit.getColor());
				} else if (fruit instanceof Pear) {
					LOGGER.trace("pear: " + fruit.getColor());
				}				
			}
		}		
	}
}
