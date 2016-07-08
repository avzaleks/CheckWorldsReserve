package avz.bz.ua.wss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import avz.bz.ua.service.DictionaryService;


public class MyWss extends TextWebSocketHandler {

	@Autowired
	private DictionaryService dictionaryService;

	

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("New connection " + session.getHandshakeHeaders());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println(status.getReason());
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		System.out.println(exception.getMessage());
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage pMessage) throws Exception {
		String message = pMessage.getPayload();
		System.out.println(message);
		if (message.contains("checkMe")) {
			String[] array = message.split(":");
			String contentString = dictionaryService.checkContent(array[1], array[2]);
			if (contentString != null) {
				contentString = contentString.replaceAll(" ", "");
				System.out.println(contentString + "3333333333333");
				session.sendMessage(new TextMessage("catch:" + contentString));
			}
			return;
		}
		dictionaryService.discoverMessage(message);
	}

	public DictionaryService getProductService() {
		return dictionaryService;
	}

	public void setProductService(DictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

}