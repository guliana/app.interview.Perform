package sk.ness.interview.dao;

import java.util.List;

import sk.ness.interview.dto.Author;
import sk.ness.interview.dto.AuthorStats;

public interface AuthorDAO {

  /** Returns all available {@link Author}s */
  List<Author> findAll();
  
  List<AuthorStats> findAllAuthorStats();

}
