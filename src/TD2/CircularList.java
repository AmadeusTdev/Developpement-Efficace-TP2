package TD2;

public class CircularList {

    private static class Node {
        public Integer element;
        public Node next;

        public Node(Integer element, Node next) {
            this.element = element;
            this.next = next;
        }
    }

    private Node tail = null;
    private int size = 0;

    public CircularList() {}

    public int getSize() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }

    public Integer first() {
        if (isEmpty()) {
            return null;
        } else {
            return tail.next.element;
        }
    }
    public Integer last() {
        if (isEmpty()) {
            return null;
        } else {
            return tail.element;
        }
    }

    public void addFirst(Integer e) {
        if (isEmpty()) {
            tail = new Node(e, null);
            tail.next = tail;
        } else {
            tail.next = new Node(e, tail.next);
        }
        size+=1;
    }

    public void addLast(Integer e) {
        addFirst(e);
        tail = tail.next; // la nouvelle tête devient la queue
    }

    public Integer removeFirst() {
        if (isEmpty()) return null;

        Node head = tail.next;
        if (head == tail) {
            tail = null;
        } else {
            tail.next = head.next;
        }
        size-=1;

        return head.element;
    }

    public int countNodes() {
        if (tail == null) return 0;
        int count = 1;
        for (Node c = tail.next; c != tail; c = c.next) count++;
        return count;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "CircularList[]";
        StringBuilder myStringBuilder = new StringBuilder("CircularList[");
        Node node = tail.next;
        do {
            myStringBuilder.append(node.element);
            node = node.next;
            if (node != tail.next) {
                myStringBuilder.append(", ");
            }
        } while (node != tail.next);
        return myStringBuilder.append("]").toString();
    }
}