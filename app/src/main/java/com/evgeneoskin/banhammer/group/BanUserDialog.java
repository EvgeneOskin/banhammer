package com.evgeneoskin.banhammer.group;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.evgeneoskin.banhammer.R;

import org.w3c.dom.Text;

class BanUserDialog {

    private final MaterialDialog.Builder builder;
    private final MaterialDialog dialog;
    private final @NonNull RelativeLayout view;

    BanUserDialog (Activity activity) {
        builder = new MaterialDialog.Builder(activity)
                .title(R.string.ban_user_dialog_title)
                .customView(R.layout.ban_user_dialog_view, false)
                .positiveText(R.string.ban_user_dialog_agree)
                .negativeText(R.string.ban_user_dialog_cancel);
        dialog = builder.build();
        view = (RelativeLayout) dialog.getCustomView();
        Spinner reason = (Spinner) view.findViewById(R.id.reason_view);
        reason.setAdapter(new ReasonSpinnerAdapter(activity));
    }

    void show() {
        dialog.show();
    }

    void dismiss() {
        dialog.show();
    }

    static class ReasonSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {
        private android.content.Context context;
        private int[] items = BannedUsersAdapter.REASONS;

        ReasonSpinnerAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int i) {
            return items[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView;
            if (view == null) {
                textView = new TextView(context);
            } else {
                textView = (TextView) view;
            }
            textView.setText(items[i]);
            return textView;
        }
    }
}

