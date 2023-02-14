/*
No. 4 [S/W 문제해결 기본] 8일차 - 암호문3

1. 문제 재정의 및 추상화 
링크드리스트를 사용해서 삽입, 삭제, 추가 작업을 수행한다. 

2. 풀이과정 상세히 적기
생략 

3. 시간 복잡도 계산 
O(N*M*V)
N: 원본 암호문의 길이
M: 명령어의 개수
V: 삽입, 삭제, 추가하는 값의 개수 

4. 코드 작성 
*/

import java.util.*;
import java.io.*;

public class Solution{
    static class Command{
        int type;
        
        LinkedList<Integer> vS;
        
        int insIdx;
        int insValN;
        int[] insValS;
        
        int addValN;
        int[] addValS;
        
        int delIdx;
        int delValN;
        
        Command(int insIdx, int insValN, int[] insValS){
            type=1;
            this.insIdx=insIdx;
            this.insValN=insValN;
            this.insValS=insValS;
        }
        
        Command(int delIdx, int delValN){
            type=2;
            this.delIdx=delIdx;
            this.delValN=delValN;
        }
        
        Command(int addValN, int[] addValS){
            type=3;
            this.addValN=addValN;
            this.addValS=addValS;
        }
        
        void run(LinkedList<Integer> vS){
            this.vS=vS;
            switch(type){
                case 1: insert();   break;
                case 2: delete();   break;
                case 3: add();      break;
            }    
        }
        
        void insert(){
            int idx=insIdx;
            for(int i=0;i<insValN;i++){
                vS.add(idx,insValS[i]);
                idx+=1;
            }
        }
        
        void delete(){
            for(int i=0;i<delValN;i++){
                vS.remove(delIdx);
            }
        }
        
        void add(){
            for(int i=0;i<addValN;i++){
                vS.add(addValS[i]);
            }
        }
    }
    
    int[] solution(int orgValN, int[] orgValS, int cmdN, Command[] cmdS){
        int[] answer=new int[10];
        LinkedList<Integer> vS=new LinkedList<>();
        for(int orgVal:orgValS){
            vS.offer(orgVal);
        }
        
        for(Command cmd:cmdS){
            cmd.run(vS);
        }
        
        for(int i=0;i<10;i++){
            answer[i]=vS.poll();
        }
        return answer;
    }
    
    public static void main(String[] args) throws Exception{
        Solution T=new Solution();
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        
        for(int tci=1;tci<=10;tci++){
            int orgValN=Integer.parseInt(kb.readLine());
            int[] orgValS=new int[orgValN];
            StringTokenizer stk=new StringTokenizer(kb.readLine());
            for(int i=0;i<orgValN;i++){
                orgValS[i]=Integer.parseInt(stk.nextToken());
            }
            
            int cmdN=Integer.parseInt(kb.readLine());
            Command[] cmdS=new Command[cmdN];
            stk=new StringTokenizer(kb.readLine());
            for(int i=0;i<cmdN;i++){
                String type=stk.nextToken();
                Command cmd=new Command(-1,-1);
                switch(type){
                    case "I":
                        int insIdx=Integer.parseInt(stk.nextToken());
                        int insValN=Integer.parseInt(stk.nextToken());
                        int[] insValS=new int[insValN];
                        for(int k=0;k<insValN;k++){
                            insValS[k]=Integer.parseInt(stk.nextToken());
                        }
                        cmd=new Command(insIdx,insValN,insValS);
                        break;
                    case "D":
                        int delIdx=Integer.parseInt(stk.nextToken());
                        int delValN=Integer.parseInt(stk.nextToken());
                        cmd=new Command(delIdx,delValN);
                        break;
                    case "A":
                        int addValN=Integer.parseInt(stk.nextToken());
                        int[] addValS=new int[addValN];
                        for(int k=0;k<addValN;k++){
                            addValS[k]=Integer.parseInt(stk.nextToken());
                        }
                        cmd=new Command(addValN,addValS);
                        break;
                }
                cmdS[i]=cmd;
            }
            int[] answer=T.solution(orgValN,orgValS,cmdN,cmdS);
            sb.append('#').append(tci).append(' ');
            for(int v: answer){
                sb.append(v).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
