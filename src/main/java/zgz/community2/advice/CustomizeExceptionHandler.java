package zgz.community2.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import zgz.community2.dto.ResultDTO;
import zgz.community2.exception.CustomizeErrorCode;
import zgz.community2.exception.CustomizeException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.alibaba.fastjson.JSON;


@ControllerAdvice
public class CustomizeExceptionHandler{

    @ExceptionHandler(Exception.class)
    void handler(Throwable ex, Model model, HttpServletRequest request, HttpServletResponse response) {

        String contentType = request.getContentType();

        if ("application/json".equals(contentType)) {
            ResultDTO resultDTO = null;
            if (ex instanceof CustomizeException) {
                resultDTO = ResultDTO.error((CustomizeException) ex);
            } else {
                resultDTO = ResultDTO.error(CustomizeErrorCode.SYS_ERROR);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException IOE) {
            }
        } else {
            if (ex instanceof CustomizeException) {
                model.addAttribute("error", ex.getMessage());
            } else {
                model.addAttribute("error", CustomizeErrorCode.SYS_ERROR.getMessage());
            }
        }
    }
}
