package com.javaweb.httpStudy;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

public class PostServlet extends GenericServlet {
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
        
    }
}
