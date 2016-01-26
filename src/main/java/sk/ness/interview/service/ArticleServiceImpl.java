package sk.ness.interview.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import sk.ness.interview.dao.ArticleDAO;
import sk.ness.interview.domain.Article;

/**
 * Service should be used as a gateway to {@link Article} world and handle all
 * article related manipulation.
 *
 * @author michal.kmetka
 *
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

	@Resource
	private ArticleDAO articleDAO;

	@Override
	public Article findByID(final Integer articleId) {
		return this.articleDAO.findByID(articleId);
	}

	@Override
	public List<Article> findAll() {
		return this.articleDAO.findAll();
	}

	@Override
	public void createArticle(final Article article) {
		this.articleDAO.persist(article);
	}

	@Override
	public void ingestArticles(final String jsonArticles) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<Article> articles = Arrays.asList(mapper.readValue(jsonArticles, Article[].class));
			for (Article article : articles) {
				this.createArticle(article);
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
