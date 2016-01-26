package sk.ness.interview.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonView;

import sk.ness.interview.view.ArticleView;

@Entity
@Table(name = "articles")
@SequenceGenerator(name = "articles_seq_store", sequenceName = "article_seq", allocationSize = 1)
public class Article {

	public Article() {
		this.createTimestamp = new Date();
	}

	@JsonView(ArticleView.ArticleList.class)
	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "articles_seq_store")
	private Integer id;

	@JsonView(ArticleView.ArticleList.class)
	@Column(name = "title", length = 250)
	private String title;

	@JsonView(ArticleView.ArticleList.class)
	@Column(name = "text", length = 2000)
	private String text;

	@JsonView(ArticleView.ArticleList.class)
	@Column(name = "author", length = 250)
	private String author;

	@JsonView(ArticleView.ArticleList.class)
	@Column(name = "create_timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTimestamp;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "article")
	private Set<Comment> comments = new HashSet<Comment>();

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(final Set<Comment> comments) {
		this.comments = comments;
	}

	public void addComment(final Comment comment) {
		this.comments.add(comment);
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getText() {
		return this.text;
	}

	public void setText(final String text) {
		this.text = text;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(final String author) {
		this.author = author;
	}

	public Date getCreateTimestamp() {
		return this.createTimestamp;
	}

	public void setCreateTimestamp(final Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

}
