package org.yangkai.jdbc.dao.impl5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.yangkai.jdbc.factory.ConnectionFactory;
interface ObjectMapper{
	public <T> T commonObjectMapper(ResultSet rs);
}
public class CommonInstanceTemplate {
	
	public <T> T find(String sql,Object[] args,ObjectMapper mapper)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		conn=ConnectionFactory.getConnection();
		T t=null;
		try {
			ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			for(int i=0;i<args.length;i++)
			{
				ps.setObject(i+1, args[i]);
			}
			rs=ps.executeQuery();
			if(rs.next())
			{
				t=mapper.commonObjectMapper(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		ConnectionFactory.free(rs, ps, conn);
		return t;
	}
}
