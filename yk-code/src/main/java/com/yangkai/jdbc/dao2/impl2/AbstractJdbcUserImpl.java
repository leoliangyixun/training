package org.yangkai.jdbc.dao2.impl2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AbstractJdbcUserImpl extends AbstractJdbcTemplate{
    /*
     * BasicDaoTemplate��ĳ��󷽷���Ȼ�޷�ʵ�֣���Ϊʵ����������޷�Ԥ֪��
     * ��ʱ�����ڲ�������ƾ����ֳ����ˡ�
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
