package com.sopiyan.uptd.config.security;



import org.apache.commons.lang.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Sopiyan on 01/02/2017.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE) // needs to come first
public class UptdCorsFilter extends OncePerRequestFilter{
    private String[] allowedMethods = {"GET", "HEAD", "POST", "PUT", "DELETE", "TRACE", "OPTIONS", "PATCH"};

    /**
     * Request headers to be allowed, e.g. content-type,accept,origin,x-requested-with,x-xsrf-token,...
     */
    private String[] allowedHeaders = {
            "Accept",
            "Accept-Encoding",
            "Accept-Language",
            "Cache-Control",
            "Connection",
            "Content-Length",
            "Content-Type",
            "Cookie",
            "Host",
            "Origin",
            "Pragma",
            "Referer",
            "User-Agent",
            "x-requested-with",
            "authorization",
            "X-XSRF-TOKEN"};

    /**
     * Response headers that you want to expose to the client JavaScript programmer, e.g. "X-XSRF-TOKEN".
     * I don't think we need to mention here the headers that we don't want to access through JavaScript.
     * Still, by default, we have provided most of the common headers.
     *
     * <br>
     * See <a href="http://stackoverflow.com/questions/25673089/why-is-access-control-expose-headers-needed#answer-25673446">
     * here</a> to know why this could be needed.
     */
    private String[] exposedHeaders = {
            "Cache-Control",
            "Connection",
            "Content-Type",
            "Date",
            "Expires",
            "Pragma",
            "Server",
            "Set-Cookie",
            "Transfer-Encoding",
            "X-Content-Type-Options",
            "X-XSS-Protection",
            "X-Frame-Options",
            "X-Application-Context",
            "X-XSRF-TOKEN"};
    private long maxAge = 3600L;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        // origin as provided by the browser
        String origin = httpServletRequest.getHeader("Origin");

        // "*" is neither recommended, nor does it work
        // when $httpProvider.defaults.withCredentials = true;
        httpServletResponse.setHeader("Access-Control-Allow-Origin","*"); // use it, or else, return the application url

        httpServletResponse.setHeader("Access-Control-Allow-Methods",
                StringUtils.join(allowedMethods, ","));

        // allow headers
        httpServletResponse.setHeader("Access-Control-Allow-Headers",
                StringUtils.join(allowedHeaders, ","));

        // See http://stackoverflow.com/questions/25673089/why-is-access-control-expose-headers-needed#answer-25673446
        httpServletResponse.setHeader("Access-Control-Expose-Headers",
                StringUtils.join(exposedHeaders, ","));

        // max age
        httpServletResponse.setHeader("Access-Control-Max-Age",
                Long.toString(maxAge));


        // needed when $httpProvider.defaults.withCredentials = true;
        //httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");

        // Don't let OPTIONs pass.
        // Otherwise certain things like Spring Security
        // don't behave properly sometimes.
        // E.g., the SwitchUserFilter doesn't work.
        if (!httpServletRequest.getMethod().equals("OPTIONS"))
            filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
