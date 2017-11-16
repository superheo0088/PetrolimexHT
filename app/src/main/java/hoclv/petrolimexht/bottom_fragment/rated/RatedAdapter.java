package hoclv.petrolimexht.bottom_fragment.rated;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hoclv.petrolimexht.MainActivity;
import hoclv.petrolimexht.R;
import hoclv.petrolimexht.object.CHXD;
import hoclv.petrolimexht.uitls.Consts;

/**

 */

public class RatedAdapter extends RecyclerView.Adapter<RatedAdapter.CHXDHolder> {

    private Activity context;
    private LayoutInflater layoutInflater;
    private ArrayList<CHXD> lstChxds;

    public RatedAdapter(Activity context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        lstChxds = new ArrayList<>();
    }

    public void addAll(List<CHXD> lst) {
        lstChxds.addAll(lst);
    }

    public void add(CHXD lst) {
        lstChxds.add(lst);
    }

    public void clearAll() {
        if (lstChxds.size() > 0) {
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


    public class CHXDHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvContent, tvStatus, tvDay, tvMonth, tvYear;
        private CHXD chxd;
        private LinearLayout lnlRootView, lnlMore;
        private ImageView imvMore;

        public CHXDHolder(View itemView) {
            super(itemView);
            lnlRootView = itemView.findViewById(R.id.lnlRootView);
            lnlMore = itemView.findViewById(R.id.lnlMore);
            imvMore = itemView.findViewById(R.id.imvMore);

            lnlMore.setOnClickListener(this);
        }

        public void bind(CHXD bind) {
            if (bind == null) {
                return;
            }

            this.chxd = bind;
            int index = lstChxds.indexOf(bind);
            if (index % 2 == 0) {
                lnlRootView.setBackgroundResource(R.drawable.bg_item_blue);
            } else {
                lnlRootView.setBackgroundResource(R.drawable.bg_item_orange);
            }
            if (chxd.isMark()) {
                lnlMore.setVisibility(View.VISIBLE);
            } else {
                lnlMore.setVisibility(View.GONE);
            }

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.lnlMore:
                    PopupMenu popupMenu = new PopupMenu(context, imvMore);
                    popupMenu.getMenuInflater()
                            .inflate(R.menu.menu_send_file_excel, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {
                            Toast.makeText(context, "Đang gửi file", Toast.LENGTH_SHORT).show();
                            return true;
                        }

                    });
                    popupMenu.show();
                    break;
                case R.id.lnlRootView:
//                    Toast.makeText(context, "oke rooot", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }
}
