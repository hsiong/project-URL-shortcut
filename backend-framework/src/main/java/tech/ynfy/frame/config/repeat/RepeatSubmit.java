package tech.ynfy.frame.config.repeat;

import java.lang.annotation.*;

/**
 * 自定义注解防止表单重复提交
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmit {

    /**
     * 重复提交时间
     * @return
     */
    long time() default 1;
    
}
