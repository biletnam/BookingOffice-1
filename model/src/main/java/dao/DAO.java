package dao;

public interface DAO<T> {
	void create(T t);
	void update(T t);
	void delete(T t);
	T read(int id);
}
