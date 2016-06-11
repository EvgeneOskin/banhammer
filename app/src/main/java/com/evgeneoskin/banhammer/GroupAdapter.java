package com.evgeneoskin.banhammer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.TextView;

import com.evgeneoskin.banhammer.vk.VKWrapper;
import com.evgeneoskin.banhammer.vk.models.Group;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {

    private List<Group> groups = new ArrayList<>();

    @Inject
    public GroupAdapter() {}

    @Override
    public GroupAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.group_item_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GroupAdapter.ViewHolder holder, int position) {
        Group group = groups.get(position);
        holder.nameView.setText(group.getName());
        if (group.isAdmin()) {
            holder.adminView.setImageResource(R.drawable.ic_block);
        } else {
            holder.adminView.setImageResource(R.drawable.ic_list);
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
        public ImageSwitcher adminView;

        public ViewHolder(View itemView) {
            super(itemView);
            adminView = (ImageSwitcher) itemView.findViewById(R.id.admin_view);
            nameView = (TextView) itemView.findViewById(R.id.name_view);
        }
    }
}
