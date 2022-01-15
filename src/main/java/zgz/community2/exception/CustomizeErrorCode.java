package zgz.community2.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{

    QUESTION_NOT_FOUND(202,"你找到问题不在了要不要换个试试"),
    //
    TARGET_PARAM_NOT_FOUND(203,"未选中任何问题或评论进行回复"),
    NO_LOGIN(201,"用户未登录请先登录"),
    SYS_ERROR(204,"服务出错"),
    TYPE_PARAM_WRONG(205,"评论类型错误"),
    COMMENT_NOT_FOUNT(206,"回复的评论不存在了，要不要换个试试？");



    @Override
    public String getMessage(){
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private Integer code;
    private String message;


    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }
}
