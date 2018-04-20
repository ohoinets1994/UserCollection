public interface UserMap<K, V> {
    boolean put(K key, V value);
    V get(K key);
    int size();
}
