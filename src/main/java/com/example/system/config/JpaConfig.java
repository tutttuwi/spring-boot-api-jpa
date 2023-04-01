package com.example.system.config;//package com.example.system.config;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * JPA設定.
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
   * データ更新ユーザ情報管理します。<br/>
   * 必要に応じて、HTTPHeaderやSpringSecurity経由で操作ユーザを特定して情報を設定する
   */
  public static class SystemAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
      ServletRequestAttributes requestAttributes =
          (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
      HttpServletRequest request = requestAttributes.getRequest();
      String username = request.getHeader("X-System-UserName"); //TODO: システム個別設定
      if (StringUtils.isEmpty(username)) {
        username = "unknown";
      }
      return Optional.of(username);
    }
  }
}

