package github.jworker.dao;

import javax.persistence.Query;
import java.util.Map;

public interface IGenericRepository  {

	void persist(Object entity);

	Object merge(Object entity);

	void remove(Object entity);

	Query getQuery(String hql, Map<String, Object> map);

  Object findById(Class c, Long id);
}
