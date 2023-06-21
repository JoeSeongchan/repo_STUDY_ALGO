import java.util.*;
import java.io.*;
public class BJ_사다리조작{
	int lineN, pointN;
	int[][] status;
	final int LEFT=1, RIGHT=2, NONE=0;
	int minCnt;
	
	int solution(int lineN, int vertN, int pointN, int[][] vertS){
		int answer=0;
		init(lineN,vertN,pointN,vertS);
		combination(0,0,0);
		if(minCnt>3){
			answer=-1;
		} else{
			answer=minCnt;
		}
		return answer;
	}
	
	void init(int lineN, int vertN, int pointN, int[][] vertS){
		this.lineN=lineN;
		this.pointN=pointN;
		status=new int[lineN][pointN];
		for(int[] vert: vertS){
			int pointIdx=vert[0];
			int srcLineIdx=vert[1];
			int tgLineIdx=vert[1]+1;
			status[srcLineIdx][pointIdx]=RIGHT;
			status[tgLineIdx][pointIdx]=LEFT;
		}
		minCnt=Integer.MAX_VALUE;
	}
	
	void combination(int cnt, int nextLineIdx, int nextPointIdx){
		if(cnt>=4)	return;
		if(minCnt<=cnt)	return;
		if(nextPointIdx>=pointN){
			combination(cnt,nextLineIdx+1, 0);
			return;
		}
		boolean ok = check();
		if(ok){
			minCnt=Math.min(minCnt,cnt);
			return;
		}
		for(int lineIdx=nextLineIdx;lineIdx<lineN-1;lineIdx++){
			for(int pointIdx=nextPointIdx;pointIdx<pointN;pointIdx++){
				if(status[lineIdx][pointIdx]!=NONE || status[lineIdx+1][pointIdx]!=NONE){
					continue;
				}
				status[lineIdx][pointIdx]=RIGHT;
				status[lineIdx+1][pointIdx]=LEFT;
				combination(cnt+1,lineIdx,pointIdx+1);
				status[lineIdx][pointIdx]=NONE;
				status[lineIdx+1][pointIdx]=NONE;
			}
			nextPointIdx=0;
		}
	}
	
	boolean check(){
		for(int lineIdx=0;lineIdx<lineN;lineIdx++){
			int curLineIdx=lineIdx;
			int pointIdx=0;
			while(pointIdx<pointN){
				switch(status[curLineIdx][pointIdx]){
					case LEFT:
						curLineIdx-=1; break;
					case RIGHT:
						curLineIdx+=1; break;
					case NONE:
						break;
				}
				pointIdx+=1;
			}
			if(curLineIdx!=lineIdx){
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception{
		BJ_사다리조작 T=new BJ_사다리조작();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk=new StringTokenizer(kb.readLine());
		int lineN=Integer.parseInt(stk.nextToken());
		int vertN=Integer.parseInt(stk.nextToken());
		int pointN=Integer.parseInt(stk.nextToken());
		int[][] vertS=new int[vertN][2];
		for(int i=0;i<vertN;i++){
			stk=new StringTokenizer(kb.readLine());
			for(int k=0;k<2;k++){
				vertS[i][k]=Integer.parseInt(stk.nextToken())-1;
			}
		}
		System.out.println(T.solution(lineN,vertN,pointN,vertS));
	}
}