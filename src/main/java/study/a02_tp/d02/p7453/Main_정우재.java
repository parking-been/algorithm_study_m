package main.java.study.a02_tp.d02.p7453;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_정우재 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int size = Integer.parseInt(st.nextToken());

        int[][] arr = new int [][] {
                new int[size],
                new int[size],
                new int[size],
                new int[size]
        };

        for (int index = 0 ; index < size ; index ++) {
            st = new StringTokenizer(in.readLine());
            for (int arrNumber = 0 ; arrNumber < 4 ; arrNumber++) {
                arr[arrNumber][index] = Integer.parseInt(st.nextToken());
            }
        }

        int mergedArrLength = size * size;
        int[] AB = new int[mergedArrLength];
        int[] CD = new int[mergedArrLength];

        int index = 0;
        for(int i = 0; i < size ; i++) {
            for (int j = 0; j < size ; j++) {
                AB[index] = arr[0][i] + arr[1][j];
                CD[index] = arr[2][i] + arr[3][j];
                index++;
            }
        }

        Arrays.sort(AB);
        Arrays.sort(CD);

        int start = 0;
        int end = mergedArrLength-1;
        long count = 0;

        while(start < mergedArrLength && end >= 0 ) {
            int result = AB[start] + CD[end];


            if(result == 0) {
                int abValue = AB[start];
                int cdValue = CD[end];
                int abSameValueCount = 0;
                int cdSameValueCount = 0;
                while ( start < mergedArrLength && AB[start] == abValue) {
                    abSameValueCount++;
                    start++;
                }

                while ( end >= 0 && CD[end] == cdValue) {
                    cdSameValueCount++;
                    end--;
                }

                count += (long)abSameValueCount * cdSameValueCount;
                continue;
            }

            if(result > 0) {
                end--;
                continue;
            }

            start++;
        }

        System.out.println(count);
    }

}
