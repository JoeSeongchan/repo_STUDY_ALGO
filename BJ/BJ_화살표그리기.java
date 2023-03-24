/*
화살표 그리기 
1. 문제 재정의 및 추상화 
    각 점을 그래프의 정점으로 생각한다. 
    점과 가장 가까운 점을 고른다. 최대 두 개의 점을 최대 인접 정점으로 가질 수 있다. 최대 인접 정점을 찾은 정점을 방문 처리를 하고, 최단 인접 정점과의 거리를 sum에 더한다. 그러면서 모든 정점에 대해서 위 작업을 반복한다.
    
    이를 위해서는 흰색 점과 검은색 점을 따로 분류해야 한다. 리스트를 두 개 만든다. 그리고 각 리스트에 인덱스를 저장한다. 그리고 정렬한다. (오름차순)
    각 정점의 최단 인접 정점을 구할 때는 왼쪽 원소와 오른쪽 원소를 확인한다. 
    간선에 대해서 중복 체크할 일이 있는가? 없다! 왜냐하면 이 그래프는 방향 그래프이기 때문이다! 
    
    모든 점에서 시작하는 화살표들의 길이 합이기 때문에 정점들을 모두 다 확인해봐도 된다. 
    
    테스트케이스 확인!
    0 3 5 => 3+2+2=7
    1 4 => 3+3=6
    
    0 3 4 6 9 => 3+1+1+2+3=10
    7 10 => 3+3=6
    16
    
    ** 주의: 색깔이 N가지... 모든 점이 색이 다 다를 수 있다... 
    
2. 풀이과정 상세히 작성
생략

3. 시간복잡도 계산
O(NlogN)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_화살표그리기{
    
    int pN;
    List<Integer>[] sameColorS;
    
    int solution(int pN, int[][] pS){
        int answer=0;
        init(pN,pS);
        for(int i=0;i<sameColorS.length;i++){
            if(sameColorS[i]==null) continue;
            answer+=count(sameColorS[i]);
        }
        return answer;
    }
    
    void init(int pN,int[][] pS){
        this.pN=pN;
        sameColorS=new ArrayList[pN+100];
        for(int[] p:pS){
            if(sameColorS[p[1]]==null){
                sameColorS[p[1]]=new ArrayList<>();
            }
            sameColorS[p[1]].add(p[0]);
        }
        for(int i=0;i<sameColorS.length;i++){
            if(sameColorS[i]==null) continue;
            Collections.sort(sameColorS[i]);
        }
    }
    
    int count(List<Integer> idxS){
        int sum=0;
        if(idxS.size()==1)  return 0;
        for(int i=0;i<idxS.size();i++){
            if(i==0){
                sum+=(idxS.get(i+1)-idxS.get(i));
            } else if(i==idxS.size()-1){
                sum+=(idxS.get(i)-idxS.get(i-1));
            } else{
                sum+=Math.min((idxS.get(i)-idxS.get(i-1)),(idxS.get(i+1)-idxS.get(i)));
            }
        }
        return sum;
    }
    
    public static void main(String[] args) throws Exception{
        BJ_화살표그리기 T=new BJ_화살표그리기();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int pN=Integer.parseInt(kb.readLine());
        int[][] pS=new int[pN][2];
        for(int i=0;i<pN;i++){
            StringTokenizer stk=new StringTokenizer(kb.readLine());
            for(int k=0;k<2;k++){
                pS[i][k]=Integer.parseInt(stk.nextToken());
            }
        }
        System.out.println(T.solution(pN,pS));
    }
}