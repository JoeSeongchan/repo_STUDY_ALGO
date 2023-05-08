import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/*
 * 문제 2. 통나무 옮기기
 * 1. 문제 재정의 및 추상화 
 * 눕혀져 있는 통나무를 옮기려고 한다. 목표지점까지 옮기는데 필요한 이동 및 회전 횟수를 구하여라. 
 * 만약 이동이 불가능하다면 0을 출력한다. 모든 경우를 탐색한다. 완전 탐색 알고리즘을 적용하면 문제를 
 * 풀 수 있을 것이다! 단계마다 5가지의 선택을 할 수 있다. 이동 , 회전 
 * 그런데 이전에 방문했던 곳을 다시 방문하는 것은 허용하지 않는다. (회전 상태, 위치 기억 )
 * BFS로 문제를 푼다! 이전에 방문했던 곳을 어떻게 표시할 것인가? (= 회전상태, 위치를 어떻게 기억할 것인가?)
 * 각 위치마다 길이가 2인 boolean 배열을 배치한다. 가로로 방문했는지, 세로로 방문했는지를 체크할 때 사용한다. 
 * 공간복잡도는 괜찮을까? 2*2500=5000byte=5kb 괜찮다! 
 * 시간복잡도는 괜찮을까? O((N^2)*2) = O(N^2) 괜찮다!
 * 
 * 2. 풀이과정 상세히 적기
 * 생략
 * 
 * 3. 시간복잡도 계산 
 * O(N^2)
 * N: 평지의 폭
 * 
 * 4. 코드 작성 
 * */

// 문제 풀이 클래스 
public class BJ_통나무옮기기 {

	// 방문 여부 기록 배열 
	boolean[][][] visited;
	// 거리 기록 
	int[][][] distS;
	int width;	// 폭
	char[][] map; // 지도 

	int[] startPos; // 출발점
	int startOri; // 출발시 방향
	int[] endPos; // 도착점
	int endOri; // 도착시 방향

	final int HORI = 0; // 수평방향
	final int VERT = 1; // 수직방향

	// 사방 이동 시 row index, column index 변동 값 배열 
	final int[] dri = new int[] { 1, 0, -1, 0 }; 
	final int[] dci = new int[] { 0, 1, 0, -1 };

	// 문제 푸는 함수 
	int solution(int width, char[][] map) {
		// 답 기본 값 : 정수 최댓값
		int answer = Integer.MAX_VALUE;
		init(width, map); // 멤버 변수 초기화 
		Queue<int[]> tskS = new ArrayDeque<>();  // BFS에 사용할 큐 
		// row index, column index, 방향
		tskS.offer(new int[] { startPos[0], startPos[1], startOri }); // 출발 점, 출발 방향
		// 방문했음을 기록
		visited[startPos[0]][startPos[1]][startOri] = true;
		// 거리 기록
		distS[startPos[0]][startPos[1]][startOri] = 0;

		while (!tskS.isEmpty()) { // 큐가 빌 때까지 반복 

			int[] p = tskS.poll(); // 큐에서 하나 꺼냄 
			int ri = p[0]; // row index
			int ci = p[1]; // column index
			int ori = p[2]; // 방향
			int dist = distS[ri][ci][ori]; // 거리 
//			System.out.printf("%d %d %d %d\n", ri, ci, ori, dist);

			// 목적지에 도착했으면 반복 중단!
			if (ri == endPos[0] && ci == endPos[1] && ori == endOri) {
				answer = dist;
				break;
			}

			// 사방 탐색
			for (int i = 0; i < 4; i++) {
				int nri = ri + dri[i]; // 이동 시 새로운 row index
				int nci = ci + dci[i]; // 이동 시 새로운 column index

				// 공통 정상 범주 넘어가는 경우, Skip!
				if (nri < 0 || nri >= width)
					continue;
				if (nci < 0 || nci >= width)
					continue;

				// 방향에 맞는 정상 범주 넘어가는 경우, Skip!
				// 현재 방향이 수평인 경우,
				if (ori == HORI) {
					if (nci <= 0 || nci >= width - 1)
						continue;
					// 그 곳에 통나무무이 있는 경우, Skip!
					if (map[nri][nci - 1] == '1' || map[nri][nci + 1] == '1' || map[nri][nci] == '1') {
						continue;
					}
				}
				// 현재 방향이 수직인 경우,
				if (ori == VERT) {
					if (nri <= 0 || nri >= width - 1)
						continue;
					// 그 곳에 통나무가 있는 경우, Skip!
					if (map[nri - 1][nci] == '1' || map[nri + 1][nci] == '1' || map[nri][nci] == '1') {
						continue;
					}
				}
				// 이미 이전에 방문한 적이 있으면, Skip!
				if (visited[nri][nci][ori])
					continue;
				visited[nri][nci][ori] = true; // 방문 기록
				distS[nri][nci][ori] = dist + 1; // 거리 기록 
				tskS.offer(new int[] { nri, nci, ori }); // 큐에 삽입 
			}

			// 회전 체크
			boolean isWallThere = false;
			// 3*3 체크 
			for (int tri = ri - 1; tri <= ri + 1; tri++) {
				for (int tci = ci - 1; tci <= ci + 1; tci++) {
					// 통나무가 있는 경우,
					if (tri < 0 || tri >= width || tci < 0 || tci >= width) {
						isWallThere = true;
						break; // 반복 중단 
					}
					// 공통 정상 범주 넘어가는 경우, Skip!
					if (map[tri][tci] == '1') {
						isWallThere = true;
						break; // 반복 중단 
					}
				}
			}
			// 회전 반경에 통나무가 있는 경우, Skip!
			if (isWallThere) {
				continue;
			}
			int nori = Math.abs(1 - ori);
			// 회전한 모양새로 이전에 방문한 적이 있는 경우, Skip!
			if (visited[ri][ci][nori]) {
				continue;
			}
			visited[ri][ci][nori] = true; // 회전 후 방문 기록
			distS[ri][ci][nori] = dist + 1; // 거리 기록 
			tskS.offer(new int[] { ri, ci, nori }); // 큐에 삽입 
		}
		// 도착할 수 없는 경우,
		if (answer == Integer.MAX_VALUE) {
			answer = 0; // 0으로 초기화 
		}
		return answer; // 답 리턴 
	}

	// 멤버 변수 초기화 
	void init(int width, char[][] map) {
		this.width = width;
		this.map = map;
		visited = new boolean[width][width][2]; // 방문 기록 배열 

		distS = new int[width][width][2]; // 거리 배열 
		for (int[][] row : distS) {
			for (int[] element : row) {
				Arrays.fill(element, Integer.MAX_VALUE); // 거리 배열 초기화 
			}
		}

		// 출발지, 도착지 찾기 
		boolean isStartFound = false;
		boolean isEndFound = false;
		for (int ri = 0; ri < width; ri++) {
			for (int ci = 0; ci < width; ci++) {
				// 출발지 발견 
				if (isStartFound == false && map[ri][ci] == 'B') {
					// 수직 방향인 경우,ㅡ 
					if (ri <= width - 2 && map[ri + 1][ci] == 'B') {
						startPos = new int[] { ri + 1, ci }; 
						startOri = VERT;
					} 
					// 수평 방향인 경우,
					else {
						startPos = new int[] { ri, ci + 1 };
						startOri = HORI;
					}
					isStartFound = true;
				}
				// 목적지 발견!
				if (isEndFound == false && map[ri][ci] == 'E') {
					// 수직 방향인 경우,
					if (ri <= width - 2 && map[ri + 1][ci] == 'E') {
						endPos = new int[] { ri + 1, ci };
						endOri = VERT;
					} 
					// 수평 방향인 경우
					else {
						endPos = new int[] { ri, ci + 1 };
						endOri = HORI;
					}
					isEndFound = true;
				}
				// 출발지와 도착지를 다 찾았으면 반복 중단 
				if (isStartFound && isEndFound) {
					break;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BJ_통나무옮기기 T = new BJ_통나무옮기기(); // 인스턴스 생성 
		// System.setIn(new FileInputStream("input.txt"));
		// 입출력 받기 위해 BufferedReader 인스턴스 생성 
		BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
		// 폭 입력 받음 
		int width = Integer.parseInt(kb.readLine());
		// 맵 입력 받음 
		char[][] map = new char[width][width];
		for (int ri = 0; ri < width; ri++) {
			map[ri] = kb.readLine().toCharArray();
		}
		// 답 출력 
		System.out.println(T.solution(width, map));
	}
}