package hoclv.petrolimexht.bottom_fragment.not_yet_rated;


import android.app.Application;
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
import hoclv.petrolimexht.object.CHXD;
import hoclv.petrolimexht.uitls.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotYetRatedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotYetRatedFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private String mParam2;

    private View view;
    private RecyclerView recyclerView;
    private NotYetRatedAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int visibleThreshold = 1;
    private int lastVisibleItem, totalItemCount;
    private boolean loading = false;
    private boolean refreshing = false;
    private int pageIndex;
    private int pageSize = 10;
    private ProgressBar pbLoadMore, pbWaitLoad;
    private TextView tvNodata;

    public NotYetRatedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotYetRatedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotYetRatedFragment newInstance(String param1, String param2) {
        NotYetRatedFragment fragment = new NotYetRatedFragment();
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
        view = inflater.inflate(R.layout.fragment_not_yet_rated, container, false);
        initView();
        getListCHXD();
        return view;
    }

    public void initView() {

        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new NotYetRatedAdapter(getActivity());
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
        setUpLoadMore();
    }

    public void setUpLoadMore() {
//        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (!refreshing) {
//                    totalItemCount = linearLayoutManager.getItemCount();
//                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
//
//                    if (!loading && totalItemCount > 9 && totalItemCount >= pageIndex * pageSize && ((lastVisibleItem + visibleThreshold) >= totalItemCount)) {
//                        loadMore();
//                    }
//                }
//            }
//        });
    }

    public void loadMore() {
//        loading = true;
//        pbLoadMore.setVisibility(View.VISIBLE);
//        getListCHXD();
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
                adapter.add(new CHXD());
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
