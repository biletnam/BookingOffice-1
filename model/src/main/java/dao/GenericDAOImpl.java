package dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class GenericDAOImpl<T> implements GenericDAO<T> {
	protected EntityManager entityManager;
	private Class<T> type;

	public GenericDAOImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType)t;
		type = (Class)pt.getActualTypeArguments()[0];
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("BookingOfficeTest");
		entityManager = factory.createEntityManager();
	}

	public EntityManager getEntityManager() {
		return entityManager;
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
		return (T)entityManager.find(type, id);
	}

}
