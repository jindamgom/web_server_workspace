package com.sh.ajax.celeb.model.dao;

import com.sh.ajax.celeb.model.entity.Celeb;
import com.sh.ajax.student.model.entity.Student;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CelebDao
{

    //celeb 전체 조회
    public List<Celeb> findByAll(SqlSession session) {
        return session.selectList("celeb.findByAll");
    }

    //한건 조회 pk
    public Celeb findById(SqlSession session, long id) {
        return session.selectOne("celeb.findById",id);
    }

    //등록
    public int insertCeleb(SqlSession session, Celeb celeb) {
        return session.insert("celeb.insertCeleb",celeb);
    }

    //수정
    public int updateCeleb(SqlSession session, Celeb celeb) {
        return session.update("celeb.updateCeleb",celeb);
    }

    //삭제
    public int deleteCeleb(SqlSession session, long id) {
        return session.delete("celeb.deleteCeleb",id);
    }
}
