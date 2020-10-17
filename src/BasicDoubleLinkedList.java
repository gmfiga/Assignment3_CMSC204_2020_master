
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Class for a Basic Doubly linked list
 * @author Gabriel Martins Figueiredo
 * @param <T> object type
 */
public class BasicDoubleLinkedList<T> implements Iterable<T> {

    protected Node firstNode;
    protected Node lastNode;
    int size; //the number of entries

    public BasicDoubleLinkedList() {

        firstNode = null;
        lastNode = null;
        size = 0;
    }


    /**
     * Notice you must not traverse the list to compute the size.
     * This method just returns the value of the instance variable you use to keep track of size.
     *
     * @return the size of the linked list
     */
    public int getSize() {
        return size;
    }


    /**
     * Adds an element to the end of the list. Do not use iterators to implement this method.
     *
     * @param data - the data for the Node within the linked list
     * @return reference to the current object
     */
    public BasicDoubleLinkedList<T> addToEnd(T data) {
        Node newNode = new Node(data, null, lastNode);
        if (size == 0) {
            firstNode = lastNode = newNode;
        } else
            lastNode.setNextNode(newNode);
        lastNode = newNode;
        size++;
        return this;
    }

    /**
     * Adds element to the front of the list. Do not use iterators to implement this method.
     *
     * @param data - the data for the Node within the linked list
     * @return reference to the current object
     */
    public BasicDoubleLinkedList<T> addToFront(T data) {
        Node newNode = new Node(data, firstNode, null);
        if (size == 0) {
            firstNode = lastNode = newNode;
        } else
            firstNode.setPreviousNode(newNode);
        firstNode = newNode;
        size++;
        return this;
    }

    /**
     * Returns but does not remove the first element from the list.
     * If there are no elements the method returns null. Do not implement this method using iterators.
     *
     * @return the data element or null
     */
    public T getFirst() {

        return firstNode.getData();
    }

    /**
     * Returns but does not remove the last element from the list.
     * If there are no elements the method returns null. Do not implement this method using iterators.
     *
     * @return the data element or null
     */
    public T getLast() {

        return lastNode.getData();
    }

    /**
     * This method must be implemented using an inner class that implements ListIterator and
     * defines the methods of hasNext(), next(), hasPrevious() and previous().
     * Remember that we should be able to call the hasNext() method as many times as we
     * want without changing what is considered the next element.
     *
     * @return
     * @throws UnsupportedOperationException- Your next() method should throw NoSuchElementException
     *                                        if there are no more elements (at the end of the list and calling next()
     *                                        or at the beginning of the list and calling previous()).
     * @throws NoSuchElementException         - You don't need to implement the ListIterator's remove(),
     *                                        add(), nextIndex() and previousIndex() and set() methods, throw UnsupportedOperationException if called.
     */
    @Override
    public ListIterator<T> iterator() throws UnsupportedOperationException, NoSuchElementException {
        return new IteratorForLinkedList();
    }


    /**
     * Removes the first instance of the targetData from the list.
     * Notice that you must remove the elements by performing a single traversal over the list.
     * You may not use any of the other retrieval methods associated with the
     * class in order to complete the removal process. You must use the provided
     * comparator (do not use equals) to find those elements that match the target.
     * Do not implement this method using iterators.
     *
     * @param targetData - the data element to be removed
     * @param comparator - the comparator to determine equality of data elements
     * @return data element or null
     */
    public BasicDoubleLinkedList<T> remove(T targetData,
                                           java.util.Comparator<T> comparator) {
        Node targetNode = firstNode;

        while (comparator.compare(targetNode.getData(), targetData) != 0) {
            targetNode = targetNode.getNextNode();
        }


        if (targetNode == firstNode) {
            firstNode = firstNode.getNextNode();
        } else if (targetNode == lastNode) {
            lastNode = lastNode.getPreviousNode();
        } else {
            Node beforeTarget = targetNode.getPreviousNode();
            Node afterTarget = targetNode.getNextNode();
            beforeTarget.setNextNode(afterTarget);
            afterTarget.setPreviousNode(beforeTarget);
        }

        size--;
        return this;
    }

    /**
     * Removes and returns the first element from the list.
     * <p>
     * If there are no elements the method returns null. Do not implement this method using iterators.
     *
     * @return data element or null
     */
    public T retrieveFirstElement() {
        T dataToReturn = firstNode.getData();
        Node secondNode = firstNode.getNextNode();
        secondNode.setPreviousNode(lastNode);
        firstNode = secondNode;
        size--;
        return dataToReturn;
    }


    /**
     * Removes and returns the last element from the list. If there are no elements the method returns null.
     * Do not implement implement this method using iterators.
     *
     * @return data element or null
     */
    public T retrieveLastElement() {
        T dataToReturn = lastNode.getData();
        Node secondToLastNode = lastNode.getPreviousNode();
        secondToLastNode.setNextNode(firstNode);
        lastNode = secondToLastNode;
        size--;
        return dataToReturn;
    }

    /**
     * Returns an arraylist of the items in the list from head of list to tail of list
     *
     * @return an arraylist of the items in the list
     */
    public ArrayList<T> toArrayList() {
        ArrayList<T> arrayList = new ArrayList<>();
        Node dummyNode = firstNode;

        for (int i = 0; i < size; i++) {
            arrayList.add(dummyNode.getData());
            dummyNode = dummyNode.getNextNode();
        }

        return arrayList;
    }


    /**
     * Node inner class
     */
    protected class Node {

        private T data;
        private Node next;
        private Node previous;

        /**
         * Standard node constructor
         * @param data
         */
        public Node(T data) {
            this(data, null, null);
        }

        /**
         * Node constructor
         * @param data object data
         * @param nextNode reference to next node
         * @param previousNode reference to previous node
         */
        public Node(T data, Node nextNode, Node previousNode) {
            this.data = data;
            this.next = nextNode;
            this.previous = previousNode;
        }

        /**
         * data getter
         * @return data
         */
        public T getData() {
            return data;
        }

        /**
         * data setter
         * @param data
         */
        public void setData(T data) {
            this.data = data;
        }

        /**
         * next node getter
         * @return
         */
        public Node getNextNode() {
            return next;
        }

        /**
         * next node setter
         * @param next
         */
        public void setNextNode(Node next) {
            this.next = next;
        }

        /**
         * previous node getter
         * @return
         */
        public Node getPreviousNode() {
            return previous;
        }

        /**
         * previous node setter
         * @param previous
         */
        public void setPreviousNode(Node previous) {
            this.previous = previous;
        }
    }


    /**
     * Iterator inner class
     */
    private class IteratorForLinkedList implements ListIterator<T> {

        private Node nextNode;
        private Node previousNode;

        /**
         * Constructor
         */
        public IteratorForLinkedList() {
            this.nextNode = firstNode;
            this.previousNode = null;
        }

        /**
         * check if has next node
         * @return
         */
        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        /**
         * get next node
         * @return result
         */
        @Override
        public T next() {
            T result;
            if (hasNext()) {
                result = nextNode.getData();
                previousNode = nextNode;
                nextNode = nextNode.getNextNode();
            } else
                throw new NoSuchElementException();

            return result;
        }

        /**
         * check if has previous
         * @return
         */
        @Override
        public boolean hasPrevious() {
            return previousNode != null;
        }

        /**
         * get previous node
         * @return
         */
        @Override
        public T previous() {
            T result;
            if (hasPrevious()) {
                result = previousNode.getData();
                nextNode = previousNode;
                previousNode = previousNode.getPreviousNode();
            } else
                throw new NoSuchElementException();

            return result;
        }

        /**
         * unsupported
         * @return
         */
        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        /**
         * unsupported
         * @return
         */
        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        /**
         * unsupported
         * @return
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /**
         * unsupported
         * @return
         */
        @Override
        public void set(T t) {
            throw new UnsupportedOperationException();
        }

        /**
         * unsupported
         * @return
         */
        @Override
        public void add(T t) {
            throw new UnsupportedOperationException();
        }
    }

}
