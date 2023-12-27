package com.sh.mvc.ws.endpoint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sh.mvc.common.LocalDateTimeSerializer;
import com.sh.mvc.notification.model.entity.Notification;
import com.sh.mvc.ws.config.HelloMvcWebSocketConfigurator;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 서버사이드 웹 소켓 엔드포인트(최초 요청을 처리하는 곳..)
 * new WebSocket(`ws://${location.host}${contextPath}/echo`);의 echo
 */
@ServerEndpoint(value = "/echo", configurator = HelloMvcWebSocketConfigurator.class)
public class EchoWebSocket {

    private static Gson gson;
    static
    {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        gson = builder.create();
    }

    private static void addToClientMap(String memberId, Session session)
    {
        clientMap.put(memberId,session);
        log();
    }

    /**
     * 개인별 실시간 알림 메소드.
     * @param noti
     */
    public static void sendNotifiCation(Notification noti)
    {
        Session session = clientMap.get(noti.getMemberId());
        if(session!=null)
        {
            //ctrl alt v
            RemoteEndpoint.Basic basicRemote = session.getBasicRemote();
            try {
                basicRemote.sendText(gson.toJson(noti));//문자열로 보내야 하는데 가장 안전한 방법은 json전송방식이다..
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(noti.getMemberId()+"에 실시간 알림을 전송했습니다.🐾");
        }
        else {
            System.out.println(noti.getMemberId()+"는 접속중이 아닙니다...🙄");
        }
    }

    private void removeFromClientMap(String memberId) {
        clientMap.remove(memberId);
        log();
    }

    private static void log() {
        System.out.println(clientMap.size()+"명 접속중...."+clientMap.keySet());
    }

    /**
     * 웹 소켓 세션 관리를 위한 맵 객체
     * 키:사용자 아이디
     * 값:세션값
     * 멀티스레드 환경을 위한 동기화(스레드간 이용 순서 정해짐,한번에 하나의 스레드만 접근 가능)
     * 처리된 맵이 필요하다.
     */
    private static Map<String,Session> clientMap
             = Collections.synchronizedMap(new HashMap<>());
    @OnOpen
    public void open(EndpointConfig config, Session session)
    {
        System.out.println("[open]");
        //저장한 props값을 가져올수있다.
        Map<String, Object> props = config.getUserProperties();
        String memberId = (String)props.get("memberId");
        System.out.println(memberId+"님이 접속하셨습니다.😄");

        addToClientMap(memberId,session);

        //연결 해제시 map에서 사용자 제거를 위해 session의 내부맵에도 사용자 id등록
        Map<String,Object> sessionProps = session.getUserProperties();
        sessionProps.put("memberId",memberId);
    }



    @OnMessage
    public void message(String msg,Session session)
    {

        System.out.println("message:"+msg);
        Collection<Session> sessions = clientMap.values();
        for(Session sess : sessions)
        {
            RemoteEndpoint.Basic basic = sess.getBasicRemote();
            try
            {
                basic.sendText(msg);
            }
            catch(IOException e)
            {
                throw  new RuntimeException(e);
            }
        }

    }
    @OnError
    public void error(Throwable e)
    {
        System.out.println("error");
        e.printStackTrace();
    }
    @OnClose
    public void close(Session session)
    {
        System.out.println("close");
        Map<String,Object> sessionProps = session.getUserProperties();
        String memberId = (String)sessionProps.get("memberId");
        System.out.println(memberId+"님이 접속 해제 하셨습니다.😥");
        removeFromClientMap(memberId);
    }
}
