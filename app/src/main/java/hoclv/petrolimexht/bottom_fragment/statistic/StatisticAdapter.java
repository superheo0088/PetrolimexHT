package hoclv.petrolimexht.bottom_fragment.statistic;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hoclv.petrolimexht.R;
import hoclv.petrolimexht.object.CHXD;

/**

 */

public class StatisticAdapter extends RecyclerView.Adapter<StatisticAdapter.CHXDHolder> {

    private Activity context;
    private LayoutInflater layoutInflater;
    private ArrayList<CHXD> lstChxds;

    public StatisticAdapter(Activity context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        lstChxds= new ArrayList<>();
    }

    public void addAll(List<CHXD> lst){
        lstChxds.addAll(lst);
    }

    public void add(CHXD lst){
        lstChxds.add(lst);
    }

    public void clearAll(){
        if(lstChxds.size()>0){
            lstChxds.clear();
        }
    }


    @Override
    public CHXDHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_statistic, parent, false);
        return new CHXDHolder(view);
    }

    @Override
    public void onBindViewHolder(final CHXDHolder holder, int position) {
        holder.bind(lstChxds.get(position));
    }

    @Override
    public int getItemCount() {
        return lstChxds.size();
    }


    public class CHXDHolder extends RecyclerView.ViewHolder {
        private CHXD chxd;

        public CHXDHolder(View itemView) {
            super(itemView);
        }

        public void bind(CHXD bind){
            if(bind==null){
                return;
            }

            this.chxd =bind;
        }
    }
}
