package sk.ness.interview.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import sk.ness.interview.dao.AuthorDAO;
import sk.ness.interview.domain.Article;
import sk.ness.interview.dto.Author;
import sk.ness.interview.dto.AuthorStats;

/**
 * Service should be used as a gateway to {@link Article} world and handle all
 * article related manipulation.
 *
 * @author michal.kmetka
 *
 */
@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

	@Resource
	private AuthorDAO authorDAO;

	@Override
	public List<Author> findAll() {
		return this.authorDAO.findAll();
	}

	@Override
	public List<AuthorStats> findAllAuthorStats() {
		return this.authorDAO.findAllAuthorStats();
	}

}
