package blibli.mobile.materialcalendarview;

/**
 * Use math to calculate first days of months by position from a minimum date.
 */
interface DateRangeIndex {

  int getCount();

  int indexOf(CalendarDay day);

  CalendarDay getItem(int position);
}
