/*
기타 레슨 
1. 문제 재정의 및 추상화
전형적인 이분탐색 문제...
XXXOOOO 첫번째 O의 위치를 찾아라...
index=블루레이의 크기 
블루레이의 크기 최솟값 구하라...
M개의 블루레이에 모두 담을 수 있는가? =O,X

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(강의의 수*log(강의의 수 * 강의의 길이)) 
10만*10만=100_0000_0000 100억...
long으로 풀자...

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_기타레슨{
    int gN,cdN;
    int[] gS;
    long min;
    
    long solution(int gN, int cdN, int[] gS){
        long answer=0;
        init(gN,cdN,gS);
        bSearch(0,(long)Math.pow(10,10)+1);
        answer=min;
        return answer;
    }
    
    void init(int gN, int cdN, int[] gS){
        this.gN=gN;
        this.cdN=cdN;
        this.gS=gS;
        min=Integer.MAX_VALUE;
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
        int cnt=0;
        long acc=0;
        for(int g: gS){
            if(g>v) return false;
            if(acc+g==v){
                acc=0;
                cnt+=1;
            } else if(acc+g<v){
                acc+=g;
            } else {
                cnt+=1;
                acc=g;
            }
            if(cnt>cdN)  return false;
        }
        if(cnt==cdN && acc>0)   return false;
        return true;
    }
    
    public static void main(String[] args) throws Exception{
        BJ_기타레슨 T=new BJ_기타레슨();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        int gN=Integer.parseInt(stk.nextToken());
        int cdN=Integer.parseInt(stk.nextToken());
        int[] gS=new int[gN];
        stk=new StringTokenizer(kb.readLine());
        for(int i=0;i<gN;i++){
            gS[i]=Integer.parseInt(stk.nextToken());
        }
        System.out.println(T.solution(gN,cdN,gS));
    }
}