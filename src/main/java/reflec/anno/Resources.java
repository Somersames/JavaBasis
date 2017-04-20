package reflec.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by szh on 2017/4/19.
 */
@Target(ElementType.FIELD) //描述方法的
@Retention(RetentionPolicy.RUNTIME) // 仅运行时保留
public @interface Resources {

}
