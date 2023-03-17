package PROGRMMERS;

/*
1. 문제 재정의 및 추상화 
문제가 조금 특이하다. 바위를 n개 제거했을 때 바위 사이의 최소 거리를 구해야 한다. 
OOOO XXXX 마지막 O의 위치를 구한다. index=바위 사이의 최소 거리 
출발지점이나 도착지점을 제거할 수 없다. 바위 사이의 최소 거리를 정한 다음, 출발지점에서부터 시작하여 바윗돌을 제거해나간다. 
바윗돌 사이의 거리가 아까 정한 최소 거리보다 큰 경우엔 그냥 지나간다. 만약 더 작은 경우에는 바위를 제거하고 다다음 바윗돌로 넘어간다. 그러다가 제거한 바윗돌의 개수가 주어진 제거 가능 바윗돌의 개수보다 많아지면 지금 케이스는 쓸 수 없는 케이스라고 판단한다. 
이분탐색으로 문제를 해결한다. 

** 느낀 점
이분탐색은 알고리즘 자체가 어렵지 않다. 하지만 이 문제를 이분탐색으로 풀어야 하는지 아닌지를 알아채리는 게 조금 어렵다. 프로그래머스 문제를 풀어가며 해당 훈련을 해나가는 게 중요할 듯 보인다. 

2. 풀이과정 상세히 적기 
생략

3. 시간복잡도 계산
O(log(K)*N+NlogN)
N: 바위의 총 개수
K: 도착지점까지의 거리

** 배운 점:
    - 도착점을 꼭 고려해야 한다. 
    - 알고리즘 문제를 풀 때는 꼭 피로를 다 푼 상태여야 한다. 
        - 잠을 잘 못 자서 제 정신이 아니다. 
    - 이분탐색 문제를 풀 때는 특정 값으로 try했을 때 가능한지 확인하는 로직이 중요하다.
    - 또한 OOOXXX 와 같은 결정 여부 배열이 어떤 식으로 정렬되어 있는지 확실하게 정해두어라 
4. 코드 작성 
*/
import java.util.*;

class PROGRAMMERS_징검다리 {
    int endDist;
    int[] rS;
    int rN;
    int canRemoveN;
    int maxOfMinDist;

    public int solution(int endDist, int[] rS, int canRemoveN) {
        int answer = 0;
        init(endDist, rS, canRemoveN);
        binarySearch(0, (int) Math.pow(10, 9));
        answer = maxOfMinDist;
        return answer;
    }

    void init(int endDist, int[] rS, int canRemoveN) {
        this.endDist = endDist;
        this.rS = rS;
        Arrays.sort(rS);
        rN = rS.length;
        this.canRemoveN = canRemoveN;
        maxOfMinDist = Integer.MIN_VALUE;
    }

    void binarySearch(int start, int end) {
        if (start > end) {
            return;
        }
        int mid = (start + end) / 2;
        if (!isEnough(mid)) {
            binarySearch(start, mid - 1);
            return;
        }
        maxOfMinDist = Math.max(maxOfMinDist, mid);
        binarySearch(mid + 1, end);
    }

    boolean isEnough(int minDist) {
        int removeCnt = 0;
        int prev = 0;
        boolean[] isRemoved = new boolean[rN];
        for (int i = 0; i < rN; i++) {
            int r = rS[i];
            int dist = r - prev;
            if (dist < minDist) {
                isRemoved[i] = true;
                removeCnt += 1;
            } else {
                prev = r;
            }
            if (removeCnt > canRemoveN) {
                return false;
            }
        }
        for (int i = rN - 1; i >= 0; i--) {
            if (isRemoved[i])
                continue;
            prev = rS[i];
            int dist = endDist - prev;
            if (dist < minDist) {
                removeCnt += 1;
                isRemoved[i] = true;
            } else {
                break;
            }
        }
        if (removeCnt > canRemoveN) {
            return false;
        }
        return true;
    }
}
