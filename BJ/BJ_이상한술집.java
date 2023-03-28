/*
이상한 술집
1. 문제 재정의 및 추상화 
전형적인 이분탐색 문제 
분배용량 최댓값 구하여라 
OOOOOXXXXX 마지막 O의 index를 구하여라 
index=분배용량 

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(주전자의 개수*log(막걸리의 용량))

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_이상한술집{
    int bowlN, frN;
    int[] bowlS;
    long max;
    
    long solution(int bowlN, int frN, int[] bowlS){
        long answer=0;
        init(bowlN,frN,bowlS);
        bSearch(0,(long)Integer.MAX_VALUE+1);
        answer=max;
        return answer;
    }
    
    void init(int bowlN, int frN, int[] bowlS){
        this.bowlN=bowlN;
        this.frN=frN;
        this.bowlS=bowlS;
        max=Integer.MIN_VALUE;
    }
    
    void bSearch(long st, long ed){
        if(st>ed)   return;
        long mid=(st+ed)/2;
        if(isOk(mid)){
            max=Math.max(max,mid);
            bSearch(mid+1,ed);
        } else{
            bSearch(st,mid-1);
        }
    }
    
    boolean isOk(long v){
        long sum=0;
        for(int bowl:bowlS){
            sum+=(bowl/v);
        }
        if(sum<frN){
            return false;
        }
        return true;
    }
    
    public static void main(String[] args) throws Exception{
        BJ_이상한술집 T=new BJ_이상한술집();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        int bowlN=Integer.parseInt(stk.nextToken());
        int frN=Integer.parseInt(stk.nextToken());
        int[] bowlS=new int[bowlN];
        for(int i=0;i<bowlN;i++){
            bowlS[i]=Integer.parseInt(kb.readLine());
        }
        System.out.println(T.solution(bowlN,frN,bowlS));
    }
}