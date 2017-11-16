package hoclv.petrolimexht;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.w3c.dom.DocumentFragment;

import hoclv.petrolimexht.bottom_fragment.not_yet_rated.NotYetRatedFragment;
import hoclv.petrolimexht.bottom_fragment.rated.RatedFragment;
import hoclv.petrolimexht.bottom_fragment.statistic.StatisticFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewGroup viewGroup;
    private String[] listTitle;
    private int[] listResId;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private MainPagerAdapter mainPagerAdapter;
    private LinearLayout lnlBottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView() {
        listTitle = new String[]{getResources().getString(R.string.chua_danh_gia), getResources().getString(R.string.da_danh_gia), getResources().getString(R.string.thong_ke)};
        listResId = new int[]{R.drawable.ic_not_yet_rated_selector, R.drawable.ic_rated_selector, R.drawable.ic_statistic_selector, };

        lnlBottomNavigation= findViewById(R.id.lnlBottomNavigation);

        viewGroup =  findViewById(android.R.id.content);

        setUpTabView();

    }
    public void setUpTabView() {
        tabLayout =  findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), this);
        mainPagerAdapter.addFragment(NotYetRatedFragment.newInstance("", ""));
        mainPagerAdapter.addFragment(RatedFragment.newInstance("", ""));
        mainPagerAdapter.addFragment(StatisticFragment.newInstance("", ""));
        viewPager.setAdapter(mainPagerAdapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(mainPagerAdapter.setTabView(i, listTitle, listResId));
        }
        viewPager.setCurrentItem(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

    }
}
