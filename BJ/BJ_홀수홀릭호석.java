
/*
 * 홀수 홀릭 호석
 * 1. 문제 재정의 및 추상화
 * 가지고 있는 수 N을 홀수로 최대한 채우고 싶음. 
 * 수가 두 자리이면 2개로 나눠서 합을 구하여 새로운 수 만듦
 * 수가 세 자리 이상이면 임의의 위치에서 끊어서 3개의 수로 분할하고 3개를 더하여 새로운 수를 만듦. 
 * 나누고 합치는 과정에서 나오는 모든 홀수의 개수를 구하여라. 
 * 최대 9자리. 규칙에 맞게 쪼갬. 모든 경우의 수를 탐색함. 완탐.
 * 
 * 2. 풀이과정 상세히 적기
 * 생략
 * 
 * 3. 시간복잡도 계산
 * O(N^4)
 * 최대 연산 횟수 : 81*81 = 6400회. 시간 안에 풀 수 있을 것이라고 생각. 
 * 
 * 4. 코드 작성 
 */
import java.util.*;
import java.io.*;

public class BJ_홀수홀릭호석 {
    int minOddNum;
    int maxOddNum;

    String solution(int v) {
        String answer = "";
        init();
        dfs(v, 0);
        answer = minOddNum + " " + maxOddNum;
        return answer;
    }

    void init() {
        minOddNum = Integer.MAX_VALUE;
        maxOddNum = Integer.MIN_VALUE;
    }

    void dfs(int v, int oddNum) {
        // 문자 배열로..
        char[] cS = String.valueOf(v).toCharArray();
        // 문자 개수
        int cN = cS.length;
        // 전체 수 안에서 홀수의 개수 세기
        for (char c : cS) {
            if (Character.getNumericValue(c) % 2 == 1) {
                oddNum += 1;
            }
        }
        // 한 자리 수인 경우
        if (v < 10) {
            minOddNum = Math.min(minOddNum, oddNum);
            maxOddNum = Math.max(maxOddNum, oddNum);
            return;
        }
        // 두 자리 수인 경우,
        if (10 <= v && v < 100) {
            int f = v / 10;
            int s = v % 10;
            dfs(f + s, oddNum);
            return;
        }
        // 세 자리 수인 경우,
        // i,j
        // i: 0 ~ n-3
        // j: i+1 ~ n-2
        for (int i = 0; i <= cN - 3; i++) {
            int f = 0;
            for (int st = 0; st <= i; st++) {
                f *= 10;
                f += Character.getNumericValue(cS[st]);
            }
            for (int k = i + 1; k <= cN - 2; k++) {
                int s = 0;
                for (int st = i + 1; st <= k; st++) {
                    s *= 10;
                    s += Character.getNumericValue(cS[st]);
                }
                int t = 0;
                for (int st = k + 1; st <= cN - 1; st++) {
                    t *= 10;
                    t += Character.getNumericValue(cS[st]);
                }
                dfs(f + s + t, oddNum);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BJ_홀수홀릭호석 T = new BJ_홀수홀릭호석();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
        int v = Integer.parseInt(kb.readLine());
        System.out.println(T.solution(v));
    }
}