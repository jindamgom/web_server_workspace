package com.sh.mvc.board.model.entity;

import java.time.LocalDateTime;

public class attachment
{
    private long id;
    private int boardId;
    private String originalFilename;
    private String renamedFilename;
    private LocalDateTime regDate;

    //alt+insert

    public attachment() {
    }

    public attachment(long id, int boardId, String originalFilename, String renamedFilename, LocalDateTime regDate) {
        this.id = id;
        this.boardId = boardId;
        this.originalFilename = originalFilename;
        this.renamedFilename = renamedFilename;
        this.regDate = regDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public String getRenamedFilename() {
        return renamedFilename;
    }

    public void setRenamedFilename(String renamedFilename) {
        this.renamedFilename = renamedFilename;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }
}
