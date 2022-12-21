package dataLayer;

import java.util.Collection;

public interface DAO<T> {
	public T selectBy(int id);
	public Collection<T> getAll();
	public T insertRow(T obj);
	public boolean updateRow(T obj);
}
