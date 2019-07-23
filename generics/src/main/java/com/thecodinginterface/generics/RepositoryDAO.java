// RepositoryDAO.java

package com.thecodinginterface.generics;

public interface RepositoryDAO<E> {

  boolean save(E e);
}