package dao;

public interface GenericDao<T> {
	T create(T t);
	T update(T t);
	void delete(Object id);
	T read(Object id);
}
