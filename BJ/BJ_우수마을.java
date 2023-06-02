
/*
 * 우수마을
 * 1. 문제 재정의 및 추상화
 * Tree + DP. 처음 풀어보는 데 풀 수 있을까?
 * 인접 정보가 주어진다. (간선 형태로)
 * dp[i][0] : i번째 마을을 우수마을로 선정하지 않는 경우, 우수마을 주민수 총합 최댓값
 * dp[i][1] : i번째 마을을 우수마을로 선정하는 경우, 우수마을 주민수 총합 최댓값
 * Top-Down으로 리프노드까지 도달할 때까지 DFS 수행한 다음, dp 배열 초기화한다. 
 * 점화식
 * dp[부모노드][0]=자식 노드 우수마을 선정 O, X
 * dp[부모노드][1]=자식 노드들은 모두 우수마을로 선정되어선 안 된다. dp[자식노드][0]+...+v[부모노드]
 * 
 * 참고 : https://wnwngus.tistory.com/54
 * 2. 풀이과정 상세히 적기
 * 생략
 * 
 * 3. 시간복잡도 계산
    O(N)
 * 아슬아슬하게 통과할 것 같음
 * 
 * 4. 코드 작성 
 */
import java.util.*;
import java.io.*;

public class BJ_우수마을 {

    int cityN, eN;
    int[] cityS;
    List<Integer>[] adjS;
    int[][] dp;
    boolean[] visited;

    int solution(int cityN, int[] cityS, int eN, int[][] eS) {
        int answer = 0;
        init(cityN, cityS, eN, eS);
        int root = 1;
        dfs(root);
        answer = Math.max(dp[root][0], dp[root][1]);
        // for (int i = 1; i <= cityN; i++) {
        // for (int k = 0; k < 2; k++) {
        // System.out.printf("%d ", dp[i][k]);
        // }
        // System.out.println();
        // }
        return answer;
    }

    void init(int cityN, int[] cityS, int eN, int[][] eS) {

        this.cityN = cityN;
        this.cityS = cityS;
        this.eN = eN;

        adjS = new ArrayList[cityN + 1];
        for (int i = 1; i <= cityN; i++) {
            adjS[i] = new ArrayList<Integer>();
        }

        for (int[] e : eS) {
            int v1 = e[0], v2 = e[1];
            adjS[v1].add(v2);
            adjS[v2].add(v1);
        }

        dp = new int[cityN + 1][2];
        visited = new boolean[cityN + 1];
    }

    void dfs(int nd) {
        visited[nd] = true;
        for (int adj : adjS[nd]) {
            if (visited[adj])
                continue;
            dfs(adj);
            dp[nd][0] += Math.max(dp[adj][0], dp[adj][1]);
            dp[nd][1] += dp[adj][0];
        }
        dp[nd][1] += cityS[nd];
    }

    public static void main(String[] args) throws Exception {
        BJ_우수마을 T = new BJ_우수마을();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
        int cityN = Integer.parseInt(kb.readLine());
        int[] cityS = new int[cityN + 1];
        StringTokenizer stk = new StringTokenizer(kb.readLine());
        for (int i = 1; i <= cityN; i++) {
            cityS[i] = Integer.parseInt(stk.nextToken());
        }
        int eN = cityN - 1;
        int[][] eS = new int[eN][2];
        for (int i = 0; i < eN; i++) {
            stk = new StringTokenizer(kb.readLine());
            for (int k = 0; k < 2; k++) {
                eS[i][k] = Integer.parseInt(stk.nextToken());
            }
        }
        System.out.println(T.solution(cityN, cityS, eN, eS));
    }
}
