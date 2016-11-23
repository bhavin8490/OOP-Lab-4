package mycollections;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The Queue implements the QUEUE ADT (abstract data type) for a Queue implementation
 * @author Byung Seon Kim
 *
 */
@SuppressWarnings("serial")
public class Queue<E> extends ArrayList<E> implements QueueInterface<E> {

	/* CONSTRUCTOR	---------------------------------------------------------------- the skeleton from Reginald Dyer */
	/** Default constructor of Queue class */
	public Queue() { }

	/* ACCESSORS	---------------------------------------------------------------- the skeleton from Reginald Dyer */
	/* MODIFIERS	---------------------------------------------------------------- the skeleton from Reginald Dyer */
	/* NORMAL BEHAVIOR	------------------------------------------------------------ the skeleton from Reginald Dyer */
	/**
	 * Add the specified element to this queue
	 * @param o element to be inserted
	 * @return true if the specified element successfully added to the queue 
	 */
	@Override
	public synchronized boolean enqueue(E o) throws NullObjectException {
		if ( o == null ) {
			throw new NullObjectException( "ERROR: NullObjectException - can't use null object" );
		}
		this.add( o );
		notifyAll();
		return true;
	}

	/**
	 * Retrieves, but does not remove, the head of this queue
	 * @return retrieved element from the queue
	 */
	@Override
	public E peek() throws EmptyQueueException {
		if ( isEmpty() ) {
			throw new EmptyQueueException( "ERROR: EmptyQueueException - empty queue" );
		}
		return this.get( 0 );
	}

	/**
	 * Retrieves and removes the head of this queue
	 * @return retrieved element from the queue
	 */
	@Override
	public synchronized E dequeue() throws EmptyQueueException {
		E o = peek();
		this.remove( 0 );
		return o;
	}
	
	/**
	 * Returns the number of elements in this queue.
	 * @return the number of elements in this queue
	 */
	@Override
	public int size() {
		return super.size();
	}
	
	/**
	 * Removes all of the elements from this queue. The queue will be empty after this call returns.
	 */
	@Override
	public void clear() {
		super.clear();
	}
	
	/**
	 * Returns a list iterator over the elements in this queue (in proper sequence), 
	 * starting at the specified position in the queue. The specified index indicates the first element 
	 * that would be returned by an initial call to next. An initial call to previous would return the element 
	 * with the specified index minus one
	 * @return a list iterator over the elements in this queue (in proper sequence), starting at the specified position in the queue
	 */
	@Override
	public Iterator<E> iterator() {
		return super.iterator();
	}
	
	/**
	 * Removes the first occurrence of the specified element from this queue, if it is present. 
	 * If the queue does not contain the element, it is unchanged. More formally, removes the element 
	 * with the lowest index i such that (o==null ? get(i)==null : o.equals(get(i))) (if such an element exists). 
	 * Returns true if this list contained the specified element (or equivalently, if this list changed as a result of the call).
	 * @param o  element to be removed from this queue, if present
	 * @return true if this queue contained the specified element
	 */
	@Override
	public boolean remove(Object o) {
		return super.remove(o);
	}
	
	/**
	 * Returns true if this queue contains the specified element. More formally, returns true if and only 
	 * if this queue contains at least one element e such that (o==null ? e==null : o.equals(e)).
	 * @param o element whose presence in this queue is to be tested
	 * @return true if this queue contains the specified element
	 */
	@Override
	public boolean contains(Object o) {
		return super.contains(o);
	}
	/* HELPER METHODS	------------------------------------------------------------ the skeleton from Reginald Dyer */
	/* ENTRY POINT for STAND-ALONE OPERATION	------------------------------------ the skeleton from Reginald Dyer */
	/* ATTRIBUTES	---------------------------------------------------------------- the skeleton from Reginald Dyer */
}
