/*
1. 문제 재정의 및 추상화 
사방이 막히면 방 하나로 센다. 한 칸 이동=>두 칸 이동
각 정점마다 간선 리스트를 둔다. 이전에 방문한 정점에 새로운 간선이 추가되면 방이 추가된 것이다. 

2. 풀이과정 상세히 적기
Map<Node,List<Node>> graph
생략

3. 시간복잡도 계산 
O(arrowN)
- 공간복잡도 계산
    O(arrowN)
    추산치.. 

4. 코드 작성 
*/
import java.util.*;
class PROGAMMERS_방의개수 {
    static class Node{
        int x, y;
        Node(int x,int y){
            this.x=x;
            this.y=y;
        }
        @Override 
        public boolean equals(Object obj){
            Node other=(Node) obj;
            if(x==other.x && y==other.y){
                return true;
            }
            return false;
        }
        @Override
        public int hashCode(){
            return Objects.hash(x, y);
        }
        @Override
        public String toString(){
            return "("+x+", "+y+")";
        }
    }
    
    Map<Node,Set<Node>> graph;
    int[] arS;
    int arN;
    final int[] dx=new int[]{0,1,1,1,0,-1,-1,-1};
    final int[] dy=new int[]{1,1,0,-1,-1,-1,0,1};
    
    public int solution(int[] arS) {
        int answer = 0;
        init(arS);
        answer=count();
        return answer;
    }
    
    void init(int[] arS){
        this.arS=arS;
        arN=arS.length;
        graph=new HashMap<>();
    }
    
    int count(){
        int cnt=0;
        Node pos=new Node(0,0);
        graph.put(pos,new HashSet<>());
        for(int ar: arS){
            for(int i=0;i<=1;i++){
                int newX=pos.x+dx[ar];
                int newY=pos.y+dy[ar];
                Node newPos=new Node(newX, newY);
                graph.get(pos).add(newPos);
                // new Pos 이전에 방문했었고, pos와 연결된 적이 없는 경우,
                if(graph.containsKey(newPos) && !graph.get(newPos).contains(pos)){
                    graph.get(newPos).add(pos);
                    cnt+=1;
                } 
                // new Pos 방문 안 한 경우,
                else if(!graph.containsKey(newPos)){
                    graph.put(newPos,new HashSet<>());
                    graph.get(newPos).add(pos);
                }
                pos=newPos;
            }
        }
        return cnt;
    }
}