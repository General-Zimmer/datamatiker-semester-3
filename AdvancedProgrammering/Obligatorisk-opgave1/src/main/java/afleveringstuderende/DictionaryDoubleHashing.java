package afleveringstuderende;

public class DictionaryDoubleHashing <K, V> implements Dictionary<K, V> {
    private Entry<K, V>[] table;
    private int size;

   private final Entry<K, V> DELETED = new Entry(null,null);

    public DictionaryDoubleHashing(int length) {
        table =  new Entry[length];
        size = 0;
    }

    // Tidskompleksitet: O(1)
    /** Hash function */
    private int hash(int hashCode) {
        if (hashCode < 0) {
            hashCode = -hashCode;
        }
        return hashCode % table.length;
    }

    // Tidskompleksitet: O(n)
    @Override
    public V get(K key) {
        int bucketIndex = hash(key.hashCode());
        Entry<K, V> current = table[bucketIndex];
        boolean found = false;
        V returnValue = null;
        int searchTime = table.length;
        while (!found && current != null && searchTime > 0) {
            if (current != DELETED && current.key.equals(key)) {
                found = true;
                returnValue = current.value;
            } else {
                bucketIndex = hash(7 - (bucketIndex%7));
                current = table[bucketIndex];
                searchTime--;
            }
        }
        return returnValue;
    }

    // Tidskompleksitet: O(1)
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Tidskompleksitet: O(n)
    @Override
    public V put(K key, V value) {

        int bucketIndex = hash(key.hashCode());
        Entry<K, V> current = table[bucketIndex];
        boolean found = false;
        V returnValue = null;
        while (!found && current != null && current != DELETED) {
            if (current.key.equals(key)) {
                found = true;
                returnValue = current.value;
                current.value = value;
            } else {
                bucketIndex = hash(7 - (bucketIndex%7));
                current = table[bucketIndex];
            }
        }

        if (!found) {
            if ((double) size / table.length > 0.5) {
                rehash();
                bucketIndex = hash(value.hashCode());
            }
            Entry<K, V> newEntry = new Entry<>(key, value);
            newEntry.value = value;
            table[bucketIndex] = newEntry;
            size++;
        }
        return returnValue;
    }

    // Tidskompleksitet: O(n)
    private void rehash() {
        Entry<K, V>[] tempArray = table;
        table = new Entry[table.length * 2 + 1];
        size = 0;

        for (Entry<K, V> node : tempArray) {
            if (node != null && node != DELETED)
                put(node.key, node.value);
        }
    }

    // Tidskompleksitet: O(n)
    @Override
    public V remove(K key) {
        int bucketIndex = hash(key.hashCode());
        Entry<K, V> current = table[bucketIndex];
        V returnValue = null;
        boolean found = false;
        int searchTime = table.length;
        while (!found && current != null && searchTime > 0) {
            if (current.key.equals(key)) {
                found = true;
                returnValue = current.value;
                table[bucketIndex] = DELETED;
                size--;
            } else {
                bucketIndex = hash(7 - (bucketIndex%7));
                current = table[bucketIndex];
                searchTime--;
            }
        }

        return returnValue;
    }

    // Tidskompleksitet: O(1)
    @Override
    public int size() {
        return size;
    }


    // method only for test purpose
    public void writeOut() {
        for (int i = 0; i < table.length; i++) {
            System.out.println(i + "\t" + table[i]);
        }
    }

    public static class Entry<K,V>{
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
        public String toString(){
            return "(" +key + " , " + value + ")";
        }
    }
}
