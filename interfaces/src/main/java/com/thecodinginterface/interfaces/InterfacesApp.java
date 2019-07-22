// InterfacesApp.java

package com.thecodinginterface.interfaces;

public class InterfacesApp {

    static RepositoryDAO fileRepo = new MovieFileRepository();
    static RepositoryDAO dbRepo = new MovieDBRepository();

    public static void main(String[] args) {
        Movie[] movies = {
            new Movie("Cars"),
            new Movie("Iron Man"),
            new Movie("Batman"),
            new Movie("The Incredibles")
        };

        for (Movie movie : movies) {
            RepositoryDAO repo = getRepo(movie);
            persist(repo, movie);
        }
    }

    // persist only requires that the reference type for repo variable
    // adhears to the contract specified in RepositoryDAO interface.
    // It doesn't know or care if MovieFileRepository or MovieDBRepository 
    // are actually being passed to it because they are both of the 
    // RepositoryDAO interface type
    static void persist(RepositoryDAO repo, Movie movie) {
        repo.save(movie);
    }

    static RepositoryDAO getRepo(Movie movie) {
        RepositoryDAO repo = fileRepo;
        if (movie.getTitle().toLowerCase().contains("man")) {
            repo = dbRepo;
        }
        return repo;
    }
}
