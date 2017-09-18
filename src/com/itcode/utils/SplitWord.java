package com.itcode.utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by along on 17/6/7.
 * 用以切分字符串，如将"切分字符串，真棒！I'm a smart man!"
 * 切分为"切 分 字 符 串 真 棒 I'm a smart man "
 * 轮子：https://stackoverflow.com/questions/1675739/to-split-only-chinese-characters-in-java
 */
public class SplitWord {
    public static List<String> splitWord(String rawStr) {
        List<String> splitList = new ArrayList<>();
        StringBuffer alphabetSb = new StringBuffer();
        for (char c : rawStr.toCharArray()) {
            if (chineseUnicodeBlocks.contains(Character.UnicodeBlock.of(c))) {
                processAlphabet(splitList, alphabetSb);
                splitList.add(String.valueOf(c));
            } else if (('A' < c ||'A'==c)&& (c=='z'||c < 'z') || c == '\'' || c == ' ') {//字母
                alphabetSb.append(c);
            } else {//是特殊字符，则不与字母在一起，以免出现 )I'm    man! 这样的单词
                processAlphabet(splitList, alphabetSb);
                splitList.add(String.valueOf(c));
            }
        }
        return splitList;
    }

    public static void main(String[] args) {
        String rawText = "切分  字 符串，真棒！I'm a smart man!";
                String mixedChinese = "查詢fuck（210ＢＯＴ法）I'm a smart man!切分a字b符串，ab(ac真棒！";
        List<String> splitList = splitWord(rawText);
        System.out.println(splitList);
        System.out.println(splitWord(mixedChinese));

//        List<String> stringList = SplitWord.splitWord(value.toString());

        for(String word:splitList){
           System.out.println("----"+word);
        }

    }

    private static void processAlphabet(List<String> splitList, StringBuffer alphabetSb) {
        if (alphabetSb.toString().length() != 0) {
            splitList.addAll(Arrays.asList(alphabetSb.toString().split(" ")));
            alphabetSb.delete(0, alphabetSb.length());
        }
    }

    private static Set<Character.UnicodeBlock> chineseUnicodeBlocks = new HashSet<Character.UnicodeBlock>() {{
        add(Character.UnicodeBlock.CJK_COMPATIBILITY);
        add(Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS);
        add(Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS);
        add(Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT);
        add(Character.UnicodeBlock.CJK_RADICALS_SUPPLEMENT);
        add(Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION);
        add(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS);
        add(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A);
        add(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B);
        add(Character.UnicodeBlock.KANGXI_RADICALS);
        add(Character.UnicodeBlock.IDEOGRAPHIC_DESCRIPTION_CHARACTERS);
    }};
}
