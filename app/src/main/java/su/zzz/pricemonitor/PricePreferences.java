package su.zzz.pricemonitor;

import android.content.Context;
import android.preference.PreferenceManager;

public class PricePreferences {
    private static final String PREF_PRICE = "price";

    public static int getPrice(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(PREF_PRICE,0);
    }
    public static void setPrice(Context context, int price){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putInt(PREF_PRICE, price)
                .apply();
    }
}
