package zgz.community2.dto;


import zgz.community2.model.User;

public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;
    private User user;

    public CommentDTO(Long id, Long parentId, Integer type, Long commentator, Long gmtCreate, Long gmtModified, Long likeCount, String content, User user) {
        this.id = id;
        this.parentId = parentId;
        this.type = type;
        this.commentator = commentator;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.likeCount = likeCount;
        this.content = content;
        this.user = user;
    }

    public CommentDTO() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getCommentator() {
        return commentator;
    }

    public void setCommentator(Long commentator) {
        this.commentator = commentator;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", type=" + type +
                ", commentator=" + commentator +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", likeCount=" + likeCount +
                ", content='" + content + '\'' +
                ", user=" + user +
                '}';
    }
}
