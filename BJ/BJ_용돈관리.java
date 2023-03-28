/*
용돈관리
1. 문제 재정의 및 추상화 
이분탐색으로 문제를 풀어보자! 
XXXOOO 첫번째 O의 index를 찾자!
index=현우가 필요한 최소 금액 K

2. 풀이과정 상세히 적기 
금액 K값을 너무 작게 잡으면 인출횟수가 M보다 커진다. 
    인출횟수 체크해야 한다. 
생략 

3. 시간복잡도 계산
O(N*log(N*금액))

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_용돈관리{
    int dayN,bringN;
    int[] costS;
    int min;
    
    long solution(int dayN, int bringN, int[] costS){
        long answer=0;
        init(dayN,bringN,costS);
        bSearch(0,dayN*10000+1);
        answer=min;
        return answer;
    }
    
    void init(int dayN, int bringN, int[] costS){
        this.dayN=dayN;
        this.bringN=bringN;
        this.costS=costS;
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
        int cnt=0;
        int acc=0;
        for(int cost:costS){
            if(cost>v)  return false;
            if(acc+cost>v){
                cnt+=1;
                acc=cost;
            } else if(acc+cost==v){
                cnt+=1;
                acc=0;
            } else{
                acc+=cost;
            }
            if(cnt>bringN){
                return false;
            }
        }
        if(cnt==bringN && acc>0){
            return false;
        }
        return true;
    }
    
    public static void main(String[] args) throws Exception{
        BJ_용돈관리 T=new BJ_용돈관리();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        int dayN=Integer.parseInt(stk.nextToken());
        int bringN=Integer.parseInt(stk.nextToken());
        int[] costS=new int[dayN];
        for(int i=0;i<dayN;i++){
            costS[i]=Integer.parseInt(kb.readLine());
        }
        System.out.println(T.solution(dayN,bringN,costS));
    }
}