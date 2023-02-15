/*
No. 8 [S/W 문제해결 응용] 3일차 - 공통조상

1. 문제 재정의 및 추상화 
두 정점의 가장 가까운 공통 조상을 찾고, 그 정점을 루트로 하는 서브 트리의 크기를 알아내어라. 
이진트리를 배열로 나타낼 수 없다. 왜냐하면 노드의 자리 번호가 순서대로 배정되지 않았기 때문이다. 그렇다면 Node 클래스를 정의해서 접근할 수 밖에 없다. Node 맵을 만들자! 

2. 풀이과정 상세히 적기
1번 단계에서 이미 상세히 적었다. 생략 

3. 시간 복잡도 계산
O(V)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class Solution{
    
    static class Node{
        int idx;
        int parentIdx;
        int count;
        Node(int idx,int parentIdx){
            this.idx=idx;
            this.parentIdx=parentIdx;
            count=1;
        }
    }
    
    int[] solution(Map<Integer,Node> tree, int nodeN, int v1, int v2){
        int[] answer=new int[2];
        for(int i=2;i<=nodeN;i++){
            if(!tree.containsKey(i))    continue;
            int parentIdx=i;
            while(parentIdx!=1){
                parentIdx=tree.get(parentIdx).parentIdx;
                tree.get(parentIdx).count+=1;
            }
        }
        Stack<Integer> pv1S=new Stack<>();
        Stack<Integer> pv2S=new Stack<>();
        int pv1=v1;
        pv1S.push(pv1);
        while(pv1!=1){
            pv1=tree.get(pv1).parentIdx;
            pv1S.push(pv1);
        }
        int pv2=v2;
        pv2S.push(pv2);
        while(pv2!=1){
            pv2=tree.get(pv2).parentIdx;
            pv2S.push(pv2);
        }
        int smallestParent=1;
        while(!pv1S.isEmpty()&&!pv2S.isEmpty()){
            pv1=pv1S.pop();
            pv2=pv2S.pop();
            if(pv1==pv2){
                smallestParent=pv1;
                continue;
            } 
            break;
        }
        answer[0]=smallestParent;
        answer[1]=tree.get(smallestParent).count;
        return answer;
    }
    
    public static void main(String[] args) throws Exception{
        Solution T=new Solution();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int tcN=Integer.parseInt(kb.readLine());
        StringBuilder sb=new StringBuilder();
        
        for(int tci=1;tci<=tcN;tci++){
            StringTokenizer stk=new StringTokenizer(kb.readLine());
            int vN=Integer.parseInt(stk.nextToken());
            int eN=Integer.parseInt(stk.nextToken());
            int v1=Integer.parseInt(stk.nextToken());
            int v2=Integer.parseInt(stk.nextToken());
            Map<Integer,Node> tree=new HashMap<>();
            stk=new StringTokenizer(kb.readLine());
            for(int i=0;i<eN;i++){
                int pv=Integer.parseInt(stk.nextToken());
                int cv=Integer.parseInt(stk.nextToken());
                tree.put(cv,new Node(cv,pv));
            }
            tree.put(1,new Node(1,-1));
            int[] answer=T.solution(tree,vN,v1,v2);
            sb.append('#').append(tci).append(' ');
            sb.append(answer[0]).append(' ').append(answer[1]).append('\n');
        }
        System.out.print(sb);
    }
}
