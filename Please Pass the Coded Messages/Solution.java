import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class Solution {
    public static int solution(int[] l) {
        var summed = sum(l);
        var remainder = summed % 3;
       
        if(remainder == 0) {
            return combine(convert(l));
        }
        Integer[] workableArray = convert(l);
        
        var remove1s = sortAndRemoveWhereRemainder(workableArray, 1);
        var remove2s = sortAndRemoveWhereRemainder(workableArray, 2);
        if(remainder == 1) {
            if(remove1s.length >= 1) {
                workableArray = popItemWhereEquals(workableArray, remove1s[0]);
            }
            else if (remove2s.length >= 2) {
                workableArray = popItemWhereEquals(workableArray, remove2s[0]);
                workableArray = popItemWhereEquals(workableArray, remove2s[1]);
            }
            else {
                return 0;
            }
        }
        else {
            if(remove2s.length >= 1) {
                workableArray = popItemWhereEquals(workableArray, remove2s[0]);
            } else if(remove1s.length >= 2) {
                workableArray = popItemWhereEquals(workableArray, remove1s[0]);
                workableArray = popItemWhereEquals(workableArray, remove1s[1]);
            }
            else {
                return 0;
            }
        }
        
        if(workableArray.length == 0) {
            return 0;
        }
        
        return combine(workableArray);
    }

    public static Integer[] popItemWhereEquals(Integer[] inputArray, Integer value) {
        return removeFirst(inputArray, value);
    }
    
    public static <TypeOfObject> TypeOfObject[] removeFirst(TypeOfObject[] array, TypeOfObject valueToRemove) {
        TypeOfObject[] result = Arrays.copyOf(array, array.length - 1);
        List<TypeOfObject> tempList = new ArrayList<>();
        tempList.addAll(Arrays.asList(array));
        tempList.remove(valueToRemove);
        return tempList.toArray(result);
    }

    public static Integer[] sortAndRemoveWhereRemainder(Integer[] inputArray, int remainder) {
        Arrays.sort(inputArray);
        
        return Arrays.stream(inputArray).filter(i -> i % 3 == remainder).toArray(Integer[]::new);
    }
    
    public static int sum(int[] l) {
        return IntStream.of(l).sum();
    }
    
    public static Integer[] convert(int[] l) {
        return IntStream.of(l).boxed().toArray(Integer[]::new);
    }
    
    public static int combine(Integer[] l) {
        Arrays.sort(l, Collections.reverseOrder());
        
        return Integer.parseInt(Arrays.stream(l).map(Object::toString).collect(Collectors.joining(""))); 
    }
}
