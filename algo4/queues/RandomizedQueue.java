/* *****************************************************************************
 *  Name:              Yuen
 *  Coursera User ID:  123456
 *  Last modified:     06/12/2020
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int size;

    private final class QueueIterator implements Iterator<Item> {
        private Item[] queueIterator;
        private int iteratorIndex = 0;

        public QueueIterator(Item[] queue, int size) {
            queueIterator = createQueue(size);

            for (int i = 0; i < queueIterator.length; i++) {
                queueIterator[i] = queue[i];
            }

            for (int j = 1; j < queueIterator.length; j++) {
                int randIndex = StdRandom.uniform(j + 1);
                Item temp = queueIterator[j];
                queueIterator[j] = queueIterator[randIndex];
                queueIterator[randIndex] = temp;
            }
        }

        @Override
        public boolean hasNext() {
            return iteratorIndex < queueIterator.length;
        }

        @Override
        public Item next() {
            if (hasNext()) {
                Item item = queueIterator[iteratorIndex];
                iteratorIndex++;
                return item;
            }
            else {
                throw new NoSuchElementException("queue is empty");
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove method not supported");
        }
    }

    public RandomizedQueue() {
        this.queue = createQueue(1);
        this.size = 0;
    }

    private Item[] createQueue(int n) {
        return (Item[]) new Object[n];
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public Item sample() {
        if (size() == 0) {
            throw new NoSuchElementException("queue is empty");
        }
        return queue[StdRandom.uniform(size())];
    }

    public Item dequeue() {
        if (size() == 0) {
            throw new NoSuchElementException("queue is empty");
        }

        int randomIndex = StdRandom.uniform(size());
        Item removedItem = queue[randomIndex];

        size--;

        queue[randomIndex] = queue[size()];
        queue[size()] = null;

        // TODO: need review algorithm of this
        if (queue.length > 4 && size() <= queue.length / 4) {
            Item[] resizedQueue = createQueue(queue.length / 2);

            for (int i = 0; i < size(); i++) {
                resizedQueue[i] = queue[i];
            }

            queue = resizedQueue;
        }

        return removedItem;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("argument cannot be null");
        }

        // TODO: need review algorithm of this
        if (this.size == queue.length) {
            Item[] resizedQueue = createQueue(queue.length * 2);

            for (int i = 0; i < queue.length; i++) {
                resizedQueue[i] = queue[i];
            }

            this.queue = resizedQueue;
        }

        queue[size] = item;
        this.size++;
    }

    public Iterator<Item> iterator() {
        return new QueueIterator(queue, size);
    }

    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();

        randomizedQueue.enqueue("A");
        randomizedQueue.enqueue("B");
        randomizedQueue.enqueue("C");
        randomizedQueue.enqueue("D");
        randomizedQueue.enqueue("E");
        randomizedQueue.enqueue("F");
        randomizedQueue.enqueue("G");


        for (String s : randomizedQueue) {
            System.out.println(s);
        }
    }
}
