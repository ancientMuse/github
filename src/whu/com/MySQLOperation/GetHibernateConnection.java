package whu.com.MySQLOperation;

import java.sql.Connection;


import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.impl.SessionFactoryImpl;

public class GetHibernateConnection {
	private Session session=null;
	private Configuration config;
	private SessionFactory factory;

	public GetHibernateConnection() {
		this.config = new Configuration().configure();
		// 获取SessionFactory
		this.factory = config.buildSessionFactory();
	}

	public Session getSession() {

		// 通过SessionFactory获取Session
		this.session = factory.getCurrentSession();
	//	this.session.
		return this.session;
		

	}
//获取connection
	public Connection  getConneciton() throws SQLException {

		ConnectionProvider cp = ((SessionFactoryImpl) factory)
				.getConnectionProvider();
		return cp.getConnection();

	}
	
	public void close(){
		
	if(!this.factory.isClosed())
		this.factory.close();
	}

}
