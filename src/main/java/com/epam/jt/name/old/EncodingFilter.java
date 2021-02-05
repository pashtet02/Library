/*
package com.epam.jt.name.servlets.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EncodingFilter implements Filter {

    private String encoding = "UTF-8";

    public void doFilter(ServletRequest request,
                         ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        req.setCharacterEncoding(encoding);
        res.setCharacterEncoding(encoding);
        System.out.println("ENCODING WORKS " + req.getCharacterEncoding());
        filterChain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) {
        String encodingParam = filterConfig.getInitParameter("encoding");
        if (encodingParam != null) {
            encoding = encodingParam;
        }
    }

    public void destroy() {
        //hello
    }
}*/
