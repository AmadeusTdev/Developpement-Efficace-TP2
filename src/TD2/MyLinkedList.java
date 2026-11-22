package TD2;

import java.util.AbstractList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.NoSuchElementException;

public class MyLinkedList<T> extends AbstractList<T> implements List<T> {
    // Node class
    private static class Node<T> {
        public T element;
        public Node<T> prev;
        public Node<T> next;

        public Node(T element, Node<T> prev, Node<T> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    // Private vvariables
    private final Node<T> header = new Node<>(null, null, null);
    private final Node<T> trailer = new Node<>(null, header, null);
    private int size = 0;

    // Constructors
    public MyLinkedList() {
        header.next = trailer;
    }

    // Methods
    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        return nodeAt(index).element;
    }

    @Override
    public T set(int index, T element) {
        Node<T> n = nodeAt(index);
        T old = n.element;
        n.element = element;
        return old;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<T> succ;
        if (index == size) {
            succ = trailer;
        } else {
            succ = nodeAt(index);
        }
        addBefore(element, succ);
    }

    @Override
    public T remove(int index) {
        return unlink(nodeAt(index));
    }

    @Override
    public void clear() {
        header.next = trailer;
        trailer.prev = header;
        size = 0;
    }

    public void addFirst(T e) {
        addBefore(e, header.next);
    }
    public void addLast(T e) {
        addBefore(e, trailer);
    }

    public T getFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return header.next.element;
    }

    public T getLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return trailer.prev.element;
    }

    public T removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return unlink(header.next);
    }

    public T removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return unlink(trailer.prev);
    }

    // Outils
    private Node<T> nodeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<T> n;
        if (index < size / 2) {
            n = header.next;
            for (int i = 0; i < index; i++) {
                n = n.next;
            }
        } else {
            n = trailer.prev;
            for (int i = size - 1; i > index; i--) {
                n = n.prev;
            }
        }
        return n;
    }

    private void addBefore(T e, Node<T> succ) {
        Node<T> pred = succ.prev;
        Node<T> n = new Node<>(e, pred, succ);
        pred.next = n;
        succ.prev = n;
        size+=1;
    }

    private T unlink(Node<T> n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
        size-=1;
        return n.element;
    }
}