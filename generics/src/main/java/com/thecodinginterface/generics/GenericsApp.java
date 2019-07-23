// GenericsApp.java

package com.thecodinginterface.generics;

public class GenericsApp {

  // must specify Movie for the interface types to take 
  // advantage of type safety
  static RepositoryDAO<Movie> fileRepo = new MovieFileRepository();
  static RepositoryDAO<Movie> dbRepo = new MovieDBRepository();

  public static void main(String[] args) {

      // compiler error: incompatible types: String cannot be converted to Movie
      //RepositoryDAO<Movie> fileRepo2 = new MovieFileRepository();
      //var favoriteMovie = "Kung Fu Panda";
      //fileRepo2.save(favoriteMovie);

      Movie[] movies = {
          new Movie("Cars"),
          new Movie("Iron Man"),
          new Movie("Batman"),
          new Movie("The Incredibles")
      };

      for (Movie movie : movies) {
          RepositoryDAO<Movie> repo = getRepo(movie);
          persist(repo, movie);
      }
  }

  static void persist(RepositoryDAO<Movie> repo, Movie movie) {
      repo.save(movie);
  }

  static RepositoryDAO<Movie> getRepo(Movie movie) {
      RepositoryDAO<Movie> repo = fileRepo;
      if (movie.getTitle().toLowerCase().contains("man")) {
          repo = dbRepo;
      }
      return repo;
  }
}
