package hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _51_N_Queens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        int[] invalid = new int[n];
        dfs(n, 0, invalid, res, new ArrayList<>());
        return res;
    }

    public void dfs(int n, int idx, int[] valid, List<List<String>> res, List<String> path) {
        if(path.size() == n) {
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i = 0; i < n; i++) {
            if(validate(valid, idx, i)) {
                valid[idx] = i;
                String s = String.join("", Collections.nCopies(i, ".")) + "Q" +
                        String.join("", Collections.nCopies(n - i - 1, "."));
                path.add(s);
                dfs(n, idx + 1, valid, res, path);
                path.remove(path.size() - 1);
            }
        }
    }

    public boolean validate(int[] valid, int idx, int q) {
        for(int i = 0; i < idx; i++) {
            if(valid[i] == q || Math.abs(q - valid[i]) == idx - i) return false;
        }
        return true;
    }
}
