package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Garvin on 10/2/2014.
 */
public class WhoCanWin {
    public static ArrayList<Integer> findPairs(int[] numbers){
        ArrayList<Integer> res = new ArrayList<Integer>();
        int[] checker = new int[10];
        for(int i:numbers){
            checker[i] ++;
            if(checker[i] == 2){
                checker[i] = 0;
                res.add(i);
            }
        }
        return res;
    }



    private static boolean helper(ArrayList<Integer> numList, int target) {
                /* empty list, return false */
        if(numList.size() == 0) {
            return false;
        }
        boolean opRes = true;            //results of opponent-google 1point3acres
        for(int i = 0; i < numList.size(); i++) {
            int num = numList.get(i);
            if(num >= target) {
                                /* pick this number, I will win */
                return true;
            }
            numList.remove(i);       //I pick this one
            if(!helper(numList, target - num)) {
                                /* my opponent cannot win */
                opRes = false;
            }
            numList.add(i, num);    //I don't pick this one since my opponent will always win, add it back
        }
        return !opRes;             //if among these numbers picking one of them will let my opponent lose, then I will win
    }

    public static boolean canIwin(int maxNum, int target) {
        if( ((1 + maxNum) * maxNum / 2) < target ) {
                        /* nobody wins */
            System.out.println("!!!");
            return false;
        }
        ArrayList<Integer> numList = new ArrayList<Integer>();
        for(int i = 1; i <= maxNum; i++) {
            numList.add(i);
        }
        return helper(numList, target);
    }


    public static boolean CanIWin(int num, int target){
        if((1+num)*num/2 < target)
            return false;
        ArrayList<Integer> checker = new ArrayList<Integer>();
        for(int i = 1;i<=num;i++)
            checker.add(i);
        return WinHelper(checker, target);
    }

    public static boolean caniwin(int num, int target){
        if((1+num)*num/2 < target)
            return false;
        ArrayList<Integer> checker = new ArrayList<Integer>();
        while(num!=0)
            checker.add(num--);
        return helper1(checker,target);
    }
    public static boolean helper1(ArrayList<Integer> checker, int target){
        boolean opponent = true;
        //base
        if(checker.isEmpty()) return false;
        //recursive
        for(int i =0 ;i<checker.size();i++) {
            int num = checker.get(i);
            if(num> target) return true;
            //take current item and check
            checker.remove(i);
            if(!helper1(checker,target-num))
                opponent = false;
            checker.add(num);
        }
        return !opponent;
    }
    public static boolean WinHelper(ArrayList<Integer> checker, int target){
        boolean opponent = true;
        if(checker.isEmpty()) return false;
        //check if i win
        for(int i =0;i<checker.size();i++) {
            int num = checker.get(i);
            if(num == Integer.MIN_VALUE) continue;
            if (num >= target) return true;
            //move next step by checking opponent
            checker.set(i,Integer.MIN_VALUE);
            if(!WinHelper(checker,target-num)) // if opponent can't win
                opponent = false;
            checker.set(i,num);
        }
        return !opponent;
    }
    public static void main(String[] args){
        System.out.println("+++"+caniwin(3, 3));

        System.out.println("+++"+caniwin(3, 4));
        System.out.println("+++"+caniwin(3, 5));
        System.out.println("+++"+caniwin(3, 6));
        System.out.println("+++"+caniwin(3, 8));
    }
}
