package bharat.sos_tarp.addContactsRecview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import bharat.sos_tarp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bk on 29-03-2018 10:50.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
    Context context;
    List<ListItem> listdata;
    LayoutInflater inflater;

    public Adapter(Context context, List<ListItem> listdata) {
        this.context = context;
        this.listdata = listdata;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = inflater.inflate(R.layout.list_item_add_contacts, parent, false);
        return new Holder(layout);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ListItem cur = listdata.get(position);
        holder.name.setText(cur.name);
        holder.mobile.setText(cur.mobile);
        holder.status.setChecked(cur.switchActivated);
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public List<ListItem> getSelectedItems() {
        List<ListItem> list = new ArrayList<>();
        for (ListItem item : listdata) {
            if (item.switchActivated) list.add(item);
        }
        return list;
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, mobile;
        Switch status;
        LinearLayout layout;

        Holder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.addContactsName);
            mobile = (TextView) itemView.findViewById(R.id.addContactsMobile);
            status = (Switch) itemView.findViewById(R.id.addContactsSwitch);
            layout = (LinearLayout) itemView.findViewById(R.id.layout);
            status.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listdata.get(getAdapterPosition()).switchActivated = !listdata.get(getAdapterPosition()).switchActivated;
        }
    }
}
