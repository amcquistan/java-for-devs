// MovieDBRepository.java

package com.thecodinginterface.interfaces;

public class MovieDBRepository implements RepositoryDAO {

    /**
     * Save Movie object to database
     */
    public boolean save(Object o) {
        Movie movie = (Movie) o;
        System.out.println("saving " + movie.getTitle() + " to database");
        return true;
    }
}
