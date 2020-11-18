package org.eclipse.jetty.demo;

import java.io.IOException;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;

public class EventSocket extends WebSocketAdapter {

  private DataFileLoader dataFileLoader = DataFileLoader.getInstance();

  @Override
  public void onWebSocketConnect(Session session) {
    super.onWebSocketConnect(session);
  }

  @Override
  public void onWebSocketText(String message) {
    super.onWebSocketText(message);
    try {
      getSession().getRemote().sendString(dataFileLoader.getData());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onWebSocketClose(int statusCode, String reason) {
    super.onWebSocketClose(statusCode, reason);
  }

  @Override
  public void onWebSocketError(Throwable cause) {
    super.onWebSocketError(cause);
  }
}
