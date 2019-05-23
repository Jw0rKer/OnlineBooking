/**
 *
 */
package github.jworker.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Map;

@Repository
public class GenericRepository implements IGenericRepository {

  @PersistenceContext
  protected EntityManager entityManager;


  @Transactional
  @Override
  public void persist(Object entity) {
    this.entityManager.persist(entity);
  }

  @Transactional
  @Override
  public Object merge(Object entity) {
    return this.entityManager.merge(entity);
  }

  @Transactional
  @Override
  public void remove(Object entity) {
    this.entityManager.remove(entity);
  }

  @Override
  public Query getQuery(String hql, Map<String, Object> map) {
    Query query = entityManager.createQuery(hql);
    for (Map.Entry<String, Object> m : map.entrySet()) {
      query.setParameter(m.getKey(), m.getValue());
    }
    return query;
  }

  @Override
  public Object findById(Class c, Long id) {
    return entityManager.find(c, id);
  }


}
