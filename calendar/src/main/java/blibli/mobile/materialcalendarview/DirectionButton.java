package blibli.mobile.materialcalendarview;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.TypedValue;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * An {@linkplain android.widget.ImageView} to pragmatically set the color of arrows using a
 * {@linkplain android.graphics.ColorFilter}
 */
class DirectionButton extends AppCompatImageView {

  public DirectionButton(Context context) {
    super(context);

    setBackgroundResource(getThemeSelectableBackgroundId(context));
  }

  public void setColor(int color) {
    setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
  }

  @Override
  public void setEnabled(boolean enabled) {
    super.setEnabled(enabled);
    setAlpha(enabled ? 1f : 0.1f);
  }

  private static int getThemeSelectableBackgroundId(Context context) {
    // Get selectableItemBackgroundBorderless defined for AppCompat
    int colorAttr = context.getResources().getIdentifier("selectableItemBackgroundBorderless",
        "attr", context.getPackageName());

    if (colorAttr == 0) {
      colorAttr = android.R.attr.selectableItemBackgroundBorderless;
    }

    TypedValue outValue = new TypedValue();
    context.getTheme().resolveAttribute(colorAttr, outValue, true);
    return outValue.resourceId;
  }
}
