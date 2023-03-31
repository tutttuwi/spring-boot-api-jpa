package com.example.system.filter;

import com.example.system.constant.MdcConst;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.MDC;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * MDCフィルター.<br/>
 * ログ出力用情報をMDCで抽出
 */
@WebFilter
@Slf4j
public class MdcFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // MDCログ設定
        MDC.put(MdcConst.X_TRACK_ID.KEY, request.getHeader(MdcConst.X_TRACK_ID.KEY)); // クライアント側から送信されたトラックID
        MDC.put(MdcConst.X_SESSION_ID.KEY, request.getHeader(MdcConst.X_SESSION_ID.KEY)); // クライアント側から送信されたセッションID
        MDC.put(MdcConst.X_REQUEST_ID.KEY, RandomStringUtils.randomAlphanumeric(16)); // リクエストID自動生成

        // フィルターチェーン
        ServletRequestAttributes attributes = new ServletRequestAttributes(request, response);
        try {
            filterChain.doFilter(request, response);
        } finally {
            if (logger.isTraceEnabled()) {
                logger.trace("Cleared thread-bound request context: " + request);
            }
            attributes.requestCompleted();
        }
    }

    @Override
    public void destroy() {
    }

}