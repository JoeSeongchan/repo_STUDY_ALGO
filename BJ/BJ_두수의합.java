/*
두 수의 합 
1. 문제 재정의 및 추상화 
두 수의 합이 x가 되는 쌍을 구하여라 
두 포인터 알고리즘으로 문제를 해결한다. 
배열을 정렬한다. 
양끝에서 시작한다. 합이 작으면 왼쪽 포인터를 이동한다. 
합이 크면 오른쪽 포인터를 이동한다. 
그러다가 합이 맞는 것을 찾으면 그 쌍을 count한다. 
그리고 왼쪽 끝을 오른쪽으로 이동시킨다. 
이런 식으로 양 포인터가 서로 만나기 전까지 반복한다. 

** 테스트케이스로 검증 
1 2 3 5 7 9 10 11 12
1 + 12 ..
2 + 12 
2 + 11 ..
3 + 11 
3 + 10 .. 
5 + 10 
5 + 9 
5 + 7 
7 + 7
성공적으로 잘 작동한다. 

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(N)
N: 배열의 길이

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_두수의합{
    int solution(int vN, int[] vS, int targetSum){
        int answer=0;
        int st=0;
        int ed=vN-1;
        Arrays.sort(vS);
        while(st<ed){
            int sum=vS[st]+vS[ed];
            if(sum<targetSum){
                st+=1;
            } else if(sum>targetSum){
                ed-=1;
            } else{
                answer+=1;
                st+=1;
            }
        }
        return answer;
    }
    
    public static void main(String[] args) throws Exception{
        BJ_두수의합 T=new BJ_두수의합();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int vN=Integer.parseInt(kb.readLine());
        int[] vS=new int[vN];
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        for(int i=0;i<vN;i++){
            vS[i]=Integer.parseInt(stk.nextToken());
        }
        int targetSum=Integer.parseInt(kb.readLine());
        System.out.println(T.solution(vN,vS,targetSum));
    }
}