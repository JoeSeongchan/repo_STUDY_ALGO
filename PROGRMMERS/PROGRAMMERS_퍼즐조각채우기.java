/*
1. 문제 재정의 및 추상화 
딱 맞는 조각을 빈 공간에 넣어야 한다.
퍼즐 조각은 상하좌우로 인접해 붙어있는 경우가 없다. 
최대한 많은 조각을 게임 보드에 채워 넣어야 한다. 
게임 보드판에서 최대 몇 칸을 채워 넣을 수 있는지 구하여라
1=이미 채워진 칸
퍼즐 조각이 놓일 빈칸을 최대 6개까지 연결된 형태로만 주어진다. 
=> 퍼즐을 하나의 숫자로 나타낼 수 있지 않을까? 자릿수 6개 커버 가능

퍼즐 조각이 놓인 판 
1=조각이 놓인 칸

테이블을 회전하면서 game_board 빈 칸을 파악한다. 
해시값이 일치하는 조각을 꺼내서 빈 칸을 채운다. 
하나의 방향에 대해서 위 작업을 수행한 다음 다시 보드판을 회전시킨다.

2. 풀이과정 상세히 적기
DFS로 조각/빈칸을 파악하면서 해당 조각/빈칸을 정수값으로 바꾼다. 
table 파악 -> (game board 파악 -> game board 빈 칸 채움 -> game board 돌림) -> 4번 game board 회전했으면 반복 탈출하고 지금까지 채운 칸의 수를 리턴 

3. 시간복잡도 계산 
DFS: O(N^2)
회전: O(N^2)
배열에서 조각 유무 확인: O(1)
최종 시간 복잡도: O(N^2)
연산 수: 50^2=2500

4. 코드 작성 
*/
import java.util.*;
class PROGRAMMERS_퍼즐조각채우기 {
    // *
    int rowN, colN;
    // 게임판
    int[][] game;
    // 조각판
    int[][] board;
    Map<String,Integer> pieceS;
    // 상-하-좌-우
    final int[] dri=new int[]{-1,1,0,0};
    final int[] dci=new int[]{0,0,-1,1};
    int filledCnt=0;
    
    // *
    public int solution(int[][] game, int[][] board) {
        int answer = -1;
        init(game,board);
        findPieceInBoard();
        for(int i=0;i<4;i++){
            fill();
            turn();
        }
        printGame();
        answer=filledCnt;
        return answer;
    }
    
    // *
    void init(int[][] game, int[][] board){
        this.game=game;
        this.board=board;
        rowN=game.length;
        colN=game[0].length;
        pieceS=new HashMap<>();
    }
    
    // *
    void printGame(){
        for(int ri=0;ri<rowN;ri++){
            for(int ci=0;ci<colN;ci++){
                System.out.print(game[ri][ci]+" ");
            }
            System.out.println();
        }
    }
    
    // *
    // (0,0) -> (rowN-1,0)
    // (0,1) -> (rowN-1-(1),0)
    // (0,2) -> (rowN-1-(2),0)
    // (1,0) -> (rowN-1,1)
    // 게임판 회전
    void turn(){
        int[][] result=new int[colN][rowN];
        for(int ri=0;ri<rowN;ri++){
            for(int ci=0;ci<colN;ci++){
                result[rowN-1-(ci)][ri]=game[ri][ci];
            }
        }
        int rowNTmp=rowN;
        rowN=colN;
        colN=rowNTmp;
        game=result;
    }
    
    // *
    // 조각판에서 조각 찾기 
    void findPieceInBoard(){
        boolean[][] visited=new boolean[rowN][colN];
        for(int ri=0;ri<rowN;ri++){
            for(int ci=0;ci<colN;ci++){
                if(board[ri][ci]==0)  continue;
                if(visited[ri][ci]) continue;
                // 조각 찾기
                String piece=bfsBoard(visited,ri,ci);
                // 조각 기록
                pieceS.putIfAbsent(piece,0);
                pieceS.put(piece,pieceS.get(piece)+1);
            }
        }
    }
    
    // BFS로 조각판에서 조각 찾기 
    String bfsBoard(boolean[][] visited, int ri, int ci){
        Queue<int[]> tskS=new ArrayDeque<>();
        tskS.offer(new int[]{ri,ci});
        visited[ri][ci]=true;
        StringBuilder piece=new StringBuilder();
        piece.append(0).append(0);
        while(!tskS.isEmpty()){
            int[] p=tskS.poll();
            for(int i=0;i<4;i++){
                int nri=p[0]+dri[i];
                int nci=p[1]+dci[i];
                if(!isValid(nri,nci))   continue;
                if(board[nri][nci]==0)  continue;
                if(visited[nri][nci])   continue;
                visited[nri][nci]=true;
                piece.append(ri-nri).append(ci-nci);
                tskS.offer(new int[]{nri,nci});
            }
        }
        return piece.toString();
    }
    
    // 게임판 빈 칸 채우기
    void fill(){
        boolean[][] visited=new boolean[rowN][colN];
        for(int ri=0;ri<rowN;ri++){
            for(int ci=0;ci<colN;ci++){
                if(game[ri][ci]==1)  continue;
                if(visited[ri][ci]) continue;
                String blank=bfsGame(visited,ri,ci);
                if(pieceS.getOrDefault(blank,0)==0)    continue;
                pieceS.put(blank,pieceS.get(blank)-1);
                filledCnt+=blank.replaceAll("-","").length()/2;
                fill(ri,ci);
            }
        }
    }
    
    // BFS로 게임판에서 빈 칸 찾기
    String bfsGame(boolean[][] visited, int ri, int ci){
        Queue<int[]> tskS=new ArrayDeque<>();
        tskS.offer(new int[]{ri,ci});
        visited[ri][ci]=true;
        StringBuilder blank=new StringBuilder();
        blank.append(0).append(0);
        while(!tskS.isEmpty()){
            int[] p=tskS.poll();
            for(int i=0;i<4;i++){
                int nri=p[0]+dri[i];
                int nci=p[1]+dci[i];
                if(!isValid(nri,nci))   continue;
                if(game[nri][nci]==1)   continue;
                if(visited[nri][nci])   continue;
                visited[nri][nci]=true;
                blank.append(ri-nri).append(ci-nci);
                tskS.offer(new int[]{nri,nci});
            }
        }
        return blank.toString();
    }
    
    // 게임판 특정 빈 칸 채우기
    void fill(int ri, int ci){
        // 게임판 칸 1로 설정
        Queue<int[]> tskS=new ArrayDeque<>();
        tskS.offer(new int[]{ri,ci});
        game[ri][ci]=1;
        while(!tskS.isEmpty()){
            int[] p=tskS.poll();
            for(int i=0;i<4;i++){
                int nri=p[0]+dri[i];
                int nci=p[1]+dci[i];
                if(!isValid(nri,nci))   continue;
                if(game[nri][nci]==1)   continue;
                game[nri][nci]=1;
                tskS.offer(new int[]{nri,nci});
            }
        }
    }
    
    // 범위 안에 있는지 확인
    boolean isValid(int ri,int ci){
        if(ri<0||ri>=rowN)  return false;
        if(ci<0||ci>=colN)  return false;
        return true;
    }
}