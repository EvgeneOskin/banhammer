package com.evgeneoskin.banhammer.group;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.evgeneoskin.banhammer.R;
import com.evgeneoskin.banhammer.vk.models.BannedUser;

import java.util.ArrayList;
import java.util.List;

public class BannedUsersAdapter extends RecyclerView.Adapter<BannedUsersAdapter.ViewHolder> {

    static int[] REASONS = new int[] {
            R.string.ban_reason_other, R.string.ban_reason_spam,
            R.string.ban_reason_abuse, R.string.ban_reason_obscene,
            R.string.ban_reason_off_topic,
};
    private List<BannedUser> items = new ArrayList<>();

    @Override
    public BannedUsersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.banned_user_item_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BannedUsersAdapter.ViewHolder holder, int position) {
        BannedUser item = items.get(position);
        holder.nameView.setText(item.getFullName());
        holder.reasonView.setText(renderReason(item));
        holder.detailView.setText(item.ban_info.comment);
        holder.adminView.setText(item.getAdminFullName());
    }

    public int renderReason(BannedUser item) {
        if (item.ban_info.reason >= 0 && item.ban_info.reason < REASONS.length) {
            return REASONS[item.ban_info.reason];
        } else {
            return R.string.ban_reason_unknown;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<BannedUser> items) {
        this.items.clear();
        this.items.addAll(items);
        this.notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nameView;
        public TextView detailView;
        public TextView reasonView;
        public TextView adminView;

        public ViewHolder(View itemView) {
            super(itemView);
            adminView = (TextView) itemView.findViewById(R.id.banner_view);
            nameView = (TextView) itemView.findViewById(R.id.name_view);
            detailView = (TextView) itemView.findViewById(R.id.detail_view);
            reasonView = (TextView) itemView.findViewById(R.id.reason_view);
        }
    }
}

