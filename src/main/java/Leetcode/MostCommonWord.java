package Leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author szh
 * @create 2018-08-18 23:31
 **/
public class MostCommonWord {
    public static void main(String[] args) {
        new MostCommonWord().mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", new String[]{"hit"});
    }

    public String mostCommonWord(String paragraph, String[] banned) {
        String[] words = paragraph.split(" ");
        Set<String> set = new HashSet<>();
        for (String s : banned) {
            set.add(s);
        }
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            StringBuffer sb = new StringBuffer();
            char[] cArray = word.toCharArray();
            for (char c : cArray) {
                if (Character.isLetter(c)) {
                    sb.append(c);
                }
            }
            // word= word.replace(",","");
            // word= word.replace(".","");
            word = sb.toString();
            word = word.toLowerCase();
            map.put(word, map.get(word) == null ? 1 : map.get(word) + 1);
        }
        for (String s : set) {
            map.remove(s);
        }
        String max = null;
        int maxNum = 0;
        for (Map.Entry temp : map.entrySet()) {
            if ((int) temp.getValue() > maxNum) {
                maxNum = (int) temp.getValue();
                max = (String) temp.getKey();
            }
        }
        return max;
    }
}
