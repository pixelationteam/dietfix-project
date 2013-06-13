package pup.thesis.controller;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import pup.thesis.logging.App;
import pup.thesis.util.mysql.DBQuerier;

public class DietfixContextListener implements ServletContextListener{
		ServletContext context;
		public void contextInitialized(ServletContextEvent contextEvent) {
			System.out.println("Context Created");
			try {
				DBQuerier.start();
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			context = contextEvent.getServletContext();
			// set variable to servlet context
			context.setAttribute("TEST", "TEST_VALUE");
		}
		public void contextDestroyed(ServletContextEvent contextEvent) {
			context = contextEvent.getServletContext();
			// This manually deregisters JDBC driver, which prevents Tomcat 7 from complaining about memory leaks wrto this class
	       
	        try {
				DBQuerier.stop();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Context Destroyed");
		}
	
}
