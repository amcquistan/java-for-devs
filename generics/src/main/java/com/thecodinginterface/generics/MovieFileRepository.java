// MovieFileRepository.java

package com.thecodinginterface.generics;

class MovieFileRepository implements RepositoryDAO<Movie> {

    @Override
    public boolean save(Movie movie) {
        System.out.println("saving " + movie.getTitle() + " to file system");
        return true;
    }
}
