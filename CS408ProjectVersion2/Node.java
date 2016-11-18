
	public class Node {
		int value;
		Node next;

		public Node (int value, Node next) {
			this.value = value;
			this.next = next;
		}

		public Node() {
			// TODO Auto-generated constructor stub
		}

		public int getValue() { return value; }
		public void setValue(int value) { this.value = value; }

		public Node getnext() { return next; }
		public void setnext(Node next) { this.next = next; }

		public Boolean hasNextNode(Node next) {
			if(next == null)
				return false;
			else
				return true;
		}

		public Node nextNode(Node node) {
			if(hasNextNode(node) == true) {
				return node.next;
			}
			return null;			
		}

		public void add(int value, Node next) {
			Node temp = next;
			while(temp.next != null) {
				temp = temp.next;
			}
			temp.next = new Node(value, null);
		}

		public boolean equals(Node node1, Node node2) {
			if(node1.value == node2.value) {
				return true;
			}
			else {
				return false;
			}
		}
				
		public boolean contains(int value, Node node) {
			if(value == node.value) {
				return true;
			}
			if(node.next != null) {
				contains(value, node.next);
			}
			else {
				return false;
			}
			return false;
		}

		public Node union(Node outb, Node inp) {
			Node unioned = new Node();
			Node t1 = outb;
			Node t2 = inp;
			
			while(t1 != null) {
				add(t1.value, unioned);
				t1 = t1.next;
			}
			
			while(t2 != null) {
				if(contains(inp.value, outb)) {
					add(t2.value, unioned);
				}
				t2 = t2.next;
			}
			return unioned;
		}
		
		public Node subtraction(Node outb, Node defb) {
			Node subtracted = new Node();
			Node t1 = outb;
			Node t2 = defb;
			Node temp;
			if(contains(t2.value, t1)) {
				if(t1.value == t2.value) {
					temp = t2.next;
					temp.value = t2.next.value;
				}
			}
			return subtracted;
		}



	}