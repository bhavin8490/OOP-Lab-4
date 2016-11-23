package testcollections;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	  Test_Queue.class
})

public class AllTests {

	public static void main( String[] args ) {
		System.out.println( "Executing AllTests ..." );
		JUnitCore junit = new JUnitCore();
		junit.addListener( new TextListener(System.out) );
		Result result = junit.run( Test_Queue.class ); 
		if (result.getFailureCount() > 0) {
			System.out.println( "Test failed." );
		} else {
			System.out.println( "Test finished successfully." );
		}
	}

}
