public class _289_Game_of_Life {
    public void gameOfLife(int[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0) return;
        int m = board.length;
        int n = board[0].length;
        int[][] tmp = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int cnt = nebCnt(board, i, j);
                if(board[i][j] == 1) {
                    if (cnt < 2) tmp[i][j] = 0;
                    else if (cnt < 4) tmp[i][j] = 1;
                    else tmp[i][j] = 0;
                }else{
                    if (cnt == 3) tmp[i][j] = 1;
                }
            }
        }
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                board[i][j] = tmp[i][j];
            }
        }
        return;
    }
    public int nebCnt(int[][] board, int i, int j) {
        int cnt = 0;
        if(i > 0 && j > 0) {
            cnt += board[i - 1][j - 1] + board[i - 1][j] + board[i][j - 1];

        }
        else if(j == 0 && i > 0) {
            cnt += board[i - 1][j];
        }
        else if(i == 0 && j > 0) {
            cnt += board[i][j - 1];
        }
        if(i < board.length - 1 && j < board[0].length - 1) {
            cnt += board[i + 1][j + 1] + board[i][j + 1] + board[i + 1][j];
        }
        else if(i == board.length - 1 && j < board[0].length - 1) {
            cnt += board[i][j +1];
        }
        else if(j == board[0].length - 1 && i < board.length - 1){
            cnt += board[i + 1][j];
        }
        if(i < board.length - 1 && j > 0) cnt += board[i + 1][j - 1];
        if(i > 0 && j < board[0].length - 1) cnt += board[i - 1][j + 1];
        return cnt;
    }

    public static void main(String[] args) {
        int[][] board = {{1, 0, 0, 1}};
        _289_Game_of_Life game_of_life = new _289_Game_of_Life();
        game_of_life.gameOfLife(board);
        System.out.println(board);
    }
}
