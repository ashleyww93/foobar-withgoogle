import java.util.HashMap;
import java.util.Map;
import java.lang.StringBuilder;

class Solution {
    public static Map<Character, String> brailleAlphabet = new HashMap<Character, String>() {{
        put('a', "100000");
        put('b', "110000");
        put('c', "100100");
        put('d', "100110");
        put('e', "100010");
        put('f', "110100");
        put('g', "110110");
        put('h', "110010");
        put('i', "010100");
        put('j', "010110");
        put('k', "101000");
        put('l', "111000");
        put('m', "101100");
        put('n', "101110");
        put('o', "101010");
        put('p', "111100");
        put('q', "111110");
        put('r', "111010");
        put('s', "011100");
        put('t', "011110");
        put('u', "101001");
        put('v', "111001");
        put('w', "010111");
        put('x', "110011");
        put('y', "101111");
        put('z', "101011");
        put(' ', "000000");
    }};
    
    public static String solution(String s) { 
        StringBuilder stringBuilder = new StringBuilder();
        for (char ch: s.toCharArray()) {
            if (Character.isUpperCase(ch)){
                stringBuilder.append("000001");
            };
            stringBuilder.append(brailleAlphabet.get(Character.toLowerCase(ch)));
        }
        return stringBuilder.toString();
    }
}
