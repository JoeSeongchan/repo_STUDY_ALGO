/*
달이 차오른다, 가자.
1. 문제 재정의 및 추상화 
기본적으로 BFS 알고리즘을 사용한다. 
시작점에서 출발하여 사방으로 퍼져나간다. 그러다가 열쇠를 발견하면 기존에 방문했던 곳을 덮어쓸 수 있다. 이런 식으로 계속 퍼져나간다.

2. 풀이과정 상세히 적기
생략

** 테스트케이스를 돌려봤을 때 원하는 결과가 잘 나오는가?
테스트케이스 1 => 잘 나옴
테스트케이스 2 => 잘 나옴 
테스트케이스 3 => 잘 나옴 
... 
3. 시간복잡도 계산
O(V+E)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

/*
16Bytes * 2500 = 40_000Bytes
메모리 초과가 왜 날까?
*/

public class Solution{
    static class Point{
        int ri,ci,dist;
        int keyS;
        
        Point(int ri, int ci){
            this.ri=ri;
            this.ci=ci;
            keyS=0;
            dist=0;
        }
        
        Point(int ri,int ci, Point prev){
            this(ri,ci);
            keyS=prev.keyS;
            dist=prev.dist+1;
        }
        
        boolean canReplace(Point target){
            if(target==null)    return true;
            // key가 조금이라도 다르면 바꾼다. <= 이 부분에서 메모리 초과 발생
            // 너무 많은 Point가 Queue에 쌓인다. 이걸 어떻게 해결할 수 있을까? 
            if(keyS == target.keyS){
                return false;
            }
            // 기존의 정보값의 키가 새로운 정보값의 키를 포함하는 경우, false 리턴
            for(int i=0;i<6;i++){
                // 기존의 정보값의 키가 새로운 정보값의 키를 포함하지 않는 경우, (업데이트된 키가 하나라도 있는 경우)
                if((keyS & 1<<i)!=0 && ((1<<i)&target.keyS)==0){
                    return true;
                } 
            }
            // 업데이트된 키가 없는 경우,
            return false;
        }
        
        void addKey(char key){
            keyS |= 1<<(key-'a'); 
        }
        
        boolean canOpen(char door){
            return (keyS & 1<<(door-'A'))!=0;
        }
        
        @Override
        public String toString(){
            return String.format("ri: %d, ci: %d, dist: %d\n",ri,ci,dist);
        }
    }
    
    int rowN,colN;
    char[][] map;
    Point startPoint;
    
    int solution(int rowN, int colN, char[][] map){
        int answer=0;
        init(rowN,colN,map);
        answer=findRoute();
        return answer;
    }
    
    void init(int rowN, int colN, char[][] map){
        this.rowN=rowN;
        this.colN=colN;
        this.map=map;
        findStartPoint();
    }
    
    void findStartPoint(){
        for(int ri=0;ri<rowN;ri++){
            for(int ci=0;ci<colN;ci++){
                if(map[ri][ci]=='0'){
                    startPoint=new Point(ri,ci);
                }
            }
        }
    }
    
    int findRoute(){
        Point[][] visited=new Point[rowN][colN];
        Queue<Point> tskS=new ArrayDeque<>();
        visited[startPoint.ri][startPoint.ci]=startPoint;
        tskS.offer(startPoint);
        final int[] dri=new int[]{1,0,-1,0};
        final int[] dci=new int[]{0,1,0,-1};
        Point endPoint=null;
        // System.out.println(Arrays.deepToString(map));
        while(!tskS.isEmpty()){
            Point p=tskS.poll();
            // System.out.println(p);
            // System.out.printf("type: %c\n\n",map[p.ri][p.ci]);
            if(map[p.ri][p.ci]=='1'){
                endPoint=p;
                break;
            }
            
            for(int i=0;i<4;i++){
                int nri=p.ri+dri[i];
                int nci=p.ci+dci[i];
                if(nri<0||nri>=rowN)    continue;
                if(nci<0||nci>=colN)    continue;
                if(map[nri][nci]=='#')  continue;
                char type=map[nri][nci];
                if(isKey(type)){
                    Point newPoint=new Point(nri,nci,p);
                    newPoint.addKey(type);
                    if(newPoint.canReplace(visited[nri][nci])){
                        visited[nri][nci]=newPoint;
                        tskS.offer(newPoint);
                    }
                } else if(isDoor(type)){
                    Point newPoint=new Point(nri,nci,p);
                    if(newPoint.canOpen(type) && newPoint.canReplace(visited[nri][nci])){
                        visited[nri][nci]=newPoint;
                        tskS.offer(newPoint);
                    }
                } else  {
                    Point newPoint=new Point(nri,nci,p);
                    if(newPoint.canReplace(visited[nri][nci])){
                        visited[nri][nci]=newPoint;
                        tskS.offer(newPoint);
                    }
                }
            }
        }
        if(endPoint==null){
            return -1;
        } 
        return endPoint.dist;
    }
    
    public boolean isKey(char type){
        if(Character.isAlphabetic(type) && Character.isLowerCase(type)){
            return true;
        }
        return false;
    }
    
    public boolean isDoor(char type){
        if(Character.isAlphabetic(type) && Character.isUpperCase(type)){
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) throws Exception{
        Solution T=new Solution();
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        int rowN=Integer.parseInt(stk.nextToken());
        int colN=Integer.parseInt(stk.nextToken());
        char[][] map=new char[rowN][colN];
        for(int ri=0;ri<rowN;ri++){
            map[ri]=kb.readLine().toCharArray();
        }
        System.out.println(T.solution(rowN,colN,map));
    }
}