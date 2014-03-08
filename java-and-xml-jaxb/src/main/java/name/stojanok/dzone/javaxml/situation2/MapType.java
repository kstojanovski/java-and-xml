package name.stojanok.dzone.javaxml.situation2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
 
/**
 *
 * @author John Yeary
 * @version 1.0
 */
public class MapType<K, V> {
 
    private List<MapEntryType<K, V>> entry = new ArrayList<MapEntryType<K, V>>();
 
    public MapType() {
    }
 
    public MapType(Map<K, V> map) {
        for (Map.Entry<K, V> e : map.entrySet()) {
            entry.add(new MapEntryType<K, V>(e));
        }
    }
 
    public List<MapEntryType<K, V>> getEntry() {
        return entry;
    }
 
    public void setEntry(List<MapEntryType<K, V>> entry) {
        this.entry = entry;
    }
}
