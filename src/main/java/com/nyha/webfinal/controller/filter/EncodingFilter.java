package com.nyha.webfinal.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Filter for setting the encoding
 *
 * @author Dovlet Jumaev
 * @see Filter
 */
@WebFilter(urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Param")})
public class EncodingFilter implements Filter {
    private static final String INIT_PARAMETER_ENCODING = "encoding";
    private String code;

    public void init(FilterConfig config) throws ServletException {
        code = config.getInitParameter(INIT_PARAMETER_ENCODING);
    }

    public void destroy() {
        code = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String codeRequest = request.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }
        chain.doFilter(request, response);
    }
}
