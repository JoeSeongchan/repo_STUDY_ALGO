/*
1. 문제 재정의 및 추상화
각 선수보다 상위 등수인 선수들 set, 하위 등수인 선수들 set을 둔다. 
상위 선수의 상위 선수를 탐색하여 set에 추가한다. 하위 등수도 마찬가지.
상위 선수 set의 크기+하위 선수 set의 크기 == 모든 선수의 수 -1 인 경우 등수 확정 

2. 풀이과정 상세히 적기 
상쉬 선수의 상위 선수를 탐색하는 법
DFS 사용, visited 배열 사용 

3. 시간복잡도 계산 
O(N*N)
O(N): 한 점을 골라서 
O(N): 그 점과 상위, 하위 등수인 선수들 탐색 (모든 정점 탐색할 때까지 반복)

4. 코드 작성
*/
import java.util.*;
class Solution {
    static class PROGRAMMERS_순위{
        Set<Integer> upperIdxS;
        Set<Integer> lowerIdxS;
        Person(){
            upperIdxS=new HashSet<>();
            lowerIdxS=new HashSet<>();
        }
        void addUpper(int idx){
            upperIdxS.add(idx);
        }
        void addLower(int idx){
            lowerIdxS.add(idx);
        }
        int getAdjN(){
            return upperIdxS.size()+lowerIdxS.size(); 
        }
    }
    
    Person[] pS;
    int pN;
    
    public int solution(int pN, int[][] rS) {
        int answer = 0;
        init(pN,rS);
        for(int i=1;i<=pN;i++){
            boolean[] visited=new boolean[pN+1];
            Set<Integer> newUpperIdxS=new HashSet<>();
            Set<Integer> newLowerIdxS=new HashSet<>();
            dfsUpper(i,i,visited,newUpperIdxS);
            dfsLower(i,i,visited,newLowerIdxS);
            pS[i].upperIdxS.addAll(newUpperIdxS);
            pS[i].lowerIdxS.addAll(newLowerIdxS);
        }
        int fixedCnt=0;
        for(int i=1;i<=pN;i++){
            if(pS[i].getAdjN()==pN-1){
                fixedCnt+=1;
            }
        }
        answer=fixedCnt;
        return answer;
    }
    
    void init(int pN, int[][] rS){
        pS=new Person[pN+1];
        for(int i=1;i<=pN;i++){
            pS[i]=new Person();
        }
        for(int[] r: rS){
            int pIdx1=r[0];
            int pIdx2=r[1];
            pS[pIdx1].addLower(pIdx2);
            pS[pIdx2].addUpper(pIdx1);
        }
    }
    
    void dfsUpper(int stIdx, int curIdx, boolean[] visited, Set<Integer> newUpperIdxS){
        visited[curIdx]=true;
        Person curP=pS[curIdx];
        for(int upperIdx: curP.upperIdxS){
            if(visited[upperIdx])   continue;
            newUpperIdxS.add(upperIdx);
            dfsUpper(stIdx,upperIdx,visited,newUpperIdxS);
        }
    }
    
    void dfsLower(int stIdx, int curIdx, boolean[] visited, Set<Integer> newLowerIdxS){
        visited[curIdx]=true;
        Person curP=pS[curIdx];
        for(int lowerIdx: curP.lowerIdxS){
            if(visited[lowerIdx])   continue;
            newLowerIdxS.add(lowerIdx);
            dfsLower(stIdx,lowerIdx,visited,newLowerIdxS);
        }
    }
}