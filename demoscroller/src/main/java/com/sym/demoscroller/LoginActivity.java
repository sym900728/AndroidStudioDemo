package com.sym.demoscroller;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.sym.adapter.LoginListViewAdapter;
import com.sym.view.ScrollerLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2015/12/21.
 */
public class LoginActivity extends Activity {

    @Bind(R.id.login_listview)
    ListView loginListView;
    @Bind(R.id.login_scroller_layout)
    ScrollerLayout loginScrollerLayout;
    @Bind(R.id.login_drop_account_rel)
    RelativeLayout loginDropAccount;

    private LoginListViewAdapter loginListViewAdapter;

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();

    }

    private void init() {
        loginListViewAdapter = new LoginListViewAdapter(this);
        loginListView.setAdapter(loginListViewAdapter);
    }

    @OnClick(R.id.login_drop_account_rel)
    void dropAccount() {

        if(loginScrollerLayout.isOpen()){
            loginScrollerLayout.close();
        } else {
            loginScrollerLayout.open();
        }
    }

}
