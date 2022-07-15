package com.sevenandhalf.common.utils.constraints.daterange;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = DateRangeValidator.class)
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Documented
public @interface DateRangeValid {

  String message() default "Invalid date range";
  DateRangeType rangeType() default DateRangeType.SECOND;
  long target() default 1;
  Class <?> [] groups() default {};
  Class <? extends Payload> [] payload() default {};
}
