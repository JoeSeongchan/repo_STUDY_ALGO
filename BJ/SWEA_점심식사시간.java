/*
점심식사시간 
*/
import java.util.*;
import java.io.*;
public class Solution{
    static class Person implements Comparable<Person>{
        int ri, ci;
        int arrivalTime;
        int downCnt;
        // 계단 아직 도착 안 함 : M
        // 계단 도착 함 : W
        // 계단 내려가는 중 : D
        // 계단 다 내려감 : C
        char status;
        Person(int ri, int ci){
            this.ri=ri;
            this.ci=ci;
        }
        // 하나의 객체를 여러 케이스에서 재사용하는 경우, init 메소드를 두어 init메소드를 호출하게 한다!
        void init(){
            arrivalTime=0;
            downCnt=0;
            status='M';
        }
        @Override
        public int compareTo(Person other){
            return Integer.compare(arrivalTime,other.arrivalTime);
        }
    }
    
    int width;
    int[][] map;
    List<Person> personS;
    int personN;
    int[][] stairS;
    int[] selected;
    int minTime;
    int breakPoint; // 디버깅을 위한 변수
    
    int solution(int width, int[][] map){
        int answer=0;
        init(width,map);
        divide(0);
        answer=minTime;
        return answer;
    }
    
    void init(int width, int[][] map){
        this.width=width;
        this.map=map;
        personS=new ArrayList<>(10);
        stairS=new int[2][3];
        for(int ri=0,sCnt=0;ri<width;ri++){
            for(int ci=0;ci<width;ci++){
                if(map[ri][ci]==0)  continue;
                if(map[ri][ci]==1){
                    personS.add(new Person(ri,ci));
                } else{
                    stairS[sCnt++]=new int[]{ri,ci,map[ri][ci]}; // 테크닉
                }
            }
        }
        personN=personS.size();
        selected=new int[personN];
        minTime=Integer.MAX_VALUE;
        breakPoint=0;
    }
    
    void divide(int idx) {
        if(idx>=personN){
            minTime=Math.min(minTime,simulation());
            return;
        }
        selected[idx]=0;
        divide(idx+1);
        selected[idx]=1;
        divide(idx+1);
    }
    
    int simulation(){
        List<Person>[] list=new ArrayList[]{new ArrayList<>(),new ArrayList<>()}; // 계단 별 사용자 리스트
        for(int i=0;i<personN;i++){
            Person p=personS.get(i);
            p.init();
            int no=selected[i]; // i번째 사람이 이용할 계단 번호
            p.arrivalTime=Math.abs(p.ri-stairS[no][0])+Math.abs(p.ci-stairS[no][1]); // 계단과의 거리 계산
            list[no].add(p); // 계단의 사용자 리스트에 사용자 추가 
        }
        int timeA=0,timeB=0; // 소요 시간
        if(list[0].size()>0){
            timeA=processDown(list[0],stairS[0][2]);
        }
        if(list[1].size()>0){
            timeB=processDown(list[1],stairS[1][2]);
        }
        return Math.max(timeA,timeB); // 모든 사용자가 계단 다 내려간 시간 계산 (최대)
    }
    
    int processDown(List<Person> list, int height){
        Collections.sort(list); // 계단 도착 시간에 따라 오름차순 정렬
        int time=list.get(0).arrivalTime; // 첫번째로 도착한 사람의 도착시간=시작시간
        int size=list.size(); // 계단 이용할 사람 수
        int ingCnt=0,cCnt=0; // 계단 위에 있는 사람 수, 계단 다 이용한 사람 수
        while(true){
            for(int i=0;i<size;i++){ // 모든 사람 순회
                Person p=list.get(i); // 사람
                // System.out.printf("ingCnt: %d, cCnt: %d\n",ingCnt,cCnt);
                if(p.status=='C'){ // 계단 다 이용한 경우
                    continue; // skip!
                } else if(p.arrivalTime==time){ // 계단에 막 도착한 경우
                    p.status='W'; // 대기상태에 돌입
                } else if(p.status=='W' && ingCnt<3){ // 대기상태이면서 현재 계단에 올라간 인원수가 3 미만인 경우
                    p.status='D'; // 계단에 진입
                    p.downCnt=1;  // 계단 내려간 횟수 1
                    ingCnt++; // 계단 이용중인 인원 수 1 증가
                } else if(p.status=='D'){ // 계단 내려가고 있는 중인 경우
                    if(p.downCnt==height){ // 계단 다 내려간 경우
                        p.status='C'; // 계단 다 내려갔음을 표시
                        ingCnt--; // 계단 이용중인 인원 수 1 감소
                        cCnt++; // 계단 다 이용한 인원 수 1 증가 
                    } else{ // 계단 다 내려가지 않은 경우
                        p.downCnt++; // 계단 내려간 횟수 1 증가
                    }
                }
            }
            // System.out.printf("ingCnt: %d, cCnt: %d\n",ingCnt,cCnt);
            if(cCnt==size)  break; // 모든 인원이 계단 다 내려간 경우, 반복 종료
            // if(breakPoint>=10)  System.exit(0);
            time+=1; // 시간 1 증가 
            breakPoint+=1;
        }
        return time;
    }
    
    public static void main(String[] args) throws Exception{
        Solution T=new Solution();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int tcN=Integer.parseInt(kb.readLine());
        StringBuilder sb=new StringBuilder();
        for(int tci=1;tci<=tcN;tci++){
            int width=Integer.parseInt(kb.readLine());
            int[][] map=new int[width][width];
            for(int ri=0;ri<width;ri++){
                StringTokenizer stk=new StringTokenizer(kb.readLine());
                for(int ci=0;ci<width;ci++){
                    map[ri][ci]=Integer.parseInt(stk.nextToken());
                }
            }
            sb.append('#').append(tci).append(' ')
                .append(T.solution(width,map)).append('\n');
        }
        System.out.print(sb);
    }
}
