/*
게리맨더링
1. 문제 재정의 및 추상화 
N개의 구역 1~N 
두 개의 선거구로 나눔. 선거구 적어도 하나의 구역 포함.
한 선거구에 있는 구역은 모두 연결 (MST). 중간 구역 또한 해당 선거구에 있어야 한다. 
두 선거구에 포함된 인구의 차이 최소
1~Math.ceil(N/2) 개의 구역을 고른다. (조합)
구역이 서로 연결되어 있는지 확인한다. (BFS)
나머지 구역 또한 서로 연결되어 있는지 확인한다. (BFS)
두 구역 그룹 또한 연결되어 있는 경우, 인구 총합 차를 계산한다. 
인구 총합 차 최소값을 구한다. 

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
1~Math.ceil(N/2) 개의 구역을 고른다. (조합): O(2^N)
구역이 서로 연결되어 있는지 확인한다. (BFS): O(N^2)

4. 코드 작성
*/
import java.util.*;
import java.io.*;
public class BJ_게리멘더링{
    int areaN;
    int[] peopleInfoS;
    Set<Integer>[] adjS;
    Set<Integer> allArea;
    int totalPeopleN;
    int minDiff;
    
    int solution(int areaN,int[] peopleInfoS, Set<Integer>[] adjS){
        int answer=0;
        init(areaN,peopleInfoS,adjS);
        for(int pickedAreaN=1;pickedAreaN<=Math.ceil(areaN/2);pickedAreaN++){
            combination(0,1,pickedAreaN,new HashSet<>(),0);
        }
        answer=minDiff;
        if(answer==Integer.MAX_VALUE){
            answer=-1;
        }
        return answer;
    }
    
    void init(int areaN,int[] peopleInfoS,Set<Integer>[] adjS){
        this.areaN=areaN;
        this.peopleInfoS=peopleInfoS;
        this.adjS=adjS;
        allArea=new HashSet<>();
        totalPeopleN=0;
        for(int i=1;i<=areaN;i++){
            allArea.add(i);
            totalPeopleN+=peopleInfoS[i];
        }
        minDiff=Integer.MAX_VALUE;
    }
    
    void combination(int cnt, int startIdx, int pickedAreaN, Set<Integer> pickedAreaS, int pickedPeopleN){
        if(cnt>=pickedAreaN){
            if(!isConnected(new HashSet<>(pickedAreaS))) return;
            Set<Integer> notPickedAreaS=new HashSet<>(allArea);
            notPickedAreaS.removeAll(pickedAreaS);
            if(!isConnected(new HashSet<>(notPickedAreaS))) return;
            // System.out.println(pickedAreaS);
            // System.out.println(notPickedAreaS);
            // System.out.println(pickedPeopleN+" "+(totalPeopleN-pickedPeopleN));
            int diff=Math.abs(pickedPeopleN-(totalPeopleN-pickedPeopleN));
            minDiff=Math.min(minDiff,diff);
            return;
        }
        for(int i=startIdx;i<=areaN;i++){
            pickedAreaS.add(i);
            combination(cnt+1,i+1,pickedAreaN,pickedAreaS,pickedPeopleN+peopleInfoS[i]);
            pickedAreaS.remove(i);
        }
    }
    
    boolean isConnected(Set<Integer> areaS){
        int startArea=pickStartArea(areaS);
        areaS.remove(startArea);
        Queue<Integer> queue=new ArrayDeque<>();
        queue.offer(startArea);
        while(!queue.isEmpty()){
            int area=queue.poll();
            for(int adj: adjS[area]){
                if(!areaS.contains(adj))   continue;
                queue.offer(adj);
                areaS.remove(adj);
            }
        }
        if(!areaS.isEmpty()){
            return false;
        }
        return true;
    }
    
    int pickStartArea(Set<Integer> areaS){
        Iterator<Integer> it=areaS.iterator();
        return it.next();
    }
    
    public static void main(String[] args) throws Exception{
        BJ_게리멘더링 T=new BJ_게리멘더링();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int areaN=Integer.parseInt(kb.readLine());
        int[] peopleInfoS=new int[areaN+1];
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        for(int i=1;i<=areaN;i++){
            peopleInfoS[i]=Integer.parseInt(stk.nextToken());
        }
        Set<Integer>[] adjS=new HashSet[areaN+1];
        for(int i=1;i<=areaN;i++){
            adjS[i]=new HashSet<>();
        }
        for(int i=1;i<=areaN;i++){
            stk=new StringTokenizer(kb.readLine());
            int n=Integer.parseInt(stk.nextToken());
            for(int k=0;k<n;k++){
                int adj=Integer.parseInt(stk.nextToken());
                adjS[i].add(adj);
                adjS[adj].add(i);
            }
        }
        System.out.println(T.solution(areaN,peopleInfoS,adjS));
    }
}