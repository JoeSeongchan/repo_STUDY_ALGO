
/*
 * 1,2,3 더하기 5
 * 1. 문제 재정의 및 추상화 
 * 정수 n를 1,2,3의 합으로 나타내어라. 단 같은 수를 두 번 이상 '연속해서' 사용하면 안 된다. 
 * dp[i][k]=총합 i, 끝 자리수 k인 경우의 수
 * 
 * 점화식
 * dp[i][k]=dp[i-k][1]+dp[i-k][2]+dp[i-k][3](단 열 값이 k와 같은 경우, 덧셈에서 제외)
 * 
 * 답
 * dp[n][1]+dp[n][2]+dp[n][3]
 * 
 * 리턴 타입 : int 
 * 왜냐하면 10억으로 나눈 나머지 두 값을 더하기 때문이다. integer 최댓값 벗어날 일 없다. 
 * 
 * 2. 풀이과정 상세히 적기
 * 생략
 * 
 * 3. 시간복잡도 계산
 * O(N)
 * 
 * ** 오답노트 
 * 한 번 계산한 배열을 다시 활용할 수 있으면 맨 처음에 다 배열 값을 다 계산해둔 다음, 그걸 가져다 쓰기만 하여라 ! 시간 초과 문제를 이런 식으로 해결할 수 있다. 
 * 4. 코드 작성 
 */
import java.util.*;
import java.io.*;

public class BJ_123더하기5 {
    int[][] dp;

    void init() {
        this.dp = new int[100001][4];
        dp[1][1] = 1;
        dp[2][2] = 1;
        dp[3][3] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        for (int i = 4; i <= 100000; i++) {
            for (int k = 1; k <= 3; k++) {
                for (int p = 1; p <= 3; p++) {
                    if (p == k)
                        continue;
                    dp[i][k] += dp[i - k][p];
                }
                dp[i][k] = dp[i][k] % 1000000009;
            }
        }
    }

    int solution(int n) {
        int answer = 0;
        answer = (dp[n][1] + dp[n][2]) % 1000000009;
        answer += dp[n][3];
        answer %= 1000000009;
        return answer;
    }

    public static void main(String[] args) throws Exception {
        BJ_123더하기5 T = new BJ_123더하기5();
        T.init();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
        int tcN = Integer.parseInt(kb.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tci = 1; tci <= tcN; tci++) {
            int n = Integer.parseInt(kb.readLine());
            sb.append(T.solution(n)).append('\n');
        }
        System.out.println(sb);
    }
}