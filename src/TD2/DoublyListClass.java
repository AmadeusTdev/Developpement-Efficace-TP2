package TD2;

public class DoublyListClass {
	// Node class
	private static class Node {
		// Private variables
		private Integer element;
		private Node next;
		private Node previous;
		
		// Constructor
		public Node(Integer s, Node n, Node p) {
			this.element = s;
			this.next = n;
			this.previous = p;
		}
		
		// Methods
		public Integer getElement() {
			return element;
		}
		public Node getNext() {
			return next;
		}
		public Node getPrevious() {
			return previous;
		}
		public void setElement(Integer newElement) {
			this.element = newElement;
		}
		public void setNext(Node newNext) {
			this.next = newNext;
		}
		public void setPrevious(Node newPrevious) {
			this.previous = newPrevious;
		}
		
		public String toString() {
			return "(" + element + ")";
		}
	}
	
	// Private variables
	private Node head;
	private long size;
	
	// Constructor
	public DoublyListClass() {
		// Chaine vide
		this.head = null;
		this.size = 0;
	}
	
	// Methods
	public long size() {
		return size;
	}
	public boolean isEmpty() {
		return (size == 0);
	}
	public Integer first() {
		if (head != null) {
			return head.getElement();
		} else {
			return null;
		}
	}
	public Integer last() {
		if (head != null) {
			Node current = head;
			while (current.getNext() != null) {
				current = current.getNext();
			}
			return current.getElement();
		} else {
			return null;
		}
	}
	public void addFirst(Integer e) {
		this.size++;
		Node newElement = new Node(e, this.head, null);
		this.head.setPrevious(newElement);
		// La tête devient le nouveau élément
		this.head = newElement;
	}
	public void addLast(Integer e) {
		this.size++;
		Node newElement = new Node(e, null, null);
		if (head != null) {
			// On fait en sorte que le next du dernier noeud soit le nouveau élément
			Node current = head;
			while (current.getNext() != null) {
				current = current.getNext();
			}
			current.setNext(newElement);
			newElement.setPrevious(current);
		} else {
			// Chaîne vide, donc on initialise le premier élément
			this.head = newElement;
		}
	}
	public Integer removeFirst() {
		if (head != null) {
			this.size--;
			
			// On récupère le premier élément
			Integer firstElement = head.getElement();
			// Le deuxième noeud prend la place du premier
			Node secondNode = head.getNext();
			head.setNext(null);
			// On met le second noeud comme tête (le premier noeud sera automatiquement détruit car aucune variable ne lui fait référence)
			head = secondNode;
			head.setPrevious(null);
			// On retourne le premier élément qui à été enlever
			return firstElement;
		} else {
			return null;
		}
	}
	
	public String toString() {
		String str = "DoublyChain[";
		if (head != null) {
			Node currentNode = head;
			for (long i=1; i <= size; i++) {
				str = str + currentNode.toString();
				if (i < size) { str = str + " "; }
				currentNode = currentNode.getNext();
			}
		}
		str = str+ "]";
		
		return str;
	}
	
	public Integer algorithme_avantDernierNoeud() {
		if (head != null && head.getNext() != null) {
			Node current = head;
			while (current.getNext().getNext() != null) {
				current = current.getNext();
			}
			return current.getElement();
		} else {
			return null;
		}
	}
	
}
