package reflec.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by szh on 2017/4/19.
 */
@Target(ElementType.TYPE) // 类
@Retention(RetentionPolicy.RUNTIME)
public @interface Entity {
}
