package hoclv.petrolimexht.bottom_fragment.not_yet_rated;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hoclv.petrolimexht.object.CHXD;
import hoclv.petrolimexht.R;

/**

 */

public class NotYetRatedAdapter extends RecyclerView.Adapter<NotYetRatedAdapter.CHXDHolder> {

    private Activity context;
    private LayoutInflater layoutInflater;
    private ArrayList<CHXD> lstChxds;

    public NotYetRatedAdapter(Activity context) {
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
        View view = layoutInflater.inflate(R.layout.item_chxd, parent, false);
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
        private TextView tvContent,tvStatus,tvDay,tvMonth,tvYear;
        private CHXD chxd;
        private LinearLayout lnlRootView,lnlMore;


        public CHXDHolder(View itemView) {
            super(itemView);
            lnlRootView =  itemView.findViewById(R.id.lnlRootView);
            lnlMore =  itemView.findViewById(R.id.lnlMore);
        }

        public void bind(CHXD bind){
            if(bind==null){
                return;
            }

            this.chxd =bind;
            int index=lstChxds.indexOf(bind);
            if(index%2==0){
                lnlRootView.setBackgroundResource(R.drawable.bg_item_blue);
            }

            else{
                lnlRootView.setBackgroundResource(R.drawable.bg_item_orange);
            }
            if(chxd.isMark()){
                lnlMore.setVisibility(View.VISIBLE);
            }
            else{
                lnlMore.setVisibility(View.GONE);
            }

        }
    }
}
