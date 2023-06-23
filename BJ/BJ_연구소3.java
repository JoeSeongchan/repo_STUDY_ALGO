/*
연구소 3
1. 문제 재정의 및 추상화 
기존에 있던 비활성 바이러스를 활성화하여 모든 빈 칸에 바이러스를 버트려보자. 벽을 제외한 모든 칸이어야 한다. 어떻게 하더라도 바이러스를 벽을 제외한 모든 칸에 퍼트릴 수 없다면 -1을 출력하라. 조합 + BFS 문제이다. 

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O((2^M)*(V+E))
M : 놓을 수 있는 활성화 바이러스의 수
V : 칸의 개수
E : 칸과 칸 사이의 관계. 사방 탐색이기 때문에 4*V에 가깝다. 

4. 코드 작성 
*/

import java.util.*;
import java.io.*;

public class BJ_연구소3{
	
	int inactiveN;
	List<int[]> inactiveS;
	
	int activeN;
	int[][] activeS;
	int blankN;
	
	int width;
	int[][] map;
	
	int minTime;
	
	final int[] driS=new int[]{-1,1,0,0};
	final int[] dciS=new int[]{0,0,-1,1};
	
	int solution(int width, int activeN, int[][] map){
		int answer=0;
		init(width,activeN,map);
		combination(0,0);
		
		if(minTime==Integer.MAX_VALUE){
			answer=-1;
		} else{
			answer=minTime;
		}
		return answer;
	}
	
	void init(int width, int activeN, int[][] map){
		this.width=width;
		this.activeN=activeN;
		this.map=map;
		blankN=0;
		findInactiveS();
		activeS=new int[activeN][2];
		minTime=Integer.MAX_VALUE;
	}
	
	void findInactiveS(){
		inactiveS=new ArrayList<>();
		for(int ri=0;ri<width;ri++){
			for(int ci=0;ci<width;ci++){
				if(map[ri][ci]==0){
					blankN+=1;
				}
				if(map[ri][ci]!=2)	continue;
				inactiveS.add(new int[]{ri,ci});
			}
		}
		inactiveN=inactiveS.size();
	}
	
	void combination(int cnt, int nextIdx){
		if(cnt>=activeN){
			int time=checkTime();
			minTime=Math.min(minTime,time);
			return;
		}
		if(nextIdx>=inactiveN)	return;
		for(int i=nextIdx;i<inactiveN;i++){
			activeS[cnt]=inactiveS.get(i);
			combination(cnt+1,i+1);
		}
	}
	
	int checkTime(){
		Queue<int[]> queue=new ArrayDeque<>();
		int[][] timeS=new int[width][width];
		for(int ri=0;ri<width;ri++){
			for(int ci=0;ci<width;ci++){
				timeS[ri][ci]=Integer.MAX_VALUE;
			}
		}
		for(int[] active: activeS){
			queue.offer(active);
			timeS[active[0]][active[1]]=0;
		}
		int curBlankN=blankN;
		if(curBlankN==0){
			return 0;
		}
		while(!queue.isEmpty()){
			int[] active = queue.poll();
			int ri=active[0], ci=active[1];
			int time=timeS[ri][ci];
			for(int i=0;i<4;i++){
				int dri=driS[i];
				int dci=dciS[i];
				int nri=ri+dri, nci=ci+dci;
				if(!isValid(nri,nci)) continue;
				if(timeS[nri][nci]!=Integer.MAX_VALUE) continue;
				if(map[nri][nci]==0){
					curBlankN-=1;
					// System.out.println(curBlankN);
					if(curBlankN==0){
						return time+1;
					}
				}
				queue.offer(new int[]{nri,nci});
				timeS[nri][nci]=time+1;
			}
		}
		return Integer.MAX_VALUE;
	}
	
	int findOutMaxTime(int[][] timeS){
		int maxTime=Integer.MIN_VALUE;
		for(int ri=0;ri<width;ri++){
			for(int ci=0;ci<width;ci++){
				if(map[ri][ci]==1) continue;
				maxTime=Math.max(maxTime,timeS[ri][ci]);
			}
		}
		return maxTime;
	}
	
	boolean isValid(int ri, int ci){
		if(ri<0||ri>=width)	return false;
		if(ci<0||ci>=width)	return false;
		if(map[ri][ci]==1)	return false;
		return true;
	}
	
	
	
	public static void main(String[] args) throws Exception{
		BJ_연구소3 T=new BJ_연구소3();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk=new StringTokenizer(kb.readLine());
		int width=Integer.parseInt(stk.nextToken());
		int activeN=Integer.parseInt(stk.nextToken());
		int[][] map=new int[width][width];
		for(int ri=0;ri<width;ri++){
			stk=new StringTokenizer(kb.readLine());
			for(int ci=0;ci<width;ci++){
				map[ri][ci]=Integer.parseInt(stk.nextToken());
			}
		}
		System.out.println(T.solution(width,activeN,map));
		
	}
}