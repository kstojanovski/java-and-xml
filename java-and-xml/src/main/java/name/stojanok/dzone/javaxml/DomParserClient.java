package name.stojanok.dzone.javaxml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.jdom2.transform.JDOMResult;
import org.jdom2.transform.JDOMSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class DomParserClient {

	private static final String namespaceURI = "http://www.stojanok.name/2013/javaxml";
	static Logger LOGGER = Logger.getLogger(DomParserClient.class);
	
	public static void use(InputStream is, InputStream inputStream2) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// namespace !!!
		factory.setNamespaceAware(true);
		LOGGER.trace(factory.isNamespaceAware());
		try {
			DocumentBuilder docBuilder = factory.newDocumentBuilder();
			Document document = docBuilder.parse(is);
			Element root = document.getDocumentElement();
			LOGGER.trace(root);
			// situation 1 - iterating through child nodes
//			searching for the tag-Node
			NodeList nodeList = root.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				if (nodeList.item(i).getNodeName().equals("tag")) {
					LOGGER.trace(nodeList.item(i).getNodeName());
				}
			}	
//			searching for the other-Node with attribute type - version 1			
			for (int i = 0; i < nodeList.getLength(); i++) {
				if (nodeList.item(i).getNodeName().equals("other")) {
					NamedNodeMap attributes = nodeList.item(i).getAttributes();
					for (int j= 0; j < attributes.getLength(); j++) {
						if ("type".equals(attributes.item(j).getNodeName())) {
							LOGGER.trace("version 1: " + attributes.item(j).getTextContent());
						}
					}
				}
			}
//			searching for the other-Node with attribute type - version 2			
			for (int i = 0; i < nodeList.getLength(); i++) {
				if (nodeList.item(i).getNodeName().equals("other")) {
					if (nodeList.item(i).getAttributes().getNamedItem("type") != null) {
						LOGGER.trace("version 2: " + nodeList.item(i).getAttributes().getNamedItem("type").getTextContent());
					}
				}
			}			
			
			// any dept level
			NodeList nodeListSomeNode = root.getElementsByTagName("tag");
			for (int i = 0; i < nodeListSomeNode.getLength(); i++) {
				LOGGER.trace("some node: " + nodeListSomeNode.item(i).getNodeName());
			}
			
			// situation 2
//			namespaces
			NodeList nodeListNS = root.getChildNodes();
			// node with namespace - any dept level
			nodeListNS = root.getElementsByTagNameNS(namespaceURI, "other");
			for (int i = 0; i < nodeListNS.getLength(); i++) {
				LOGGER.trace("ElemByTName: " + nodeListNS.item(i).getNodeName());
			}
			// node with namespace - any dept level
			nodeListNS = root.getElementsByTagNameNS(namespaceURI, "other");
			for (int i = 0; i < nodeListNS.getLength(); i++) {
				Node node = nodeListNS.item(i).getAttributes().getNamedItemNS(namespaceURI, "type");
				if (node != null) {
					LOGGER.trace(node.getNodeValue());
				}
				LOGGER.trace("ElemByTName Attribute: " + nodeListNS.item(i).getAttributes().getNamedItemNS(namespaceURI, "type"));
			}
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				if (nodeList.item(i).getNamespaceURI() != null
						&& nodeList
								.item(i)
								.getNamespaceURI()
								.equals(namespaceURI)) {
					String prefix = nodeList.item(i).lookupPrefix(namespaceURI);					
					if (nodeList.item(i).getNodeName().equals(prefix.concat(":").concat("other"))) {
						NamedNodeMap attributes = nodeList.item(i).getAttributes();
						for (int j= 0; j < attributes.getLength(); j++) {
							if (prefix.concat(":").concat("type").equals(attributes.item(j).getNodeName())) {
								LOGGER.trace("end: " + attributes.item(j).getTextContent());
							}
						}
					}
				}
				
			}
			
//			Xpath
			//Evaluate XPath against Document itself
			XPath xPath = XPathFactory.newInstance().newXPath();
			NodeList nodes = (NodeList) xPath.evaluate("/root/deep/tag/other/text()",
					document.getDocumentElement(), XPathConstants.NODESET);
			for (int i = 0; i < nodes.getLength(); ++i) {
				if (nodes.item(i) instanceof Text) {
					System.out.println(((Text)nodes.item(i)).getTextContent());					
				} else if (nodes.item(i) instanceof Element) {
					Element e = (Element) nodes.item(i);
					System.out.println(e.getNodeValue());
				}
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			DOMSource source = new DOMSource(document);
			Result result = new StreamResult(new FileOutputStream(new File(("d:\\test_dom.xml"))));
			// output to the System.out
			// Result result =  new StreamResult(System.out);
			transformer.transform(source, result);

			Transformer transformer2 = TransformerFactory.newInstance()
					.newTransformer(new StreamSource(inputStream2));
			Source source2 = new DOMSource(document);
			Result result2 = new StreamResult(new FileOutputStream(new File(("d:\\test_dom.xml"))));
			transformer2.transform(source2, result2);
//			out.getResult();
//			xmlOutput.output(out.getResult(), new FileOutputStream(new File("d:\\test_jdom.xml")));
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
