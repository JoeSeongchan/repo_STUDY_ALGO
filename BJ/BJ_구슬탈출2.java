/*
구슬 탈출 2 
풀이원본 : https://minhamina.tistory.com/191
*/
import java.util.*;
import java.io.*;

public class BJ_구슬탈출2{
	
	static class Position{
		
		int ri, ci;
		
		Position(int ri,int ci){
			this.ri=ri;
			this.ci=ci;
		}
		
		Position(Position origin){
			this.ri=origin.ri;
			this.ci=origin.ci;
		}
		
		@Override
		public boolean equals(Object obj){
			Position other=(Position)obj;
			if(ri==other.ri && ci==other.ci){
				return true;
			}
			return false;
		}
		
		@Override
		public String toString(){
			return String.format("( %d, %d )",ri,ci);
		}
	}
	
	static class Case{
		
		Position red, blue;
		int cnt;
		
		Case(Position red, Position blue, int cnt){
			// Position 복사
			this.red=new Position(red);
			this.blue=new Position(blue);
			this.cnt=cnt;
		}
		
		@Override
		public String toString(){
			return String.format("red : %s, blue : %s, cnt: %d",red,blue,cnt);
		}
	}
	
	int rowN, colN;
	char[][] map;
	Position hole;
	Queue<Case> queue;
	
	// 상 하 좌 우
	final int[] driS=new int[]{-1,1,0,0};
	final int[] dciS=new int[]{0,0,-1,1};
	
	int solution(int rowN, int colN, char[][] map){
		int answer=0;
		init(rowN, colN, map);
		answer=bfs();
		return answer;
	}
	
	void init(int rowN, int colN, char[][] map){
		this.rowN=rowN;
		this.colN=colN;
		Position red=null, blue=null;
		for(int ri=1;ri<=rowN-2;ri++){
			for(int ci=1;ci<=colN-2;ci++){
				if(map[ri][ci]=='R'){
					red=new Position(ri,ci);
					map[ri][ci]='.';
				} else if(map[ri][ci]=='B'){
					blue=new Position(ri,ci);
					map[ri][ci]='.';
				} else if(map[ri][ci]=='O'){
					hole=new Position(ri,ci);
				}
			}
		}
		this.map=map;
		queue=new ArrayDeque<>();
		queue.offer(new Case(red,blue,0));
	}
	
	int bfs(){
		while(!queue.isEmpty()){
			Case c=queue.poll();
			// System.out.println(c);
			if(c.cnt>=10){
				return -1;
			}
			int cnt=c.cnt;
			
			
			for(int i=0;i<4;i++){
				int dri=driS[i];
				int dci=dciS[i];
				Position red=new Position(c.red);
				Position blue=new Position(c.blue);
				boolean isRedIn=false, isBlueIn=false;
				while(map[red.ri+dri][red.ci+dci]!='#'){
					red.ri+=dri;
					red.ci+=dci;
					if(map[red.ri][red.ci]=='O'){
						isRedIn=true;
						break;
					}
				}
				while(map[blue.ri+dri][blue.ci+dci]!='#'){
					blue.ri+=dri;
					blue.ci+=dci;
					if(map[blue.ri][blue.ci]=='O'){
						isBlueIn=true;
						break;
					}
				}
				// 빨간색 In & 파란색 Not In
				if(isRedIn && !isBlueIn){
					return cnt+1;
				}
				// 빨간색 Not In & 파란색 In
				if(!isRedIn && isBlueIn){
					continue;
				}
				// 빨간색 자리 == 파란색 자리
				if(blue.ri==red.ri && blue.ci==red.ci){
					if(i==0){ // 상 
						if(c.red.ri>c.blue.ri)	red.ri-=dri;
						else blue.ri-=dri;
					} else if(i==1){ // 하
						if(c.red.ri<c.blue.ri)	red.ri-=dri;
						else blue.ri-=dri;
					} else if(i==2){ // 좌
						if(c.red.ci>c.blue.ci)	red.ci-=dci;
						else blue.ci-=dci;
					} else { // 우
						if(c.red.ci<c.blue.ci)	red.ci-=dci;
						else blue.ci-=dci;
					}
				}
				// 파란색 자리 == 홀
				if(blue.equals(hole))	continue;
				// 빨간색 자리 == 홀
				if(red.equals(hole))	continue;
				// 빨간색, 파란색 원래 자리 유지
				if(blue.equals(c.blue) && red.equals(c.red)){
					continue;
				}
				// 경우(케이스) 추가
				// System.out.println("방향 : "+i);
				queue.offer(new Case(red,blue,cnt+1));
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws Exception{
		BJ_구슬탈출2 T=new BJ_구슬탈출2();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk=new StringTokenizer(kb.readLine());
		int rowN=Integer.parseInt(stk.nextToken());
		int colN=Integer.parseInt(stk.nextToken());
		char[][] map=new char[rowN][colN];
		for(int ri=0;ri<rowN;ri++){
			map[ri]=kb.readLine().toCharArray();
		}
		System.out.println(T.solution(rowN,colN,map));
	}
}