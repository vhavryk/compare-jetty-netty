package com.corundumstudio.socketio.demo;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.Transport;
import com.corundumstudio.socketio.listener.DataListener;

public class ChatLauncher {

  public static void main(String[] args) throws InterruptedException {

    DataFileLoader dataFileLoader = DataFileLoader.getInstance();
    dataFileLoader.load();
    Configuration config = new Configuration();
    config.setHostname("localhost");
    config.setPort(8888);
    config.setBossThreads(1);
    config.setWorkerThreads(Runtime.getRuntime().availableProcessors());
    config.setTransports(Transport.WEBSOCKET);

    SocketConfig socketConfig = new SocketConfig();
    socketConfig.setReuseAddress(true);
    config.setSocketConfig(socketConfig);

    final SocketIOServer server = new SocketIOServer(config);
    server.addEventListener("chatevent", String.class, new DataListener<String>() {
      @Override
      public void onData(SocketIOClient client, String data, AckRequest ackRequest) {
        client.sendEvent("chatevent", dataFileLoader.getData());
      }
    });

    server.start();
    Thread.sleep(Integer.MAX_VALUE);
    server.stop();
  }


}
