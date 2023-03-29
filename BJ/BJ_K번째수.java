package 조성찬;
/*
K번째 수 
1. 문제 재정의 및 추상화 
이분탐색으로 문제를 푼다. 
[최솟값~최댓값] 범위의 중간값을 구한다. 
배열 안에서 이 중간값보다 작거나 같은 값의 개수가 K 이상인 케이스를 찾는다.
    각 배열의 행은 구구단 결과값과 구조가 유사하다. 
    (K단의 구구단 결과값 중에서 P보다 작거나 같은 개수 = P/K)
XXXOOOO 첫번째 O의 위치를 찾는다. Lower bound
출처: https://st-lab.tistory.com/281

나의 생각: 벽이 느껴진다... 
    [못 푼 문제] 큐에 넣어놓고 시간이 조금 지난 후에 다시 풀어보자.

2. 풀이과정 상세히 적기
생략 

3. 시간복잡도 계산 
O(NlogN)
N: 배열의 크기

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class Solution{
    long width;
    long min;
    long targetIdx;
    
    long solution(long width, long targetIdx){
        long answer=0;
        init(width,targetIdx);
        bSearch(1,(width*width));
        answer=min;
        return answer;
    }
    
    void init(long width,long targetIdx){
        this.width=width;
        this.targetIdx=targetIdx;
        min=Long.MAX_VALUE;
    }
    
    void bSearch(long st, long ed){
        if(st>ed)   return;
        long mid=(st+ed)/2;
        if(isOk(mid)){
            min=Math.min(min,mid);
            bSearch(st,mid-1);
        } else{
            bSearch(mid+1,ed);
        }
    }
    
    boolean isOk(long v){
        long sum=0;
        for(long i=1;i<=width;i++){
            sum+=Math.min((v/i),width);
            if(sum>=targetIdx)  return true;
        }
        return false;
    }
    
    
    
    public static void main(String[] args) throws Exception{
        Solution T=new Solution();
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        long width=Integer.parseInt(kb.readLine());
        long targetIdx=Integer.parseInt(kb.readLine());
        System.out.println(T.solution(width,targetIdx));
    }
}

