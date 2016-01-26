package sk.ness.interview.dao;

import sk.ness.interview.domain.Comment;

public interface CommentDAO {
	
	public void persist(final Comment comment);
}
