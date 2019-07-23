// MovieDBRepository.java

package com.thecodinginterface.generics;

class MovieDBRepository implements RepositoryDAO<Movie> {

    @Override
    public boolean save(Movie movie) {
        System.out.println("saving " + movie.getTitle() + " to database");
        return true;
    }
}
