/*
1228. [S/W 문제해결 기본] 8일차 - 암호문1

1. 문제 재정의 및 추상화 
원본 암호문의 지정된 위치에 여러 문자를 추가한다. 
앞에서부터 x의 위치 바로 다음에 y개의 숫자를 삽입한다.
최종 결과물의 처음 10개의 숫자를 출력하라. 
링크드 리스트를 사용하라 .

2. 풀이과정 상세히 적기 
링크드 리스트 사용. 자세한 풀이과정 필요 없음.

3. 시간 복잡도 계산 
O(N*M*L)
N: 명령어의 개수(5~10)
M: 삽입하는 원소의 개수
L: 원본 암호문의 길이

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class Solution{
	static class Command{
		int idx;
		int vN;
		int[] vS;
		
		Command(int idx,int vN,int[] vS){
			this.idx=idx;
			this.vN=vN;
			this.vS=vS;
		}
	}
	
	int[] solution(int orgN, int[] orgS, int cmdN, Command[] cmdS){
		int[] answer;
		LinkedList<Integer> linkedList=new LinkedList<>();
		for(int orgValue: orgS){
			linkedList.offerLast(orgValue);
		}
		
		for(Command cmd:cmdS){
			int idx=cmd.idx;
			int vN=cmd.vN;
			int[] vS=cmd.vS;
			
			for(int v: vS){
				linkedList.add(idx,v);
				idx+=1;
			}
			
		}
		
		answer=new int[10];
		for(int i=0;i<10;i++){
			answer[i]=linkedList.pollFirst();
		}
		return answer;
	}
	
	public static void main(String[] args) throws Exception{
		Solution T=new Solution();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		int tcN=10;
		
		for(int tci=1;tci<=tcN;tci++){
			int orgN=Integer.parseInt(kb.readLine());
			int[] orgS=new int[orgN];
			StringTokenizer stk=new StringTokenizer(kb.readLine());
			for(int i=0;i<orgN;i++){
				orgS[i]=Integer.parseInt(stk.nextToken());
			}
			
			int cmdN=Integer.parseInt(kb.readLine());
			Command[] cmdS=new Command[cmdN];
			stk=new StringTokenizer(kb.readLine());
			int cmdIdx=0;
			
			while(cmdIdx<cmdN){
				stk.nextToken();
				int idx=Integer.parseInt(stk.nextToken());
				int vN=Integer.parseInt(stk.nextToken());
				int[] vS=new int[vN];
				for(int i=0;i<vN;i++){
					vS[i]=Integer.parseInt(stk.nextToken());
				}
				
				cmdS[cmdIdx]=new Command(idx,vN,vS);
				cmdIdx+=1;
			}
			
			int[] answer=T.solution(orgN, orgS, cmdN, cmdS);
			sb.append('#').append(tci).append(' ');
			for(int v: answer){
				sb.append(v).append(' ');
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
}
