package zgz.community2.dto;


import zgz.community2.exception.CustomizeErrorCode;
import zgz.community2.exception.CustomizeException;

public class ResultDTO {

    private Integer code;
    private String message;



    public static ResultDTO error(Integer code,String message){
        ResultDTO resultDTO = new ResultDTO(code,message);
        return resultDTO;
    }

    public static ResultDTO ok(){
        ResultDTO resultDTO = new ResultDTO(200,"请求成功");
        return resultDTO;
    }





    public ResultDTO() {
    }

    public ResultDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResultDTO error(CustomizeErrorCode noLogin) {
        return error(noLogin.getCode(),noLogin.getMessage());
    }

    public static ResultDTO error(CustomizeException e) {
        return ResultDTO.error(e.getCode(), e.getMessage());
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
