package com.yws.security.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;
import sun.plugin2.message.Message;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author weisen.yang@hand-china.com
 * @Date 2017/11/23 12:13
 * @name
 * @description
 */
public class RequestAccessDeniedHandler implements AccessDeniedHandler {

    private String errorPage;
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        request.setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        RequestDispatcher dispatcher = request.getRequestDispatcher(errorPage);
        dispatcher.forward(request, response);

//        boolean isAjax = ControllerTools.isAjaxRequest(request);
//        if(isAjax){
//            Message msg = MessageManager.exception(accessDeniedException);
//            ControllerTools.print(response, msg);
//        }else if (!response.isCommitted()) {
//            if (errorPage != null) {
//                // Put exception into request scope (perhaps of use to a view)
//                request.setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException);
//
//                // Set the 403 status code.
//                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//
//                // forward to error page.
//                RequestDispatcher dispatcher = request.getRequestDispatcher(errorPage);
//                dispatcher.forward(request, response);
//            } else {
//                response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
//            }
//        }
    }


    public void setErrorPage(String errorPage) {
        if ((errorPage != null) && !errorPage.startsWith("/")) {
            throw new IllegalArgumentException("errorPage must begin with '/'");
        }

        this.errorPage = errorPage;
    }
}
