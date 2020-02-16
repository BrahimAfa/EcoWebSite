package com.eco.DAO;

import java.util.List;

public interface IDAO<T> {
	public List<T> getAll();
	public T getOne(int id);
	public void Updat(T id);
	public void Insert(T id);
	public void Delete (int id);
}
