package testcollections;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import mycollections.EmptyQueueException;
import mycollections.NullObjectException;
import mycollections.Queue;

/**
 * The Test_Queue is the JUnit test program to test the Queue ADT class from the "mycollections" package,
 * including throwing of exceptions. The test should include the enqueue'ing and dequeue'ing of at least
 * 1 million elements and one of 100,000 elements. The test provides timings (displayed to the user) for each of
 * these two tests to track the time of total enqueues, total dequeues and total run time(s).
 * 
 * @author Byung Seon Kim
 */
@FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class Test_Queue {
	
//	@Rule
//    public Stopwatch stopwatch = new Stopwatch() {
//		/** Invoked when a test succeed */
//		protected void succeeded( long nanos, Description description ) {
//            System.out.println(
//            		String.format("\t%s succeeded, time taken %d msec", description.getMethodName(),
//            				TimeUnit.NANOSECONDS.toMillis(nanos)));
//        }
//        /** Invoked when a test fails */
//        protected void failed(long nanos, Throwable e, Description description) { }
//        /** Invoked when a test is skipped due to a failed assumption. */
//        protected void skipped(long nanos, AssumptionViolatedException e, Description description) { }
//        /** Invoked when a test method finishes (whether passing or failing) */
//        protected void finished(long nanos, Description description) { }
//    };

	/**
 	 * Test the Queue() Constructor.
 	 */
	@Test
	public void testAConstructors() {
		System.out.println("\nExecuting Test_Queue.testConstructors");
		Queue<String> queue = new Queue<>();
		msg = "\tTest_Queue.testConstructors: Expected queue is null";
		assertNotNull(msg, queue);
	}
	
	/**
	 * Test the enqueue() and dequeue() 
	 */
	@Test
	public void testBQueue() {
		String seedStr[] = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten" };
		Integer seedInt[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		
		System.out.println("\nExecuting Test_Queue.testQueue");
		Queue<String> qStr = new Queue<>();
		msg = "\tTest_Queue.testQueue: Expected queue is null";
		assertNotNull(msg, qStr);
		
		Queue<Integer> qInt = new Queue<>();
		msg = "\tTest_Queue.testQueue: Expected queue is null";
		assertNotNull(msg, qInt);
		
		// after queue'ing 10 data, test to same original data and dequque'ing data
		for ( int i=0; i < 10; i++ ) {
			try {
				qStr.enqueue( seedStr[i] );
				qInt.enqueue( seedInt[i] );
			} catch ( NullObjectException ex ) {
				System.out.println( ex.getMessage() );
			}
		}
		
		for ( int i=0; i < 10; i++ ) {
			String targetStr;
			Integer targetInt;
			try {
				targetStr = qStr.dequeue();
				msg = "\tTest_Queue.testQueue: Expected false";
				assertTrue( msg, targetStr.equals(seedStr[i]) );
				targetInt = qInt.dequeue();
				assertTrue( msg, targetInt.equals(seedInt[i]) );
			} catch ( EmptyQueueException ex ) {
				System.out.println( ex.getMessage() );
			}
		}
	}
	
	/**
	 * Test the NullObjectException class
	 */
	@Test
	public void testCNullObjectException() {
		System.out.println( "\nExecuting Test_Queue.testNullObjectException" );
		Queue<String> queue = new Queue<>();
		try {
			queue.enqueue( null );
	        fail( "Expected an NullObjectException to be thrown" );
	    } catch ( NullObjectException ex ) {
	        assertThat( ex.getMessage(), is( "ERROR: NullObjectException - can't use null object" ));
	    }
	}

	/**
	 * Test the EmptyQueueException class
	 */
	@Test
	public void testDEmptyQueueException() {
		System.out.println("\nExecuting Test_Queue.testEmptyQueueException");
		Queue<String> queue = new Queue<>();
		try {
			queue.dequeue();
	        fail( "Expected an EmptyQueueException to be thrown" );
	    } catch ( EmptyQueueException ex ) {
	        assertThat( ex.getMessage(), is( "ERROR: EmptyQueueException - empty queue" ));
	    }
	}
	
	/* ATTRIBUTES	---------------------------------------------------------------- the skeleton from Reginald Dyer */
	/** the attribute to display message string */
	String msg;
	
} /* End of CLASS:	Test_Queue.java	*/