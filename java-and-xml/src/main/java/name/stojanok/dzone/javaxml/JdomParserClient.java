package name.stojanok.dzone.javaxml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.transform.JDOMResult;
import org.jdom2.transform.JDOMSource;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;


public class JdomParserClient {
		
	static Logger LOGGER = Logger.getLogger(JdomParserClient.class);
	
	public static void use(InputStream is, InputStream inputStream2) {
		SAXBuilder saxBuilder = new SAXBuilder();
		try {
			Document document = saxBuilder.build(is);
			Element root = document.getRootElement();
			
			LOGGER.trace(root);
			// situation 1
//			searching for the tag-Node
			for (Element node : root.getChildren()) {
				if (node.getName().equals("tag")) {
					LOGGER.trace(node.getName());
				}
			}
//			iterating for the tag-Node			
			for (Element node : root.getChildren("tag")) {
				LOGGER.trace(node.getName());
			}
//			searching for the other-Node with attribute type		
			for (Element node : root.getChildren("other")) {
				if (node.getAttribute("type") != null) {
					LOGGER.trace(node.getAttribute("type").getValue());
				}
			}			

//			iterating over all nodes			
			for (Content content : root.getDescendants()) {
				LOGGER.trace(content);
			}
			
			// situation 2
			// namespaces
			Namespace nsMy = root.getNamespace("my"); 
			LOGGER.trace("nsMy: " + nsMy.getPrefix() + " | " + nsMy.getURI());
			LOGGER.trace("other: " + root.getChild("other", nsMy).getTextTrim());
			
			for (Element node : root.getChildren("other", nsMy)) {
				if (node.getAttribute("type", nsMy) != null) {
					LOGGER.trace(node.getAttribute("type", nsMy).getValue());
				}
			}			
			
			XPathFactory xpathFactory = XPathFactory.instance();
			String titelTextPath = "root/deep/tag/other/text()";
			XPathExpression<Object> expr = xpathFactory.compile(titelTextPath);
			List<Object> xPathSearchedNodes = expr.evaluate(document);
			for (int i = 0; i < xPathSearchedNodes.size(); i++) {
				Content content = (Content) xPathSearchedNodes.get(i);
				System.out.println(content.getValue());
			}
			
			XMLOutputter xmlOutput = new XMLOutputter();
			// output to the System.out
//			xmlOutput.output(document, System.out);
			xmlOutput.output(document, new FileOutputStream(new File("d:\\test_jdom.xml")));
			
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer(new StreamSource(inputStream2));
			JDOMSource jdomSource = new JDOMSource(document);
			JDOMResult jdomResult = new JDOMResult();
			transformer.transform(jdomSource, jdomResult);
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(jdomResult.getResult(), new FileOutputStream(new File("d:\\test_jdom.xml")));
			
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
