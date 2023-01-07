package web.actions;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

//@WebServlet(name = "DeptServlet", value = {"/dept/list", "/dept/save","/dept/edit","/dept/detail","/dept/delete","/dept/modify",})
@WebServlet("/dept/*")
public class DeptServlet extends HttpServlet {
    /**
     * Receives standard HTTP requests from the public
     * <code>service</code> method and dispatches
     * them to the <code>do</code><i>Method</i> methods defined in
     * this class. This method is an HTTP-specific version of the
     * {@link Servlet#service} method. There's no
     * need to override this method.
     *
     * @param request  the {@link HttpServletRequest} object that
     *             contains the request the client made of
     *             the servlet
     * @param response the {@link HttpServletResponse} object that
     *             contains the response the servlet returns
     *             to the client
     * @throws IOException      if an input or output error occurs
     *                          while the servlet is handling the
     *                          HTTP request
     * @throws ServletException if the HTTP request
     *                          cannot be handled
     * @see Servlet#service
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if ("/dept/list".equals(servletPath)){
            doList(request,response);
        } else if ("/dept/save".equals(servletPath)) {
            doSave(request,response);
        } else if ("/dept/edit".equals(servletPath)) {
            doEdit(request,response);
        } else if ("/dept/detail".equals(servletPath)) {
            doDetail(request,response);
        } else if ("/dept/delete".equals(servletPath)) {
            doDel(request,response);
        } else if ("/dept/modify".equals(servletPath)) {
            doModify(request,response);
        }
    }

    private void doModify(HttpServletRequest request, HttpServletResponse response) {

    }

    private void doDel(HttpServletRequest request, HttpServletResponse response) {

    }

    private void doDetail(HttpServletRequest request, HttpServletResponse response) {

    }

    private void doEdit(HttpServletRequest request, HttpServletResponse response) {

    }

    private void doSave(HttpServletRequest request, HttpServletResponse response) {

    }

    private void doList(HttpServletRequest request, HttpServletResponse response) {

    }
}
