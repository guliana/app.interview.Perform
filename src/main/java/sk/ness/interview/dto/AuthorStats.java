package sk.ness.interview.dto;

/**
 * Simple DTO containing all informations we need about author statistics
 *
 * @author michal.kmetka
 *
 */
public class AuthorStats {

  private String authorName;
  private Integer articleCount;

  public String getAuthorName() {
    return this.authorName;
  }

  public void setAuthorName(final String authorName) {
    this.authorName = authorName;
  }

  public Integer getArticleCount() {
    return this.articleCount;
  }

  public void setArticleCount(final Integer articleCount) {
    this.articleCount = articleCount;
  }

}
