package com.sh.mvc.board.model.service;
import com.sh.mvc.board.model.dao.BoardDao;
import com.sh.mvc.board.model.entity.Board;
import com.sh.mvc.member.model.entity.Member;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

import static com.sh.mvc.common.SqlSessionTemplate.getSqlSession;
//1215 게시판 전용으로 controller 단에 새로 생성한 service 클래스
//BoardDao에 할 일 전해주기.

public class BoardService {
    private BoardDao boardDao = new BoardDao();



    //페이징용 findAll
    public List<Board> findAll(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        List<Board> boards = boardDao.findAll(session,param);
        session.close();
        return boards;
    }



    //findAll
    public List<Board> findAll()
    {
        //getSqlSession : 파일을 연결하여 XML 파일 정보를 바탕으로 DB와 연결하고,
        // 해당 Connection(SqlSession)을 리턴하는 역할

        SqlSession session = getSqlSession();
        //session을 줄테니 db 결과값좀..ㅎ
        List<Board> boards = boardDao.findAll(session);
        //다썼으면 닫기
        session.close();
        return boards; //결과값을 리턴
    }


    //int 보다 long이 범위가 넓기 때문에 long 추천.
    public Board findById(long id)
    {
        //id로 게시글 1개를 찾는다.
        SqlSession session = getSqlSession();
        Board board = boardDao.findById(session, id);
        session.close();
        return board;
    }

    //게시글 추가하기.
    public int insertBoard(Board board)
    {
        int result = 0;
        SqlSession session = getSqlSession();

        try
        {
            //정상적으로 수행했을 경우 커밋.
            result = boardDao.insertBoard(session,board);
            session.commit();
        }
        catch(Exception e)
        {
            //에러가 발생할 경우 롤백하기
            session.rollback();
            throw  e;
        }
        finally
        {
            session.close();
        }
        return result;
    }

    public int updateBoard(Board board)
    {
        //insert와 같은 dml처리 이므로 try-catch-finally 사용하고
        //성공시 commit, 실패시 rollback 처리
        int result = 0;
        SqlSession session = getSqlSession();
        try
        {
            result = boardDao.updateBoard(session,board);
            session.commit();
        }
        catch(Exception e)
        {
            session.rollback();
            throw e;
        }
        finally
        {
            //어찌됐든간에 세션종료는 필수다.
            session.close();
        }
        return result;
    }

    public int deleteBoard(long myBoardId)
    {
        int result=0;
        SqlSession session = getSqlSession();
        try
        {
            result = boardDao.deleteBoard(session,myBoardId);
            session.commit();
        }
        catch(Exception e)
        {
            session.rollback();
            throw e;
        }
        finally
        {
            //어찌됐든간에 세션종료는 필수다.
            session.close();
        }

        return result;
    }

    public int getTotalCount()
    {
        SqlSession session = getSqlSession();
        int totalCount = boardDao.getToTalCount(session);
        session.close();
        return totalCount;
    }

//    public int getToTalCount() {
//    }
}
