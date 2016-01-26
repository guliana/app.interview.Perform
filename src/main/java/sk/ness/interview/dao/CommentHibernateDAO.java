package sk.ness.interview.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import sk.ness.interview.domain.Comment;

@Repository
public class CommentHibernateDAO implements CommentDAO {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public void persist(final Comment comment) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(comment);
	}

}
