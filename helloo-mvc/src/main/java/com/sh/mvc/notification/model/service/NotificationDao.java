package com.sh.mvc.notification.model.service;

import com.sh.mvc.notification.model.entity.Notification;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class NotificationDao
{
    public int insertNotification(SqlSession session, Notification n1)
    {
        return session.insert("notification.insertNotification",n1);
    }

    public List<Notification> findByMemberId(SqlSession session, String memberId) {
        return session.selectList("notification.findByMemberId",memberId);
    }
}
