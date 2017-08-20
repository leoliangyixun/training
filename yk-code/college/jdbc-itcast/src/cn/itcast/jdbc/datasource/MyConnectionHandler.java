package cn.itcast.jdbc.datasource;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * 
 * 2008-12-13
 * 
 * @author <a href="mailto:liyongibm@gmail.com">liyong</a>
 * 
 */
class MyConnectionHandler implements InvocationHandler {
	private Connection realConnection;
	private Connection warpedConnection;
	private MyDataSource dataSource;

	private int maxUseCount = 5;
	private int currentUseCount = 0;

	MyConnectionHandler(MyDataSource dataSource) {
		this.dataSource = dataSource;
	}

	Connection bind(Connection realConn) {
		this.realConnection = realConn;
		this.warpedConnection = (Connection) Proxy.newProxyInstance(this
				.getClass().getClassLoader(), new Class[] { Connection.class },
				this);
		return warpedConnection;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		if ("close".equals(method.getName())) {
			this.currentUseCount++;
			if (this.currentUseCount < this.maxUseCount)
				this.dataSource.connectionsPool.addLast(this.warpedConnection);
			else {
				this.realConnection.close();
				this.dataSource.currentCount--;
			}
		}
		return method.invoke(this.realConnection, args);
	}

}
