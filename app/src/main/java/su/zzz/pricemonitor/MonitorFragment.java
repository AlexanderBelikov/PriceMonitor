package su.zzz.pricemonitor;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MonitorFragment extends Fragment {
    private static final String TAG = MonitorFragment.class.getSimpleName();
    private TextView viewPrice;

    public static MonitorFragment newInstance(){
        return new MonitorFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_monitor, container, false);
        Toolbar toolbar = v.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        viewPrice = v.findViewById(R.id.view_price);
//        new FetchPriceTask().execute();
        updateUI();
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_monitor, menu);

        MenuItem itemAutoUpdatePrice = menu.findItem(R.id.auto_update_price);
        itemAutoUpdatePrice.setActionView(R.layout.menu_item_switch);
        final Switch sw = menu.findItem(R.id.auto_update_price).getActionView().findViewById(R.id.item_switch);
        sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "test", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.update_price:
                new FetchPriceTask().execute();
            default:
                return super.onOptionsItemSelected(item);
        }
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
