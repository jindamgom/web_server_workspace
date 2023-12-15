package com.sh.mvc.member.model.service;
import com.sh.mvc.common.SqlSessionTemplate;
import com.sh.mvc.member.model.dao.MemberDao;
import com.sh.mvc.member.model.entity.Member;
import com.sh.mvc.member.model.entity.Role;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

import static com.sh.mvc.common.SqlSessionTemplate.getSqlSession;

public class MemberService {

    private MemberDao memberDao = new MemberDao();

    public Member findById(String id){
        SqlSession session = getSqlSession();
        Member member = memberDao.findById(session,id);
        session.close();

        return member;
    }

    public List<Member> findAll() {
        //dql
        SqlSession session = getSqlSession();
        List<Member> members = memberDao.findAll(session);
        session.close();

        return members;
    }

    public List<Member> findByName(String name)
    {
        SqlSession session = getSqlSession();
        List<Member> members = memberDao.findByName(session,name);
        session.close();
        return members;
    }

    public List<Member> findByGender(String gender)
    {
        SqlSession session = getSqlSession();
        List<Member> members = memberDao.findByGender(session,gender);
        session.close();
        return members;
    }

    public int insertMember(Member member) {
        int result = 0;
        SqlSession session = getSqlSession();
        //dml 처리
        try
        {
            result = memberDao.insertMember(session,member);
            session.commit();
        }
        catch(Exception e)
        {
            session.rollback();
            throw e; //컨트롤러로 오류 던지기
        }
        finally {
            session.close();
        }

        return result;
    }

    public int updateMember(Member member)
    {
        int result=0;

        SqlSession session = getSqlSession();
        //dml 처리
        try
        {
            result = memberDao.updateMember(session,member);
            session.commit();
        }
        catch(Exception e)
        {
            session.rollback();
            throw e; //컨트롤러로 오류 던지기
        }
        finally {
            session.close();
        }

        return result;
    }

    public int deleteMember(String id)
    {
        int result=0;

        SqlSession session = getSqlSession();
        try
        {
            result = memberDao.deleteMember(session,id);
            session.commit();
        }
        catch(Exception e)
        {

            session.rollback();
            throw e;
        }
        finally
        {
            session.commit();
        }

        return result;
    }


    public int updateMemberRole(Member member)
    {
        int result=0;

        SqlSession session = getSqlSession();
        //dml 처리
        try
        {
            result = memberDao.updateMember(session,member);
            session.commit();
        }
        catch(Exception e)
        {
            session.rollback();
            throw e; //컨트롤러로 오류 던지기
        }
        finally {
            session.close();
        }

        return result;
    }

    public List<Member> searchMember(Map<String,Object> param)
    {
        SqlSession session = getSqlSession();
        List<Member> member = memberDao.searchMember(session,param);
        session.close();

        return member; //조회시 null일 수도있기 때문

    }

    public List<Member> findAll(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        List<Member> members = memberDao.findAll(session,param);
        session.close();

        return members;
    }

    public int getToTalCount()
    {
        SqlSession session = getSqlSession();
        int totalCount = memberDao.getToTalCount(session);
        session.close();
        return totalCount;
    }

    //검색용으로 메소드 하나 더 만든다...
    public int getToTalCount(Map<String,Object> param)
    {
        SqlSession session = getSqlSession();
        int totalCount = memberDao.getToTalCount(session,param);
        session.close();
        return totalCount;
    }
}
