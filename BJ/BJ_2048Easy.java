/*
2048 (Easy)
1. 문제 재정의 및 추상화 
보드 위의 전체 블록을 상하좌우 네 방향 중 하나로 이동. 
같은 값을 가지는 두 블럭 충돌 => 하나로 합쳐짐
이미 합쳐진 블럭은 한 번의 이동에서 다른 블럭과 합쳐질 수 없음. 
블럭이 추가되지 않음. 
똑같은 수가 세 개 있는 경우, 이동하려고 하는 쪽의 칸이 먼저 합쳐짐. 
위쪽으로 이동 = 위쪽에 있는 블럭을 먼저 합침. 
N*N 보드크기. 최대 5번 이동해서 만들 수 있는 가장 큰 블럭의 값. 
전형적인 DFS 문제. 한 케이스를 탐색할 때마다 맵 백업, 복원해야 함. 

2. 풀이과정 상세히 적기
Class 정의. 꼼꼼히 문제를 풀면 해결할 수 있는 문제.

3. 시간복잡도 계산
O((4^N)*N*N) 
최대 연산 수 : 4000*4^5 = 1000*4000=400_0000=400만
무난히 시간 안에 해결 가능.
최대 필요 메모리 : 5*4000*4=100_000=100KB

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_2048Easy{
	int width;
	int maxBlock;
	
	int solution(int width, int[][] map){
		int answer=0;
		init(width);
		dfs(map,0);
		answer=maxBlock;
		return answer;
	}
	
	void init(int width){
		this.width=width;
		maxBlock=Integer.MIN_VALUE;
	}
	
	void copy(int[][] dst, int[][] origin){
		for(int ri=0;ri<width;ri++){
			for(int ci=0;ci<width;ci++){
				dst[ri][ci]=origin[ri][ci];
			}
		}
	}
	
	void dfs(int[][] map, int cnt){
		if(cnt>=5){
			for(int ri=0;ri<width;ri++){
				for(int ci=0;ci<width;ci++){
					maxBlock=Math.max(maxBlock,map[ri][ci]);
					// System.out.printf("%d ",map[ri][ci]);
				}
				//System.out.println();
			}
			return;
		}
		
		int[][] backupMap=new int[width][width];
		copy(backupMap,map);
		// printMap(backupMap);
		
		up(map);
		dfs(map,cnt+1);
		copy(map,backupMap);
		
		down(map);
		dfs(map,cnt+1);
		copy(map,backupMap);
		
		left(map);
		dfs(map,cnt+1);
		copy(map,backupMap);
		
		right(map);
		dfs(map,cnt+1);
		copy(map,backupMap);
	}
	
	void printMap(int[][] map){
		for(int ri=0;ri<width;ri++){
			for(int ci=0;ci<width;ci++){
				System.out.printf("%d ",map[ri][ci]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	void up(int[][] map){
		boolean[][] combined=new boolean[width][width];
		int dri=-1, dci=0;
		for(int ci=0;ci<width;ci++){
			for(int ri=0;ri<width;ri++){
				if(map[ri][ci]==0)	continue;
				int pRi=ri,pCi=ci; // 블럭 위치
				while(isValid(pRi+dri,pCi+dci)){
					// 블럭 이동
					if(map[pRi+dri][pCi+dci]==0){
						map[pRi+dri][pCi+dci]=map[pRi][pCi];
						map[pRi][pCi]=0;
						pRi+=dri; pCi+=dci;
						continue;
					} 
					// 블럭 합칠 수 있는 경우, 
					if(map[pRi+dri][pCi+dci]==map[pRi][pCi] 
					&& combined[pRi+dri][pCi+dci]==false){
						combined[pRi+dri][pCi+dci]=true;
						map[pRi+dri][pCi+dci]*=2;
						map[pRi][pCi]=0;
						break;
					} 
					// 블럭 합칠 수 없는 경우, 
					break;
				}
			}
		}
	}
	
	void down(int[][] map){
		boolean[][] combined=new boolean[width][width];
		int dri=1, dci=0;
		for(int ci=0;ci<width;ci++){
			for(int ri=width-1;ri>=0;ri--){
				if(map[ri][ci]==0)	continue;
				int pRi=ri,pCi=ci; // 블럭 위치
				while(isValid(pRi+dri,pCi+dci)){
					// 블럭 이동
					if(map[pRi+dri][pCi+dci]==0){
						map[pRi+dri][pCi+dci]=map[pRi][pCi];
						map[pRi][pCi]=0;
						pRi+=dri; pCi+=dci;
						continue;
					} 
					// 블럭 합칠 수 있는 경우, 
					if(map[pRi+dri][pCi+dci]==map[pRi][pCi] 
					&& combined[pRi+dri][pCi+dci]==false){
						combined[pRi+dri][pCi+dci]=true;
						map[pRi+dri][pCi+dci]*=2;
						map[pRi][pCi]=0;
						break;
					} 
					// 블럭 합칠 수 없는 경우, 
					break;
				}
			}
		}
	}
	
	void left(int[][] map){
		boolean[][] combined=new boolean[width][width];
		int dri=0, dci=-1;
		for(int ri=0;ri<width;ri++){
			for(int ci=0;ci<width;ci++){
				if(map[ri][ci]==0)	continue;
				int pRi=ri,pCi=ci; // 블럭 위치
				while(isValid(pRi+dri,pCi+dci)){
					// 블럭 이동
					if(map[pRi+dri][pCi+dci]==0){
						map[pRi+dri][pCi+dci]=map[pRi][pCi];
						map[pRi][pCi]=0;
						pRi+=dri; pCi+=dci;
						continue;
					} 
					// 블럭 합칠 수 있는 경우, 
					if(map[pRi+dri][pCi+dci]==map[pRi][pCi] 
					&& combined[pRi+dri][pCi+dci]==false){
						combined[pRi+dri][pCi+dci]=true;
						map[pRi+dri][pCi+dci]*=2;
						map[pRi][pCi]=0;
						break;
					} 
					// 블럭 합칠 수 없는 경우, 
					break;
				}
			}
		}
	}
	
	void right(int[][] map){
		boolean[][] combined=new boolean[width][width];
		int dri=0, dci=1;
		for(int ri=0;ri<width;ri++){
			for(int ci=width-1;ci>=0;ci--){
				if(map[ri][ci]==0)	continue;
				int pRi=ri,pCi=ci; // 블럭 위치
				while(isValid(pRi+dri,pCi+dci)){
					// 블럭 이동
					if(map[pRi+dri][pCi+dci]==0){
						map[pRi+dri][pCi+dci]=map[pRi][pCi];
						map[pRi][pCi]=0;
						pRi+=dri; pCi+=dci;
						continue;
					} 
					// 블럭 합칠 수 있는 경우, 
					if(map[pRi+dri][pCi+dci]==map[pRi][pCi] 
					&& combined[pRi+dri][pCi+dci]==false){
						combined[pRi+dri][pCi+dci]=true;
						map[pRi+dri][pCi+dci]*=2;
						map[pRi][pCi]=0;
						break;
					} 
					// 블럭 합칠 수 없는 경우, 
					break;
				}
			}
		}
	}
	
	boolean isValid(int ri,int ci){
		if(ri<0||ri>=width||ci<0||ci>=width)	return false;
		return true;
	}
	
	
	public static void main(String[] args) throws Exception{
		BJ_2048Easy T=new BJ_2048Easy();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		int width=Integer.parseInt(kb.readLine());
		int[][] map=new int[width][width];
		for(int ri=0;ri<width;ri++){
			StringTokenizer stk=new StringTokenizer(kb.readLine());
			for(int ci=0;ci<width;ci++){
				map[ri][ci]=Integer.parseInt(stk.nextToken());
			}
		}
		System.out.println(T.solution(width,map));
	}
}