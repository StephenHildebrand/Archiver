package net.shiild.archiver.util;

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
     * <p>
     * Node is a private, static, nested class with an element that is the same type
     * as itself. Node members are public, which allows the outer class to
     * access the members directly (no need for getters/setters). The Node constructor
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
         * @param data the current node's String data
         * @param link the pointer to the next Node in the list
         */
        public Node(String data, Node link) {
            this.data = data;
            this.link = link;
        }
    }

    /**
     * Constructor for an empty LinkedList. It can also be used to erase
     * the current list.
     */
    public LinkedList() {
        head = null;
    }

    /**
     * Insert new data string into the front of the list.
     *
     * @param data the data to insert into the list
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
     * Check whether the list contains data or is empty.
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
     * Search for a particular String object in the list.
     * <p>
     * Begins by setting the pointer (p) to the head. Increments to the next list
     * item by setting 'p' to the link so that it references the next list element
     * and continues until the element is found or the list's end is reached.
     *
     * @param x the string to search for.
     * @return the integer position of the string if found. If not, return -1.
     */
    public int findPosByString(String x) {
        int pos = 0;
        for (Node p = head; p != null; p = p.link) {
            if (p.data.equals(x)) { // Found matching string
                return pos;
            }
            pos++; // Not found, continue to next node
        }
        return -1;
    }

    /**
     * Search for a particular object at the specified 'x' location in the list .
     * <p>
     * Begins by setting the pointer (p) to the head, then increments by setting
     * p to the link, so that it references the next list element and continues
     * until the element is found or the list's end is reached.
     *
     * @param x the int position to search for.
     * @return the String at this position. If not found, return null.
     */
    public String findStringByPos(int x) {
        int pos = 0;
        for (Node p = head; p != null; p = p.link) {
            if (pos == x) { // Found matching int position
                return p.data;
            }
            pos++; // Not found, continue to next node
        }
        return null;
    }

    /**
     * Remove a particular item from the list. If the item is not in the list,
     * leave the list unchanged.
     *
     * @param s the String to remove.
     */
    public void remove(String s) {
        Node current = head; // Pointer to traverse list, beginning with first element
        Node previous = null; // Pointer to previous Node, follows the first pointer

        // Now search for s. Iterate to next node if not a match.
        while (current != null && !current.data.equals(s)) {
            previous = current;
            current = current.link;
        }
        if (current != null) { // If current doesn't drop off the end of the list
            if (current == head) { // Is s the first list item?
                head = head.link;
            } else if (previous != null) { // s is somewhere after the first item
                previous.link = current.link;
            }
        }
    }
}
