package com.dwarfeng.scg.sdk.util;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 序列码生成设置粒度字段有效性验证注解。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
@Documented
@Constraint(
        validatedBy = {ValidScgSettingGranularity.InternalConstraintValidator.class}
)
@Target({
        ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
        ElementType.PARAMETER, ElementType.TYPE_USE
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidScgSettingGranularity {

    String message() default "invalid scg setting granularity";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class InternalConstraintValidator implements ConstraintValidator<ValidScgSettingGranularity, Integer> {

        // 执行校验操作
        @Override
        public boolean isValid(Integer value, ConstraintValidatorContext context) {
            try {
                return Constants.scgSettingGranularitySpace().contains(value);
            } catch (Exception e) {
                return false;
            }
        }
    }
}
