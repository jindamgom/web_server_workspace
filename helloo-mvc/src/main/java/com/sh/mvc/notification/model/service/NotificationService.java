package com.sh.mvc.notification.model.service;

import com.sh.mvc.board.model.entity.Board;
import com.sh.mvc.board.model.entity.BoardComment;
import com.sh.mvc.board.model.service.BoardService;
import com.sh.mvc.notification.model.entity.Notification;
import com.sh.mvc.notification.model.entity.Type;
import com.sh.mvc.ws.endpoint.EchoWebSocket;
import org.apache.ibatis.session.SqlSession;


import java.util.List;

import static com.sh.mvc.common.SqlSessionTemplate.getSqlSession;

public class NotificationService {
    private NotificationDao notificationDao = new NotificationDao();
    private BoardService boardService = new BoardService();

    public int insertNotification(Notification n1)
    {
        int result=0;
        SqlSession session = getSqlSession();

        try
        {
            result = notificationDao.insertNotification(session,n1);
            session.commit();
        }
        catch(Exception e)
        {
            session.rollback();
            throw e;
        }
        finally {
            session.close();
        }
        return result;
    }


    public List<Notification> findByMemberId(String memberId) {
        SqlSession session = getSqlSession();
        List<Notification> notifications = notificationDao.findByMemberId(session,memberId);

        session.close();
        return notifications;
    }

    /**
     * 새 댓글 알림 용도 notification 객체 생성하기
     * @param comment
     * @return
     */
    public int notify(BoardComment comment) {

        Long boardId = comment.getBoardId();
        Board board = boardService.findById(boardId);

        Notification noti = new Notification();
        noti.setMemberId(board.getMemberId());
        noti.setContent(board.getTitle()+"게시글에 댓글이 달렸습니다.");
        noti.setType(Type.NEW_COMMENT);

        //1.실시간 알림 - 접속한 상태면 보내고 아니면 무시
        EchoWebSocket.sendNotifiCation(noti);


        //2.db알림테이블 insert
        return insertNotification(noti);


    }
}
