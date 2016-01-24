/*
 * Author: Wei Su
 * Contact: weisu@usc.edu
 * Date: 9/9/2014 7:00pm
  */
package LeetCode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
public class Movie {
    private final int movieId;
    private final float rating;
    private List<Movie> similarMovies; // Similarity is bidirectional

    public Movie(int movieId, float rating) {
        this.movieId = movieId;
        this.rating = rating;
        similarMovies = new ArrayList<Movie>();
    }

    public int getId() {
        return movieId;
    }

    public float getRating() {
        return rating;
    }

    public void addSimilarMovie(Movie movie) {
        similarMovies.add(movie);
        movie.similarMovies.add(this);
    }

    public List<Movie> getSimilarMovies() {
        return similarMovies;
    }

    /*
     * Implement a function to return top rated movies in the network of movies 
     * reachable from the current movie
     * eg:             A(Rating 1.2)
     *               /   \
     *            B(2.4)  C(3.6)
     *              \     /
     *                D(4.8)
     * In the above example edges represent similarity and the number is rating.
     * getMovieRecommendations(A,2) should return C and D (sorting order doesn't matter so it can also return D and C)
     * getMovieRecommendations(A,4) should return A, B, C, D (it can also return these in any order eg: B,C,D,A)
     * getMovieRecommendations(A,1) should return D. Note distance from A to D doesn't matter, return the highest rated.
     *     
     *     @param movie
     *     @param numTopRatedSimilarMovies 
     *                      number of movies we want to return
     *     @return List of top rated similar movies
     */
    public  static List<Movie> getMovieRecommendations(Movie movie, int numTopRatedSimilarMovies) {
        List<Movie> res = new LinkedList<Movie>();
        //error checking
        if(numTopRatedSimilarMovies<=0) return res; //if numTopRatedSimilarMovies is less than 0, return empty set
        if(movie == null) return res;//if movie is null, return empty set
        //Build min-heap with rewritten comparator.
        Comparator<Movie> cmp = new Comparator<Movie>() {
            public int compare(Movie o1, Movie o2) {
                return (int)(o1.getRating() - o2.getRating()); // get elements in ascending order
            }
        };
        PriorityQueue<Movie> topK = new PriorityQueue<Movie>(numTopRatedSimilarMovies,cmp);//define size of heap and its rewritten comparator
        topK.add(movie);
        List<Movie> similarMovies = movie.getSimilarMovies();
        for(Movie t:similarMovies){
            if(topK.size()<numTopRatedSimilarMovies){ // if the heap is not full, push any movie instance 
                topK.add(t);
            }
         /*if heap is full, compare movie's rating with peek element in the heap.
          * if movie's rating is larger than peek, poll peek which has smaller rating, push movie which has large rating. 
          * Which means, all the large rating movies are in the min-heap. The larger the rating is, the more similar the movies are.
         */
            else if(t.getRating()>topK.peek().getRating()){
                topK.poll();
                topK.add(t);
            }
        }
        //add elements from heap to result set.
        while(!topK.isEmpty()){
            res.add(topK.poll());
        }
        return res;
    }
    public static void main(String[] args){
        Movie a = new Movie(1,1.2f);
        Movie b = new Movie(2,2.4f);
        Movie c = new Movie(3,3.6f);
        Movie d = new Movie(4,4.8f);
        a.addSimilarMovie(b);
        a.addSimilarMovie(c);
        a.addSimilarMovie(d);
        List<Movie> res = getMovieRecommendations(a,1);//can be tested with different parameters here.
        for(Movie i:res)
            System.out.println((char)(i.getId()+64)); // in order to obey output format, I output with movie's letter representation;
        //1-A; 2-B; 3-C;4-D;
        //can simply output movie's id to prove results.
//    	System.out.println(i.getId());


    }
    /*
     * Time complexity Analysis:
     * 1. With min-heap implementation, the whole time complexity is O(nlogk), where n is number of movie's all similar movies.K is requested 
     * result number. 
     * 2. Building heap will theoretically request O(k). Updating(insert,delete,find,update) each element in the heap will be O(logk)
     * 3. We have n elements needed to be used to update min-heap, so the total time complexity would be O(nlogk).
     */
    
    /*Space complexity Analysis:
     * 1. In the whole solution, I use min-heap to store processing data, where the size of min-heap is k. So the space complexity is O(k), where
     * k is requested result number. 
     * 2. I use O(k) to output result set (List<Movie> res);
     * 3. So the total space complexity is O(k)
     */
    /* Assumption: 
     * 1. movie id 1 -- A, movie id 2--B, movie id 3--C, movie id 4 -- D
     * 2. In the code, I assume movie can't add itself as its similar movie. So prior to process min-heap, I add movie itself in the heap first.
     * The code can be changed easily if movie is able to add itself as its similar movie.
     */
    /*Possible optimization:
     * 1.If the data set is huge, an alternative solution is to use Map-Reduce model to deal with this problem.
     * The key solution is get topK small result in MAP function, and merge all the small sets in REDUCE function with min-heap as well.
     * 
     */

}