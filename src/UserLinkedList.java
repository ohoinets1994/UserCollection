import java.util.Iterator;

public class UserLinkedList<T> implements Iterable<T>{
    private int size = 0;
    private Node<T> first;
    private Node<T> last;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if (size == 0)
            return true;
        return false;
    }

    private class MyIterator implements Iterator<T>{
        private Node<T> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T result = current.value;
            current = current.next;
            return result;
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }


    private static class Node<T> {
        T value;
        Node<T> next;

        Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        String string = "";
        for (Node<T> n = first; n != null; n = n.next) {
            string += "[" + n.value + "]";
        }
        return string;
    }

    private boolean removeFirst() {
        Node<T> next = first.next;

        first = next;
        first.next = next.next;
        size--;
        return true;
    }

    private boolean unlink(Node<T> node) {
        Node<T> next = node.next.next;

        node.next = next;
        size--;
        return true;
    }

    public boolean remove(Object element) {
        if (!contains(element))
            return false;
        if (element == null) {
            if (first.next == null) {
                first = null;
                size--;
                return true;
            }
            for (Node<T> node = first; node.next != null; node = node.next) {
                if (first.value == null)
                    return removeFirst();
                else if (node.next.value == null)
                    return unlink(node);
            }
        } else {
            if (first.next == null) {
                first = null;
                size--;
                return true;
            }
            for (Node<T> node = first; node.next != null; node = node.next) {
                if (element.equals(first.value))
                    return removeFirst();
                else if (element.equals(node.next.value))
                    return unlink(node);
            }
        }
        return false;
    }

    public boolean contains(Object object) {
        if (object == null) {
            for (Node<T> node = first; node != null; node = node.next)
                if (node.value == null)
                    return true;
        } else {
            for (Node<T> node = first; node != null; node = node.next)
                if (object.equals(node.value))
                    return true;
        }
        return false;
    }

    public boolean add(T object) {
        try {
            Node<T> newNode = new Node<>(object, null);
            if (last == null) {
                last = newNode;
                first = newNode;
            } else {
                last.next = newNode;
                last = last.next;
            }
            size++;
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}