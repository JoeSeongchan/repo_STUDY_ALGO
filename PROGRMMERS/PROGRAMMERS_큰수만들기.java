package PROGRMMERS;

/*
1. 문제 재정의 및 추상화
number가 100만 자리 숫자이기 때문에 완전 탐색으로는 이 문제를 풀 수 없다. 
DP로 풀 수 있는가? 작은 문제로 나누고 이를 Memoization과 테이블을 사용해서 풀 수 있는가?
당장 눈에 보이지 않는다..
그리디로 풀어볼까? 
1. 선택 절차: 현재 상태에서의 최적의 해답을 선택
2. 적절성 검사: 선택한 해가 문제의 조건을 만족하는지 검사
3. 해답 검사: 원래의 문제가 해결되었는지 검사하고, 해결되지 않았다면 선택절차로 돌아가 위의 과정을 반복한다. 
제거할 값을 어떻게 고를 수 있을까? 다음 위치의 값과 비교해서 더 작으면 해당 값을 제거한다. 제거하고 나서 그 자리를 그대로 유지한다. 만약 맨 끝 자리에 있어서 다음 값이 없다면 해당 위치 숫자를 제거한다. 이 작업을 계속 반복한다. 

* 문제를 푸는 아이디어를 어디서 얻었는가?
    input의 개수가 100만개이기 때문에 O(N) 또는 O(NlogN)으로 문제를 풀어야 한다. 
    그리디 알고리즘의 경우, 한 번 내린 선택은 다시 돌리지 않는다. 
    앞에서 순차 탐색을 하며 제거할 원소를 고르는 방법을 생각했다. 
    숫자를 제거하면서 최대한 앞에서부터의 원소는 내림차순 구조를 만족하도록 하면 최댓값이 나오지 않을까 싶었다.
    문제를 풀면서 주의할 점은 오름차순을 만드는 현재 원소를 제거하고 한 칸 이전으로 돌아가야 한다는 점이다. 
    중간에 있던 작은 값이 제거되면서 오름차순을 만들 수 있기 때문이다. 그리디 알고리즘의 특성에 맞는지는 잘 모르겠지만 (한 번 내린 선택은 돌리지 않는다.) 문제가 풀렸다. 
    
2. 풀이과정 상세히 적기
생략. StringBuilder의 charAt을 사용한다.  

3. 시간복잡도 계산
O(N)

4. 코드 작성 
*/

class PROGRAMMERS_큰수만들기 {
    String number;
    int removeN;

    public String solution(String number, int k) {
        String answer = "";
        init(number, k);
        answer = remove();
        return answer;
    }

    void init(String number, int removeN) {
        this.number = number;
        this.removeN = removeN;
    }

    public String remove() {
        StringBuilder sb = new StringBuilder(number);
        int idx = 0;
        int cnt = 0;
        while (cnt < removeN) {
            if (idx == sb.length() - 1) {
                sb.deleteCharAt(idx);
                idx -= 1;
                cnt += 1;
                continue;
            }
            if (sb.charAt(idx) < sb.charAt(idx + 1)) {
                sb.deleteCharAt(idx);
                idx -= 1;
                if (idx < 0)
                    idx = 0;
                cnt += 1;
                continue;
            }
            idx += 1;
        }
        return sb.toString();
    }
}
