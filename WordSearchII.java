package LeetCode2015;

import java.util.*;

class TrieNode{
    final int SIZE = 26;
    TrieNode[] children;
    char c;
    boolean isEnd;
    int count =0;
    TrieNode(char c){
        this.c = c ;
        children = new TrieNode[SIZE];
    }
    TrieNode(){
        children = new TrieNode[SIZE];
    }
}
class Trie{
    TrieNode root;
    Trie(){
        root = new TrieNode();
    }
    public void insert(String s){
         insert(s,root);
    }
    private void insert(String s, TrieNode root){
        TrieNode runner = root;
        for(int i =0 ;i<s.length();i++){
            char c = s.charAt(i);
            int index = c - 'a';
            if(runner.children[index] == null){
                runner.children[index] = new TrieNode(c);
            }
            runner = runner.children[index];
        }
        runner.isEnd = true;
    }

    public boolean has(String s){
        TrieNode runner = root;
        for(int i =0;i<s.length();i++){
            char c = s.charAt(i);
            int index = c -'a';
            if(runner.children[index] == null) return false;
            runner = runner.children[index];
        }
        return runner.isEnd;
    }
    public boolean startWith(String s){
        TrieNode runner = root;
        for(int i =0;i<s.length();i++){
            char c = s.charAt(i);
            int index = c -'a';
            if(runner.children[index] == null) return false;
            runner = runner.children[index];
        }
        return true;
    }
    public void collect(ArrayList<String> ret, StringBuffer collector, TrieNode runner){
        collector.append(runner.c);
        if(runner.isEnd) {
            ret.add(collector.toString());
        }
        for(int i =0;i<26;i++){
            if(runner.children[i]!=null){
                collect(ret,collector,runner.children[i]);
            }
        }
        collector.deleteCharAt(collector.length()-1);
    }

}
public class WordSearchII {
    public static void main(String[] args){
//      char[][] matrix = { {'o','a','a','n'},
//               {'e','t','a','e'},
//               {'i','h','k','r'},
//               {'i','f','l','v'}};
//        String[] words = {"oath","pea","eat","rain"};
        char[][] matrix = {{'a','b'},{'a','a'}};
        String[] words = {"aba","baa","bab","aaab","aaa","aaaa","aaba"};
        Trie trie = new Trie();
        for(String s:words)
            trie.insert(s);
        for(String s: words)
        System.out.println(trie.has(s));
        ArrayList<String> ret = new ArrayList<String>();
        trie.collect(ret,new StringBuffer(),trie.root);
        System.out.println(ret);

//        char[][] matrix = {{'a','a'}};
//        String[] words = {"a"};
//        long start = System.currentTimeMillis();
        System.out.println(findWords(matrix,words));
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
//        Trie Solution
//        start = System.currentTimeMillis();
        System.out.println(findWordsTrie(matrix,words));
//        end = System.currentTimeMillis();
//        System.out.println(end - start);





    }
    public static List<String> findWordsTrie(char[][] board, String[] words){
        Trie trie = new Trie();
        for(String word:words) trie.insert(word);
//        List<String> ret = new LinkedList<String>();
        Set<String> ret = new HashSet<String>();
        boolean[][] checker = new boolean[board.length][board[0].length];
        for(int i =0 ;i<board.length;i++){
            for(int j =0;j<board[0].length;j++){
                helper(ret,new StringBuffer(),i,j,board,trie,checker);
            }
        }
        return new LinkedList<String>(ret);
    }
    public static void helper(Set<String> collector, StringBuffer s, int x, int y, char[][] board, Trie trie, boolean[][] checker){
        //base
        if(x < 0 || x==board.length || y<0 || y ==board[0].length || checker[x][y]){
            return ;
        }
        s.append(board[x][y]);
        checker[x][y] = true;
        if(!trie.startWith(s.toString())) {
            s.deleteCharAt(s.length()-1);
            checker[x][y] = false;
            return;
        }
        if(trie.has(s.toString())){
            collector.add(s.toString());
        }
        helper(collector,s,x-1,y,board,trie,checker);
        helper(collector,s,x+1,y,board,trie,checker);
        helper(collector,s,x,y-1,board,trie,checker);
        helper(collector,s,x,y+1,board,trie,checker);
        checker[x][y] = false;
        s.deleteCharAt(s.length()-1);
    }




    public static List<String> findWords(char[][] board, String[] words) {
        //error checking
        List<String> ret = new LinkedList<String>();
        for(String s: words)
            if(exist(board,s)) ret.add(s);
        return ret;
    }
    public static boolean exist(char[][] board, String word){
        boolean[][] checker = new boolean[board.length][board[0].length];
        for(int i =0;i<board.length;i++){
            for(int j =0;j<board[0].length;j++){
                if(helper(board,word,i,j,checker)) return true;
            }
        }
        return false;
    }
    public static boolean helper(char[][] board, String word, int x, int y, boolean[][] checker){
        if(word.isEmpty()) return true;
        if(x < 0 || x == board.length ||y <0 || y==board[0].length || checker[x][y] || board[x][y] != word.charAt(0))
            return false;
        boolean ret = false;
        checker[x][y] = true;
        ret|=helper(board,word.substring(1),x-1,y,checker);
        ret|=helper(board,word.substring(1),x+1,y,checker);
        ret|=helper(board,word.substring(1),x,y-1,checker);
        ret|=helper(board,word.substring(1),x,y+1,checker);
        checker[x][y] = false;
        return ret;
    }
}
