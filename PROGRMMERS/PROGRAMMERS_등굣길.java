package PROGRMMERS;

/*
1. 문제 재정의 및 추상화 
V(i,j)=i번째 row, j번째 column으로 가는 경로의 수 
V(i,j)=V(i,j-1)+V(i-1,j)
전형적인 DP 문제

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산 
O(R*C)
R: row 수
C: column 수

** 배운 점:
    - 해시 함수를 작성하여 간단하게 해시셋에 저장할 해시값을 만들 수 있다. 
    - 문제를 너무 어렵게 풀지 말자. 코드가 복잡하다면 문제를 잘못 읽었을 가능성이 높다.
    - row와 column이 뒤집어서 주어지는 문제의 조건을 잘 캐치해서 구현하자
4. 코드 작성 
*/
import java.util.*;

class PROGRAMMERS_등굣길 {
    int rowN, colN;
    int[][] pathS;
    Set<Integer> pudAreaS;

    public int solution(int colN, int rowN, int[][] pudRawS) {
        init(colN, rowN, pudRawS);
        for (int ri = 1; ri <= rowN; ri++) {
            for (int ci = 1; ci <= colN; ci++) {
                if (isPud(ri, ci))
                    continue;
                if (ri == 1 && ci == 1)
                    continue;
                pathS[ri][ci] = (pathS[ri - 1][ci] + pathS[ri][ci - 1]) % 1000000007;
            }
        }
        return pathS[rowN][colN];
    }

    void init(int colN, int rowN, int[][] pudRawS) {
        this.rowN = rowN;
        this.colN = colN;
        pudAreaS = new HashSet<>();
        for (int[] pudRaw : pudRawS) {
            pudAreaS.add(hash(pudRaw[1], pudRaw[0]));
        }
        for (int ri = 0, ci = 0; ci <= colN; ci++) {
            pudAreaS.add(hash(ri, ci));
            pudAreaS.add(hash(ci, ri));
        }
        pathS = new int[rowN + 1][colN + 1];
        pathS[1][1] = 1;
    }

    int hash(int ri, int ci) {
        return ri * 1000 + ci;
    }

    boolean isPud(int ri, int ci) {
        return pudAreaS.contains(hash(ri, ci));
    }

    void addPud(int ri, int ci) {
        pudAreaS.add(hash(ri, ci));
    }
}