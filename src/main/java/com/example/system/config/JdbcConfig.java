package com.example.system.config;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
@ServletComponentScan(basePackages = "com.example.system") // @WebFilterを認識させるため付与
public class SystemConfig {
    /**
     * メッセージリソース設定. <br/>
     * 必要に応じてリソースファイルを切り替えることで多言語化対応を行う
     *
     * @return messageSource
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("i18n/messages"); // クラスパス上に格納されているプロパティファイル（拡張子は除く）を指定する
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

//    /**
//     * デフォルトのロケール定義.
//     *
//     * @return resolver
//     */
//    public LocaleResolver localeResolver() {
//        CookieLocaleResolver resolver = new CookieLocaleResolver();
//        resolver.setCookieName("locale");
//        resolver.setDefaultLocale(Locale.JAPANESE);
//        return resolver;
//    }
}
