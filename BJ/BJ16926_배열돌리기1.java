package 조성찬;

/*
배열 돌리기 1 (BJ16926)

1. 문제 재정의 및 추상화 
가장 바깥 둘레를 돌리고, 그 안에 있는 배열을 재귀 호출문에 넘긴다. 
배열을 한 번 돌리기 위해서 위 작업을 수행한다. 
N번 배열을 돌려야 한다면 위 작업을 N번 수행해야 한다. 

2. 풀이과정 상세히 적기
재귀 사용 생략 

3. 시간복잡도 계산 
O(가로*세로*회전 횟수)

4. 코드 작성 
*/

import java.util.*;
import java.io.*;

public class Solution {
	int[][] map;
	int rowN;
	int colN;

	int[][] solution(int[][] map, int rowN, int colN, int turnN) {
		init(map, rowN, colN);
		for (int i = 0; i < turnN; i++) {
			turn(0, rowN - 1, 0, colN - 1);
		}
		return map;
	}

	void init(int[][] map, int rowN, int colN) {
		this.map = map;
		this.rowN = rowN;
		this.colN = colN;
	}

	void turn(int startRowIdx, int endRowIdx, int startColIdx, int endColIdx) {
		if (startRowIdx > endRowIdx || startColIdx > endColIdx)
			return;
		int rowIdx = startRowIdx;
		int colIdx = startColIdx + 1;
		int idx = 0;
		int firstElem = map[startRowIdx][startColIdx];
		while (true) {
			if (rowIdx == startRowIdx && colIdx > startColIdx && colIdx <= endColIdx) {
				map[rowIdx][colIdx - 1] = map[rowIdx][colIdx];
				if (colIdx == endColIdx) {
					rowIdx += 1;
					continue;
				}
				colIdx += 1;
			} else if (rowIdx > startRowIdx && rowIdx <= endRowIdx && colIdx == endColIdx) {
				map[rowIdx - 1][colIdx] = map[rowIdx][colIdx];
				if (rowIdx == endRowIdx) {
					colIdx -= 1;
					continue;
				}
				rowIdx += 1;
			} else if (rowIdx == endRowIdx && colIdx >= startColIdx && colIdx < endColIdx) {
				map[rowIdx][colIdx + 1] = map[rowIdx][colIdx];
				if (colIdx == startColIdx) {
					rowIdx -= 1;
					continue;
				}
				colIdx -= 1;
			} else if (rowIdx > startRowIdx && rowIdx < endRowIdx && colIdx == startColIdx) {
				map[rowIdx + 1][colIdx] = map[rowIdx][colIdx];
				rowIdx -= 1;
			} else {
				map[rowIdx + 1][colIdx] = firstElem;
				break;
			}
		}

		turn(startRowIdx + 1, endRowIdx - 1, startColIdx + 1, endColIdx - 1);
	}

	public static void main(String[] args) throws Exception {
		Solution T = new Solution();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(kb.readLine());
		int rowN = Integer.parseInt(stk.nextToken());
		int colN = Integer.parseInt(stk.nextToken());
		int turnN = Integer.parseInt(stk.nextToken());
		int[][] map = new int[rowN][colN];
		for (int i = 0; i < rowN; i++) {
			stk = new StringTokenizer(kb.readLine());
			for (int k = 0; k < colN; k++) {
				map[i][k] = Integer.parseInt(stk.nextToken());
			}
		}
		StringBuilder sb = new StringBuilder();
		int[][] answer = T.solution(map, rowN, colN, turnN);
		for (int i = 0; i < rowN; i++) {
			for (int k = 0; k < colN; k++) {
				sb.append(answer[i][k]).append(' ');
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
}
