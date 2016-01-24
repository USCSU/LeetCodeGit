package LeetCode2015;

/**
 * Created by Trace_route on 7/7/15.
 */
public class WordSearch {
    public static void main(String[] args){
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        System.out.println(exist(board,"ABCCED"));
        System.out.println(exist(board,"SEE"));
        System.out.println(exist(board,"ABCB"));
        System.out.println(exist(board,"ABCF"));
    }
    public static boolean exist(char[][] board, String word) {
        //error checking
        boolean[][] checker = new boolean[board.length][board[0].length];
        for(int i =0;i<board.length;i++){
            for(int j =0;j<board[0].length;j++){
                if(helper(board,word,i,j,checker)) return true;
            }
        }
        return false;
    }
    public static boolean helper (char[][] board, String word, int x, int y, boolean[][] checker){
        //base
        if(word.isEmpty()) return true;
        if(x < 0 || x == board.length || y<0 || y ==board[0].length || checker[x][y]||board[x][y]!=word.charAt(0))
            return false;
        boolean ret = false;
        checker[x][y] = true;
        ret |=helper(board,word.substring(1),x-1,y,checker);
        ret |=helper(board,word.substring(1),x+1,y,checker);
        ret |=helper(board,word.substring(1),x,y-1,checker);
        ret |=helper(board,word.substring(1),x,y+1,checker);
        checker[x][y] = false;
        return ret;
    }
}
