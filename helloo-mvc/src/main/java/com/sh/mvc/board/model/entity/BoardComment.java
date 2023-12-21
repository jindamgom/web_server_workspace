package com.sh.mvc.board.model.entity;


import java.time.LocalDateTime;

//1221 board_comment와 1대1로 매칭되는 클래스
public class BoardComment
{
    //Long:0 Long:null
    
    private Long id;
    private Long boardId;
    private String memberId;
    private String content;
    private Integer commentLevel;
    private Long parentCommentId;
    private LocalDateTime regDate;

    public BoardComment(Long id, Long boardId, String memberId, String content, Integer commentLevel, Long parentCommentId, LocalDateTime regDate) {
        this.id = id;
        this.boardId = boardId;
        this.memberId = memberId;
        this.content = content;
        this.commentLevel = commentLevel;
        this.parentCommentId = parentCommentId;
        this.regDate = regDate;
    }

    public BoardComment() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCommentLevel() {
        return commentLevel;
    }

    public void setCommentLevel(Integer commentLevel) {
        this.commentLevel = commentLevel;
    }

    public Long getPraentCommentId() {
        return parentCommentId;
    }

    public void setPraentCommentId(Long parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "BoardComment{" +
                "id=" + id +
                ", boardId=" + boardId +
                ", memberId='" + memberId + '\'' +
                ", content='" + content + '\'' +
                ", commentLevel=" + commentLevel +
                ", parentCommentId=" + parentCommentId +
                ", regDate=" + regDate +
                '}';
    }
}
