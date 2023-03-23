/*
국영수
1. 문제 재정의 및 추상화 
단순 정렬 문제!
이름은 사전 순에 따라 오름차순 정렬 

2. 풀이과정 상세히 적기
생략 

3. 시간복잡도 계산 
O(NlogN)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_국영수{
    static class Person{
        String name;
        int g1,g2,g3;
        Person(String name, int g1,int g2,int g3){
            this.name=name;
            this.g1=g1;
            this.g2=g2;
            this.g3=g3;
        }
    }
    
    int pN;
    Person[] pS;
    
    String solution(int pN, String[][] pInfoS){
        String answer="";
        init(pN,pInfoS);
        Arrays.sort(pS,(p1,p2)->{
            if(p1.g1<p2.g1) return 1;
            if(p1.g1>p2.g1) return -1;
            if(p1.g2<p2.g2) return -1;
            if(p1.g2>p2.g2) return 1;
            if(p1.g3<p2.g3) return 1;
            if(p1.g3>p2.g3) return -1;
            if(p1.name.compareTo(p2.name)<0)    return -1;
            if(p1.name.compareTo(p2.name)>0)    return 1;
            return 0;
        });
        StringBuilder sb=new StringBuilder();
        for(Person p:pS){
            sb.append(p.name).append('\n');
        }
        answer=sb.toString();
        return answer;
    }
    
    void init(int pN,String[][] pInfoS){
        this.pN=pN;
        pS=new Person[pN];
        for(int i=0;i<pN;i++){
            String[] pInfo=pInfoS[i];
            pS[i]=new Person(pInfo[0],Integer.parseInt(pInfo[1]),
            Integer.parseInt(pInfo[2]),Integer.parseInt(pInfo[3]));
        }
    }
    
    public static void main(String[] args) throws Exception{
        BJ_국영수 T=new BJ_국영수();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int pN=Integer.parseInt(kb.readLine());
        String[][] pInfoS=new String[pN][4];
        for(int i=0;i<pN;i++){
            StringTokenizer stk=new StringTokenizer(kb.readLine());
            for(int k=0;k<4;k++){
                pInfoS[i][k]=stk.nextToken();
            }
        }
        System.out.println(T.solution(pN,pInfoS));
    }
}