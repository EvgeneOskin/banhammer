package com.evgeneoskin.banhammer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import com.evgeneoskin.banhammer.vk.models.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {

    private List<Group> groups = new ArrayList<>();

    @Override
    public GroupAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.group_item_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GroupAdapter.ViewHolder holder, int position) {
        Group group = groups.get(position);
        holder.nameView.setText(group.getName());
        if (group.isAdmin()) {
            holder.adminView.setImageResource(android.R.drawable.ic_dialog_alert);
        } else {
            holder.adminView.setImageResource(android.R.drawable.ic_dialog_info);
        }
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public void setGroups(List<Group> groups) {
        this.groups.clear();
        this.groups.addAll(groups);
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
