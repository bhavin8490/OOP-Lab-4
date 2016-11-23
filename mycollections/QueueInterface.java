package mycollections;

/**
 * The interface to implement Queue
 * @author Byung Seon Kim
 *
 */
public interface QueueInterface<E> {
	/**
	 * Add the specified element to this queue
	 * @param o element to be inserted
	 * @return true if the specified element successfully added to the queue 
	 * @throws NullObjectException null object exception
	 */
	public boolean enqueue(E o) throws NullObjectException;
	/**
	 * Retrieves, but does not remove, the head of this queue
	 * @return retrieved element from the queue
	 * @throws EmptyQueueException empty queue exception
	 */
	public E peek() throws EmptyQueueException;
	/**
	 * Retrieves and removes the head of this queue
	 * @return retrieved element from the queue
	 * @throws EmptyQueueException empty queue exception
	 */
	public E dequeue() throws EmptyQueueException;
}
