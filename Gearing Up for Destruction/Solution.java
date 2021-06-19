import java.util.Arrays;
import java.util.ArrayList;

public class Solution {
    public static int[] solution(int[] pegs) {
        int[] failureResult = new int[] {-1, -1};
        
        //First we should work out the ratio for the first peg.
        var firstPegRatio = pegs[1] - pegs[0] - 1;
        
        //Now we check over all the possible ratios for the other pegs,
        //Because we already know the first peg, we know we can use that as a max value
        for(int possibleGearSize = 1; possibleGearSize < firstPegRatio; possibleGearSize++) {
            ArrayList<Integer> gears = new ArrayList<Integer>();
            gears.add(possibleGearSize);
            
            for(int pegIndex = 1; pegIndex < pegs.length; pegIndex++) {
                gears.add(pegs[pegIndex] - (pegs[pegIndex-1] + gears.get(gears.size()-1)));
            }
            
            //Lets check if any of the gears are invalid
            //We cannot have a gear with a non-positive ratio,
            //so we will just skip the rest of this calucation
            var negativeGears = gears.stream().filter(gear -> gear <= 0).toArray(Integer[]::new);
            if(negativeGears.length > 0) {
                continue;
            }
            
            var lastGear = 2 * gears.get(gears.size()-1);
            if(possibleGearSize == lastGear) {
                return new int[] {possibleGearSize, 1};
            }
            
            if(possibleGearSize + 1 == lastGear) {
                return new int[] {(possibleGearSize * 3) + 1, 3};
            } else if(possibleGearSize + 2 == lastGear) {
                return new int[] {(possibleGearSize * 3) + 2, 3};
            }
        }
        
        return failureResult;
    }
}
