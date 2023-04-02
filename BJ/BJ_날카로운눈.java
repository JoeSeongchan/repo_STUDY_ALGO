/*
날카로운 눈 
1. 문제 재정의 및 추상화
자기보다 작은 수가 홀수개인 첫번째 수를 구하여라. lower bound!
이분탐색으로 문제를 푼다. 첫 탐색범위: 1~Integer.MAX_VALUE

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(N*logK)
N: 입력의 개수
K: 정수 최대 수 

** 오답노트 
로직을 손으로 적어보며 맞는지 체크한다. (테스트케이스 돌려보자)
답을 찾지 못했음을 Integer.MAX_VALUE와 같은 값으로 체크하려 하지 말자
    그 값으로 체크하려 한다면 예외는 없는지 확인한다.
    이 문제에서 예외는 Integer.MAX_VALUE 값이 딱 한 번 나온 경우였다. 
4. 코드 적성 
*/
import java.util.*;
import java.io.*;

public class Solution{
    int inputN;
    int[][] inputS;
    int oddValue;
    int oddValueN;
    boolean found;
    
    String solution(int inputN, int[][] inputS){
        String answer="";
        init(inputN,inputS);
        bSearch(1,Integer.MAX_VALUE);
        if(!found){
            answer="NOTHING";
        } else{
            for(int[] input: inputS){
                if(input[0]>oddValue)   continue;
                if(input[1]<oddValue)  continue;
                if((oddValue-input[0])%input[2]==0){
                    oddValueN+=1;
                }
            }
            answer=oddValue+" "+oddValueN;
        }
        return answer;
    }
    
    void init(int inputN,int[][] inputS){
        this.inputN=inputN;
        this.inputS=inputS;
        oddValue=Integer.MAX_VALUE;
        oddValueN=0;
        found=false;
    }
    
    void bSearch(long st, long ed){
        if(st>ed)   return;
        long mid=(st+ed)/2;
        // System.out.printf("%d %d %d -> ",st, mid, ed);
        int cnt=count(mid);
        if(cnt%2==0){
            bSearch(mid+1,ed);
        } else{
            found=true;
            oddValue=Math.min(oddValue,(int)mid);
            bSearch(st,mid-1);
        }
    }
    
    int count(long v){
        int sum=0;
        for(int[] input: inputS){
            if(v<input[0])  continue;
            sum+=(Math.min(v,input[1])-input[0])/input[2]+1;
        }
        // System.out.println(v+" "+sum);
        return sum;
    }
    
    public static void main(String[] args) throws Exception{
        Solution T=new Solution();
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int inputN=Integer.parseInt(kb.readLine());
        int[][] inputS=new int[inputN][3];
        for(int i=0;i<inputN;i++){
            StringTokenizer stk=new StringTokenizer(kb.readLine());
            for(int k=0;k<3;k++){
                inputS[i][k]=Integer.parseInt(stk.nextToken());
            }
        }
        System.out.println(T.solution(inputN, inputS));
    }
}