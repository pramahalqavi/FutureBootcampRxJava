package blibli.mobile.materialcalendarview.format;

import blibli.mobile.materialcalendarview.CalendarUtils;
import blibli.mobile.materialcalendarview.MaterialCalendarView;

/**
 * Supply labels for a given day of the week
 */
public interface WeekDayFormatter {
  /**
   * Convert a given day of the week into a label
   *
   * @param dayOfWeek the day of the week as returned by {@linkplain java.util.Calendar#get(int)}
   *        for {@linkplain java.util.Calendar#DAY_OF_WEEK}
   * @return a label for the day of week
   */
  CharSequence format(int dayOfWeek);

  /**
   * Default implementation used by {@linkplain MaterialCalendarView}
   */
  WeekDayFormatter DEFAULT = new CalendarWeekDayFormatter(CalendarUtils.getInstance());
}
