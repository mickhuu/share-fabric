package cn.edu.gxmzu.ai.mickhu.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author mickhu
 * @describe
 * @date 2022-04-14 20:20
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface GrpcService {

    @AliasFor(annotation = Component.class)
    String value() default "";

}
