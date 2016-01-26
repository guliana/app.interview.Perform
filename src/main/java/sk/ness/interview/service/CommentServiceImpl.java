package sk.ness.interview.service;


import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import sk.ness.interview.dao.CommentDAO;
import sk.ness.interview.domain.Comment;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	@Resource
	private CommentDAO commentDAO;

	@Override
	public void addComment(final Comment comment) {
		commentDAO.persist(comment);
	}

}
