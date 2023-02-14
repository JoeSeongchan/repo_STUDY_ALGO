/*
No. 1 새로운 불면증 치료법

1. 문제 재정의 및 추상화 
N의 배수 계산. digit 0~9 모두 나올 때까지 N의 배수 계산
몇 배수까지 계산해야 하는가?

2. 풀이과정 상세히 적기
비트마스킹 사용. 나머지 생략 

3. 시간복잡도 계산 
최악: O(T*(N^2))? 확실하지 않음 

4. 코드 작성 
*/

import java.util.*;
import java.io.*;

public class Solution{
	int solution(int v){
		int answer=0;
		int visited=0;
		int curVal=v;
		while(true){
			char[] charValS=String.valueOf(curVal).toCharArray();
			boolean isFinished=false;
			for(char c:charValS){
				visited|=1<<Character.getNumericValue(c);
				if(visited==(1<<10)-1){
					isFinished=true;
					break;
				}
			}
			if(isFinished){
				break;
			}
			curVal+=v;
		}
		answer=curVal;
		return answer;
	}
	
	public static void main(String[] args) throws Exception{
		Solution T=new Solution();
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		int tcN=Integer.parseInt(kb.readLine());
		StringBuilder sb=new StringBuilder();
		for(int tci=1;tci<=tcN;tci++){
			int v=Integer.parseInt(kb.readLine());
			sb.append('#').append(tci).append(' ').append(T.solution(v)).append('\n');
		}
		System.out.print(sb);
	}
}
