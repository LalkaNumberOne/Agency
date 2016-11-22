package space.vladoff.util;

import java.util.NoSuchElementException;

/**
 * Created by Vladislav Russinovich on 18.11.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
public class LabList<E> {
    //Узел списка
    public static class Node<E> {
        //Текущий элемент узла
        E element;
        //Предыдущий узел
        Node<E> prev;
        //Следующий узел
        Node<E> next;

        Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    int size = 0;
    Node<E> first;
    Node<E> last;

    public LabList() {
    }

    /**
     * Вставка элемента в начало списка
     *
     * @param e - элемент для вставки
     */

    private void linkFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(e, f, null);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;
    }

    /**
     * Связывает последний Е элемент
     */
    void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(e, l, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
    }

    /**
     * Вставляет элемент E до узла succ
     */
    void linkBefore(E e, Node<E> succ) {
        // assert succ != null;
        final Node<E> pred = succ.prev;
        final Node<E> newNode = new Node<>(e, pred, succ);
        succ.prev = newNode;
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        size++;
    }

    /**
     * Удаляет первый узел f.
     */
    private E unlinkFirst(Node<E> f) {
        // assert f == first && f != null;
        final E element = f.element;
        final Node<E> next = f.next;
        f.element = null;
        f.next = null; // help GC
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        return element;
    }

    /**
     * Удаляет последний узел l
     */
    private E unlinkLast(Node<E> l) {
        // assert l == last && l != null;
        final E element = l.element;
        final Node<E> prev = l.prev;
        l.element = null;
        l.prev = null; // help GC
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        size--;
        return element;
    }

    /**
     * Отвзявывет узел x.
     */
    E unlink(Node<E> x) {
        // assert x != null;
        final E element = x.element;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.element = null;
        size--;
        return element;
    }

    public E getFirst() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return f.element;
    }

    /**
     * Возвращает последний элемент в этом списке
     *
     * @return последний элемент списка
     * @throws NoSuchElementException, если список пуст
     */
    public E getLast() {
        final Node<E> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return l.element;
    }

    /**
     * Удаляет и возращает первый элемент списка
     *
     * @return первый элемент списка
     * @throws NoSuchElementException, если список пуст
     */
    public E removeFirst() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return unlinkFirst(f);
    }

    /**
     * Удаляет и возращает последний элемент списка
     *
     * @return последний элемент списка
     * @throws NoSuchElementException если список пуст
     */
    public E removeLast() {
        final Node<E> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return unlinkLast(l);
    }

    /**
     * Вставляет указанный элемент в начало списка
     *
     * @param e - элемент для добавления в список
     */
    public void addFirst(E e) {
        linkFirst(e);
    }

    /**
     * Добавляет указанный элемент в конец списка
     *
     * @param e - элемент для добавления
     */
    public void addLast(E e) {
        linkLast(e);
    }

    public int size() {
        return size;
    }

    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.element == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.element)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    Node<E> node(int index) {
        // assert isElementIndex(index);

        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.element == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.element))
                    return index;
                index++;
            }
        }
        return -1;
    }

    public E get(int index) {
        //TODO: Throw exception when index is non-valid
        return node(index).element;
    }
}
