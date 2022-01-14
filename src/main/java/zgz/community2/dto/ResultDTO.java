package zgz.community2.dto;



public class ResultDTO {

    private Integer code;
    private String message;



    public static ResultDTO error(Integer code,String message){
        ResultDTO resultDTO = new ResultDTO(code,message);
        return resultDTO;
    }





    public ResultDTO() {
    }

    public ResultDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
