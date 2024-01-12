package com.sh.mvc.board.model.service;
import com.sh.mvc.board.model.dao.BoardDao;
import com.sh.mvc.board.model.entity.Attachment;
import com.sh.mvc.board.model.entity.Board;
import com.sh.mvc.board.model.entity.BoardComment;
import com.sh.mvc.board.model.vo.BoardVo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

import static com.sh.mvc.common.SqlSessionTemplate.getSqlSession;
//1215 게시판 전용으로 controller 단에 새로 생성한 service 클래스
//BoardDao에 할 일 전해주기.

public class BoardService {
    private BoardDao boardDao = new BoardDao();



    //페이징용 findAll
    public List<BoardVo> findAll(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        List<BoardVo> boards = boardDao.findAll(session,param);
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

    //1220 조회수 증가 처리 추가. =>  조회수 상관없이 게시글 조회하는 경우
    public BoardVo findById(long id)
    {

        return findById(id,true);
    }


    //1220 조회여부까지 추가한 findById - true는 이미 읽음 / false: 첫조회이므로 false때만 증가처리
    public BoardVo findById(long id, boolean hasRead)
    {

        //id로 게시글 1개를 찾는다.
        SqlSession session = getSqlSession();

        //조회수 증가 처리
        BoardVo board = null;
        int result=0;
        try
        {
            if(!hasRead)
                result = boardDao.updateBoardReadCount(session,id);

            board = boardDao.findById(session, id);
            List<BoardComment> comments = boardDao.findCommentByBoardId(session,id);
            board.setComments(comments);//보드에 보드커맨츠 추가
            
            session.commit();
        }
        catch(Exception e)
        {
            session.rollback();
            throw e;
        }
        finally
        {
            session.close();
        }
        return board;
    }





    //게시글 추가하기.
    public int insertBoard(BoardVo board)
    {
        int result = 0;
        SqlSession session = getSqlSession();

        try
        {
            //board + attach 둘 다 insert하고 commit or rollback 처리 해야함

            //board 테이블에 등록
            result = boardDao.insertBoard(session,board);

            System.out.println("board get id())" + board.getId());
            //attach 테이블에 등록
            List<Attachment> attachments = board.getAttachments();
            if(!attachments.isEmpty())
            {
                for(Attachment attach : attachments)
                {
                    attach.setBoardId(board.getId()); //fk로 사용할 보드id
                    result = boardDao.insertAttachment(session,attach);

                    //attachment 테이블삭제
                    List<Long> delFiles = board.getDelFiles();
                    if(!delFiles.isEmpty())
                    {
                        System.out.println("지울 첨부파일이 있다........");
                        for(Long id : delFiles)
                            result = boardDao.deleteAttachment(session,id);
                    }
                }
            }


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

    //1220 게시판 수정
//    public int updateBoard(Board board)
//    {
//        //insert와 같은 dml처리 이므로 try-catch-finally 사용하고
//        //성공시 commit, 실패시 rollback 처리
//        int result = 0;
//        SqlSession session = getSqlSession();
//        try
//        {
//            result = boardDao.updateBoard(session,board);
//            session.commit();
//        }
//        catch(Exception e)
//        {
//            session.rollback();
//            throw e;
//        }
//        finally
//        {
//            //어찌됐든간에 세션종료는 필수다.
//            session.close();
//        }
//        return result;
//    }

        //new version
      public int updateBoard(BoardVo board)
      {
            int result = 0;
            SqlSession session = getSqlSession();
        try
        {
            result = boardDao.updateBoard(session,board);
            List<Attachment> attachments = board.getAttachments();
            if(!attachments.isEmpty())
            {
                for(Attachment attach : attachments)
                {
                    attach.setBoardId(board.getId());
                    result = boardDao.insertAttachment(session,attach);
                }
            }
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

    public int insertBoardComment(BoardComment comment)
    {
        //dml
        SqlSession session = getSqlSession();
        int result=0;
        try
        {
            result = boardDao.insertBoardComment(session,comment);
            session.commit();
        }
        catch(Exception e)
        {
            //오류발생시 롤백
            session.rollback();
            throw e; //오류던져
        }
        finally
        {
            session.close();//어찌됐든간 종료
        }
        return result;
    }


//    public int getToTalCount() {
//    }
}
