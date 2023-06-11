/*
표현 가능한 이진트리 
1. 문제 재정의 및 추상화 
이진트리를 수로 표현. 
주어진 이진트리에 더미노드 추가 => 포화 이진트리로 만듦. 
포화 이진트리의 노드들을 가장 왼쪽 노드부터 가장 오른쪽 노드까지 순서대로 살펴봄. 
number => 2진수 => 포화이진트리 자릿수 맞추기 (앞 부분 0으로 채움) => 리프 노드 하나씩 살펴봄. 자식노드가 있는데 부모노드가 없는 경우? -> 이진트리로 해당 수 표현 못함. 살펴본 노드는 따로 체크 (visited) 
시간복잡도는? number 최댓값 = 10^15. 2진수로 나타내면 길이가 64는 넘지 않을 것. 64*1만 = 64만. 시간 안에 문제 풀 수 있음. 

2. 풀이과정 상세히 적기 
2진수로 나타냈을 때 길이가 5라면 7을 맞춘다. 앞에 0을 2개 추가한다. (2^K)-1개 맞출 것.

예시 적용 
7 = 111 = 111
2 1 2
1 1 1
58 = 111010 = 0111010
3 2 3 1 3 2 3
0 1 1 1 0 1 0
42=101010=0101010
3 2 3 1 3 2 3
0 1 0 1 0 1 0
Divide and conquer로 문제 풀 수 있음. 
서브트리의 루트노드가 0이면 자식 노드를 루트노드로 하는 서브트리를 다음으로 탐색할 때 이 사실을 알려준다. 
만약 루트노드가 1인데, 부모노드가 0인 것이 하나라도 있다면 옳지 않은, 유효하지 않은 이진트리이다. Reject한다. 

3. 시간복잡도 계산
이전 단계에서 살펴봄.

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

class Solution {
    int strN;
    String[] strS;
    
    public int[] solution(long[] numbers) {
        int[] answer = {};
        init(numbers);
        answer=new int[strN];
        // debug();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<strN;i++){
            String str=strS[i];
            answer[i]=divConq(str,0,str.length()-1,1);
        }
        return answer;
    }
    
    void init(long[] numbers){
        strN=numbers.length;
        strS=new String[strN];
        for(int k=0;k<strN;k++){
            long number=numbers[k];
            StringBuilder sb=new StringBuilder();
            sb.append(Long.toBinaryString(number));
            int length=sb.length();
            int exponent=(int)Math.ceil(Math.log(length+1)/Math.log(2));
            int newLength=(1<<exponent)-1;
            for(int i=0;i<newLength-length;i++){
                sb.insert(0,0);
            }
            strS[k]=sb.toString();
        }
    }
    
    void debug(){
        for(String str: strS){
            System.out.println(str);
        }
    }
    
    int divConq(String str, int stIdx, int edIdx, int prevMid){
        if(stIdx>edIdx){
            return 1;
        }
        int midIdx=(stIdx+edIdx)/2;
        int mid=Character.getNumericValue(str.charAt(midIdx));
        if((~prevMid&mid)==1){
            return 0;
        }
        int ans=divConq(str,stIdx,midIdx-1,mid);
        ans&=divConq(str,midIdx+1,edIdx,mid);
        return ans;
    }
    
    
}