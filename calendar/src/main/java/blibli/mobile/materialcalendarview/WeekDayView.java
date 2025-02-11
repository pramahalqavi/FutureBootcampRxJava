package blibli.mobile.materialcalendarview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatTextView;
import blibli.mobile.materialcalendarview.format.WeekDayFormatter;

import java.util.Calendar;

/**
 * Display a day of the week
 */
@Experimental
@SuppressLint("ViewConstructor")
class WeekDayView extends AppCompatTextView {

  private WeekDayFormatter formatter = WeekDayFormatter.DEFAULT;
  private int dayOfWeek;

  public WeekDayView(Context context, int dayOfWeek) {
    super(context);

    setGravity(Gravity.CENTER);

    setTextAlignment(TEXT_ALIGNMENT_CENTER);

    setDayOfWeek(dayOfWeek);
  }

  public void setWeekDayFormatter(WeekDayFormatter formatter) {
    this.formatter = formatter == null ? WeekDayFormatter.DEFAULT : formatter;
    setDayOfWeek(dayOfWeek);
  }

  public void setDayOfWeek(int dayOfWeek) {
    this.dayOfWeek = dayOfWeek;
    setText(formatter.format(dayOfWeek));
  }

  public void setDayOfWeek(Calendar calendar) {
    setDayOfWeek(CalendarUtils.getDayOfWeek(calendar));
  }
}
