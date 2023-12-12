package com.sh.mvc.member.model.service;
import com.sh.mvc.common.SqlSessionTemplate;
import com.sh.mvc.member.model.dao.MemberDao;
import com.sh.mvc.member.model.entity.Member;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MemberService {

    private MemberDao memberDao = new MemberDao();

    public Member findById(String id){
        SqlSession session = SqlSessionTemplate.getSqlSession();
        Member member = memberDao.findById(session,id);
        session.close();

        return member;
    }

    public List<Member> findAll() {
        //dql
        SqlSession session = SqlSessionTemplate.getSqlSession();
        List<Member> members = memberDao.findAll(session);
        session.close();

        return members;
    }

    public List<Member> findByName(String name)
    {
        SqlSession session = SqlSessionTemplate.getSqlSession();
        List<Member> members = memberDao.findByName(session,name);
        session.close();
        return members;
    }

    public List<Member> findByGender(String gender)
    {
        SqlSession session = SqlSessionTemplate.getSqlSession();
        List<Member> members = memberDao.findByGender(session,gender);
        session.close();
        return members;
    }

    public int insertMember(Member member) {
        int result = 0;
        SqlSession session = SqlSessionTemplate.getSqlSession();
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

        SqlSession session = SqlSessionTemplate.getSqlSession();
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

        SqlSession session = SqlSessionTemplate.getSqlSession();
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
}
