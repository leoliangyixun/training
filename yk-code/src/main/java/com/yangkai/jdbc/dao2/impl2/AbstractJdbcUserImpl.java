package org.yangkai.jdbc.dao2.impl2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AbstractJdbcUserImpl extends AbstractJdbcTemplate{
    /*
     * BasicDaoTemplate类的抽象方法依然无法实现，因为实际情况我们无法预知，
     * 这时匿名内部类的优势就体现出来了。
     * */
	@Override
	protected <T> T doInCallback(Connection conn, PreparedStatement ps,ResultSet rs) {
		
		return null;
	}
	
	@Override
	protected <T> T doInCallback(ResultSet rs) {
		
		return null;
	}

}
