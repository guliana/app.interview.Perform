package sk.ness.interview.service;

import java.util.List;

import sk.ness.interview.dto.Author;
import sk.ness.interview.dto.AuthorStats;

/**
 * Service should handle all author related manipulation.
 *
 * @author michal.kmetka
 *
 */
public interface AuthorService {

  /** Returns all available {@link Author}s */
  List<Author> findAll();

  List<AuthorStats> findAllAuthorStats();
}
