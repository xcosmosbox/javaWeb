package com.javaweb.studyservletcontext;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class ConfigTestServlet extends GenericServlet {
    /**
     * Called by the servlet container to allow the servlet to respond to a
     * request. See {@link Servlet#service}.
     * <p>
     * This method is declared abstract so subclasses, such as
     * <code>HttpServlet</code>, must override it.
     *
     * @param req the <code>ServletRequest</code> object that contains the
     *            client's request
     * @param res the <code>ServletResponse</code> object that will contain the
     *            servlet's response
     * @throws ServletException if an exception occurs that interferes with the servlet's
     *                          normal operation occurred
     * @throws IOException      if an input or output exception occurs
     */
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        // getting ServletConfig obj
        ServletConfig config = this.getServletConfig();

        // output ServletConfig obj
        out.print("ServletConfig obj: " + config.toString());
        out.print("<br>");

        // getting <servlet-name></servlet-name>
        String servletName = config.getServletName();
        out.print("<servlet-name>"+servletName+"</servlet-name>");
        out.print("<br>");

        //java.util.Enumeration<java.lang.String> getInitParameterNames() getting all init params
        Enumeration<String> initParameterNames = config.getInitParameterNames();

        // iter collection
        while (initParameterNames.hasMoreElements()){
            String parameterName = initParameterNames.nextElement();
            out.print(parameterName);
            out.print("<br>");
        }

        String driver = config.getInitParameter("driver");
        out.print(driver);


    }
}
