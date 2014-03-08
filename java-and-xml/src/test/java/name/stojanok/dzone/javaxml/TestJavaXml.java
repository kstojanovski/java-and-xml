package name.stojanok.dzone.javaxml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class TestJavaXml {

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
			inputStream = new FileInputStream(new File("C:\\Users\\KOCTA2010\\workspace_dzone\\java-and-xml\\src\\test\\resources\\xml\\test.xml"));
			inputStream2 = new FileInputStream(new File("C:\\Users\\KOCTA2010\\workspace_dzone\\java-and-xml\\src\\test\\resources\\xml\\test.xsl"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
