package LeetCode;

/**
 * Created by Garvin on 9/7/2014.
 */
public class BitManipulation {
    public int getBit(int num, int i){
        return (1<<i & num) == 0? 0:1;
    }

    public int setBit(int num, int i){
        return num| (1<<i);
    }
    public int clearBit(int num, int i){
        int mask = ~(1<<i);
        return num&mask;
    }
    public int updateBit(int num, int i, int v){
        int mask = ~(1<<i); //mask : 1110111
        return num&mask | (v<<i); //
    }
    public int clearBitFromBeginToBit(int num, int i){
        return num& ((1<<i)-1);
    }
    public int clearBitFromBitToEnd(int num, int i){
        return num& (~(1<<(i+1) - 1));
    }
    public int mergeBitManipulation(int a, int b, int i, int j){
        //clear bits from i to j
        int mask = ~0;
        int left = mask<<(j+1);//clear bits from beginning to j+1;
        int right = (1<<i)-1;//clear bits from i to end
        mask = left|right;
        return a&mask | b<<i;
    }

    public static String printDouble(double num){
        //error checking
        if(num<=0 || num >=1)
            return "ERROR";
        StringBuffer sb = new StringBuffer();
        sb.append("0.");
        while(num>0){
            if(sb.length() >32) return "ERROR";
            num*=2;
            if(num >= 1){
                num-=1;
                sb.append("1");
            }else {
                sb.append("0");
            }
            System.out.println(sb.toString());
        }
        return sb.toString();
    }
    //count bit differences between a and b
    public static int bitGapBetween(int a, int b){
        int count = 0;
        for(int i = a^b; i>0;i>>=1){
            if((i&1)==1) count++;
        }
        return count;
    }
    public static int bitGapBetween1(int a, int b){
        int count =0;
        for(int i = a^b;i>0;i>>=1)
            count+=(i&1);
        return count;
    }

    public static int bitGapBetween2(int a, int b){
        int count =0 ;
        for(int i = a^b;i>0;i&=(i-1))
            count++;
        return count;
    }
    public static int swap(int x){
        int mask = (x&1) ==1?1:0;
        x>>=1;
        mask<<=32;
        return x|mask;
    }
    public static int swapOddEvenBits(int x){
        return ((x&0xaaaaaaaa) >> 1 | ((x&0x55555555) << 1));
    }
    //get next bigger num with same 1s
    public static int getNext(int x){
        int p =0; // pos of first 0 with 1s on its right;
        int c0 = 0; // starting of 1s sequence
        int c1=0; //ending of 1s sequence
        int num = x;
        //calculate c0;
        while(num > 0&& (num &1) ==0){
            c0++;
            num>>=1;
        }
        //calculate c1
        while((num&1) == 1) {
            c1++;
            num>>=1;
        }
        //error checking
        if(c0+c1 == 31 || c0+c1 == 0) return -1;//non-bigger existed
        //locate p's position
        p = c0+c1;
        //flip p
        x|=(1<<p);
        //clear all right bits of p
        x&=~((1<<p)-1);
        System.out.println(x);
        //add c1-1 ones to the right
        x|=((1<<(c1-1))-1);
        System.out.println(c0 + " ->" + c1);
        return x;
    }

   public static int getPre(int x){
       int num = x;
       int c0 = 0, c1=0, p=0;
       while((num&1)==1){
           c1++;
           num>>=1;
       }
       if(num == 0) return -1;
       while((num&1)==0 && num > 0){
           c0++;
           num>>=1;
       }
       //set p
       p = c0+c1;
       //clear right bits of p
       x&=((~0)<<(p+1));
       x|=(((1<<(c1+1))-1)<<(c0-1));
       byte a = 1;

       return x;
   }
    public static void main(String[] args){
//        System.out.println(printDouble(0.125));
//        System.out.println(bitGapBetween(125,4));
//        System.out.println(bitGapBetween1(125,4));
//        System.out.println(bitGapBetween2(125,4));
//        System.out.println(swap(1125));
//        System.out.println(swapOddEvenBits(1125));
//        System.out.println(getNextBiggerWithSame1s(46));
//        System.out.println(getPre(41));

    }
}
