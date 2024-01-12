package com.ncs.test.member.model.service;

import com.ncs.test.member.model.dao.MemberDao;
import com.ncs.test.member.model.vo.Member;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MemberService
{
    private MemberDao memberDao = new MemberDao();
    private static SqlSessionFactory factory;

    static{
        String resource = "mybatis-config.xml";
        //src/main/resources/mybatis-config.xml

        try(InputStream is = Resources.getResourceAsStream(resource)) {
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            factory = builder.build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static SqlSession getSqlSession()
    {
        return factory.openSession(false);//auto-commit false
    }

    public Member loginMember(Member member)
    {
        SqlSession session = getSqlSession();
        Member memberResult = memberDao.loginMember(session,member);
        session.close();//사용후 닫기.
        return memberResult;
    }
}
