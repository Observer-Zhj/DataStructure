package cn.zhj.linkedlist;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MyLinkedList<E extends Comparable<E>> {
    private int size = 0;
    private Node<E> head, tail;

    public MyLinkedList() {}

    public MyLinkedList(@NotNull E[] array) {
        for (E e : array) {
            addLast(e);
        }
    }

    public void addFirst(E e) {
        Node<E> node = new Node<E>(e);
        node.next = head;
        head = node;
        size++;
        if (tail == null) {
            tail = head;
        }
    }

    public void addLast(E e) {
        Node<E> node = new Node<E>(e);
        if (tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public void add(int index, E e) {
        if (index == 0) {
            addFirst(e);
        } else if (index >= size) {
            addLast(e);
        } else {
            Node<E> current = head;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<E>(e);
            current.next.next = temp;
            size++;
        }
    }

    public E getFirst() {
        if (size == 0)
            return null;
        return head.value;
    }

    public E getLast() {
        if (size == 0)
            return null;
        return tail.value;
    }

    public E get(int index) {
        if (size == 0)
            return null;
        if (size == 1 || index == 0)
            return getFirst();
        if (size == index)
            return getLast();
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size)
            return;
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.value = e;
    }

    public E removeFirst() {
        if (size == 0)
            return null;
        if (size == 1) {
            Node<E> e = head;
            head = tail = null;
            size = 0;
            return e.value;
        }
        Node<E> e = head;
        head = head.next;
        size--;
        return e.value;
    }

    public E removeLast() {
        if (size == 0 || size == 1) {
            return removeFirst();
        }
        Node<E> current = head;
        for (int i = 1; i < size-1; i++) {
            current = current.next;
        }
        Node<E> e = tail;
        current.next = null;
        tail = current;
        size--;
        return e.value;
    }

    public E remove(int index) {
        if (index == 0)
            return removeFirst();
        if (index == size - 1)
            return removeLast();
        if (index < 0 || index >= size)
            return null;
        Node<E> current = head;
        for (int i = 1; i < index; i++) {
            current = current.next;
        }
        Node<E> e = current.next;
        current.next = current.next.next;
        size--;
        return e.value;
    }

    public boolean contains(E e) {
        if (size == 0)
            return false;
        Node<E> current = head;
        do {
            if (current.value.equals(e)) {
                return true;
            }
            current = current.next;
        } while (current != null);
        return false;
    }

    public int indexOf(E e) {
        if (size == 0)
            return -1;
        Node<E> current = head;
//        for (int i = 0; i < size -1; i++) {
//            if (current.value == e)
//                return i;
//            current = current.next;
//        }
//        if (current.value == e)
//            return size;
        int i = 0;
        do {
            if (current.value == e)
                return i;
            i++;
            current = current.next;
        } while (current != null);
        return -1;
    }

    public Object[] toArray() {
        if (size == 0)
            return null;
        Object[] array = new Object[size];
//        Node<E> current = head;
//        for (int i = 0; i < size; i++) {
//            array[i] = current.value;
//            current = current.next;
//        }
        int i = 0;
        for (Node<E> node = head; node != null; node = node.next) {
            array[i++] = node.value;
        }
        return array;
    }

    public void clear() {
        size = 0;
        head = tail = null;
    }

    public void swap(int i, int j) {
        if (size == 0 || size == 1 || i-j == 0) {
            return;
        }
        if (i > j) {
            int tmp = j;
            j = i;
            i = tmp;
        }
        if (j-i == 1) {
            if (i == 0) {
                Node<E> temp = head.next;
                head.next = temp.next;
                temp.next = head;
                head = temp;
            } else {
                Node<E> current = head;
                for (int index = 1; index < i; index++) {
                    current = current.next;
                }
                Node<E> temp = current.next.next;
                current.next.next = current.next;
                current.next.next = temp.next;
                temp.next = current.next;
                current.next = temp;
                if (j == size-1) {
                    tail = current.next.next;
                }
            }
        } else {
            if (i == 0) {
                Node<E> current = head;
                for (int index = 1; index < j; index++) {
                    current = current.next;
                }
                Node<E> temp1 = head;
                Node<E> temp2 = current.next.next;
                head = current.next;
                head.next = temp1.next;
                current.next = temp1;
                temp1.next = temp2;
                if (j == size-1) {
                    tail = temp1;
                }

            } else {
                Node<E> current1 = head;
                int index = 0;
                for (int z=1; z < i; z++) {
                    index++;
                    current1 = current1.next;
                }
                Node<E> current2 = current1.next.next;
                for (int z = index + 3; z < j; z++) {
                    current2 = current2.next;
                }
                Node<E> temp1 = current1.next.next;
                Node<E> temp2 = current2.next;
                current1.next.next = temp2.next;
                temp2.next = temp1;
                current2.next = current1.next;
                current1.next = temp2;
                if (j == size-1) {
                    tail = current2.next;
                }
            }
        }
    }

    public void reverse() {
        if (size == 0 || size == 1) {
            return;
        }
        Node<E> prev = head;
        Node<E> current = head.next;
        while (current != null) {
            prev.next = current.next;
            current.next = head;
            head = current;
            current = prev.next;
        }
        tail = prev;
    }

    public void swapValue(Node<E> node1, Node<E> node2) {
        E temp = node1.value;
        node1.value = node2.value;
        node2.value = temp;
    }

//    public static void quickSort(int[] array) {
//        quickSort1(array, 0, array.length-1);
//    }
//
//    private static void quickSort1(int[] array, int p, int r) {
//        if (p < r) {
//            int q = partition(array, p, r);
//            quickSort1(array, p, q-1);
//            quickSort1(array, q+1, r);
//        }
//    }
//
//    private static int partition(int[] array, int p, int r) {
//        int x = array[p];
//        int i = p;
//        for (int j = p + 1; j <= r; j++) {
//            if (array[j] <= x) {
//                i++;
//                int temp = array[i];
//                array[i] = array[j];
//                array[j] = temp;
//            }
//        }
//        int temp = array[i];
//        array[i] = array[p];
//        array[p] = temp;
//        return i;
//    }

    public void quickSort() {
        Node<E> pre = new Node<E> (null);
        pre.next = head;
        quickSort(pre, head, null);
        Node<E> current = head;
        for (int i = 1; i < size; i++) {
            current = current.next;
        }
        tail = current;
    }

    private void quickSort(Node<E> pre, Node<E> start, Node<E> end) {
        if (start != end && start != null) {
            Node<E> mid = partition(pre, start, end);
            quickSort(pre, pre.next, mid);
            quickSort(mid, mid.next, end);
            head = pre.next;
        }
    }

    private Node<E> partition(Node<E> pre, Node<E> start, Node<E> end) {
        E key = start.value;
        Node<E> little = new Node<E>(null);
        Node<E> littlePoint = new Node<E>(null);
        little.next = littlePoint;
        Node<E> big = new Node<E>(null);
        Node<E> bigPoint = new Node<E>(null);
        big.next = bigPoint;
        Node<E> current = start.next;
        while (current != end) {
            if (current.value.compareTo(key) < 0) {
                littlePoint.next = current;
                littlePoint = current;
            } else {
                bigPoint.next = current;
                bigPoint = current;
            }
            current = current.next;
        }
        bigPoint.next = end;
        littlePoint.next = start;
        start.next = big.next.next;
        pre.next = little.next.next;
        return start;
    }

    @Override
    public String toString() {
        if (size == 0)
            return "[]";
        Node<E> current = head;
        StringBuffer string = new StringBuffer("[");
        for (int i = 0; i < size-1; i++) {
            string.append(current.value);
            string.append(", ");
            current = current.next;
        }
        string.append(current.value);
        string.append("]");
        return string.toString();
    }

    public static void main(String[] args) {

        Integer[] arr = {9,8,7,6,5,4,3,2,1,0,1,2,0,1};
        MyLinkedList<Integer> m = new MyLinkedList<Integer>(arr);
        m.quickSort();
        System.out.println(m);
        System.out.println(m.head);
        System.out.println(m.tail);
        System.out.println(m.tail.next);

        Integer[] arr1 = {0,1,2,3,4,5};
        MyLinkedList<Integer> m1 = new MyLinkedList<Integer>(arr1);
        m1.quickSort();
        System.out.println(m1);
        System.out.println(m1.head);
        System.out.println(m1.tail);
        System.out.println(m1.tail.next);
    }
}

