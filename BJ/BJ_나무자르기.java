/*
나무 자르기 (BJ2805)
1. 문제 재정의 및 추상화
절단기 높이 =0 이상의 정수값
나무를 필요한 만큼만 가져간다. 기본 조건인 M보다는 크거나 같게 만들기만 하면 된다. 전형적인 파라매트릭 서치 (결정 알고리즘) 문제다. 이분탐색 알고리즘을 기반으로 하고 있다.

2. 풀이과정 상세히 적기
OOOOXXXX 마지막 O의 위치를 구하는 문제이다. 
이 배열을 정렬된 배열이라고 볼 수 있다. 
전형적인 이분탐색 알고리즘이다.

3. 시간복잡도 계산
O(N*log(K))
N: 나무의 수
K: 나무의 최대 높이
최대 연산 수: 30만

4. 코드 작성
*/

import java.util.*;
import java.io.*;

public class BJ_나무자르기 {
    int treeN;
    int targetSum;
    int[] treeS;
    int maxCutLine;

    int solution(int treeN, int targetSum, int[] treeS) {
        int answer = 0;
        init(treeN, targetSum, treeS);
        binarySearch(0, (int) Math.pow(10, 12));
        answer = maxCutLine;
        return answer;
    }

    void init(int treeN, int targetSum, int[] treeS) {
        this.treeN = treeN;
        this.targetSum = targetSum;
        this.treeS = treeS;
        maxCutLine = Integer.MIN_VALUE;
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
        maxCutLine = Math.max(maxCutLine, mid);
        binarySearch(mid + 1, end);
    }

    boolean isEnough(int cutLine) {
        long sum = 0;
        for (int tree : treeS) {
            if (tree <= cutLine)
                continue;
            sum += (tree - cutLine);
            if (sum >= targetSum) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        Solution T = new Solution();
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(kb.readLine());
        int treeN = Integer.parseInt(stk.nextToken());
        int targetSum = Integer.parseInt(stk.nextToken());
        int[] treeS = new int[treeN];
        stk = new StringTokenizer(kb.readLine());
        for (int i = 0; i < treeN; i++) {
            treeS[i] = Integer.parseInt(stk.nextToken());
        }
        System.out.println(T.solution(treeN, targetSum, treeS));
    }
}
