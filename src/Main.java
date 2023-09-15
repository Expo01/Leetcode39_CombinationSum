import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
    }
}


class Solution {
// example, candidates = [3,4,5] target = 8
    protected void backtrack(int remain, LinkedList<Integer> comb, int start, int[] candidates, List<List<Integer>> results) {

        if (remain == 0) {
            // make a deep copy of the current combination
            results.add(new ArrayList<Integer>(comb)); // if base case reached of correct combo, then add the combo to answer list
            return;
        } else if (remain < 0) { // alternate base case where combo exceeds desired value
            // exceed the scope, stop exploration.
            return;
        }

        for (int i = start; i < candidates.length; ++i) {
            comb.add(candidates[i]); // add the number into the temporary combo
            this.backtrack(remain - candidates[i], comb, i, candidates, results); // recursive call until either
            // base case is found. until then, the for loop will NOT increment. Suppose target of 8, add to comb: 3,3,3
            // where on third add, remain <0 and base case reached, and the third 3 is removed from the temp list.
            comb.removeLast();
            // after removal of '3' is this example, we increment to index 1. recall we are still in the 3rd call of the method
            // where remain = 8 -> remain = 5 -> remain = 2. so we are on the third call, but second loop of the for loop
            // with '4' nnow as the int added to temp for testing where recursion (remain[2] - 4) results in second base case again
            // and this is removed again. same process for final loop with value of 5 in [3,3,5]

            // now how does it get back?? suppose 5 got removed frm combo, now we have [3,3] but need to remove the second
            // 3.  this hapens because on the second call of backtrack, 3 gets added a second time during the loop, calls
            // recursion to test all numbers and when all have been tried and removed from temp, the for loop recurison
            // finishes that instance and [3,3] instance moves past the backtrack and to comb.remove last to [3]
            // where it when continues to increment ints for loop testng 4,5, adding [3,5]  loop exists, [3] becomes [], etc.
        }
    }// taking a look at this again, using example of if sum is 4 and the first int in candidates[] is '1'. in the for loop,
    // 'i' is only incremented once a base case found, otherwise 1 is contiinually passed as index in new recursion.
    // eventually we will get to 4th call where 4 1's = 4 so remain 0. returns back to the prior call on the stack and
    // removes 1 from [1,1,1,1] to [1,1,1] and then the loop continues on the same recursivbe call with i = 1. we then
    // test all ints at [1,1,1,X] and reach either base case. ultimately we progress further and further back [1,1,X,X] etc.
    // until we increment the for loop 'i' in the very first method call and repeat the whole process starting frmo index 1
    // on first call. new start index is passed though so we never travser backwards to elements precidng candidates[i].


    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<List<Integer>>(); // this will store final answer which is a list of
        // lists, supposing original array of 3,4,5 this could contain (targt of 8) :  [3,5],[4,4]
        LinkedList<Integer> comb = new LinkedList<Integer>(); // will temporarily hold integers. Note this is NOT a list
        // of lists, and items will be deleted as needed from a single linked list

        this.backtrack(target, comb, 0, candidates, results); // this keyword used because of protected access mod?
        return results;
    }
}