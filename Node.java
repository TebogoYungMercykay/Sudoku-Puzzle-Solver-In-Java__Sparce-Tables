public class Node<T> {
    public T data;
    public Node<T> next;

    public String toString() {
        return this.data.toString();
    }

    public Node(T val) {
        this.data = val;
        this.next = null;
    }
}