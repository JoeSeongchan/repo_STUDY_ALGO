/*
낚시왕 (BJ 17143)
1. 문제 재정의 및 추상화 
R*C 격자판 row: 1~R, column: 1~C
상어는 크기와 속도를 가진다. 
상어 게임 시뮬레이션.. 다른 알고리즘을 사용할 필요가 없다. 
R: row, C: column, M: 상어 수 
r: sh row, c: sh col, d: 방향, z: 크기 
방향 1: 위, 2: 아래, 3: 오른쪽, 4: 왼쪽 
두 상어 같은 크기 같는 경우 없음. 초기 상태에서는 하나의 칸에 둘 이상의 상어가 있지 않다. 
어떤 상어가 다른 상어를 잡아 먹는다고 해서 상어의 무게는 달라지지 않는다. 

2. 풀이과정 상세히 적기 
생략 

3. 시간복잡도 계산 
O(C*(R*C))
최대 연산 수: 100^3=100만.
제한 시간 안에 능히 문제를 풀 수 있다. 

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class Main{
    // *
    class Shark implements Comparable<Shark>{
        int ri,ci,speed,dir,size;
        // 정지-위-아래-오른쪽-왼쪽
        final int[] dri=new int[]{0,-1,1,0,0};
        final int[] dci=new int[]{0,0,0,1,-1};
        
        Shark(int ri, int ci, int speed, int dir, int size){
            this.ri=ri;
            this.ci=ci;
            this.speed=speed;
            this.dir=dir;
            this.size=size;
        }
        
        void move(){
            int nri=ri+dri[dir]*speed;
            int nci=ci+dci[dir]*speed;
            
            while(nri<1 || nri>rowN || nci<1 || nci>colN){
                if(nri<1){
                    // 1 -> 1, 0 -> 2 
                    nri=(1-nri)+1;
                    
                }
                else if(nri>rowN){
                    // rowN -> rowN, rowN+1 -> rowN-1
                    nri=rowN+rowN-nri;
                }
                if(nci<1){
                    // 1 -> 1, 0 -> 2 
                    nci=(1-nci)+1;
                    
                }
                else if(nci>colN){
                    // colN -> colN, colN+1 -> colN-1
                    nci=colN+colN-nci;
                }
                changeDir();
            }
            ri=nri;
            ci=nci;
        }
        
        void changeDir(){
            if(dir==1)  dir=2;
            else if(dir==2) dir=1;
            else if(dir==3) dir=4;
            else if(dir==4) dir=3;
        }
        
        @Override
        public int compareTo(Shark other){
            return Integer.compare(other.size,size);
        }
    }
    
    // *
    static class Cell{
        PriorityQueue<Shark> container; 
        
        Cell(){
            container=new PriorityQueue<>();
        }
        
        void addShark(Shark sh){
            container.offer(sh);
        }
        
        void leaveOnlyOne(){
            if(container.isEmpty()){
                return;
            }
            Shark sh=container.poll();
            container.clear();
            container.offer(sh);
        }
        
        boolean isEmpty(){
            return container.isEmpty();
        }
        
        Shark getShark(){
            return container.poll();
        }
    }
    
    // *
    int rowN, colN;
    Cell[][] cellS;
    int catchN;
    
    // *
    int solution(int rowN, int colN, int sharkN, int[][] sharkS){
        int answer=0;
        init(rowN,colN,sharkN,sharkS);
        for(int personIdx=1;personIdx<=colN;personIdx++){
            catchShark(personIdx);
            moveAllSharkS();
            leaveOnlyOneSharkInCell();
        }
        answer=catchN;
        return answer;
    }
    
    // *
    void init(int rowN, int colN, int sharkN, int[][] sharkS){
        this.rowN=rowN;
        this.colN=colN;
        cellS=new Cell[rowN+1][colN+1];
        for(int ri=1;ri<=rowN;ri++){
            for(int ci=1;ci<=colN;ci++){
                cellS[ri][ci]=new Cell();
            }
        }
        for(int i=0;i<sharkN;i++){
            Shark sh=new Shark(sharkS[i][0],sharkS[i][1],
                                    sharkS[i][2],sharkS[i][3],
                                    sharkS[i][4]);
            cellS[sh.ri][sh.ci].addShark(sh);
        }
    }
    
    // *
    void catchShark(int personIdx){
        for(int ri=1;ri<=rowN;ri++){
            Cell cell=cellS[ri][personIdx];
            if(cell.isEmpty())  continue;
            Shark sh=cell.getShark();
            // System.out.printf("(%d, %d), %d\n",sh.ri,sh.ci,sh.size);
            catchN+=sh.size;
            break;
        }
    }
    
    // *
    void moveAllSharkS(){
        List<Shark> shS=new ArrayList<>();
        for(int ri=1;ri<=rowN;ri++){
            for(int ci=1;ci<=colN;ci++){
                Cell cell=cellS[ri][ci];
                if(cell.isEmpty())  continue;
                Shark sh=cell.getShark();
                shS.add(sh);
            }
        }
        for(Shark sh: shS){
            // System.out.print("("+sh.ri+", "+sh.ci+"), "+sh.dir+" -> ");
            sh.move();
            // System.out.println("("+sh.ri+", "+sh.ci+"), "+sh.dir);
            cellS[sh.ri][sh.ci].addShark(sh);
        }
    }
    
    // *
    void leaveOnlyOneSharkInCell(){
        for(int ri=1;ri<=rowN;ri++){
            for(int ci=1;ci<=colN;ci++){
                Cell cell=cellS[ri][ci];
                cell.leaveOnlyOne();
            }
        }
    }
    
    public static void main(String[] args) throws Exception{
        Main T=new Main();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        
        int rowN=Integer.parseInt(stk.nextToken());
        int colN=Integer.parseInt(stk.nextToken());
        int sharkN=Integer.parseInt(stk.nextToken());
        
        int[][] sharkS=new int[sharkN][5];
        for(int i=0;i<sharkN;i++){
            stk=new StringTokenizer(kb.readLine());
            for(int k=0;k<5;k++){
                sharkS[i][k]=Integer.parseInt(stk.nextToken());
            }
        }
        
        System.out.println(T.solution(rowN,colN,sharkN,sharkS));
    }
}