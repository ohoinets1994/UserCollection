import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class UserHashMap<K, V> implements UserMap<K, V>{

    private Node<K, V>[] table;
    private int size = 0;
    private float capacity;

    UserHashMap() {
        table = new Node[16];
        capacity = table.length * 0.75f;
    }

    @Override
    public boolean put(K key, V value) {
        if (size + 1 >= capacity) {
            capacity *= 2;
            resizeTable();
        }

        Node<K, V> newNode = new Node<>(key, value);
        int index = newNode.hash();

        if (table[index] == null)
            return simplePut(index, newNode);

        List<Node<K, V>> nodeList = table[index].getListNodes();
        for (Node<K, V> nodeFromNodeList : nodeList) {
            if (collision(nodeFromNodeList, newNode, nodeList) /*|| keyExistButValueNew(nodeFromNodeList, newNode)*/)
                return true;
        }
        return false;
    }

    private void resizeTable() {
        Node<K, V>[] oldTable = table;
        table = new Node[oldTable.length * 2];
        size = 0;

        for (Node<K, V> node : oldTable) {
            if (node != null)
                for (Node <K, V> n : node.getListNodes())
                    put(n.key, n.value);
        }
    }

    private boolean collision(Node<K, V> nodeFromNodeList, Node<K, V> newNode, List<Node<K, V>> nodes) {
        if (nodeFromNodeList.hashCode() == newNode.hashCode() &&
                !Objects.equals(nodeFromNodeList.key, newNode.key)) {

            nodes.add(newNode);
            size++;
            return true;
        } else if (nodeFromNodeList.hashCode() == newNode.hashCode() &&
                Objects.equals(nodeFromNodeList.key, newNode.key)) {

            nodeFromNodeList.value = newNode.value;
            return true;
        }
        return false;
    }

//    private boolean keyExistButValueNew(Node<K, V> nodeFromNodeList, Node<K, V> newNode) {
//        if (nodeFromNodeList.key.equals(newNode.key) && !nodeFromNodeList.value.equals(newNode.value)) {
//            nodeFromNodeList.value = newNode.value;
//            return true;
//        }
//        return false;
//    }

    private boolean simplePut(int index, Node<K, V> newNode) {
        table[index] = new Node<>(null, null);
        table[index].getListNodes().add(newNode);
        size++;
        return true;
    }

    @Override
    public V get(K key) {
        int index = key.hashCode() % table.length;
        if (index < table.length && table[index] != null) {
            List<Node<K, V>> list = table[index].getListNodes();
            for (Node<K, V> node : list) {
                if (node.key.equals(key))
                    return node.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    private class Node<K, V> {
        private List<Node<K, V>> listNodes;
        private K key;
        private V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.listNodes = new LinkedList<Node<K, V>>();
        }

        private List<Node<K, V>> getListNodes() {
            return listNodes;
        }

        private int hash() {
            return hashCode() % table.length;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || this.getClass() != o.getClass()) return false;
            Node<K, V> node = (Node<K, V>) o;
            return (Objects.equals(key, node.key) &&
                    Objects.equals(value, node.value));
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }
    }
}