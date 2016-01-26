package sk.ness.interview.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.Api;
import sk.ness.interview.domain.Article;
import sk.ness.interview.domain.Comment;
import sk.ness.interview.dto.Author;
import sk.ness.interview.dto.AuthorStats;
import sk.ness.interview.service.ArticleService;
import sk.ness.interview.service.AuthorService;
import sk.ness.interview.service.CommentService;
import sk.ness.interview.view.ArticleView;

@Api
@RestController
public class BlogController {

	@Resource
	private ArticleService articleService;

	@Resource
	private AuthorService authorService;

	@Resource
	private CommentService commentService;

	// ~~ Article

	@JsonView(ArticleView.ArticleList.class)
	@RequestMapping(value = "articles", method = RequestMethod.GET)
	public List<Article> getAllArticles() {
		return this.articleService.findAll();
	}

	@RequestMapping(value = "articles/{articleId}", method = RequestMethod.GET)
	public Article getArticle(@PathVariable final Integer articleId) {
		return this.articleService.findByID(articleId);
	}

	@JsonView(ArticleView.ArticleList.class)
	@RequestMapping(value = "articles/search/{searchText}", method = RequestMethod.GET)
	public List<Article> searchArticle(@PathVariable final String searchText) {
		if (searchText == null || searchText.isEmpty()) {
			throw new IllegalArgumentException("Cannot search for empty String");
		}
		List<Article> allArticles = this.articleService.findAll();
		List<Article> matchingArticles = new ArrayList<Article>();
		for (Article article : allArticles) {
			if (article.getAuthor().indexOf(searchText) > -1 || article.getTitle().indexOf(searchText) > -1
					|| article.getText().indexOf(searchText) > -1) {
				matchingArticles.add(article);
			}
		}
		return matchingArticles;
	}

	@RequestMapping(value = "articles/add", method = RequestMethod.PUT)
	public void addArticle(@RequestBody final Article article) {
		this.articleService.createArticle(article);
	}

	// ~~ Author

	@RequestMapping(value = "authors", method = RequestMethod.GET)
	public List<Author> getAllAuthors() {
		return this.authorService.findAll();
	}

	@RequestMapping(value = "authors/stats", method = RequestMethod.GET)
	public List<AuthorStats> authorStats() {
		return this.authorService.findAllAuthorStats();
	}

	// ~~ Comment

	@RequestMapping(value = "articles/{articleId}/comment", method = RequestMethod.PUT)
	public void addComment(@RequestBody final Comment comment, @PathVariable final Integer articleId) {
		Article article = articleService.findByID(articleId);
		if (article == null) {
			throw new IllegalArgumentException("No such article with id: " + articleId);
		}
		comment.setArticle(article);
		this.commentService.addComment(comment);
	}
}
