package com.sh.mvc.notification.model.service;

import com.sh.mvc.notification.model.entity.Notification;
import com.sh.mvc.notification.model.entity.Type;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.w3c.dom.Notation;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NotificationServiceTest {

    private NotificationService notificationService = new NotificationService();

    @BeforeEach
    void setUp() {
        this.notificationService = new NotificationService();
    }
    @Disabled
    @DisplayName("한 행의 알림 데이터를 기록한다.")
    @ParameterizedTest
    @CsvSource({
            "rhgPwls,NEW_COMMENT,인간사료 게시글에 댓글이 달렸습니다,false",
            "abcde,NEW_COMMENT,게임추천좀 게시글에 댓글이 달렸습니다,true"
            })
    void test1(String memberId, Type type, String content,boolean checked)
    {
        //given
        assertThat(memberId).isNotNull();
        assertThat(type).isNotNull();
        assertThat(content).isNotNull();
        assertThat(checked).isNotNull();

        //when
        Notification n1 = new Notification();
        n1.setMemberId(memberId);
        n1.setType(type);
        n1.setContent(content);
        n1.setChecked(checked);

        int result = notificationService.insertNotification(n1);
        //then
        assertThat(result).isGreaterThan(0);

    }

    @DisplayName("특정회원의 확인안한 알림내역 조회")
    @ParameterizedTest
    @ValueSource(strings = {"rhgPwls","abcde"})
    void test2(String memberId)
    {
        //given
        assertThat(memberId).isNotNull();
        //when
        List<Notification> notifications = notificationService.findByMemberId(memberId);
        System.out.println(notifications);
        //then
        assertThat(notifications)
                .isNotNull()
                .allSatisfy((noti)->{
                    assertThat(noti.getId()).isNotNull().isNotZero();
                    assertThat(noti.getMemberId()).isEqualTo(memberId);
                    assertThat(noti.getType()).isNotNull();
                    assertThat(noti.getContent()).isNotNull();
                    assertThat(noti.isChecked()).isFalse();//확인안한 알림
                    assertThat(noti.getRegDate()).isNotNull();
                });
    }
}