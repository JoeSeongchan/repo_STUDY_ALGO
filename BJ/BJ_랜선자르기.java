/*
랜선 자르기 
1. 문제 재정의 및 추상화 
전형적인 이분탐색 문제
랜선의 길이를 크게 하면 개수가 줄어든다.
랜선의 길이를 작게 하면 개수가 는다.
개수 조건을 만족하면서 랜선의 길이가 제일 큰 것을 고른다. 
OOOXXX 마지막 O의 위치를 찾는다.

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(KlogN)
K: 랜선의 개수 
N: 만들어야 하는 랜선의 개수 

** 오답 노트
- 0 ~ 2^31 범위 내에서 탐색해야 하기 때문에 binary search 시 long 타입을 써야 한다. 
- 문제 풀이가 틀렸을 때는 최댓값, 최솟값 테스트케이스를 꼭 넣어보자! 
4. 코드 작성
*/
import java.util.*;
import java.io.*;
public class BJ_랜선자르기{
    
    int lanN, resultN;
    int[] lanS;
    long maxLen;
    
    int solution(int lanN, int resultN, int[] lanS){
        int answer=0;
        init(lanN,resultN,lanS);
        bSearch(0,(long)(Integer.MAX_VALUE)+1);
        answer=(int)maxLen;
        return answer;
    }
    
    void init(int lanN, int resultN, int[] lanS){
        this.lanN=lanN;
        this.resultN=resultN;
        this.lanS=lanS;
        maxLen=Integer.MIN_VALUE;
    }
    
    void bSearch(long st, long ed){
        if(st>ed){
            return;
        }
        long mid=(st+ed)/2;
        if(isOk(mid)){
            maxLen=Math.max(mid,maxLen);
            bSearch(mid+1,ed);
        } else{
            bSearch(st,mid-1);
        }
    }
    
    boolean isOk(long len){
        int sum=0;
        for(int lan:lanS){
            sum+=(lan/len);
        }
        if(sum>=resultN){
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) throws Exception{
        BJ_랜선자르기 T=new BJ_랜선자르기();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        int lanN=Integer.parseInt(stk.nextToken());
        int resultN=Integer.parseInt(stk.nextToken());
        int[] lanS=new int[lanN];
        for(int i=0;i<lanN;i++){
            lanS[i]=Integer.parseInt(kb.readLine());
        }
        System.out.println(T.solution(lanN,resultN,lanS));
    }
}