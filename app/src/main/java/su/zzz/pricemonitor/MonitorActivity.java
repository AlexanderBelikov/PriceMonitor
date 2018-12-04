package su.zzz.pricemonitor;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MonitorActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return MonitorFragment.newInstance();
    }
}
