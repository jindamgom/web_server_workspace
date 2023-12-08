package com.sh.mybatis.member.model.dao;

import com.sh.mybatis.member.model.entity.Member;
import org.apache.ibatis.session.SqlSession;

public class MemberDao {
    public Member findById(SqlSession session, String id) {
        //selectOne[한건]
        //selectList[여러건]
        //SqlSession#selectOne(namespace.id",param)
        //param:? 값
        //실제 쿼리는 mapper에 작성
        return session.selectOne("member.findById",id);
    }
}
