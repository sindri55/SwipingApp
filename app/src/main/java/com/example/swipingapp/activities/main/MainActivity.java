package com.example.swipingapp.activities.main;

import android.content.Context;
import android.content.Intent;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.swipingapp.DTOs.UserDTO;
import com.example.swipingapp.R;
import com.example.swipingapp.activities.account.LoginActivity;
import com.example.swipingapp.enums.DrawerItem;
import com.example.swipingapp.fragments.userAccount.UserFragment;
import com.example.swipingapp.fragments.payment.AmountFragment;
import com.example.swipingapp.services.user.IUserService;
import com.example.swipingapp.services.user.UserServiceStub;
import com.mikepenz.iconics.context.IconicsContextWrapper;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity{

    // region Constants

    private static String TAG = MainActivity.class.getSimpleName();

    // endregion

    // region Properties

    private IUserService mUserService;
    private FragmentManager mFragmentManager;
    private Drawer mDrawer;
    private ArrayList<PrimaryDrawerItem> mDrawerItems;
    private AccountHeader mDrawerAccountHeader;

    // endregion

    // region UI references

    private Toolbar mToolbarView;

    //endregion

    // region Override functions

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(IconicsContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserService = UserServiceStub.getInstance();
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.add(R.id.fragment_container, new AmountFragment());
        ft.commit();

        mToolbarView = (Toolbar) findViewById(R.id.toolbar);

        initializeDrawer();
    }

    // endregion

    // region Private functions

    private void initializeDrawer() {
        setDrawerItems();
        setAccountHeader();

        mDrawer = new DrawerBuilder()
                .withAccountHeader(mDrawerAccountHeader)
                .withActivity(this)
                .withToolbar(mToolbarView)
                .withOnDrawerItemClickListener(new DrawerItemClickListener())
                .build();

        for (PrimaryDrawerItem drawerItem : mDrawerItems) {
            mDrawer.addItem(drawerItem);
            mDrawer.addItem(new DividerDrawerItem());
        }

        mDrawer.addStickyFooterItem(DrawerItem.LOG_OUT.getDrawerItem());
    }

    private void setAccountHeader() {
        UserDTO userDto = mUserService.getUser(1);

        ProfileDrawerItem profileDrawerItem = new ProfileDrawerItem()
                .withName(userDto.name)
                .withEmail(userDto.email)
                .withIcon(R.mipmap.ic_launcher); // TODO: Get user logo or some

        mDrawerAccountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.background_account_header)
                .addProfiles(profileDrawerItem)
                .build();
    }

    private void setDrawerItems() {
        mDrawerItems = new ArrayList<>();
        mDrawerItems.add(DrawerItem.MAKE_TRANSACTION.getDrawerItem());
        mDrawerItems.add(DrawerItem.PROFILE.getDrawerItem());
        mDrawerItems.add(DrawerItem.HISTORY.getDrawerItem());
        mDrawerItems.add(DrawerItem.SETTINGS.getDrawerItem());
    }

    private void selectItem(int id) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();

        DrawerItem drawerItem = DrawerItem.getEnum(id);
        if(drawerItem != null) {
            switch (drawerItem){
                case MAKE_TRANSACTION:
                    ft.replace(R.id.fragment_container, new AmountFragment(), TAG);
                    ft.commit();
                    break;
                case PROFILE:
                    ft.replace(R.id.fragment_container, new UserFragment(), TAG);
                    ft.commit();
                    break;
                case HISTORY:
                    // TODO: Create fragment
                    break;
                case SETTINGS:
                    // TODO: Create fragment
                    break;

                case LOG_OUT:
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }

    // endregion

    // region Listeners

    private class DrawerItemClickListener implements Drawer.OnDrawerItemClickListener {

        @Override
        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
            if (findViewById(R.id.fragment_container) != null) {
                selectItem(drawerItem.getIdentifier());
                mDrawer.closeDrawer();
            }
            return true;
        }
    }

    // endregion
}
