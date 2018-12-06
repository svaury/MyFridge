// Generated code from Butter Knife. Do not modify!
package sophie.chronos;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view7f070080;

  private View view7f070060;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    target.chronometerTv = Utils.findRequiredViewAsType(source, R.id.chronometerTv, "field 'chronometerTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.startStopButton, "field 'startStopButton' and method 'startStopButton'");
    target.startStopButton = Utils.castView(view, R.id.startStopButton, "field 'startStopButton'", Button.class);
    view7f070080 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.startStopButton();
      }
    });
    target.delayEditText = Utils.findRequiredViewAsType(source, R.id.delayEditText, "field 'delayEditText'", EditText.class);
    view = Utils.findRequiredView(source, R.id.reset, "method 'reset'");
    view7f070060 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.reset();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.chronometerTv = null;
    target.startStopButton = null;
    target.delayEditText = null;

    view7f070080.setOnClickListener(null);
    view7f070080 = null;
    view7f070060.setOnClickListener(null);
    view7f070060 = null;
  }
}
