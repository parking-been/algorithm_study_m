package main.java.study.a01_ds.d02.p2504;

import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Main_박다빈 {
    static Stack<Integer> stack = new Stack<>();
    static Map<Character, Integer> map = Map.of('(',-1,
            ')',-2,
            '[',-3,
            ']',-4);
    static Map<Integer, Integer> pp = Map.of(-1,-2,
            -2,-1,
            -3,-4,
            -4,-3);
    public static void main(String [] args){
        //( : -1, ) : -2, [ : -3, ] : -4

        Scanner sc = new Scanner(System.in);
        String w = sc.next();

        int latest = 0;

        for (int i=0;i<w.length();i++){
            char c = w.charAt(i);
            int mC = map.get(c);


        }
    }




}
