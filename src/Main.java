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
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<List<Integer>>(); // this will store final answer which is a list of
        // lists, supposing original array of 3,4,5 this could contain (targt of 8) :  [3,5],[4,4]
        LinkedList<Integer> comb = new LinkedList<Integer>(); // will temporarily hold integers. Note this is NOT a list
        // of lists, and items will be deleted as needed from a single linked list

        this.backtrack(target, comb, 0, candidates, results); // this keyword used because of protected access mod?
        return results;
    }
}