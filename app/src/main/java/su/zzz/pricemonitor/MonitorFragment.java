package su.zzz.pricemonitor;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MonitorFragment extends Fragment {
    private static final String TAG = MonitorFragment.class.getSimpleName();
    private TextView viewPrice;

    public static MonitorFragment newInstance(){
        return new MonitorFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_monitor, container, false);
        viewPrice = v.findViewById(R.id.view_price);
        new FetchPriceTask().execute();
        return v;
    }

    private void updateUI(){
        int price = PricePreferences.getPrice(getActivity());
        viewPrice.setText(String.valueOf(price));
    }

    private class FetchPriceTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            int new_price = new PriceFetcher().fetchPrice();
            PricePreferences.setPrice(getActivity(), new_price);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            updateUI();
        }
    }

}
