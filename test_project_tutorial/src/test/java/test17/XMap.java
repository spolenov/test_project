package test17;

public interface XMap<K, V> {
	
	void put(K key, V value);
	
	V get(K key);
	
	boolean containsKey(K key);
	
	void remove(K key);
}
