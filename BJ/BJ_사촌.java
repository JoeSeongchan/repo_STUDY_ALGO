/*
사촌 
1. 문제 재정의 및 추상화
사촌의 수를 구하여라. 사촌 = level이 같은 노드들
특정 노드 번호 k가 주어졌을 때 k의 사촌의 수를 구하여라 = k와 같은 level에 있는 노드들의 개수를 구하여라 (단 부모가 동일한 노드는 사촌이 아니다. 개수를 셀 때 제외한다.)

부모가 동일한 노드의 개수를 어떻게 구할 수 있을까? 노드안에 저장해둔다. 처음에 노드 리스트를 받고서 인접 정점 리스트를 만들 때 넣어둔다. 
BFS로 문제를 풀 수 있다. 
인접 정점 리스트를 만들어서 BFS 알고리즘을 적용한다.

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(V+E)
V=노드의 개수
E=간선의 개수 (=V-1)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_사촌{
    
    static class NodeGroup{
        int vN;
        boolean isTargetIn;
        NodeGroup(int vN, boolean isTargetIn){
            this.vN=vN;
            this.isTargetIn=isTargetIn;
        }
    }
    
    int vN, target;
    List<NodeGroup> graph;
    
    int solution(int vN, int target, int[] vS){
        int answer=0;
        init(vN, target, vS);
        int leftPadding=0;
        for(int i=0;i<graph.size();i++){
            NodeGroup list=graph.get(i);
            if(leftPadding<=i){
                leftPadding=i+1;
            } 
            int left=leftPadding;
            int right=left+list.vN-1;
            right=Math.min(right,graph.size()-1);
            int sum=0;
            boolean isTargetIn=false;
            for(int k=left;k<=right;k++){
                NodeGroup ng=graph.get(k);
                isTargetIn |= ng.isTargetIn;
                sum+=ng.vN;
                if(ng.isTargetIn){
                    sum-=ng.vN;
                }
            }
            if(isTargetIn){
                answer=sum;
                break;
            }
            leftPadding=right+1;
        }
        return answer;
    }
    
    void init(int vN, int target, int[] vS){
        this.vN=vN;
        this.target=target;
        graph=new ArrayList<>();
        graph.add(new NodeGroup(0,false));
        int prev=vS[0]-1;
        for(int v: vS){
            if(prev+1!=v){
                graph.add(new NodeGroup(0,false));
            }
            NodeGroup lastGroup=graph.get(graph.size()-1);
            lastGroup.vN+=1;
            if(v==target){
                lastGroup.isTargetIn=true;
            }
            prev=v;
        }
    }
    
    public static void main(String[] args) throws Exception{
        BJ_사촌 T=new BJ_사촌();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        while(true){
            StringTokenizer stk=new StringTokenizer(kb.readLine());
            int vN=Integer.parseInt(stk.nextToken());
            int target=Integer.parseInt(stk.nextToken());
            if(vN==0 && target==0)  break;
            int[] vS=new int[vN];
            stk=new StringTokenizer(kb.readLine());
            for(int i=0;i<vN;i++){
                vS[i]=Integer.parseInt(stk.nextToken());
            }
            sb.append(T.solution(vN, target, vS)).append('\n');
        }
        System.out.println(sb);
    }
}