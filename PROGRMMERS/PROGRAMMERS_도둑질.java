/*
1. 문제 재정의 및 추상화 
dp[i]: i번째 집까지 도둑이 왔을 때 도둑이 훔친 최대 금액
dp[i]=max(dp[i-2]+vS[i],dp[i-1])

두 가지 경우로 나눌 수 있다.
1. 첫번째 집을 털고, 마지막 집은 절대 털지 않는 경우
2. 첫번째 집을 무조건 털지 않는 경우

2. 풀이과정 상세히 적기 
두 개의 일차원 배열을 만든다. 따로따로 관리한다. 
첫번째 케이스의 배열은 마지막 집에 도착했을 때 이전 값을 그대로 가지고 와서 초기화한다.
두 배열의 마지막 원소의 값을 가져와서 최댓값을 구한다. 

** 배운 점
인접한 두 집이 털릴 수는 없기 때문에 dp[i]=max(dp[i-2]+vS[i],dp[i-1]) 점화식이 나온다.
dp[i-2]+vS[i]: i-1번째 집은 고려하지 않고, i-2번째 집까지 방문했을 때의 최대 금액에 i번째 집의 금액을 더한 값
dp[i-1]: i-1번째 집까지 고려했을 때 최대 금액 = i번째 집은 고려하지 않으므로 i번째 집은 털지 않는다는 의미이다. 
처음 집과 마지막 집은 연결되어 있으므로 두 케이스로 나뉘어진다. 
1. 첫번째 집을 털고, (마지막 집은 절대 털지 않는) 경우
2. 첫번째 집을 무조건 털지 않는 경우
이렇게 케이스를 나누면 좋다! 

3. 시간복잡도 계산
O(N)

4. 코드 작성 
*/
class Solution {
  int[] pickFirstIgnoreLast;
  int[] ignoreFirst;
  int[] mS;
  int mN;

  public int solution(int[] money) {
    int answer = 0;
    init(money);
    pickFirstIgnoreLast[0] = mS[0];
    pickFirstIgnoreLast[1] = Math.max(mS[0], mS[1]);
    ignoreFirst[0] = 0;
    ignoreFirst[1] = mS[1];
    for (int i = 2; i < mN; i++) {
      if (i != mN - 1) {
        pickFirstIgnoreLast[i] = Math.max(pickFirstIgnoreLast[i - 2] + mS[i], pickFirstIgnoreLast[i - 1]);
      } else {
        pickFirstIgnoreLast[i] = pickFirstIgnoreLast[i - 1];
      }
      ignoreFirst[i] = Math.max(ignoreFirst[i - 2] + mS[i], ignoreFirst[i - 1]);
    }
    answer = Math.max(pickFirstIgnoreLast[mN - 1], ignoreFirst[mN - 1]);
    return answer;
  }

  void init(int[] mS) {
    this.mS = mS;
    mN = mS.length;
    pickFirstIgnoreLast = new int[mN];
    ignoreFirst = new int[mN];
  }
}
