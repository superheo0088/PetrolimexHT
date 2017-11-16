package hoclv.petrolimexht.bottom_fragment.statistic;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import hoclv.petrolimexht.R;
import hoclv.petrolimexht.bottom_fragment.rated.RatedAdapter;
import hoclv.petrolimexht.object.CHXD;
import hoclv.petrolimexht.uitls.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatisticFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatisticFragment extends Fragment   implements SwipeRefreshLayout.OnRefreshListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;
    private RecyclerView recyclerView;
    private StatisticAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int visibleThreshold = 1;
    private int lastVisibleItem, totalItemCount;
    private boolean loading = false;
    private boolean refreshing = false;
    private int pageIndex;
    private int pageSize = 10;
    private ProgressBar pbLoadMore, pbWaitLoad;
    private TextView tvNodata;


    public StatisticFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatisticFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatisticFragment newInstance(String param1, String param2) {
        StatisticFragment fragment = new StatisticFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_statistic, container, false);
        initView();
        getListCHXD();
        return view;
    }

    public void initView() {

        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new StatisticAdapter(getActivity());
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setEnabled(true);
        swipeRefreshLayout.setOnRefreshListener(this);

        pbLoadMore = view.findViewById(R.id.pbLoadMore);
        pbWaitLoad = view.findViewById(R.id.pbWaitLoad);
        tvNodata = view.findViewById(R.id.tvNodata);

        pbWaitLoad.setVisibility(View.VISIBLE);
        pbLoadMore.setVisibility(View.GONE);
        tvNodata.setVisibility(View.GONE);
//        setUpLoadMore();
    }

    private void getListCHXD() {
        if (!Utils.isNetworkAvailable(getActivity())) {
            swipeRefreshLayout.setRefreshing(false);
            refreshing = false;
            pbWaitLoad.setVisibility(View.GONE);
            pbLoadMore.setVisibility(View.GONE);
            loading = false;
            Utils.showSnackBar(getActivity(), view, getResources().getString(R.string.err_no_internet_connection));
            return;
        }
        else{
            for (int i = 0;i<30;i++){
                CHXD chxd = new CHXD();
                chxd.setMark(true);
                adapter.add(chxd);
            }
            swipeRefreshLayout.setEnabled(true);
            refreshing = false;
            swipeRefreshLayout.setRefreshing(false);
            loading = false;
            pbWaitLoad.setVisibility(View.GONE);
        }

    }

    @Override
    public void onRefresh() {
        refreshing = true;
        pbLoadMore.setVisibility(View.GONE);
        loading = false;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getListCHXD();
            }
        }, 2000);
    }
}
