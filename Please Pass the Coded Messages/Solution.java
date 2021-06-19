import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.IntStream;

public class Solution {
    public static int solution(int[] l) {
        int possibleValue = solve(l);
        
        if(possibleValue != 0) {
            return possibleValue;
        }
        
        for(int index = 0; index < l.length; index++) {
           int[] arrayWithExcluded = removeItemAtIndex(l, index);
            possibleValue = solve(arrayWithExcluded);
            
            if(possibleValue != 0) {
                return possibleValue;
            }
        }

        return 0;
    }
    
    public static int solve(int[] l) {
        List<Integer> returnList = getPermutations(l, 0);
        Collections.sort(returnList, new ReverseComparator());
        
        for(Integer permutation : returnList) {
            if(permutation % 3 == 0) {
                return permutation;
            }
        }
        
        return 0;
    }
    
    public static List<Integer> getPermutations(int[] inputArray, int start) {
        List<Integer> returnList = new ArrayList<Integer>();
        
        for(int i = start; i < inputArray.length; i++){
            int temp = inputArray[start];
            inputArray[start] = inputArray[i];
            inputArray[i] = temp;
            List<Integer> recursiveResult = getPermutations(inputArray, start + 1);
            returnList.addAll(recursiveResult);
            inputArray[i] = inputArray[start];
            inputArray[start] = temp;
        }
        
        if (start == inputArray.length - 1) {
            StringBuilder sb = new StringBuilder();
            Arrays.stream(inputArray).forEach(element -> sb.append(element));
            returnList.add(Integer.parseInt(sb.toString()));
        }
        
        return returnList;
    }
    
    public static int[] removeItemAtIndex(int[] arr,int index)
    {
        if (arr == null || index < 0 || index >= arr.length) {
            return arr;
        }
  
        return IntStream.range(0, arr.length)
            .filter(i -> i != index)
            .map(i -> arr[i])
            .toArray();
    }
  
    static class ReverseComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    }
}
