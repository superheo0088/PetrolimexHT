package hoclv.petrolimexht;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by SuperHeo on 07/09/2017.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> listFragment;
    private Context context;

    public MainPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        listFragment = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }
    public void addFragment(Fragment fragment) {
        listFragment.add(fragment);
    }

    @Override
    public int getCount() {
        if (!listFragment.isEmpty()) {
            return listFragment.size();
        } else {
            return 0;
        }
    }
    public View setTabView(int position, String[] listTitle, int[] resId) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_tablayout_main, null);
        TextView tvTitle =  v.findViewById(R.id.tvTitle);
        ImageView imvIcon =  v.findViewById(R.id.imvIcon);
        tvTitle.setText(listTitle[position]);
        imvIcon.setImageResource(resId[position]);
        return v;
    }
}
