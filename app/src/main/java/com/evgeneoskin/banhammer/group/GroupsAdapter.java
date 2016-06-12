package com.evgeneoskin.banhammer.group;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.evgeneoskin.banhammer.R;
import com.evgeneoskin.banhammer.vk.models.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.ViewHolder> {

    private List<Group> items = new ArrayList<>();

    @Override
    public GroupsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.group_item_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GroupsAdapter.ViewHolder holder, int position) {
        Group group = items.get(position);
        holder.nameView.setText(group.getName());
        int iconResource;
        if (group.isAdmin()) {
            iconResource = android.R.drawable.ic_dialog_alert;
        } else {
            iconResource = android.R.drawable.ic_dialog_info;
        }
        holder.adminView.setImageResource(iconResource);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Group> items) {
        this.items.clear();
        this.items.addAll(items);
        this.notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nameView;
        public ImageView adminView;

        public ViewHolder(View itemView) {
            super(itemView);
            adminView = (ImageView) itemView.findViewById(R.id.admin_view);
            nameView = (TextView) itemView.findViewById(R.id.name_view);
        }
    }
}
