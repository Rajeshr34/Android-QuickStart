package info.rajeshr.quickstart.Fragments.Parent;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;

public abstract class ParentFragment extends Fragment {

    protected abstract void inject();

    @CallSuper
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        inject();
    }


}
