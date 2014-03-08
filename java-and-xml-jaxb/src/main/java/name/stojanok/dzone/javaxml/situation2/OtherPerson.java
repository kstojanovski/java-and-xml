package name.stojanok.dzone.javaxml.situation2;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class OtherPerson {

	private Map<String, String> map0 = new HashMap<String, String>();
	private Map<String, String> map1 = new HashMap<String, String>();
	private Map<Key, Value> map3 = new HashMap<Key, Value>();

	private String cdata;
	
	private String someData;
	
	@XmlJavaTypeAdapter(DependMapAdapter.class)
	public Map<String, String> getMap0() {
		return map0;
	}
	public void setMap0(Map<String, String> map0) {
		this.map0 = map0;
	}
	
	@XmlJavaTypeAdapter(XmlGenericMapAdapter.class)
	public Map<String, String> getMap1() {
		return map1;
	}
	public void setMap1(Map<String, String> map1) {
		this.map1 = map1;
	}	
	
	@XmlJavaTypeAdapter(XmlGenericMapAdapter.class)
	public Map<Key, Value> getMap3() {
		return map3;
	}
	public void setMap3(Map<Key, Value> map3) {
		this.map3 = map3;
	}
	
	@XmlJavaTypeAdapter(CdataAdapter.class)
	public String getCdata() {
		return cdata;
	}
	
	public void setCdata(String cdata) {
		this.cdata = cdata;
	}
	
	public String getSomeData() {
		return someData;
	}
	
	public void setSomeData(String someData) {
		this.someData = someData;
	}

}
