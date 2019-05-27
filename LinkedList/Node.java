package cn.zhj.linkedlist;

public class Node<E extends Comparable<E>> {
    E value;
    Node<E> next;

    public Node(E value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (value == null)
            return null;
        return value.toString();
    }
}
