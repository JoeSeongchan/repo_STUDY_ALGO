/*
부분수열의 합 
1. 문제 재정의 및 추상화 
부분수열은 연속한 수열이 아니어도 된다. 
{1,2,3,4,5,6,7,8,9} 에서 {1,3,5,7} 부분수열을 뽑아낼 수 있다. 
** 중요한 점
    부분수열의 정의를 제대로 알고 있어야 한다. 

부분집합을 구한다. 모든 케이스를 구해서 총합이 S인지 확인한다. 
    백트래킹 적용할 수 있다. 중간에 이미 S 넘는 경우 중단 

2. 풀이과정 상세히 적기
부분집합, 재귀 사용

3. 시간복잡도 계산
O(2^N)
N: 전체 수열의 길이
총 연산 수: 2^20 = 100_0000=100만

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_부분수열의합{
    int vN, targetSum;
    int[] vS;
    int cnt;
    
    int solution(int vN, int targetSum, int[] vS){
        int answer=0;
        init(vN,targetSum,vS);
        subset();
        answer=cnt;
        return answer;
    }
    
    void init(int vN, int targetSum, int[] vS){
        this.vN=vN;
        this.targetSum=targetSum;
        this.vS=vS;
        cnt=0;
    }
    
    void subset(){
        int i=1;
        while(i<=(1<<vN)-1){
            int sum=0;
            // System.out.println();
            for(int k=0;k<vN;k++){
                if((i&(1<<k))==0) continue;
                sum+=vS[k];
                // System.out.println(sum);
                // if(sum>targetSum)   break;
            }
            // System.out.println(sum);
            if(sum==targetSum){
                // System.out.println(new StringBuilder(Integer.toBinaryString(i)).reverse());
                cnt+=1;
            }
            i+=1;
        }
    }
    
    public static void main(String[] args) throws Exception{
        BJ_부분수열의합 T=new BJ_부분수열의합();
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