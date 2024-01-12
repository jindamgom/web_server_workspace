package com.ncs.test.member.model.vo;

import java.time.LocalDateTime;

public class Member
{
    private String memberId;
    private String memberPwd;
    private String memberName;
    private LocalDateTime memberEnrollDate;

    public Member() {
    }

    public Member(String memberId, String memberPwd, String memberName, LocalDateTime memberEnrollDate) {
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.memberName = memberName;
        this.memberEnrollDate = memberEnrollDate;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberPwd() {
        return memberPwd;
    }

    public void setMemberPwd(String memberPwd) {
        this.memberPwd = memberPwd;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public LocalDateTime getMemberEnrollDate() {
        return memberEnrollDate;
    }

    public void setMemberEnrollDate(LocalDateTime memberEnrollDate) {
        this.memberEnrollDate = memberEnrollDate;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId='" + memberId + '\'' +
                ", memberPwd='" + memberPwd + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberEnrollDate=" + memberEnrollDate +
                '}';
    }
}
