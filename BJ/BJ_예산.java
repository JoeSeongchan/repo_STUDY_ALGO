/*
예산
1. 문제 재정의 및 추상화
상한액 정함.. 상한액 최댓값 구함 
상한액 최댓값 => 배정된 예산들 중 최댓값! 자연스러움 
OOOXXX 마지막 O의 위치를 구한다. index: 상한액

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(NlogM)
N: 지방의 수
M: 지방이 요구하는 예산요청의 최댓값 = 10만 

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_예산{
    int cN;
    int[] cS;
    int limit;
    int bg;
    
    int solution(int cN, int[] cS, int limit){
        int answer=Integer.MIN_VALUE;
        init(cN,cS,limit);
        bSearch(0,100001);
        for(int c: cS){
            answer=Math.max(answer,Math.min(c,bg));
        }
        return answer;
    }
    
    void init(int cN, int[] cS, int limit){
        this.cN=cN;
        this.cS=cS;
        this.limit=limit;
        bg=Integer.MIN_VALUE;
    }
    
    void bSearch(int st, int ed){
        if(st>ed)   return;
        int mid=(st+ed)/2;
        if(isOk(mid)){
            bg=Math.max(bg,mid);
            bSearch(mid+1,ed);
        } else{
            bSearch(st,mid-1);
        }
    }
    
    boolean isOk(int v){
        int sum=0;
        for(int c:cS){
            sum+=Math.min(c,v);
        }
        if(sum>limit){
            return false;
        }
        return true;
    }
    
    public static void main(String[] args) throws Exception{
        BJ_예산 T=new BJ_예산();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int cN=Integer.parseInt(kb.readLine());
        int[] cS=new int[cN];
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        for(int i=0;i<cN;i++){
            cS[i]=Integer.parseInt(stk.nextToken());
        }
        int limit=Integer.parseInt(kb.readLine());
        System.out.println(T.solution(cN,cS,limit));
    }
}