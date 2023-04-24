/*
토마토 
1. 문제 재정의 및 추상화 
전형적인 BFS 문제

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(M*N*H)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_토마토{
    
    Queue<int[]> tskS;
    int rowN, colN, height;
    int[][][] container;
    int[][][] dayS;
    int unripedCnt;
    
    int solution(int rowN, int colN, int height, int[][][] container){
        int answer=-1;
        init(rowN,colN,height,container);
        final int[] dri=new int[]{1,0,-1,0,0,0};
        final int[] dci=new int[]{0,1,0,-1,0,0};
        final int[] dcti=new int[]{0,0,0,0,1,-1};
        
        if(unripedCnt==0){
            return 0;
        }
        
        while(!tskS.isEmpty()){
            int[] p=tskS.poll();
            int ri=p[0],ci=p[1],cti=p[2];
            int day=dayS[cti][ri][ci];
            //System.out.printf("(%d, %d, %d) -> day: %d\n",cti,ri,ci,day);
            
            for(int i=0;i<6;i++){
                int nri=ri+dri[i];
                int nci=ci+dci[i];
                int ncti=cti+dcti[i];
                
                if(nri<0 || nri>=rowN)  continue;
                if(nci<0 || nci>=colN)  continue;
                if(ncti<0 || ncti>=height)  continue;
                
                if(container[ncti][nri][nci]==-1)   continue;
                if(container[ncti][nri][nci]==1)    continue;
                
                container[ncti][nri][nci]=1;
                unripedCnt-=1;
                tskS.offer(new int[]{nri,nci,ncti});
                dayS[ncti][nri][nci]=day+1;
                
                if(unripedCnt==0){
                    answer=day+1;
                    break;
                }
            }
        }
        
        return answer;
    }
    
    void init(int rowN,int colN,int height,int[][][] container){
        this.rowN=rowN;
        this.colN=colN;
        this.container=container;
        
        tskS=new ArrayDeque<>();
        unripedCnt=0;
        dayS=new int[height][rowN][colN];
        
        for(int cti=0;cti<height;cti++){
            for(int ri=0;ri<rowN;ri++){
                for(int ci=0;ci<colN;ci++){
                    if(container[cti][ri][ci]==-1){
                        dayS[cti][ri][ci]=-1;
                        continue;
                    }
                    if(container[cti][ri][ci]==0){
                        unripedCnt+=1;
                        dayS[cti][ri][ci]=-1;
                        continue;
                    }
                    tskS.offer(new int[]{ri,ci,cti});
                    dayS[cti][ri][ci]=0;
                }
            }
        }
    }
    
    
    public static void main(String[] args) throws Exception{
        BJ_토마토 T=new BJ_토마토();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        int colN=Integer.parseInt(stk.nextToken());
        int rowN=Integer.parseInt(stk.nextToken());
        int height=Integer.parseInt(stk.nextToken());
        int[][][] container=new int[height][rowN][colN];
        for(int cti=0;cti<height;cti++){
            for(int ri=0;ri<rowN;ri++){
                stk=new StringTokenizer(kb.readLine());
                for(int ci=0;ci<colN;ci++){
                    container[cti][ri][ci]=Integer.parseInt(stk.nextToken());
                }
            }
        }
        System.out.println(T.solution(rowN,colN,height,container));
    }
}