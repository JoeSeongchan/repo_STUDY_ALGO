/*
다리 만들기 2
1. 문제 재정의 및 추상화
Kruskal 알고리즘 또는 Prim 알고리즘 사용하여 문제를 푼다.
BFS 알고리즘으로 각 섬을 잇는 다리의 길이를 구한다. (직선을 그대로 유지해야 하기 때문에 뻗어나갈 때 각 점을 방문하는 경로의 방향을 알려주는 값을 큐에 함께 담아야 한다.)

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(S*SlogS+S*(N*M)*N*M)
S = 다리의 개수 = N*M

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class Main{
    
    static class Edge implements Comparable<Edge>{
        int stIdx,edIdx,weight;
        
        Edge(int stIdx,int edIdx,int weight){
            this.stIdx=stIdx;
            this.edIdx=edIdx;
            this.weight=weight;
        }
        
        // 작은 weight를 가진 Edge가 더 높은 우선순위를 가진다. 
        @Override
        public int compareTo(Edge other){
            return Integer.compare(weight,other.weight);
        }
    }
    
    final int[] dri=new int[]{1,0,-1,0};
    final int[] dci=new int[]{0,1,0,-1};
    
    int rowN,colN;
    int[][] map;
    int[][] landMap;
    int landN;
    Map<Integer,List<int[]>> borderOfLandS;
    List<Edge> edgeS;
    
    int[] pInfoS;
    int[] rInfoS;
    
    int solution(int rowN, int colN, int[][] map){
        int answer=0;
        init(rowN,colN,map);
        answer=kruskal();
        return answer;
    }
    
    void init(int rowN, int colN, int[][] map){
        this.rowN=rowN;
        this.colN=colN;
        this.map=map;
        landMap=new int[rowN][colN];
        landN=0;
        for(int ri=0;ri<rowN;ri++){
            Arrays.fill(landMap[ri],-1);
        }
        borderOfLandS=new HashMap<>();
        edgeS=new ArrayList<>();
        
        findBorderOfLandS();
        findEdgeS();
        
    }
    
    // 각 섬의 경계 영역 찾기
    void findBorderOfLandS(){
        for(int ri=0;ri<rowN;ri++){
            for(int ci=0;ci<colN;ci++){
                // 바다인 경우
                if(map[ri][ci]==0)  continue;
                // 이미 조사한 영역인 경우
                if(landMap[ri][ci]!=-1)   continue;
                // BFS
                Queue<int[]> tskS=new ArrayDeque<>();
                landMap[ri][ci]=landN;
                landN+=1;
                tskS.offer(new int[]{ri,ci});
                
                while(!tskS.isEmpty()){
                    int[] p=tskS.poll();
                    int land=landMap[p[0]][p[1]];
                    int adjBlockN=0;
                    for(int i=0;i<4;i++){
                        int nri=p[0]+dri[i];
                        int nci=p[1]+dci[i];
                        // 영역 밖인 경우
                        if(!isValid(nri,nci))   continue;
                        // 바다인 경우
                        if(map[nri][nci]==0)    continue;
                        // 인접 영역 개수 증가
                        adjBlockN+=1;
                        // 이미 조사한 영역인 경우
                        if(landMap[nri][nci]!=-1) continue;
                        landMap[nri][nci]=land;
                        tskS.offer(new int[]{nri,nci});
                    }
                    // 바다와 인접한 경우
                    if(adjBlockN<4){
                        borderOfLandS.putIfAbsent(land,new ArrayList<>());
                        // 다리를 건설할 수 있는 경계 추가
                        borderOfLandS.get(land).add(p);
                    }
                }
            }
        }
    }
    
    // 좌표 유효성 검사
    boolean isValid(int ri, int ci){
        if(ri<0||ri>=rowN)  return false;
        if(ci<0||ci>=colN)  return false;
        return true;
    }
    
    // 모든 엣지 찾기
    void findEdgeS(){
        for(int stIdx=0;stIdx<landN;stIdx++){
            List<int[]> borderS=borderOfLandS.get(stIdx);
            for(int[] border: borderS){
                int ri=border[0];
                int ci=border[1];
                // 경계의 타입 (위, 아래,...)
                int type=typeOfBorder(ri,ci);
                
                // 상하좌우 검사
                for(int i=0;i<4;i++){
                    int len=0;
                    if((type&(1<<i))==0)  continue;
                    int nri=ri;
                    int nci=ci;
                    while(true){
                        nri+=dri[i];
                        nci+=dci[i];
                        len+=1;
                        // 범위 밖 좌표인 경우
                        if(!isValid(nri,nci))   break;
                        int edIdx=landMap[nri][nci];
                        // 자기 자신에게 연결하는 경우
                        if(edIdx==stIdx) break;
                        // 다른 섬과 연결하는 경우
                        if(edIdx!=-1){
                            // 길이가 2 미만인 경우
                            if(len-1<2)   break;
                            edgeS.add(new Edge(stIdx,edIdx,len-1));
                            // System.out.printf("stIdx: %d, edIdx: %d, len: %d\n",stIdx,edIdx,len-1);
                            break;
                        }
                        
                    }
                }
            }
        }
    }
    
    // 타입 찾기
    int typeOfBorder(int ri,int ci){
        int type=0;
        for(int i=0;i<4;i++){
            int nri=ri+dri[i];
            int nci=ci+dci[i];
            // 범위 밖인 경우
            if(!isValid(nri,nci))   continue;
            // 바다인 경우
            if(map[nri][nci]==0){
                type|=(1<<i);
            }
        }
        return type;
    }
    
    // 집합 만들기
    void makeSet(int i){
        pInfoS[i]=i;
        rInfoS[i]=0;
    }
    
    // 루트 찾기
    int findRoot(int i){
        if(pInfoS[i]!=i){
            pInfoS[i]=findRoot(pInfoS[i]);
        }
        return pInfoS[i];
    }
    
    // 합집합
    boolean union(int i1, int i2){
        int root1=findRoot(i1);
        int root2=findRoot(i2);
        int rank1=rInfoS[root1];
        int rank2=rInfoS[root2];
        if(root1==root2)    return false;
        if(rank1>=rank2){
            if(rank1==rank2){
                rInfoS[root1]+=1;
            }
            pInfoS[root2]=root1;
        } else{
            pInfoS[root1]=root2;
        }
        return true;
    }
    
    // 크루스칼 (MST)
    int kruskal(){
        // 다리 길이 총합
        int sum=0;
        pInfoS=new int[landN];
        rInfoS=new int[landN];
        for(int i=0;i<landN;i++){
            makeSet(i);
        }
        Collections.sort(edgeS);
        for(Edge e: edgeS){
            int i1=e.stIdx;
            int i2=e.edIdx;
            int w=e.weight;
            if(union(i1,i2)){
                sum+=w;         
            }
        }
        int root=findRoot(0);
        for(int i=1;i<landN;i++){
            if(root!=findRoot(i)){
                sum=-1;
                break;
            }
        }
        return sum;
    }
    
    
    public static void main(String[] args) throws Exception{
        // 입출력 코드
        Main T=new Main();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        int rowN=Integer.parseInt(stk.nextToken());
        int colN=Integer.parseInt(stk.nextToken());
        int[][] map=new int[rowN][colN];
        for(int ri=0;ri<rowN;ri++){
            stk=new StringTokenizer(kb.readLine());
            for(int ci=0;ci<colN;ci++){
                map[ri][ci]=Integer.parseInt(stk.nextToken());
            }
        }
        System.out.println(T.solution(rowN,colN,map));
    }
}