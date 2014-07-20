package dataset.dao;

import java.util.List;

public interface IntfDAO<E> {

	public void add(E e);
	public void delete(E e);
	public void update(E e);
	public List<E> findAll();
	public E find(int id);
}
