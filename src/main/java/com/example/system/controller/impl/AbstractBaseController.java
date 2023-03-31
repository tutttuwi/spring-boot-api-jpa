package com.example.system.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * {@code BaseController} 基底コントローラは、<br/>
 * すべてのコントローラのスーパークラスとして定義し、<br/>
 * コントローラ内で使用する共通処理の実装を保持します.
 */
@Slf4j
public class AbstractBaseController {
    
    @Autowired
    protected ApplicationContext applicationContext;

    /**
     * コンテキストを返します.
     *
     * @return
     */
    protected ApplicationContext getApplicationContext() {
        return applicationContext;
    }

}
