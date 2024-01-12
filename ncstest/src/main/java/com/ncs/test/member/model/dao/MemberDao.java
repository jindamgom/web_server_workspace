package com.ncs.test.member.model.dao;

import com.ncs.test.member.model.vo.Member;
import org.apache.ibatis.session.SqlSession;

public class MemberDao {
    public Member loginMember(SqlSession session, Member member)
    {
        return session.selectOne("member.loginMember",member);
    }
}
