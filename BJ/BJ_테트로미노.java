/*
테트로미노
1. 문제 재정의 및 추상화 
테트로미노 조건 주어짐. NxM 종이 위에 테트로미노 하나 놓음. 각각의 칸에는 정수 쓰여짐. 테트로미노가 놓인 칸에 쓰여진 수들의 합 최대값 구하여라. 회전이나 대칭 가능. 
테트로미노 5가지 주어짐. 회전이나 대칭 가능하다! DFS. 출발점=종이 칸 하나하나. 

2. 풀이과정 상세히 적기
출발점 =종이 칸 하나하나. 동서남북으로 뻗아나감. 이전에 방문했던 칸은 방문하지 않음. 방문했던 칸은 어떻게 기억하는가? 1차원 배열. 

3. 시간복잡도 계산
O((4^4) * N*M)=O(N*M)
최대 연산 수: 256*25*1만 = 6400만. 시간 안에 풀 수 있다! 

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_테트로미노{
	
	class Position{
		
		int ri, ci;
		int v;
		
		Position(int ri, int ci, int v){
			this.ri=ri;
			this.ci=ci;
			this.v=v;
		}
		
		@Override
		public boolean equals(Object obj){
			Position other=(Position)obj;
			if(ri==other.ri && ci==other.ci)	return true;
			return false;
		}
	}
	
	Position[] positionS;
	int rowN, colN;
	int[][] map;
	int maxSum;
	
	// 북남서동
	final int[] driS=new int[]{-1,1,0,0};
	final int[] dciS=new int[]{0,0,-1,1};
	
	int solution(int rowN, int colN, int[][] map){
		int answer=0;
		init(rowN,colN,map);
		for(int ri=0;ri<rowN;ri++){
			for(int ci=0;ci<colN;ci++){
				Position st=new Position(ri,ci,map[ri][ci]);
				positionS[0]=st;
				dfs(1);
			}
		}
		answer=maxSum;
		return answer;
	}
	
	void init(int rowN, int colN, int[][] map){
		this.rowN=rowN;
		this.colN=colN;
		this.map=map;
		positionS=new Position[4];
		maxSum=Integer.MIN_VALUE;
	}
	
	void dfs(int cnt){
		if(cnt>=4){
			int sum=0;
			for(Position p: positionS){
				sum+=p.v;
			}
			maxSum=Math.max(maxSum,sum);
			return;
		}
		if(cnt==1){
			for(int ignored=0;ignored<4;ignored++){
				int cntTmp=cnt;
				for(int i=0;i<4;i++){
					if(i==ignored)	continue;
					int dri=driS[i], dci=dciS[i];
					Position last=positionS[0];
					int nri=last.ri+dri, nci=last.ci+dci;
					if(nri<0||nri>=rowN||nci<0||nci>=colN)	continue;
					if(alreadyVisited(nri,nci,cntTmp))	continue;
					positionS[cntTmp]=new Position(nri,nci,map[nri][nci]);
					cntTmp+=1;
				}
				//System.out.println(cntTmp);
				if(cntTmp==4){
					
					int sum=0;
					for(Position p:positionS){
						sum+=p.v;
					}
					maxSum=Math.max(maxSum,sum);
				}
			}
			
		}
		for(int i=0;i<4;i++){
			int dri=driS[i], dci=dciS[i];
			Position last=positionS[cnt-1];
			int nri=last.ri+dri, nci=last.ci+dci;
			if(nri<0||nri>=rowN||nci<0||nci>=colN)	continue;
			if(alreadyVisited(nri,nci,cnt))	continue;
			positionS[cnt]=new Position(nri,nci,map[nri][nci]);
			dfs(cnt+1);
		}
	}
	
	boolean alreadyVisited(int ri, int ci, int length){
		Position target=new Position(ri,ci,0);
		for(int i=0;i<length;i++){
			Position p=positionS[i];
			if(target.equals(p))	return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception{
		
		BJ_테트로미노 T=new BJ_테트로미노();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk=new StringTokenizer(kb.readLine());
		int rowN=Integer.parseInt(stk.nextToken());
		int colN=Integer.parseInt(stk.nextToken());
		
		int[][] map=new int[rowN][colN];
		for(int ri=0;ri<rowN;ri++){
			stk=new StringTokenizer(kb.readLine());
			for(int ci=0;ci<colN;ci++){
				map[ri][ci]=Integer.parseInt(stk.nextToken());
			}
		}
		
		System.out.println(T.solution(rowN,colN,map));
	}
}