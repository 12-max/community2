package zgz.community2.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import zgz.community2.exception.CustomizeException;


@ControllerAdvice
public class CustomizeExceptionHandler{

    @ExceptionHandler(Exception.class)
    @ResponseBody
    ModelAndView handler( Throwable ex, Model model){
        if (ex instanceof CustomizeException){
            model.addAttribute("error",ex.getMessage());
        }else {
            model.addAttribute("error","服务出错");
        }
        return new ModelAndView("error");
    }

}
