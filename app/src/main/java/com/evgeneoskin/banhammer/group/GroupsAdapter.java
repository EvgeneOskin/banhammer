package com.evgeneoskin.banhammer.group;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.evgeneoskin.banhammer.R;
import com.evgeneoskin.banhammer.vk.models.Group;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.ViewHolder> {

    private final Context context;
    private List<Group> items = new ArrayList<>();

    GroupsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public GroupsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.group_item_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GroupsAdapter.ViewHolder holder, int position) {
        final Group group = items.get(position);
        holder.nameView.setText(group.getName());
        int iconResource;
        if (group.isAdmin()) {
            iconResource = android.R.drawable.ic_dialog_alert;
        } else {
            iconResource = android.R.drawable.ic_dialog_info;
        }
        holder.adminView.setImageResource(iconResource);
        holder.adminView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBannedUsers(group);
            }
        });
    }

    public void showBannedUsers(Group group){
        Intent myIntent = new Intent(context, BannedUsersActivity.class);
        myIntent.putExtra("group", Parcels.wrap(group)); //Optional parameters
        context.startActivity(myIntent);
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
