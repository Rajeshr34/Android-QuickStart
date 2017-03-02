package info.rajeshr.quickstart.Fragments.Parent;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;


public abstract class ParentDialogFragment extends DialogFragment {

    public int gravity_ = Gravity.BOTTOM;

    protected abstract void inject();

    @CallSuper
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        inject();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setGravity(this.gravity_);
        }
    }
}
