package 조성찬;

/*
1233. [S/W 문제해결 기본] 9일차 - 사칙연산 유효성 검사

1. 문제 재정의 및 추상화 
DFS를 사용한 트리 순회 문제. 

자식 노드가 둘 다 없으면 leaf node이다. 

자식 노드가 하나만 있으면 오류!
자식 노드가 둘 다 없는데(leaf node인데) 값이 연산자이면 오류!
자식 노드가 둘 다 있는데 값이 정수값이면 오류! 

2. 풀이과정 상세히 적기
DFS 재귀를 사용한다. 나머지 풀이과정은 생략한다. 

3. 시간복잡도 계산 
O(V+E)

4. 코드 작성 
*/

import java.util.*;
import java.io.*;

public class SWEA1233_사칙연산유효성검사 {
	static class Node {
		int idx;
		boolean isInteger;
		int leftIdx;
		int rightIdx;

		Node(int idx, boolean isInteger, int leftIdx, int rightIdx) {
			this.idx = idx;
			this.isInteger = isInteger;
			this.leftIdx = leftIdx;
			this.rightIdx = rightIdx;
		}
	}

	int solution(Map<Integer, Node> nodeS) {
		int answer = 0;
		if (dfs(nodeS, 1)) {
			return 1;
		}
		return 0;
	}

	// 자식 노드가 둘 다 없으면 leaf node이다.
  
	// 자식 노드가 하나만 있으면 오류!
	// 자식 노드가 둘 다 없는데(leaf node인데) 값이 연산자이면 오류!
	// 자식 노드가 둘 다 있는데 값이 정수값이면 오류!
	boolean dfs(Map<Integer, Node> nodeS, int nodeIdx) {
		Node node = nodeS.get(nodeIdx);
		if (node.leftIdx == -1 && node.rightIdx == -1) {
			if (!node.isInteger) {
				return false;
			}
			return true;
		}
		if (node.leftIdx == -1 ^ node.rightIdx == -1) {
			return false;
		}
		if (node.isInteger) {
			return false;
		}
		return dfs(nodeS, node.leftIdx) && dfs(nodeS, node.rightIdx);
	}

	public static void main(String[] args) throws Exception {
		SWEA1233_사칙연산유효성검사 T = new SWEA1233_사칙연산유효성검사();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
		int tcN = 10;
		for (int tci = 1; tci <= tcN; tci++) {
			int nodeN = Integer.parseInt(kb.readLine());
			Map<Integer, Node> nodeS = new HashMap<>();
			for (int i = 0; i < nodeN; i++) {
				StringTokenizer stk = new StringTokenizer(kb.readLine());
				int idx = Integer.parseInt(stk.nextToken());
				boolean isInteger = true;
				char value = stk.nextToken().charAt(0);
				if (value == '/' || value == '*' || value == '+' || value == '-') {
					isInteger = false;
				}
				int leftIdx = -1;
				int rightIdx = -1;
				if (stk.hasMoreTokens())
					leftIdx = Integer.parseInt(stk.nextToken());
				if (stk.hasMoreTokens())
					rightIdx = Integer.parseInt(stk.nextToken());
				Node node = new Node(idx, isInteger, leftIdx, rightIdx);
				nodeS.put(idx, node);
			}
			System.out.println("#" + tci + " " + T.solution(nodeS));
		}
	}
}
