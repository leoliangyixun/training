package com.yangkai.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class ResultMapper<T> {
	public List<T> getResult(ResultSet rs, List<T> objs){
		try {
			if(rs.isAfterLast()!=rs.isBeforeFirst()){
				objs=new ArrayList<T>();
				while(rs.next()){
					T obj=getObject(rs);	
					objs.add(obj);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objs;
	}
	public T getResult(ResultSet rs){
		T obj=null;
		try {
			if(rs.next()){
				obj=getObject(rs);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
		
	}
	protected abstract T getObject(ResultSet rs);
}
