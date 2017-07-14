/**
 * An unsorted linked list made up of nodes, each of which contains data and a
 * link to the next list element. The only data member of LinkedList is the
 * head Node.
 *
 * @author Stephen Hildebrand
 */
public class LinkedList {
	Node head; // Reference to the first list element

	/**
	 * List elements are nodes.
	 *
	 * Node is a static, nested class and has an element that is the same type
	 * as itself. Node members are public, which allows the outer class to
	 * access them directly (no need for getters/setters). The Node constructor
	 * requires both data and link.
	 *
	 * @author Stephen Hildebrand
	 */
	private static class Node {
		public String data;
		public Node link;

		/**
		 * Constructs a list Node with string data and a link to the next
		 * element.
		 *
		 * @param data
		 *            the current node's integer data
		 * @param link
		 *            the link to the next node
		 */
		public Node(String data, Node link) {
			this.data = data;
			this.link = link;
		}
	}

	/**
	 * Constructor -- creates an empty LinkedList. It can also be used to erase
	 * the current list.
	 */
	public LinkedList() {
		head = null;
	}

	/**
	 * Insert new string data into the front of the list.
	 *
	 * @param data
	 *            the data to insert into the list
	 */
	public void addToFront(String data) {
		head = new Node(data, head);
	}

	/**
	 * Remove the first item from the list.
	 *
	 * @return the first list item, or null if the list is empty
	 */
	public String removeFromFront() {
		if (head == null)
			return null;
		String removedItem = head.data;
		head = head.link;
		return removedItem;
	}

	/**
	 * Does this list have any data?
	 *
	 * @return true if the list has no data, return false if the list has data.
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Print the list data to standard output.
	 */
	public void print() {
		Node p = head;
		while (p != null) {
			if (p.link != null) {
				System.out.print(p.data + "--");
			} else {
				System.out.print(p.data);
			}
			p = p.link;
		}
		System.out.println();
	}

	/**
	 * Search for a particular object in the list.
	 *
	 * Begins by setting the pointer (p) to the head, then increments by setting
	 * p to the link, so that it references the next list element and continues
	 * until the element is found or the list's end is reached.
	 *
	 * @param x
	 *            the string to search for.
	 * @return the integer position of the string if found. If not, return -1.
	 */
	public int findPosByString(String x) {
		int pos = 0;
		for (Node p = head; p != null; p = p.link) {
			if (p.data.equals(x)) { // Found matching string.
				return pos;
			}
			pos++;
		}
		return -1;
	}

	/**
	 * Search for a particular object in the list.
	 *
	 * Begins by setting the pointer (p) to the head, then increments by setting
	 * p to the link, so that it references the next list element and continues
	 * until the element is found or the list's end is reached.
	 *
	 * @param x
	 *            the int to search for.
	 * @return the String at this position. If not found, return null.
	 */
	public String findStringByPos(int x) {
		int pos = 0;
		for (Node p = head; p != null; p = p.link) {
			if (pos == x) {
				return p.data;
			}
			pos++;
		}
		return null;
	}

	/**
	 * Remove a particular item from the list. If the item is not in the list,
	 * leave the list unchanged.
	 *
	 * @param x
	 *            the string to remove.
	 */
	public void remove(String x) {
		Node current = head; // First pointer to travel down the list, beginning
								// with the first element.
		Node previous = null; // Follows the first pointer

		// Now search for x
		while (current != null && !current.data.equals(x)) {
			previous = current;
			current = current.link;
		}
		// If you don't drop off the end of the list, there is work to do!
		if (current != null) {
			if (current == head) { // Is x the first list item?
				head = head.link;
			} else { // x is somewhere after the first item
				previous.link = current.link;
			}
		}
	}

}
