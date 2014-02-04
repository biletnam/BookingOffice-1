package dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {
	protected EntityManager entityManager;
	private Class<T> type;

	public GenericDaoImpl() {
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
		//TODO remove transaction in future
		entityManager.getTransaction().begin();
		entityManager.persist(t);
		entityManager.getTransaction().commit();
		return t;
	}

	@Override
	public T update(final T t) {
		return this.entityManager.merge(t);
	}

	@Override
	public void delete(Object id) {
		//TODO remove transaction in future
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.getReference(type, id));
		entityManager.getTransaction().commit();
	}

	@Override
	public T read(Object id) {
		return (T)entityManager.find(type, id);
	}

}
