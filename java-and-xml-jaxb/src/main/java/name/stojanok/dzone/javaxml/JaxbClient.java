package name.stojanok.dzone.javaxml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import name.stojanok.dzone.javaxml.situation1.Person;
import name.stojanok.dzone.javaxml.situation2.CDataXMLStreamWriter;
import name.stojanok.dzone.javaxml.situation2.Key;
import name.stojanok.dzone.javaxml.situation2.OtherPerson;
import name.stojanok.dzone.javaxml.situation2.Value;
import name.stojanok.dzone.javaxml.situation3.Market;

import com.sun.xml.txw2.output.IndentingXMLStreamWriter;

public class JaxbClient {

	public void marshaller(Object object, File file) {
		try {
			JAXBContext jc = JAXBContext.newInstance(Person.class,
					Market.class, OtherPerson.class, Key.class, Value.class);
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			//handling CDATA - is described in adapter section
			XMLOutputFactory xof = XMLOutputFactory.newInstance();
			XMLStreamWriter streamWriter = new CDataXMLStreamWriter(
					new IndentingXMLStreamWriter(
							xof.createXMLStreamWriter(new FileWriter(file))));
			
			m.marshal(object, streamWriter);			
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Object unmarshaller(File file) {
		Object outputObject = new Object();
		try {
			JAXBContext jc = JAXBContext.newInstance(Person.class,
					Market.class, OtherPerson.class, Key.class, Value.class);
			Unmarshaller u = jc.createUnmarshaller();
			outputObject = u.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return outputObject;
	}
}
