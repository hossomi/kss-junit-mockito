package hossomi.kss.junitmockito.filters;

import hossomi.kss.junitmockito.Server;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LogFilter extends OncePerRequestFilter {

    private Logger log;

    public LogFilter() {
        this.log = LoggerFactory.getLogger(Server.class);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("<< {} {}", request.getMethod(), request.getRequestURI());
        filterChain.doFilter(request, response);
        log.info(">> [{}] {} {}", response.getStatus(), request.getMethod(), request.getRequestURI());
    }
}
