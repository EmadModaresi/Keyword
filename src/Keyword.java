
import java.io.IOException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * here the interface of Linkedlist
 *
 * @param <E>
 */
interface ILinkedList<E> {
    /**
     * It is used to append the specified element to the end of a list.
     */

    void addLast(E e);

    /**
     * It is used to insert the specified element at the specified position index in a list.
     * throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size());
     *
     * @param index
     * @param element
     * @throws IndexOutOfBoundsException
     */

    void add(int index, E element) throws IndexOutOfBoundsException;

    /**
     * It is used to insert the given element at the beginning of a list.
     *
     * @param e
     */
    void addFirst(E e);

    /**
     * It is used to remove all the elements from a list.
     */
    void clear();

    /**
     * It is used to return the element at the specified position in a list.
     * throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size());
     *
     * @param index
     * @return
     * @throws IndexOutOfBoundsException
     */

    E get(int index) throws IndexOutOfBoundsException;

    /**
     * It is used to return the first element in a list.
     *
     * @return
     * @throws NoSuchElementException
     */
    E getFirst() throws NoSuchElementException;

    //It is used to return the last element in a list.
    E getLast() throws NoSuchElementException;

    //It is used to return true if a list contains a specified element.
    boolean contains(E e);

    //It is used to return the index in a list of the first occurrence of the specified element, or -1 if the list does not contain any element.
    int indexOf(E e);

    //It is used to remove the element at the specified position in a list.
    E remove(int index) throws IndexOutOfBoundsException;

    //	It is used to remove the first occurrence of the specified element in a list, if it is present.
    boolean remove(E e);

    //It removes and returns the first element from a list.
    E removeFirst() throws NoSuchElementException;

    //It removes and returns the last element from a list.
    E removeLast() throws NoSuchElementException;

    //It replaces the element at the specified position in a list with the specified element.
    E set(int index, E e) throws IndexOutOfBoundsException;

    //	It is used to return the number of elements in a list.
    int size();

    // If the list is empty returns true otherwise returns false
    boolean isEmpty();

    //void reverse();
}

/**
 * the mian class
 */
public class Keyword {
    public static void main(String[] args) throws IOException {
        /**
         * the data that code is using
         */
        int pointer = 0;
        DoublyLinkedList<String> data = new DoublyLinkedList<>();
        Scanner input = new Scanner(System.in);
        String[] in = input.nextLine().split("");
        /**
         * Algoritm of that code
         */
        for (int i = 0; i < in.length; i++) {
            if (in[i].equals(">")) {
                if (pointer < data.size()) {
                    pointer++;
                }
                continue;
            } else if (in[i].equals("<")) {
                if (pointer > 0) {
                    pointer--;
                }
                continue;
            } else if (in[i].equals("e") || in[i].equals("E")) {
                pointer = data.size();
            } else if (in[i].equals("h") || in[i].equals("H")) {
                pointer = 0;
            } else if (in[i].equals("b") || in[i].equals("B")) {
                if (pointer<=0)
                    continue;
                pointer--;
                data.remove(pointer);
            } else if (in[i].equals("d") || in[i].equals("D")) {
                if (pointer>=data.size())
                    continue;
                data.remove(pointer);
            } else {
                data.add(pointer, in[i]);
                pointer++;
            }
        }
        System.out.println(data.toString());
    }
}

/**
 * Implementing a doubly linked list data structure
 * Three Node classes in this class are defined first for pointing to next Node
 * secend for pointing to previewe
 * and last one for saving data
 *
 * @param <T>
 */
class DoublyLinkedList<T> implements ILinkedList<T> {
    private static class Node<T> {
        private T data;
        private Node<T> next;
        private Node<T> prev;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getPrev() {
            return prev;
        }

        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public DoublyLinkedList() {
        head = new Node<>(null, null, null);
        tail = new Node<>(null, head, null);
        head.setNext(tail);
        size = 0;
    }

    private void addbetween(T t, Node<T> prev, Node<T> next) {
        Node<T> newnode = new Node<>(t, prev, next);
        prev.setNext(newnode);
        next.setPrev(newnode);
        size++;
    }

    private T remove(Node<T> x) {
        Node<T> p = x.getPrev();
        Node<T> n = x.getNext();
        p.setNext(n);
        n.setPrev(p);
        size--;
        return x.getData();
    }

    private Node<T> getNode(int index) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        Node c = head.getNext();
        for (int i = 0; i < index; i++) {
            c = c.getNext();
        }
        return c;
    }

    private Node<T> getNode(T t) {
        Node c = head.getNext();
        while (c != tail) {
            if (c.getData().equals(t))
                return c;
            c = c.getNext();
        }
        return null;
    }

    @Override
    public void addLast(T t) {
        addbetween(t, tail.getPrev(), tail);
    }

    @Override
    public void add(int index, T element) throws IndexOutOfBoundsException {
        if (index == 0)
            addFirst(element);
        else if (index == size)
            addLast(element);
        else
            addbetween(element, getNode(index - 1), getNode(index));

    }

    @Override
    public void addFirst(T t) {
        addbetween(t, head, head.getNext());
    }

    @Override
    public void clear() {
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        return getNode(index).getData();
    }

    @Override
    public T getFirst() throws NoSuchElementException {
        return getNode(0).getData();
    }

    @Override
    public T getLast() throws NoSuchElementException {
        return getNode(size - 1).getData();
    }

    @Override
    public boolean contains(T t) {
        if (getNode(t) != null) {
            return true;
        }
        return false;
    }

    @Override
    public int indexOf(T t) {
        Node c = head.getNext();
        for (int i = 0; c != tail; i++) {
            if (c.getData().equals(t))
                return i;
            c = c.getNext();
        }
        return -1;
    }

    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        Node newnode = getNode(index);
        Node<T> p = newnode.getPrev();
        Node<T> n = newnode.getNext();
        p.setNext(n);
        n.setPrev(p);
        size--;
        return (T) newnode.getData();
    }

    @Override
    public boolean remove(T t) {
        if (getNode(t) != null) {
            remove(getNode(t));
            return true;
        }
        return false;
    }

    @Override
    public T removeFirst() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException();
        return remove(head.getNext());
    }

    @Override
    public T removeLast() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException();
        return remove(tail.getPrev());
    }

    @Override
    public T set(int index, T t) throws IndexOutOfBoundsException {
        Node<T> newnode = getNode(index);
        getNode(index).setData(t);
        return newnode.getData();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        String ans = "";
        Node<T> c = head.getNext();
        while (c.equals(tail) == false) {
            ans += (String) c.getData();
            c = c.getNext();
        }
        if (ans.equals(""))
            return "There is no text";
        else {
            return ans;
        }
    }

}


