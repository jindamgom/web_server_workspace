package com.sh.mvc.board.model.vo;


import com.sh.mvc.board.model.entity.Attachment;
import com.sh.mvc.board.model.entity.Board;
import com.sh.mvc.board.model.entity.BoardComment;
import com.sh.mvc.member.model.entity.Member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * vo 클래스 value object
 * - immutable한 value객체(수정하지않는,불변)
 * - entity 클래스를 확장한 객체
 * - 기존 entity는 수정하지말고 상속하여 확장/사용
 */
public class BoardVo extends Board {

    //1221 게시글마다 상이한 리플 갯수 보여주기
    private int commentCount;

    //보드를 상속하고, 원래 보드에 없는 첨부파일 갯수 필드를 하나 추가함
    private int attachCount;
    private List<Attachment> attachments = new ArrayList<>();

    private List<Long> delFiles = new ArrayList<>();

    //커맨츠 자리 추가

    private List<BoardComment> comments;

    public List<Long> getDelFiles() {
        return delFiles;
    }

    public void setDelFiles(List<Long> delFiles) {
        this.delFiles = delFiles;
    }

    //test 보드에서 사용자 이름값도 추가해보기..
    private String userName;

    //Member 클래스 사용하는 방법도있음...
    private Member member;


    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }


    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAttachCount() {
        return attachCount;
    }

    public void setAttachCount(int attachCount) {
        this.attachCount = attachCount;
    }

    public void addAttachment(Attachment attachment)
    {
        this.attachments.add(attachment); //누적
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public List<BoardComment> getComments() {
        return comments;
    }

    public void setComments(List<BoardComment> comments) {
        this.comments = comments;
    }

    //1221 기존 board에 있던 setValue 메소드를 vo클래스로 옮김
    public void setValue(String name, String value)
    {
        switch(name)
        {

//            case "id" :this.id = Long.parseLong(value); break;
//            case "title":this.title = value; break;
//            case "memberId":this.memberId = value; break;
//            case "content":this.content = value; break;
//            //1221 첨부파일 삭제 관련 기능 추가 delFile은 리스트
//            case "delFile":this.delFiles.add(Long.parseLong(value)); break;
//            default:throw new RuntimeException("부적절한 name값:"+name);

            case "id" : setId(Long.parseLong(value)); break;
            case "title": setTitle(value); break;
            case "memberId": setMemberId(value); break;
            case "content": setContent(value); break;
            //1221 첨부파일 삭제 관련 기능 추가 delFile은 리스트
            case "delFile" : this.delFiles.add(Long.parseLong(value)); break;
            default:throw new RuntimeException("부적절한 name값:"+name);
        }
    }


    @Override
    public String toString() {
        return "BoardVo{" +
                "attachCount=" + attachCount +
                ", attachments=" + attachments +
                ", delFiles=" + delFiles +
                ", member=" + member +
                ", comments=" + comments +
                "} " + super.toString();
    }
}
