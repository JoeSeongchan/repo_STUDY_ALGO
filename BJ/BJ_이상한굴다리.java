/*
이상한 굴다리
1. 문제 재정의 및 추상화 
아주 쉬운 이분탐색 문제 
XXXOOOO 첫번째 O의 index를 구하여라 
index= 가로등의 높이

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(x*logN)

** 어두운 영역이 있는지는 어떻게 알아낼 수 있는가? 
가로등이 비추는 영역(범위)를 변수값으로 가지는 클래스 선언
인스턴스 정렬.. 앞에 있는 인스턴스의 끝 범위 <= 뒤에 있는 인스턴스의 시작 범위이면 유효하다. 만약 그렇지 않다면 어두운 영역이 있는 것이다! 

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_이상한굴다리{
    int min;
    int brLen, lgN;
    int[] pS;
    
    int solution(int brLen, int lgN, int[] pS){
        int answer=0;
        init(brLen,lgN,pS);
        bSearch(0,brLen+1);
        answer=min;
        return answer;
    }
    
    void init(int brLen, int lgN, int[] pS){
        this.brLen=brLen;
        this.lgN=lgN;
        this.pS=pS;
        min=Integer.MAX_VALUE;
    }
    
    void bSearch(int st, int ed){
        if(st>ed)   return;
        int mid=(st+ed)/2;
        if(isOk(mid)){
            min=Math.min(min,mid);
            bSearch(st,mid-1);
        } else{
            bSearch(mid+1,ed);
        }
    }
    
    boolean isOk(int v){
        int prevEnd=0;
        for(int p:pS){
            if(p-v>prevEnd){
                return false;
            }
            prevEnd=p+v;
        }
        if(prevEnd<brLen)   return false;
        return true;
    }
    
    public static void main(String[] args) throws Exception{
        BJ_이상한굴다리 T=new BJ_이상한굴다리();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int brLen=Integer.parseInt(kb.readLine());
        int lgN=Integer.parseInt(kb.readLine());
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        int[] pS=new int[lgN];
        for(int i=0;i<lgN;i++){
            pS[i]=Integer.parseInt(stk.nextToken());
        }
        System.out.println(T.solution(brLen,lgN,pS));
    }
}