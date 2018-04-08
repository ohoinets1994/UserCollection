
public class UserTreeSet<T extends Comparable<T>> {
    private int size = 0;
    private Node<T> root;

    public int size() {
        return size;
    }

    public boolean isTmpty() {
        if (size == 0)
            return true;
        return false;
    }

    private static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;

        Node(T value, Node<T> left, Node<T> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public boolean add(T element) {
        if (contains(element))
            return false;
        root = add(root, element);
        return true;
    }

    private Node<T> add(Node<T> node, T element) {
        if (node == null) {
            size++;
            return new Node<>(element, null, null);
        } else {
            if (element.compareTo(node.value) < 0)
                node.left = add(node.left, element);
            else
                node.right = add(node.right, element);
        }
        return node;
    }

    public boolean contains(T element) {
        return contains(root, element);
    }

    private boolean contains(Node<T> node, T element) {
        if (node == null)
            return false;
        else {
            if (element.compareTo(node.value) == 0)
                return true;
            else if (element.compareTo(node.value) < 0)
                return contains(node.left, element);
            else
                return contains(node.right, element);
        }
    }

    public boolean remove(T element) {
        boolean isContained = contains(element);
        if (!isContained)
            return false;
        root = remove(root, element);
        return true;
    }

    private Node<T> remove(Node<T> node, T element) {
        if (element.compareTo(node.value) == 0) {
            if ((node.right == null && node.left != null) || (node.right == null)) {
                node = node.left;
                size--;
            } else if (node.right.left == null) {
                Node<T> tmp = node.left;
                node = node.right;
                node.left = tmp;
                size--;
            } else {
                Node<T> newNode = node.right;
                Node<T> prev = node;

                while (newNode.left != null) {
                    prev = newNode;
                    newNode = newNode.left;
                }
                if (prev != node)
                    prev.left = null;

                newNode.left = node.left;
                newNode.right = node.right;
                size--;
                return newNode;
            }
        } else if (element.compareTo(node.value) < 0)
            node.left = remove(node.left, element);
        else
            node.right = remove(node.right, element);
        return node;
    }

    public void print() {
        if (root == null)
            System.out.println("Your collection is empty!");
        else printTree(root);
    }

    private void printTree(Node<T> root) {
        if (root != null) {
            printTree(root.left);
            System.out.println(root.value);
            printTree(root.right);
        }
    }

    public T maxElement() {
        Node<T> node = root;
        T maxValue = root.value;

        if (node.right == null)
            return maxValue;
        else {
            while (node.right != null) {
                node = node.right;
                maxValue = node.value;
            }
        }
        return maxValue;
    }

    public T minElement() {
        Node<T> node = root;
        T minValue = root.value;

        if (node.left == null)
            return minValue;
        else {
            while (node.left != null) {
                node = node.left;
                minValue = node.value;
            }
        }
        return minValue;
    }
}