// MovieFileRepository.java

package com.thecodinginterface.interfaces;

public class MovieFileRepository implements RepositoryDAO {

    /**
     * Save Movie object to file
     */
    public boolean save(Object o) {
        Movie movie = (Movie) o;
        System.out.println("saving " + movie.getTitle() + " to file");
        return true;
    }
}
