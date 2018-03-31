package bk.myapp.showContactsRecview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import bk.myapp.R;

import java.util.List;

/**
 * Created by bk on 27-03-2018 16:57.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
    Context context;
    List<ListItem> listdata;
    LayoutInflater inflater;

    public Adapter(Context context, List<ListItem> listdata) {
        this.context = context;
        this.listdata = listdata;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = inflater.inflate(R.layout.list_item_contacts,parent,false);
        return new Holder(layout);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ListItem cur = listdata.get(position);
        holder.name.setText(cur.name);
        holder.mobile.setText(cur.mobile);
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView name,mobile;
        public Holder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.viewContactsName);
            mobile = (TextView) itemView.findViewById(R.id.viewContactsMobile);
        }
    }
}
