package org.eclipse.jetty.demo;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

public class EventServer {

  public static void main(String[] args) {
    DataFileLoader dataFileLoader = DataFileLoader.getInstance();
    dataFileLoader.load();

    Server server = new Server(new QueuedThreadPool(1000));
    ServerConnector connector = new ServerConnector(server);
    connector.setPort(8888);
    connector.setReuseAddress(true);
    server.addConnector(connector);

    // Setup the basic application "context" for this application at "/"
    // This is also known as the handler tree (in jetty speak)
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");
    server.setHandler(context);

    // Add a websocket to a specific path spec
    ServletHolder holderEvents = new ServletHolder("ws-events", EventServlet.class);
    context.addServlet(holderEvents, "/socket.io/*");

    try {
      server.start();
      server.join();
    } catch (Throwable t) {
      t.printStackTrace(System.err);
    }
  }
}
