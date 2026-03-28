package main.java.study.a01_ds.d02.p2504;
import java.util.*;
import java.io.*;

public class Main_김인송 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayDeque<String> stack = new ArrayDeque<>();
        String s = br.readLine();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[') {
                stack.push(s.charAt(i) + "");
            }

            if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    System.out.println(0);
                    return;
                }
                String pop = stack.pop();
                if (pop.equals("(")) stack.push("2");
                else if (pop.equals("[") || pop.equals(")") || pop.equals("]")) {
                    System.out.println(0);
                    return;
                }
                else{
                    if (stack.isEmpty()) {
                        System.out.println(0);
                        return;
                    }
                    String pop2 = stack.pop();
                    if (pop2.equals("(")) stack.push(String.valueOf(Integer.parseInt(pop) * 2));
                    else if (pop2.equals("[") || pop2.equals(")") || pop2.equals("]")) {
                        System.out.println(0);
                        return;
                    }
                    else{
                        if (stack.isEmpty()) {
                            System.out.println(0);
                            return;
                        }
                        String pop3 = stack.pop();
                        if (pop3.equals("(")) stack.push(String.valueOf(Integer.parseInt(pop) * 2 + Integer.parseInt(pop2) * 2));
                        else if (pop3.equals("[") || pop3.equals(")") || pop3.equals("]")) {
                            System.out.println(0);
                            return;
                        }
                    }

                }

            }




        }






    }
}


