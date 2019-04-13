package test17;

import static java.lang.Math.abs;

/**
 * Реализовать методы Get, Contains, Remove
 */
public class XHashMap<K, V> implements XMap<K, V> {
	private final static int DEFAULT_INITIAL_CAPACITY = 16;
	private final static float DEFAULT_LOAD_FACTOR = 0.75f;

	private final float loadFactor;
	private Entry<K, V>[] table;
	private int size = 0;
	
	public XHashMap() {
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
	}
	
	
	public XHashMap(int initialCapacity, float loadFactor) {
		this.loadFactor = loadFactor;
		table = new Entry[initialCapacity];
	}
	
	private static class Entry<K0, V0> {
		final K0 key;
		V0 value;
		Entry<K0, V0> next;
		
		public Entry(K0 key, V0 value, Entry next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}

	@Override
	public void put(K key, V value) {
		int busketIndex = abs(key.hashCode()) % table.length;
		Entry<K, V> currentEntry = table[busketIndex];
		table[busketIndex] = new Entry<K, V>(key, value, currentEntry);
		size++;
		if ((size * loadFactor) > table.length) {
			Entry<K, V>[] newTable = new Entry[table.length * 2]; // O(n) выделение памяти быстро, заполнение нулями долго
			transfer(this.table, newTable);
		}

	}

	private void transfer(Entry<K, V>[] oldTable, Entry<K, V>[] newTable) {
		for (int oldBacketIndex = 0; oldBacketIndex < oldTable.length; oldBacketIndex++) {
			Entry<K, V> oldEntry = oldTable[oldBacketIndex];
			while (oldEntry != null) {
				K key = oldEntry.key;
				V value = oldEntry.value;
				int newBacketIndex = key.hashCode() % newTable.length; // рехэширование по длине новой таблицы
				Entry<K, V> newEntry = newTable[newBacketIndex];
				newTable[newBacketIndex] = new Entry<K, V>(key, value, oldEntry);
			}
		}
	}

	@Override
	public V get(K key) {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
	}

	@Override
	public boolean containsKey(K key) {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
	}

	@Override
	public void remove(K key) {
        // TODO реализовать метод
        throw new UnsupportedOperationException("to do implementation");
	}

}
