package com.evgeneoskin.banhammer.group;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.evgeneoskin.banhammer.R;
import com.evgeneoskin.banhammer.vk.VK;
import com.evgeneoskin.banhammer.vk.VKImpl;
import com.evgeneoskin.banhammer.vk.models.BannedUser;
import com.evgeneoskin.banhammer.vk.models.Group;

import org.parceler.Parcels;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BannedUsersActivity extends AppCompatActivity {

    private VK vk;
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private BannedUsersAdapter adapter;
    private Group group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vk = new VKImpl();

        Intent intent = getIntent();
        group = Parcels.unwrap(intent.getParcelableExtra("group"));

        setContentView(R.layout.activity_banned_users);

        recyclerView = (RecyclerView) findViewById(R.id.items_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new BannedUsersAdapter();
        recyclerView.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Ban user", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
        vk.listBannedUsers(group)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<BannedUser>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Snackbar.make(
                                recyclerView, R.string.list_banned_user_error, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                    @Override
                    public void onNext(List<BannedUser> items) {
                        adapter.setItems(items);
                    }
                });
    }
}

