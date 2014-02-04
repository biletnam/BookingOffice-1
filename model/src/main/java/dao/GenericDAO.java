package dao;

public interface GenericDAO<T> {
	T create(T t);
	T update(T t);
	void delete(Object id);
	T read(Object id);
}
