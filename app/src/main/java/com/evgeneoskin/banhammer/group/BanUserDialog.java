package com.evgeneoskin.banhammer.group;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.evgeneoskin.banhammer.R;
import com.evgeneoskin.banhammer.vk.VK;
import com.evgeneoskin.banhammer.vk.models.BanInfo;
import com.evgeneoskin.banhammer.vk.models.BanUserResult;
import com.evgeneoskin.banhammer.vk.models.BannedUser;
import com.evgeneoskin.banhammer.vk.models.Group;
import com.evgeneoskin.banhammer.vk.models.User;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

class BanUserDialog {

    private final MaterialDialog.Builder builder;
    private final MaterialDialog dialog;
    private final @NonNull RelativeLayout view;
    private VK vk;
    private Group group;
    private int userId;

    BanUserDialog (final Activity activity, final VK vk, final Group group, final int userId) {
        this.vk = vk;
        this.group = group;
        this.userId = userId;
        builder = new MaterialDialog.Builder(activity)
                .title(R.string.ban_user_dialog_title)
                .customView(R.layout.ban_user_dialog_view, false)
                .positiveText(R.string.ban_user_dialog_agree)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        BanInfo banInfo = getBanInfo();
                        vk.banUser(group, userId, banInfo)
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Subscriber<List<BanUserResult>>() {
                                    @Override
                                    public void onCompleted() {
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Snackbar.make(
                                                view, R.string.ban_user_error, Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show();
                                    }

                                    @Override
                                    public void onNext(List<BanUserResult> items) {
                                        Snackbar.make(
                                                view, R.string.ban_user_success, Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show();
                                    }
                                });
                    }
                })
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

    BanInfo getBanInfo() {
        BanInfo banInfo = new BanInfo();

        return banInfo;
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

