package com.example.system.config;//package com.example.system.config;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

/**
 * Validation用プロパティとメッセージプロパティを統合.
 */
@Configuration
@EnableJpaAuditing
public class JpaConfig {

    /**
     * 監査用リスナー登録
     *
     * @return
     */
    @Bean
    public AuditorAware<String> auditorAware() {
        return new SystemAuditorAware();
    }

    /**
     * 監査用ユーザ設定クラス.<br/>
     */
    public static class SystemAuditorAware implements AuditorAware<String> {
        @Override
        public Optional<String> getCurrentAuditor() {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            String username = request.getHeader("X-UserName");
            if (StringUtils.isEmpty(username)) {
                username = "unknown";
            }
            return Optional.of(username);
        }
    }
}

