/*
수들의 합 2
1. 문제 재정의 및 추상화 
두 포인터 문제.. 왼쪽 끝에서 두 포인터가 출발한다. M보다 작은 경우엔 오른쪽 포인터를 오른쪽으로 이동시킨다. 그러다가 M과 같아지면 경우의 수를 1 증가시킨다. 총합이 M보다 커지면 왼쪽 포인터를 오른쪽으로 이동시킨다. 이 작업을 반복하다가 오른쪽 포인터가 범위를 벗어나면 그 즉시 위 반복 작업을 중지한다. 

2. 풀이과정 상세히 적기 
생략 

3. 시간복잡도 계산
O(N)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_수들의합2{
    int solution(int vN, int targetSum, int[] vS){
        int answer=0;
        int lp=0, rp=0;
        int sum=vS[rp];
        int cnt=0;
        while(rp<vN){
            if(sum<targetSum){
                rp+=1;
                if(rp>=vN)  break;
                sum+=vS[rp];
            } else if(sum>targetSum){
                sum-=vS[lp];
                lp+=1;
            } else{
                cnt+=1;
                rp+=1;
                if(rp>=vN)  break;
                sum+=vS[rp];
            }
        }
        answer=cnt;
        return answer;
    }
    
    public static void main(String[] args) throws Exception{
        BJ_수들의합2 T=new BJ_수들의합2();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        int vN=Integer.parseInt(stk.nextToken());
        int targetSum=Integer.parseInt(stk.nextToken());
        int[] vS=new int[vN];
        stk=new StringTokenizer(kb.readLine());
        for(int i=0;i<vN;i++){
            vS[i]=Integer.parseInt(stk.nextToken());
        }
        System.out.println(T.solution(vN,targetSum,vS));
    }
}