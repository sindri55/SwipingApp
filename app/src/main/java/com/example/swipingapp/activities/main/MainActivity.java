package com.example.swipingapp.activities.main;

import android.content.Intent;

import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.swipingapp.DTOs.UserDTO;
import com.example.swipingapp.R;
import com.example.swipingapp.activities.account.LoginActivity;
import com.example.swipingapp.enums.DrawerItem;
import com.example.swipingapp.fragments.history.HistoryFragment;
import com.example.swipingapp.fragments.profile.ProfileFragment;
import com.example.swipingapp.fragments.payment.AmountFragment;
import com.example.swipingapp.fragments.settings.SettingsFragment;
import com.example.swipingapp.listeners.IFragmentListener;
import com.example.swipingapp.services.user.IUserService;
import com.example.swipingapp.services.user.UserServiceStub;
import com.example.swipingapp.utils.FragmentUtils;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IFragmentListener, FragmentManager.OnBackStackChangedListener {

    // region Properties

    private View.OnClickListener mNavigationOriginalClickListener;
    private View.OnClickListener mNavigationBackButtonClickListener;
    private IUserService mUserService;
    private FragmentManager mFragmentManager;
    private Drawer mDrawer;
    private ArrayList<PrimaryDrawerItem> mDrawerItems;
    private AccountHeader mDrawerAccountHeader;
    private boolean mShowNavigationBackButton;

    // endregion

    // region UI references

    private Toolbar mToolbarView;

    //endregion

    // region Override functions

    @Override
    public void onBackPressed() {
        if(mFragmentManager.getBackStackEntryCount() > 0 && mShowNavigationBackButton) {
            mFragmentManager.popBackStack();
        } else {
            selectItem(DrawerItem.MAKE_TRANSACTION.getIdentifier());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mShowNavigationBackButton = true;
        mUserService = UserServiceStub.getInstance();
        mFragmentManager = getSupportFragmentManager();
        mNavigationBackButtonClickListener = new NavigationBackButtonClickListener();

        mToolbarView = (Toolbar) findViewById(R.id.toolbar);
        mToolbarView.setTitle("");
        setSupportActionBar(mToolbarView);

        mFragmentManager.addOnBackStackChangedListener(this);

        initializeDrawer();
        selectItem(DrawerItem.MAKE_TRANSACTION.getIdentifier());
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
        if(mDrawerItems != null && mDrawerItems.size() > 0) {
            mDrawer.setSelection(mDrawerItems.get(0));
        }

        mNavigationOriginalClickListener = mDrawer.getActionBarDrawerToggle().getToolbarNavigationClickListener();
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
            // Clear the back stack
            clearBackStack();
            mShowNavigationBackButton = true;

            switch (drawerItem){
                case MAKE_TRANSACTION:
                    ft.replace(R.id.fragment_container, new AmountFragment(), AmountFragment.TAG);
                    ft.commit();
                    break;
                case PROFILE:
                    ft.replace(R.id.fragment_container, new ProfileFragment(), ProfileFragment.TAG);
                    ft.commit();
                    break;
                case HISTORY:
                    ft.replace(R.id.fragment_container, new HistoryFragment(), HistoryFragment.TAG);
                    ft.commit();
                    break;
                case SETTINGS:
                    ft.replace(R.id.fragment_container, new SettingsFragment(), SettingsFragment.TAG);
                    ft.commit();
                    break;

                case LOG_OUT:
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }

    private void clearBackStack() {
        FragmentUtils.sDisableFragmentAnimations = true;
        mFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentUtils.sDisableFragmentAnimations = false;
    }

    // endregion

    // region OnBackStackChangedListener

    @Override
    public void onBackStackChanged() {
        if(mFragmentManager.getBackStackEntryCount() > 0 && mShowNavigationBackButton) {
            // Show back arrow
            mDrawer.getActionBarDrawerToggle().setDrawerIndicatorEnabled(false);
            if(getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            } else {
                Log.e("getSupportActionBar", "null exception");
            }

            // Change the navigation listener
            mToolbarView.setNavigationOnClickListener(mNavigationBackButtonClickListener);
        } else {
            // Show the hamburger icon
            if(getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            } else {
                Log.e("getSupportActionBar", "null exception");
            }
            mDrawer.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);

            // Change the navigation listener to the original one
            mToolbarView.setNavigationOnClickListener(mNavigationOriginalClickListener);
        }
    }

    // endregion

    // region IFragmentListener

    @Override
    public void setShowNavigationBackButton(boolean show) {
        mShowNavigationBackButton = show;
    }

    @Override
    public void setNavigationTitle(String title) {
        mToolbarView.setTitle(title);
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

    private class NavigationBackButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO: Set title or some?
            mFragmentManager.popBackStack();
        }
    }

    // endregion
}