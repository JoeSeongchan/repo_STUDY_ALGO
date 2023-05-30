/*
암호코드 
1. 문제 재정의 및  추상화 
숫자를 알파벳 문자열로 복호화하는 가짓수를 구하라. 
25114
2 5 1 1 4 
2 5 11 4
2 5 1 14
25 1 1 4
25 11 4
25 1 14
=> 6가지 

dp1[i] := 1~i 번째 숫자를 문자로 바꾸는 가짓수 (i번째 숫자는 단독으로 문자로 바꿈)
dp2[i] := 1~i 번째 숫자를 문자로 바꾸는 가짓수 (i번째 숫자는 i-1번째 숫자와 함께 묶여서 문자로 바뀌어짐)
점화식 
dp1[i] = dp1[i-1]+dp2[i-1] (if i번째 숫자가 문자로 바뀌어지는 경우)
dp2[i] = dp1[i-2]+dp2[i-2] (if i-1번째 숫자와 i번째 숫자를 함께 묶었을 때 문자로 바뀌어지는 경우) 
최종 답 : dp1[n-1]+dp2[n-1]

2. 풀이과정 상세히 적기
dp1[0], dp2[0], dp1[1], dp2[1] 초기화
예시로 한 번 돌려보기 
값 = 25114
dp1
[ 1 1 2 2 4 ]

dp2
[ 0 1 0 2 2 ]
4+2 = 6

값 = 2001
dp1
[ 1 0 0 0]

dp2
[ 0 1 0 0]
3. 시간복잡도 계산 
O(N)

4. 코드 작성
*/
import java.util.*;
import java.io.*;

public class BJ_암호코드{
	
	int[] dS;
	int dN;
	int[] dp1;
	int[] dp2;
	
	int solution(String str){
		int answer=0;
		init(str);
		
		if(dN==1 && dS[0]!=0)	return 1;
		else if(dN==1 && dS[0]==0)	return 0;
		
		if(dS[0]!=0)	dp1[0]=1;
		dp2[0]=0;
		
		if(dS[1]!=0)	dp1[1]= dp1[0]+dp2[0];
		if(canConvertWithTwo(dS[0]*10+dS[1]))	dp2[1]= 1;
		
		for(int i=2;i<dN;i++){
			if(canConvert(dS[i])){
				dp1[i]=(dp1[i-1]+dp2[i-1])%1000000;
			}
			
			if(canConvertWithTwo(dS[i-1]*10+dS[i])){
				dp2[i]=(dp1[i-2]+dp2[i-2])%1000000;
			}
		}
		
		answer=(dp1[dN-1]+dp2[dN-1])%1000000;
		return answer;
	}
	
	boolean canConvert(int v){
		if(v< 1|| v>9)	return false;
		return true;
	}
	
	boolean canConvertWithTwo(int v){
		if(v< 10|| v>26)	return false;
		return true;
	}
	
	void init(String str){
		char[] cS=str.toCharArray();
		dS=new int[cS.length];
		for(int i=0;i<cS.length;i++){
			dS[i]=Character.getNumericValue(cS[i]);
		}
		dN=dS.length;
		dp1=new int[dN];
		dp2=new int[dN];
	}
	
	public static void main(String[] args) throws Exception{
		BJ_암호코드 T=new BJ_암호코드();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		String str=kb.readLine();
		System.out.println(T.solution(str));
	}
}