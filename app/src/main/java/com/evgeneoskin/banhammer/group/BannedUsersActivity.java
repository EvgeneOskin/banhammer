package com.evgeneoskin.banhammer.group;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.evgeneoskin.banhammer.R;
import com.evgeneoskin.banhammer.vk.VK;
import com.evgeneoskin.banhammer.vk.VKWrapper;
import com.evgeneoskin.banhammer.vk.models.Group;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@EActivity(R.layout.activity_main)
public class BannedUsersActivity extends AppCompatActivity {

    private VK vk;
    @ViewById(R.id.items_view)
    RecyclerView recyclerView;
    private BannedUsersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vk = new VKWrapper();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BannedUsersAdapter();
        recyclerView.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onStart() {
        this.populateMembers();
        super.onStart();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!vk.onActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void populateMembers() {
        vk.listBannedUsers()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Group>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Snackbar.make(
                                recyclerView, R.string.list_group_error, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                    @Override
                    public void onNext(List<Group> groups) {
                        adapter.setItems(groups);
                    }
                });
    }
}

