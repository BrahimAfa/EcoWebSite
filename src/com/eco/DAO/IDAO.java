package com.eco.DAO;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<T>  {
	public List<T> getAll() throws SQLException;
	public T getOne(int id) throws SQLException;
	public boolean Insert(T obj) throws SQLException;
}
