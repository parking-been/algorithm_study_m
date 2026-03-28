package main.java.study.a03_gt.d01.p2606;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_구연우 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 컴퓨터의 수
        int m = Integer.parseInt(br.readLine()); // 연결된 쌍의 수

        visited = new boolean[n+1];

        // 연결 정보를 이중 리스트에 저장
        links = new ArrayList<>();

        // 노드 개수 만큼 내부 리스트 생성 -> 1번 인덱스에 1번 노드를 저장하기 위해 n+1개 생성 (노드 개수+1)
        for (int i=0; i<=n; i++) {
            links.add(new ArrayList<>());
        }

        for (int i=0; i<m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            int edge = Integer.parseInt(st.nextToken());

            // 노드와 간선의 연결 정보 리스트에 각각 추가해줌
            links.get(node).add(edge);
            links.get(edge).add(node);
        }

        dfs(1);
        System.out.print(cnt - 1); // 시작 노드(1번 컴퓨터)는 제외

    }

    static ArrayList<ArrayList<Integer>> links; // 연결 정보 저장
    static boolean[] visited; // 방문 여부 저장
    static int cnt; // 연결된 개수 저장

    static void dfs(int cur) {
        visited[cur] = true; // 현재 노드 방문 처리
        cnt++; // 방문한 노드는 1번과 연결된 노드 -> 카운트 추가
        for (int next : links.get(cur)) {
            if (!visited[next]) dfs(next);
        }
    }

}
