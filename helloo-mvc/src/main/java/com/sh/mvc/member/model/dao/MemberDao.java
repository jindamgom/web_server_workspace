package com.sh.mvc.member.model.dao;

import com.sh.mvc.member.model.entity.Member;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class MemberDao {
    public Member findById(SqlSession session, String id) {
        //selectOne[한건]
        //selectList[여러건]
        //SqlSession#selectOne(namespace.id",param)
        //param:? 값
        //실제 쿼리는 mapper에 작성
        return session.selectOne("member.findById",id);
    }

    public List<Member> findAll(SqlSession session) {
        //member-mapper에 신규 쿼리를 작성한다.
        return session.selectList("member.findAll");
    }

    public List<Member> findByName(SqlSession session,String name)
    {
        return session.selectList("member.findByName",name);
    }

    public List<Member> findByGender(SqlSession session, String gender) {
        return session.selectList("member.findByGender",gender);
    }

    public int insertMember(SqlSession session, Member member) {

        return session.insert("member.insertMember",member);
    }

    public int updateMember(SqlSession session, Member member)
    {
        return session.update("member.updateMember",member);
    }

    public int deleteMember(SqlSession session, String id)
    {
        System.out.println("MemberDao-deleteMember id:"+id);
        return session.delete("member.deleteMember",id);
    }

    public List<Member> searchMember(SqlSession session, Map<String,Object> param)
    {
        int page = (int)param.get("page");
        int limit = (int)param.get("limit");
        int offset = (page-1)*limit;
        RowBounds rb = new RowBounds(offset,limit);
//        System.out.println("MemberDao-searchMember param:"+param);
        return session.selectList("member.searchMember",param,rb);
    }

    /**
     * limit = 10 일때
     * -page = 1 , offset : 0  =>1~10
     * -page = 2 , offset : 10 =>11~20
     * -page = 3 , offset : 20 =>21~30
     * @param session
     * @param param
     * @return
     */
    public List<Member> findAll(SqlSession session, Map<String, Object> param) {

        int page = (int)param.get("page");
        int limit = (int)param.get("limit");
        //건너 뛸 회원수
        int offset = (page-1) * limit;
        RowBounds rowBounds = new RowBounds(offset,limit);

        //쿼리에 전달할 값 null(어차피 전체 조회라), 그담에 rowBounds
        return session.selectList("member.findAllPage",param, rowBounds);

    }
    
    //selectList, selectOne = 행의 수 차이
    public int getToTalCount(SqlSession session)
    {

        return session.selectOne("member.getTotalCount");
    }


    //페이징용으로 하나 더 만든 메소드 2->1로 수정
    public int getToTalCount(SqlSession session, Map<String, Object> param)
    {
        return session.selectOne("member.getTotalCount",param);
    }
}
