package com.itacademy.jd2.lg.ms.web;

import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Separate startup class for people that want to run the examples directly. Use
 * parameter -Dcom.sun.management.jmxremote to startup JMX (and e.g. connect
 * with jconsole).
 */
public class StartJetty {
	/**
	 * Main function, starts the jetty server.
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		startInstance(8081);
		// startInstance(8082);
		// startInstance(8083);
		// startInstance(8084);
	}

	private static void startInstance(int port) {
		Server server = new Server();

		HttpConfiguration http_config = new HttpConfiguration();
		http_config.setOutputBufferSize(32768);

		ServerConnector http = new ServerConnector(server, new HttpConnectionFactory(http_config));
		http.setPort(port);
		http.setIdleTimeout(1000 * 60 * 60);

		server.addConnector(http);

		org.eclipse.jetty.webapp.Configuration.ClassList classlist = org.eclipse.jetty.webapp.Configuration.ClassList
				.setServerDefault(server);
		classlist.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration",
				"org.eclipse.jetty.plus.webapp.EnvConfiguration", "org.eclipse.jetty.plus.webapp.PlusConfiguration");
		classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
				"org.eclipse.jetty.annotations.AnnotationConfiguration");

		WebAppContext bb = new WebAppContext();
		bb.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*/[^/]*jstl.*\\.jar$");
		bb.setServer(server);
		bb.setContextPath("/");
		bb.setWar("src/main/webapp");

		server.setHandler(bb);

		/*
		 * MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		 * MBeanContainer mBeanContainer = new MBeanContainer(mBeanServer);
		 * server.addEventListener(mBeanContainer); server.addBean(mBeanContainer);
		 */

		try {
			server.start();
			// server.join();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(100);
		}
	}
}
