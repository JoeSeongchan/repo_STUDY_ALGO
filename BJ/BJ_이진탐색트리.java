/*
이진 검색 트리 
1. 문제 재정의 및 추상화 
루트 노드부터 순회하며 노드를 삽입할 곳을 찾는다.

2. 풀이과정 상세히 적기
생략 

3. 시간복잡도 계산
O(V^2)

** 배운 점
기초로 돌아가자. 문제를 꼬아서 보지 말고, 그래프를 소프트웨어로 어떻게 구현할 지 생각하자.
시간복잡도를 계산해서 충분히 돌아갈 만 하다면 Try한다. 그만큼 시간을 준 이유가 있을 것이다.
시간이 남는다면 그 시간을 충분히 쓸 수 있는 해법을 사용해 문제를 풀자.

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_이진탐색트리{
    
    static class Node{
        
        Node left,right;
        int v;
        
        Node(int v){
            this.v=v;
        }
        
        @Override
        public String toString(){
            return String.format("left: %s, right: %s, v: %d",left,right,v);
        }
    }
    
    Node root;
    StringBuilder sb;
    
    String solution(int vN, int[] preTr){
        String answer="";
        init(vN, preTr);
        postTr(root);
        answer=sb.toString();
        return answer;
    }
    
    void init(int vN, int[] preTr){
        root=new Node(preTr[0]); // 이진탐색 트리의 루트 노드
        for(int i=1;i<vN;i++){
            int v=preTr[i];
            Node cur=new Node(v);
            Node mov=root; // 루트노드부터 시작하여 탐색
            while(true){
                // 왼쪽
                if(v<mov.v){
                    if(mov.left==null){
                        mov.left=cur; // 왼쪽 자식 노드로 넣음
                        break;
                    } else{
                        mov=mov.left; 
                    }
                } 
                // 오른쪽
                else{
                    if(mov.right==null){
                        mov.right=cur; // 오른쪽 자식 노드로 넣음
                        break;
                    } else{
                        mov=mov.right;
                    }
                }
            }
        }
        sb=new StringBuilder();
    }
    
    void postTr(Node cur){
        if(cur==null)   return;
        postTr(cur.left);
        postTr(cur.right);
        sb.append(cur.v).append('\n');
    }
    
    public static void main(String[] args) throws Exception{
        BJ_이진탐색트리 T=new BJ_이진탐색트리();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        List<Integer> inputList=new ArrayList<>();
        while(true){
            try{
                inputList.add(Integer.parseInt(kb.readLine()));
            }catch(Exception e){
                break;
            }
        }
        int[] preTr=new int[inputList.size()];
        for(int i=0;i<inputList.size();i++){
            preTr[i]=inputList.get(i);
        }
        System.out.println(T.solution(preTr.length,preTr));
    }
}