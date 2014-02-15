package dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public abstract class GenericDaoImpl<T> implements GenericDao<T> {
	
	@PersistenceContext
	protected EntityManager entityManager;
	private Class<T> type;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public GenericDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}

	public Class<T> getType() {
		return type;
	}

	@Override
	public T create(T t) {
		entityManager.persist(t);
		return t;
	}

	@Override
	public T update(final T t) {
		return this.entityManager.merge(t);
	}

	@Override
	public void delete(Object id) {
		entityManager.remove(entityManager.getReference(type, id));
	}

	@Override
	public T read(Object id) {
		return (T) entityManager.find(type, id);
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

}
