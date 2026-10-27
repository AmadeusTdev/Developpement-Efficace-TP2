package TD2;

public class SinglyListClass {
	// Node class
	private static class Node {
		// Private variables
		private Integer element;
		private Node next;
		
		// Constructor
		public Node(Integer s, Node n) {
			this.element = s;
			this.next = n;
		}
		
		// Methods
		public Integer getElement() {
			return element;
		}
		public Node getNext() {
			return next;
		}
		public void setElement(Integer newElement) {
			this.element = newElement;
		}
		public void setNext(Node newNext) {
			this.next = newNext;
		}
		
		public String toString() {
			return "(" + element + ")";
		}
	}
	
	// Private variables
	private Node head;
	private long size;
	
	// Constructor
	public SinglyListClass() {
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
		Node newElement = new Node(e, this.head);
		// La tête devient le nouveau élément
		this.head = newElement;
	}
	public void addLast(Integer e) {
		this.size++;
		Node newElement = new Node(e, null);
		if (head != null) {
			// On fait en sorte que le next du dernier noeud soit le nouveau élément
			Node current = head;
			while (current.getNext() != null) {
				current = current.getNext();
			}
			current.setNext(newElement);
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
			// On retourne le premier élément qui à été enlever
			return firstElement;
		} else {
			return null;
		}
	}
	
	public String toString() {
		String str = "Chain[";
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
	
	// Question 1.2 - 1
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
	
	// Question 1.2 - 2
	public void algorithme_inverserChaine() {
		if (size > 1) {
			
			Node previousNode = null;
			Node currentNode = head;
			Node nextNode = currentNode.getNext();
			
			while (nextNode != null) {
				Node nextNodeTmp = nextNode.getNext();
				
				currentNode.setNext(previousNode);
				nextNode.setNext(currentNode); // On inverse
				
				previousNode = currentNode;
				currentNode = nextNode;
				nextNode = nextNodeTmp;
			}
			
			// On gère la fin de la chaine
			currentNode.setNext(previousNode);
			head = currentNode;
		}
	}
	
	// Question 1.2 - 3
	public void algorithme_swapNode(int valX, int valY) {
		if (head != null) {
			// On cherche le premier node de valeur valX
			Node nodeX = null;
			Node currX = head;
			Node predX = null; // Le prédecesseur de currX
			while (nodeX == null && currX != null) {
				if (currX.getElement() == valX) {
					nodeX = currX; // On a trouvé le bon node
				} else {
					predX = currX;
					currX = currX.getNext(); // node suivant
				}
			}

			// On cherche le premier node de valeur valY
			Node nodeY = null;
			Node currY = head;
			Node predY = null; // Le prédecesseur de currY
			while (nodeY == null && currY != null) {
				if (currY.getElement() == valY) {
					nodeY = currY; // On a trouvé le bon node
				} else {
					predY = currY;
					currY = currY.getNext(); // node suivant
				}
			}

			// Si on à trouvé les deux nodes alors on les échanges
			if (nodeX != null && nodeY != null) {
				// Si les nodes sont côte à côte
				if (nodeX.getNext() == nodeY) {
					// On est dans le cas predX -> nodeX -> nodeY
					nodeX.setNext(nodeY.getNext());
					nodeY.setNext(nodeX);
					predX.setNext(nodeY);

				} else if (nodeY.getNext() == nodeX) {
					// On est dans le cas predY -> nodeY -> nodeX
					nodeY.setNext(nodeX.getNext());
					nodeX.setNext(nodeY);
					predY.setNext(nodeX);

				} else {

					// Sinon on échange simplement les next des nodes que l'on échange et des nodes précédentes
					// On a donc predX -> nodeX    et    predY -> nodeY
					if (predX != null) { // Au cas ou nodeX est le head
						predX.setNext(nodeY);
					}
					if (predY != null) { // Au cas ou nodeY est le head
						predY.setNext(nodeX);
					}
					
					Node tmp = nodeX.getNext();
					nodeX.setNext(nodeY.getNext());
					nodeY.setNext(tmp);
				}
			}
		}
	}
	
}
