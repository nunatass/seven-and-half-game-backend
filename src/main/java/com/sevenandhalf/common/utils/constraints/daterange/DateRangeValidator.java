package com.sevenandhalf.common.utils.constraints.daterange;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;
import java.util.Date;

public class DateRangeValidator implements ConstraintValidator<DateRangeValid, Date> {

  DateRangeType rangeType;
  long target;

  @Override
  public void initialize(DateRangeValid constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
    rangeType = constraintAnnotation.rangeType();
    target = constraintAnnotation.target();
  }

  @Override
  public boolean isValid(Date value, ConstraintValidatorContext context) {
    if (value == null) {
      return false;
    }

    Calendar dateInCalendar = Calendar.getInstance();
    dateInCalendar.setTime(value);

    Calendar now = Calendar.getInstance();

    switch (rangeType) {
      case YEAR:
        return now.get(Calendar.YEAR) - dateInCalendar.get(Calendar.YEAR) >= target;
      case MONTH:
        return now.get(Calendar.MONTH) - dateInCalendar.get(Calendar.MONTH) >= target;
      case WEEK:
        return now.get(Calendar.WEEK_OF_YEAR) - dateInCalendar.get(Calendar.WEEK_OF_YEAR) >= target;
      case DAY:
        return now.get(Calendar.DAY_OF_YEAR) - dateInCalendar.get(Calendar.DAY_OF_YEAR) >= target;
      case HOUR:
        return now.get(Calendar.HOUR_OF_DAY) - dateInCalendar.get(Calendar.HOUR_OF_DAY) >= target;
      case MINUTE:
        return now.get(Calendar.MINUTE) - dateInCalendar.get(Calendar.MINUTE) >=target;
      case SECOND:
        return now.get(Calendar.SECOND) - dateInCalendar.get(Calendar.SECOND) >= target;
      default:
        return false;
    }
  }
}
