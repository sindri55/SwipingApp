package com.example.swipingapp.activities.main;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swipingapp.R;
import com.example.swipingapp.activities.account.LoginActivity;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        new DrawerBuilder().withActivity(this).build();

        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName(R.string.nav_item_home);
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withName(R.string.nav_item_home);

        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withName(R.string.nav_item_notifications);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withName(R.string.nav_item_notifications);
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withName(R.string.nav_item_notifications);
        //SecondaryDrawerItem item4 = new SecondaryDrawerItem().withName(R.string.nav_item_notifications);

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.profile_background_image)
                .addProfiles(
                        new ProfileDrawerItem().withName("Sindri Þór").
                                withEmail("55@55.is").
                                withIcon(getResources().
                                        getDrawable(R.drawable.ic_launcher))
                )

                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();


        //create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withAccountHeader(headerResult)
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1.withName("Make a transaction")
                                .withBadge("4")
                                .withDescriptionTextColor(getResources().getColor(R.color.green_dark))
                                .withIcon(getResources().getDrawable(R.drawable.ic_launcher)
                                )
                                .withBadgeStyle(new BadgeStyle()
                                        .withTextColor(Color.WHITE)
                                        .withColorRes(R.color.md_red_700)),
                        new DividerDrawerItem(),
                        item5.withName("Profile")
                                .withIcon(getResources().getDrawable(R.drawable.ic_launcher))
                                .withDescription("description"),
                        new DividerDrawerItem(),

                        item2.withName("History")
                                .withIcon(getResources().getDrawable(R.drawable.ic_launcher))

                                .withTextColor(getResources().getColor(R.color.blue_dark)),
                        new DividerDrawerItem(),

                        item3.withName("Settings")
                                .withDescription("Hægt að leika sér endalaust með þetta")
                                .withIcon(getResources().getDrawable(R.drawable.ic_launcher))

                                .withBadge("Item")
                                .withBadgeStyle(new BadgeStyle()
                                        .withColorPressed(Color.BLUE)
                                        .withTextColor(Color.CYAN)
                                        .withColorRes(R.color.green_dark))
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D

                        Toast.makeText(MainActivity.this, "Clicked:" + position, Toast.LENGTH_SHORT).show();
                        return true;
                    }
                })

                .build();

        result.addItem(new DividerDrawerItem());

        result.addStickyFooterItem(new PrimaryDrawerItem().withName("Log out"));


    }
}
