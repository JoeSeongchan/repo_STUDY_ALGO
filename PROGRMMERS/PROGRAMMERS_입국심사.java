package PROGRMMERS;

/*
1. 문제 재정의 및 추상화 
가장 빠르게 사람 줄을 소진시키면 문제가 풀린다. 이 문제를 어떻게 풀 수 있을까?
일반적인 문제와 다른 점: 줄이 없어도 다른 심사대를 기다리면 더 빨리 심사 받을 수 있는 경우가 있다. 
그런 경우엔 기다린다. (그 사람 뒤에 있는 사람 또한 기다리게 된다.)
최대 10억명의 사람, 최소 1명의 심사관, 최대 10억분의 심사시간 
파라매트릭 서치! 이분탐색을 사용한다. 어떤 식으로? 
XXOOO 가장 첫번째 O의 위치를 구하여라 
모든 사람이 심사를 받는데 걸리는 시간=T
T 값=배열의 index
long으로 나타낼 수 있다. 10억*10억<=2^63
7 10
1 2
3(7분) 4(10분)
5(14분) 
28분, 30분
28분 선택!
Naive하게 접근하면 한 사람을 배정하기 위해서 모든 심사관을 확인해야 한다. 
물리적으로 불가능하다! 
T를 심사 시간으로 나누면서 몫을 더해간다. 몫의 합이 n을 넘어가는 걸 확인한다.
몫의 합이 n 이상이면서 가장 작은 T값을 구한다. 

2. 풀이과정 상세히 적기
생략 

3. 시간복잡도 계산
O(C*log(N*K))
N: 사람 수
K: 최대 심사시간
C: 심사위원 수 
최대 연산 횟수: 63*10만=630만

4. 코드 작성
*/
import java.util.*;
import java.io.*;

class PROGRAMMERS_입국심사 {
    int pN;
    int tN;
    int[] tS;

    long minTotal;

    public long solution(int pN, int[] tS) {
        long answer = 0;
        init(pN, tS);
        binarySearch(0, (long) (Math.pow(10, 12) * Math.pow(10, 12)));
        answer = minTotal;
        return answer;
    }

    void init(int pN, int[] tS) {
        this.pN = pN;
        this.tS = tS;
        tN = tS.length;
        minTotal = Long.MAX_VALUE;
    }

    void binarySearch(long start, long end) {
        if (start > end) {
            return;
        }
        long mid = (start + end) / 2;
        if (!isEnough(mid)) {
            binarySearch(mid + 1, end);
            return;
        }
        minTotal = Math.min(minTotal, mid);
        binarySearch(start, mid - 1);
    }

    boolean isEnough(long totalTime) {
        long sum = 0;
        for (int time : tS) {
            sum += totalTime / time;
            if (sum >= pN) {
                return true;
            }
        }
        return false;
    }
}
