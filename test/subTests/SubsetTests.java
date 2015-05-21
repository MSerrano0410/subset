package subTests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

import subsetLogic.SubsetLogic;

/**
 * This is a small jUnit test suite with several values. Please feel free to modify the 
 * ArrayList and summation value as you see fit to test this application.
 * @author marvin
 */
public class SubsetTests {

	@Test
	public void test() {
		ArrayList<Integer> set = new ArrayList<Integer>();
		set.add(1);
		set.add(2);
		set.add(3);
		set.add(4);
		set.add(5);
		set.add(6);
		set.add(7);
		set.add(8);
		SubsetLogic sub = new SubsetLogic(set, 15);
	}

}
