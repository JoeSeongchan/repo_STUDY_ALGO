
/*
 * Strahler 순서 
 * 1. 문제 재정의 및 추상화 
 * 진입 차수 = 0 => 노드 순서(odS)=1
 * 진입 edge의 원 노드 순서(odS) 최댓값, 개수 기록 (2차원 배열)
 * 문제에서 제시하는 노드 순서 규칙 적용
 * 
 * 2. 풀이과정 상세히 적기
 * 생략 (위상정렬 알고리즘)
 * 
 * 3. 시간복잡도 계산 
 * O(V+E)
 * 
 * 4. 코드 작성 
 */
import java.util.*;
import java.io.*;

public class BJ_Strahler순서 {
	int[] finalOdS;
	int[][] odS;
	List<Integer>[] adjS;
	Queue<Integer> tskS;
	int[] inEdgeS;
	int vN;

	int solution(int vN, int eN, int[][] eS) {
		int answer = 0;
		init(vN, eN, eS);
		int lastOd = -1;
		while (!tskS.isEmpty()) {
			int v = tskS.poll();
			int od = finalOdS[v];
			lastOd = od;
			for (int adj : adjS[v]) {
				if (odS[adj][0] == -1 || odS[adj][0] < od) {
					odS[adj] = new int[] { od, 1 };
				} else if (odS[adj][0] == od) {
					odS[adj][1] += 1;
				}
				inEdgeS[adj] -= 1;
				if (inEdgeS[adj] == 0) {
					if (odS[adj][1] == 1) {
						finalOdS[adj] = odS[adj][0];
					} else {
						finalOdS[adj] = odS[adj][0] + 1;
					}
					tskS.offer(adj);
				}
			}
		}
		answer = lastOd;
		return answer;
	}

	void init(int vN, int eN, int[][] eS) {
		this.vN = vN;
		adjS = new ArrayList[vN + 1];
		inEdgeS = new int[vN + 1];
		for (int i = 1; i <= vN; i++)
			adjS[i] = new ArrayList<Integer>();
		for (int[] e : eS) {
			int v1 = e[0], v2 = e[1];
			adjS[v1].add(v2);
			inEdgeS[v2] += 1;
		}
		odS = new int[vN + 1][2];
		for (int i = 1; i <= vN; i++) {
			odS[i] = new int[] { -1, -1 };
		}
		tskS = new ArrayDeque<>();
		finalOdS = new int[vN + 1];
		for (int i = 1; i <= vN; i++) {
			if (inEdgeS[i] == 0) {
				tskS.offer(i);
				finalOdS[i] = 1;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BJ_Strahler순서 T = new BJ_Strahler순서();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
		int tcN = Integer.parseInt(kb.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tci = 1; tci <= tcN; tci++) {
			StringTokenizer stk = new StringTokenizer(kb.readLine());
			stk.nextToken();
			int vN = Integer.parseInt(stk.nextToken());
			int eN = Integer.parseInt(stk.nextToken());
			int[][] eS = new int[eN][2];
			for (int i = 0; i < eN; i++) {
				stk = new StringTokenizer(kb.readLine());
				for (int k = 0; k < 2; k++) {
					eS[i][k] = Integer.parseInt(stk.nextToken());
				}
			}
			sb.append(tci).append(' ').append(T.solution(vN, eN, eS)).append('\n');
		}
		System.out.println(sb);
	}
}