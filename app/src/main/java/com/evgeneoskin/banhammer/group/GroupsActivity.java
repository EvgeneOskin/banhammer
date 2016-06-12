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
import com.evgeneoskin.banhammer.vk.VKImpl;
import com.evgeneoskin.banhammer.vk.models.Group;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GroupsActivity extends AppCompatActivity {

    private VK vk;
    RecyclerView recyclerView;
    private GroupsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vk = new VKImpl();

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.items_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new GroupsAdapter(this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onStart() {
        vk.login(this);
        this.populateGroups();
        super.onStart();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (vk.onActivityResult(requestCode, resultCode, data)) {
            this.populateGroups();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void populateGroups() {
        vk.listGroups()
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
