package subsetLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class that performs a summation by:
 * 	- starting at the beginning of the Integer set
 *  - adding the current set index to the total sum
 *  - checking the difference between the summation value and the current sum;
 *    if that value is in the result set, add it into the subset and the sum.
 *  - the difference usually results in the sum == the summation. 
 *  - This code checks for several special cases:
 *  	- If the sum goes over the summation, we've gone too far; in this case,
 *  	  and in the case of the sum == summation, we remove the 2nd element in
 *  	  the Integer set copy. This is because we only modify the primary
 *        Integer set when we want the "base" number to go to the next [0] number.
 *      - Example: for a summation of 10 and an Integer set of 1-6, we'd start
 *        at 1 and add until the difference is found in the set. In this case, when 
 *        we get to 3, 10-6 = 4, which is in the set. So, 4 gets added to there and
 *        we have {1, 2, 3, 4}. If we remove the 2, we add until the difference is found.
 *        In that case, that happens when we hit 3 (1+3=4, 10-4=6, which is in the set), we
 *        add it in, so we get {1,3,6}. 
 *      - If we go over the summation at any point during the iteration, we'll remove the 2nd
 *        element and perform the above logic from the first element.
 *      - As values are added to the sum and the sum != summation, the copy of the Integer list
 *        will dwindle as the added elements will be removed.
 *      - This will continue until there is only one element in the list. Once we reach the end 
 *        we go back to the initial Integer list, remove the first element, and continue.
 *      - Example: In the above example, when we hit "1, 6" and then "1", we'll loop back to "2,3,4,5,6".
 *        This is done to rule out any combination of 1 or 2 numbers in the set that might 
 *        equal the summation.
 * @author marvin
 *
 */
public class SubsetLogic {
	public SubsetLogic(ArrayList<Integer> intSet, int summation) {
		this.intSet = intSet;
		Collections.sort(this.intSet);
		this.summation = summation;
		doSummation(new ArrayList<Integer>(), new ArrayList<Integer>(this.intSet),0, 0);
	}
	
	protected boolean doSummation(ArrayList<Integer> subSet, ArrayList<Integer> intCopy, int sum, int sIndex) {
		try {
			if(!(intSet.get(0) == Math.round(summation / 2))) {
				if(subSet.isEmpty())
					subSet = new ArrayList<Integer>();
				
				if(intCopy.isEmpty())
					intCopy = new ArrayList<Integer>(intSet);
				
				if(sum == summation)
					printResults(subSet);
				
				else if(sum < summation) {
					int current = intCopy.get(sIndex);
					sum += current;
					subSet.add(current);
					int diff = Math.abs(summation - sum);
					if(intCopy.contains(diff) && !subSet.contains(diff)) {
						sum += diff;
						subSet.add(diff);
					}
							
					if(intCopy.size() > 1 && sIndex < intCopy.size() - 1)
						doSummation(subSet, intCopy, sum, sIndex + 1);
				}
							
				else {//if sum > summation
					if(intCopy.size() == 1) {//last element
						intSet.remove(0);
						intCopy.clear();
						subSet.clear();
						doSummation(subSet, intCopy, 0, 0);
					}
								
					intCopy.remove(1);
					subSet.clear();
					doSummation(subSet, intCopy, 0, 0);
				}
						
				if(intCopy.size() > 1) {
					subSet.clear();
					intCopy.remove(1);//explained in comments above.
					//no sense continuing if this value is above this value; after this value, 
					//it'll just be the same sets in reverse.
					doSummation(subSet, intCopy, 0, 0);
				}
						
				else {//if last element, it'll go to the next number.
					if(intSet.size() > 1) {
						intSet.remove(0);
						intCopy.clear();
						subSet.clear();
						doSummation(subSet, intCopy, 0, 0);
					}
					
					else {
						if(intSet.get(0) == summation) {
							subSet.clear();
							subSet.add(intSet.get(0));
							printResults(subSet);
						}
					}	
				}
			}
			return true; //only reached when doSummation not called recursively.
		}
		
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private void printResults(ArrayList<Integer> results) {
		String allInts = "";
		for (int i : results) {
			allInts += i + " ";
		}
		System.out.println("Result set: " + allInts + ".");
	}
	
	private ArrayList<Integer> intSet;
	private int summation;
}
