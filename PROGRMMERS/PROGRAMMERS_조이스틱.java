package PROGRMMERS;

class PROGRAMMERS_조이스틱 {
    String name;
    int nameLen;
    int minCnt;
    int[] moveS;
    int sumMove;
    int needToMoveS;

    public int solution(String name) {
        int answer = 0;
        init(name);
        exhaustiveSearch(0, 0, needToMoveS);
        answer += sumMove;
        answer += minCnt;
        return answer;
    }

    public void init(String name) {
        this.name = name;
        nameLen = name.length();
        minCnt = Integer.MAX_VALUE;
        moveS = new int[nameLen];
        sumMove = 0;
        needToMoveS = 0;
        for (int i = 0; i < nameLen; i++) {
            char c = name.charAt(i);
            moveS[i] = Math.min(c - 'A', 26 - (c - 'A'));
            sumMove += moveS[i];
            if (moveS[i] == 0)
                continue;
            needToMoveS |= 1 << i;
        }
        // System.out.println(needToMoveS);
    }

    public void exhaustiveSearch(int cnt, int idx, int needToMoveS) {
        if (needToMoveS == 0) {
            minCnt = Math.min(minCnt, cnt - 1);
            if (minCnt < 0)
                minCnt = 0;
            // System.out.println(minCnt);
            return;
        }
        if (cnt >= nameLen) {
            return;
        }
        if (cnt > minCnt) {
            return;
        }

        // 오른쪽으로 이동
        int nextIdx = idx + 1;
        if (nextIdx >= nameLen)
            nextIdx = 0;
        exhaustiveSearch(cnt + 1, nextIdx, needToMoveS & (~(1 << idx)));
        // 왼쪽으로 이동
        nextIdx = idx - 1;
        if (nextIdx < 0)
            nextIdx = nameLen - 1;
        exhaustiveSearch(cnt + 1, nextIdx, needToMoveS & (~(1 << idx)));
    }
}