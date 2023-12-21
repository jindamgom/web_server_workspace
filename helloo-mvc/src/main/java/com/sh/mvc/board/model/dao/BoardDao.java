package com.sh.mvc.board.model.dao;


import com.sh.mvc.board.model.entity.Attachment;
import com.sh.mvc.board.model.entity.Board;
import com.sh.mvc.board.model.entity.BoardComment;
import com.sh.mvc.board.model.vo.BoardVo;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

//1215 db- board table에 crud 하기 위해 생성한 클래스~~

public class BoardDao {


    //페이징용 findAll
    public static List<BoardVo> findAll(SqlSession session, Map<String, Object> param)
    {
        //db 결과값 BoardService로 반환
        //selectList!
        int page = (int)param.get("page");
        int limit = (int)param.get("limit");
        //건너 뛸 회원수
        int offset = (page-1) * limit;
        RowBounds rowBounds = new RowBounds(offset,limit);
        //쿼리에 전달할 값 null(어차피 전체 조회라), 그담에 rowBounds
        return session.selectList("board.findAll",param,rowBounds);
    }

    public static List<Board> findAll(SqlSession session)
    {
        //db 결과값 BoardService로 반환
        //selectList!
        return session.selectList("board.findAll");
    }

    public static BoardVo findById(SqlSession session, long id) {
        return session.selectOne("board.findById",id);
    }

    public int insertBoard(SqlSession session, Board board)
    {
        return session.insert("board.insertBoard",board);
    }

    public int updateBoard(SqlSession session, Board board) {
        return session.update("board.updateBoard",board);
    }

    public int deleteBoard(SqlSession session, long myBoardId) {
        return session.delete("board.deleteBoard",myBoardId);
    }

    public int getToTalCount(SqlSession session)
    {
        return session.selectOne("board.getTotalCount");
    }

    public int insertAttachment(SqlSession session, Attachment attach) {
        return session.insert("board.insertAttachment",attach);
    }

    public int updateBoardReadCount(SqlSession session, long id) {
        return session.update("board.updateBoardReadCount",id);
    }

    public int deleteAttachment(SqlSession session, Long id) {
        return session.delete("board.deleteAttachment",id);
    }

    public List<BoardComment> findCommentByBoardId(SqlSession session, long boardId)
    {
        return session.selectList("board.findCommentByBoardId",boardId);
    }


    public int insertBoardComment(SqlSession session, BoardComment comment) {
        return session.insert("board.insertBoardComment",comment);
    }

    public BoardComment findCommentById(SqlSession session, Long id)
    {
        return session.selectOne("board.findCommentById",id);
    }

    public int deleteBoardComment(SqlSession session, long id)
    {
        return session.delete("board.deleteBoardComment",id);
    }


}
