package com.sh.mvc.ws.config;

import com.sh.mvc.member.model.entity.Member;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import java.util.Map;

public class HelloMvcWebSocketConfigurator extends ServerEndpointConfig.Configurator {

    /**
     * 소켓 연결을 수립하기 위한 과정 = HandShake
     * -HandShakeRequest : 웹소켓 연결 요청 관련
     * -HandshakeResponse : 웹소켓 연결 응답 관련
     *
     * ServerEndpointConfig 객체
     * -필요한 설정 정보를 작성해두면 Endpoint 클래스의 @OnOpen에서 참조.
     * -내부적으로 userProperties라는 맵객체를 통해 정보 관리..
     * 
     * @param sec
     * @param request
     * @param response
     */
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        //HttpSession을 통해 로그인한 사용자 정보 가져오기.
        HttpSession session = (HttpSession) request.getHttpSession(); //다운캐스팅
        Member loginMember = (Member)session.getAttribute("loginMember");
        String memberId = loginMember.getId();

        //설정 객체의 내부 맵에 저장.
        Map<String,Object> props = sec.getUserProperties();
        props.put("memberId",memberId); //->OnOpen에서 꺼낼수있다

    }
}
