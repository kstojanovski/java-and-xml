package name.stojanok.dzone.javaxml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class TestJavaXml {

	private static final Object SEPARATOR = File.separator;

	Logger LOGGER = Logger.getLogger(TestJavaXml.class);
	
	InputStream inputStream = null;
	InputStream inputStream2 = null;
	
	/**
	 * parsing
	 * processing - getChild, getChildren, find attributes, iterate trough all nodes
	 * name spaces
	 * 
	 * XPath
	 * writing back
	 * 
	 */
	@Before
	public void init() {
		try {
			inputStream = new FileInputStream(new File(getPathToXmlResource().concat("test.xml")));
			inputStream2 = new FileInputStream(new File(getPathToXmlResource().concat("test.xsl")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String getPathToXmlResource() {
		return new StringBuilder().append("src").append(SEPARATOR)
				.append("test").append(SEPARATOR).append("resources")
				.append(SEPARATOR).append("xml").append(SEPARATOR).toString();
	}
	
	@Test
	public void testDom() {
		LOGGER.trace("TestJavaXml.testDom()");
		DomParserClient.use(inputStream, inputStream2);
	}
	
	@Test
	public void testJdom() {
		LOGGER.trace("TestJavaXml.testJdom()");
		JdomParserClient.use(inputStream, inputStream2);
	}
}
