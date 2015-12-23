package avz.bz.ua.wss;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import avz.bz.ua.dao.Manager;

@ServerEndpoint("/actions")
public class MyWss {

	Manager manager = new Manager();

	@OnOpen
	public void open(Session session) {
		System.out.println("WebSocket have opened: " + session.getId());

	}

	@OnClose
	public void close(Session session) {
		manager.saveAll();
	}

	@OnError
	public void onError(Throwable error) {
		System.out.println(error.toString());
	}

	@OnMessage
	public void handleMessage(String message, Session session)
			throws IOException {

		if (message.contains("checkMe")) {
			String[] array = message.split(":");
			String contentString = manager.checkContent(array[1], array[2]);
			if (contentString != null) {
				contentString = contentString.replaceAll(" ", "");
				session.getBasicRemote().sendText("catch:" + contentString);
			}
			return;
		}
		discoverMessage(message);
	}

	private void discoverMessage(String message) {
		message = message.replaceAll(" ", "");
		String[] array = message.split(":");
		manager.putValueInDict(array[0], array[1], array[2]);
	}

}