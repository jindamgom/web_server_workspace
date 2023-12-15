package com.sh.mvc.board.model.dao;


import com.sh.mvc.board.model.entity.Board;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

//1215 db- board table에 crud 하기 위해 생성한 클래스~~
public class BoardDao {


    //페이징용 findAll
    public static List<Board> findAll(SqlSession session, Map<String, Object> param)
    {
        //db 결과값 BoardService로 반환
        //selectList!
        int page = (int)param.get("page");
        int limit = (int)param.get("limit");
        //건너 뛸 회원수
        int offset = (page-1) * limit;
        RowBounds rowBounds = new RowBounds(offset,limit);
        //쿼리에 전달할 값 null(어차피 전체 조회라), 그담에 rowBounds
        return session.selectList("board.findAllPage",null,rowBounds);
    }

    public static List<Board> findAll(SqlSession session)
    {
        //db 결과값 BoardService로 반환
        //selectList!
        return session.selectList("board.findAll");
    }

    public static Board findById(SqlSession session,long id) {
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
}
