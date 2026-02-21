package org.example.CricInfo.dto;

import java.time.LocalDateTime;

public class Commentary {
    private LocalDateTime time;
    private String comment;
    private String commentator;

    public Commentary(LocalDateTime time, String comment, String commentator) {
        this.time = time;
        this.comment = comment;
        this.commentator = commentator;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getComment() {
        return comment;
    }

    public String getCommentator() {
        return commentator;
    }
}
