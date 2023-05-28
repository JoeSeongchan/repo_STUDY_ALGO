/*
1,2,3 더하기 6
1. 문제 재정의 및 추상화 
정수를 1,2,3의 합으로 나타내어라. 합을 나타낼 때는 수를 1개 이상 사용해야 한다. (당연) 단, 합은 대칭을 이루어야 한다. (이걸 어떻게 구현할까?)
대칭임을 어떻게 알아낼 수 있을까? 
아! n/2 값을 1,2,3으로 만드는 방법의 가짓수를 구하면 되겠구나! "1,2,3 더하기 5" 문제의 풀이법을 그대로 가지고 오면 되겠다! 

** 오답노트 : 모든 테스트케이스에 대해서 각각 DP 배열을 초기화할 필요가 없다. 딱 한 번만 초기화하면 된다. 그러므로 init 메소드를 solution 메소드 호출 전에 딱 한 번 호출한다! 

2. 풀이과정 상세히 적기 
전형적인 DP 알고리즘 적용 문제
생략

3. 시간복잡도 계산 
O(N)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_123더하기6{
	int n;
	long[] dp;
	
	long solution(int n){
		long answer=0;
		if(n==1)	return 1;
		if(n==2)	return 2;
		if(n==3)	return 2;
		if(n%2==0){
			answer=calculate(n/2);
			answer+=calculate((n-2)/2);
		} else{
			answer=calculate((n-1)/2);
			answer+=calculate((n-3)/2);
		}
		answer%=1000000009;
		return answer;
	}
	
	void init(){
		dp=new long[100001];
		dp[0]=0;
		dp[1]=1;
		dp[2]=2;
		dp[3]=4;
		for(int i=4;i<=100000;i++){
			dp[i]=dp[i-1]+dp[i-2]+dp[i-3];
			dp[i]%=1000000009;
		}
	}
	
	long calculate(int targetSum){
		long answer=0;
		answer=dp[targetSum];
		return answer;
	}
	
	public static void main(String[] args) throws Exception{
		BJ_123더하기6 T=new BJ_123더하기6();
		T.init();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		int tcN=Integer.parseInt(kb.readLine());
		StringBuilder sb=new StringBuilder();
		for(int tci=1;tci<=tcN;tci++){
			int n=Integer.parseInt(kb.readLine());
			sb.append(T.solution(n)).append('\n');
		}
		System.out.println(sb);
	}
}