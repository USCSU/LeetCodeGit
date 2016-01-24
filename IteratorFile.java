package LeetCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Garvin on 10/22/2014.
 */
public class IteratorFile implements Iterable {
    File file;
    public IteratorFile(String name) throws IOException{
        try {

            file = new File(name);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public FileIterator iterator(){
        try {
            return new FileIterator(file);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args){
        String name = "d:/Company.java";
        try {
            FileIterator it = new IteratorFile(name).iterator();
            while(it.hasNext()){
                System.out.println(it.next());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
class   FileIterator implements  Iterator{
    FileReader fr;
    BufferedReader br;
    String output;
    public FileIterator(File file) throws IOException{
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            output = br.readLine();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public boolean hasNext(){
        return output != null;
    }
    public String next() throws NoSuchElementException{
        String result = output;
        try {
            if (output == null)
                throw new NoSuchElementException("No such line");
            result= br.readLine();
            if (result == null && br != null) {
                br.close();
                br = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
//    public void remove(){
//        throw new UnsupportedOperationException("not support remove");
//    }
}
