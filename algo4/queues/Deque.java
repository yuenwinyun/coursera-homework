/* *****************************************************************************
 *  Name:              Yuen
 *  Coursera User ID:  123456
 *  Last modified:     07/12/2020
 **************************************************************************** */


import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Item[] queue;

    private final class DequeIterator implements Iterator<Item> {
        private int currentIndex = 0;
        private Item[] innerQueue;

        public DequeIterator(Item[] queue) {
            innerQueue = queue;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < innerQueue.length;
        }

        @Override
        public Item next() {
            if (hasNext()) {
                return innerQueue[currentIndex++];
            }
            else {
                throw new NoSuchElementException("queue is empty now.");
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove method not supported");
        }
    }

    ;

    public Deque() {
        queue = createQueue(0);
    }

    private Item[] createQueue(int n) {
        return (Item[]) new Object[n];
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return queue.length;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("argument cannot be null");
        }
        Item[] newQueue = createQueue(size() + 1);
        Arrays.setAll(newQueue, i -> {
            if (i > 0) {
                return queue[i - 1];
            }
            else {
                return item;
            }
        });
        queue = newQueue;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("argument cannot be null");
        }
        Item[] newQueue = createQueue(size() + 1);
        Arrays.setAll(newQueue, i -> {
            if (i < size()) {
                return queue[i];
            }
            else {
                return item;
            }
        });
        queue = newQueue;
    }

    public Item removeFirst() {
        if (size() == 0) {
            throw new NoSuchElementException("queue is empty");
        }
        Item[] newQueue = createQueue(size() - 1);
        Item removedItem = queue[0];
        Arrays.setAll(newQueue, i -> queue[i + 1]);
        queue = newQueue;
        return removedItem;
    }

    public Item removeLast() {
        if (size() == 0) {
            throw new NoSuchElementException("queue is empty");
        }
        Item[] newQueue = createQueue(size() - 1);
        Item removedItem = queue[size() - 1];
        Arrays.setAll(newQueue, i -> queue[i]);
        queue = newQueue;
        return removedItem;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator(queue);
    }

    public static void main(String[] args) {

        Deque<Double> deque = new Deque<>();

        deque.addFirst(0.4);
        deque.addFirst(0.3);
        deque.addFirst(0.0);
        deque.addFirst(0.0);
        deque.addFirst(0.0);
        deque.addFirst(0.2);
        deque.addFirst(0.0);

        for (Double s : deque) {
            System.out.println(s);
        }
    }
}
