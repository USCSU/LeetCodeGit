package LeetCode;

import java.util.*;

/**
 * Created by Garvin on 9/4/2014.
 */
public class foursum {
    public List<List<Integer>> fourSum1(int[] num, int target) {
        // Since we use set here, we don't need to dedup data
        Set<List<Integer>> result = new HashSet<List<Integer>>();
        Arrays.sort(num);
        Map<Integer, Set<Pair>> map = new HashMap<Integer, Set<Pair>>();
        for (int i=0; i<num.length; i++) {
            // Note the order of these two for-loops is critical
            for (int j=i+1; j<num.length; j++) {
                int pairSum = num[i] + num[j];
                if (map.containsKey(target - pairSum)) {
                    for (Pair p : map.get(target - pairSum)) {
                        List l = new LinkedList();
                        l.add(p.first);
                        l.add(p.second);
                        l.add(num[i]);
                        l.add(num[j]);
                        result.add(l);
                    }
                }
            }
            for (int j=0; j<i; j++) {
                int a = num[j], b = num[i];
                if (!map.containsKey(a+b)) {
                    map.put(a+b, new HashSet());
                }
                map.get(a+b).add(new Pair(a, b));
            }
        }
        return new ArrayList<List<Integer>>(result);
    }
    class Pair {
        Integer first;
        Integer second;
        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
        @Override
        public int hashCode() {
            return this.first.hashCode() + this.second.hashCode();
        }
        @Override
        public boolean equals(Object d){
            if (!(d instanceof Pair)) {
                return false;
            }
            Pair p = (Pair) d;
            return (this.first == p.first) && (this.second == p.second);
        }
    }
    public static ArrayList<ArrayList<Integer>> foursum(int[] num, int target){
        HashSet<ArrayList<Integer>> res = new HashSet<ArrayList<Integer>>();
        if(num == null || num.length < 4) return new ArrayList<ArrayList<Integer>>(res);
        Arrays.sort(num);
        for(int i = 0;i< num.length;i++){
            for(int j = i+1;j<num.length;j++){
                int low = j+1, high = num.length -1;
                while(low<high){
                    int sum = num[i]+num[j]+num[low]+num[high];
                    if(sum == target){
                        ArrayList<Integer> temp = new ArrayList<Integer>();
                        temp.add(num[i]);
                        temp.add(num[j]);
                        temp.add(num[low]);
                        temp.add(num[high]);
                        res.add(temp);
                        low++; high--;
                    }else if(sum>target) high--;
                    else low++;
                }
            }
        }
        return new ArrayList<ArrayList<Integer>>(res);
    }
    public static ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
        HashSet<ArrayList<Integer>> res = new HashSet<ArrayList<Integer>>();
        if(num == null || num.length < 4) return null;
        Arrays.sort(num);
        for(int i =0;i<num.length;i++){
            for(int j =i+1;j<num.length;j++){
                int low = j+1, high = num.length - 1;
                while(low<high){
                    int sum = num[i]+num[j]+num[low]+num[high];
                    if(sum == target){
                        ArrayList<Integer> temp = new ArrayList<Integer>();
                        temp.add(num[i]);
                        temp.add(num[j]);
                        temp.add(num[low]);
                        temp.add(num[high]);
                        if(!res.contains(temp))
                            res.add(temp);
                        low++; high--;
                    }else if(sum > target){
                        high--;
                    }else{
                        low++;
                    }
                }
            }
        }
        return new ArrayList<ArrayList<Integer>>(res);
    }
    public static  void main(String[] args){
        int[] num = {1, 0, -1, 0, -2, 2};
        System.out.println(fourSum(num,0));
        System.out.println(new foursum().fourSum1(num,0));
    }
}
