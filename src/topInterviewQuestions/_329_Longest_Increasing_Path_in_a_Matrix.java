public class _329_Longest_Increasing_Path_in_a_Matrix {
    // 时间复杂度太大
    int max = Integer.MIN_VALUE;
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int pre = Integer.MIN_VALUE;
                walkForLongest(matrix, i, j, visited, 0, pre);
            }
        }
        return max;
    }
    public void walkForLongest(int[][] matrix, int i, int j, boolean[][] visited, int le, int pre) {
        if(i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || visited[i][j] == true || matrix[i][j] <= pre) {
            max = Math.max(max, le);
            return;
        }
        visited[i][j] = true;
        walkForLongest(matrix, i + 1, j, visited, le + 1, matrix[i][j]);
        walkForLongest(matrix, i - 1, j, visited, le + 1, matrix[i][j]);
        walkForLongest(matrix, i, j + 1, visited, le + 1, matrix[i][j]);
        walkForLongest(matrix, i, j - 1, visited, le + 1, matrix[i][j]);
        visited[i][j] = false;
    }


    // 方法二：使用dp，减少重复计算
    int max2 = Integer.MIN_VALUE;
    int[][] dp;
    public int longestIncreasingPath2(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        dp = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(dp[i][j] != 0) max2 = Math.max(max2, dp[i][j]);
                else {
                    max2 = Math.max(max2, walkForLongest(matrix, i, j, -1));
                }
            }
        }
        return max2;
    }
    public int walkForLongest(int[][] matrix, int i, int j, int pre) {
        if(i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] == -1) {
            return 0;
        }
        if(matrix[i][j] <= pre) return 0;
        if(dp[i][j] != 0) return dp[i][j];
        int tmp = matrix[i][j];
        matrix[i][j] = -1;
        int l = walkForLongest(matrix, i - 1, j, tmp);
        int r = walkForLongest(matrix, i + 1, j, tmp);
        int u = walkForLongest(matrix, i, j + 1, tmp);
        int d = walkForLongest(matrix, i, j - 1, tmp);
        matrix[i][j] = tmp;
        dp[i][j] = 1 + Math.max(r, Math.max(l, Math.max(u, d)));
        max2 = Math.max(max2, dp[i][j]);
        return dp[i][j];
    }


    public static void main(String[] args) {
        _329_Longest_Increasing_Path_in_a_Matrix longest_increasing_path_in_a_matrix = new _329_Longest_Increasing_Path_in_a_Matrix();
        int[][] matrix = {{0,1,2,3,4,5,6,7,8,9},{19,18,17,16,15,14,13,12,11,10},{20,21,22,23,24,25,26,27,28,29},{39,38,37,36,35,34,33,32,31,30},{40,41,42,43,44,45,46,47,48,49},{59,58,57,56,55,54,53,52,51,50},{60,61,62,63,64,65,66,67,68,69},{79,78,77,76,75,74,73,72,71,70},{80,81,82,83,84,85,86,87,88,89},{99,98,97,96,95,94,93,92,91,90},{100,101,102,103,104,105,106,107,108,109},{119,118,117,116,115,114,113,112,111,110},{120,121,122,123,124,125,126,127,128,129},{139,138,137,136,135,134,133,132,131,130},{0,0,0,0,0,0,0,0,0,0}};
        int i = longest_increasing_path_in_a_matrix.longestIncreasingPath(matrix);
        System.out.println(i);


    }
}
