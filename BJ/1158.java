/*
요세푸스 문제 (BJ1158)

1. 문제 재정의 및 추상화 
1~N 원을 따라 앉아있다. 1번부터 시작해서 K번째 사람 제거
N명의 사람들이 모두 제거될 때까지 반복
원에서 사람들이 제거되는 순서를 (N,K)-요세푸스 순열이라 한다.
(N,K)-요세푸스 순열을 구하라.

2. 풀이과정 상세히 적기
큐를 사용하여 문제를 푼다. 큐의 맨 앞 사람을 제거하고 뒤에 넣는다. 이를 K-1번 반복하다가 K번째 사람은 그대로 제거하고 이를 배열에 넣는다. 
위 과정을 반복한다. 

3. 시간복잡도 계산 
O(N*K)
5000*5000

4. 코드 작성 
*/

import java.util.*;
import java.io.*;
public class Main{
	int[] solution(int n, int k){
		int[] answer=new int[n];
		Queue<Integer> que=new ArrayDeque<>();
		for(int i=1;i<=n;i++){
			que.offer(i);
		}
		int count=0;
		int idx=0;
		while(!que.isEmpty()){
			int v=que.poll();
			count+=1;
			if(count==k){
				count=0;
				answer[idx]=v;
				idx+=1;
				continue;
			}
			que.offer(v);
		}
		return answer;
	}
	
	public static void main(String[] args) throws Exception{
		Main T=new Main();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk=new StringTokenizer(kb.readLine());
		int n=Integer.parseInt(stk.nextToken());
		int k=Integer.parseInt(stk.nextToken());
		StringBuilder sb=new StringBuilder();
		sb.append('<');
		int[] answer=T.solution(n,k);
		for(int i=0;i<n;i++){
			sb.append(answer[i]);
			if(i<n-1){
				sb.append(", ");
			}
		}
		sb.append(">\n");
		System.out.print(sb);
	}
}
