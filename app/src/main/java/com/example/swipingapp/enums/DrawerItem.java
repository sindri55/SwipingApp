package com.example.swipingapp.enums;

import android.content.Context;

import com.example.swipingapp.R;
import com.example.swipingapp.extensions.App;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;

public enum DrawerItem {

    // region Values

    MAKE_TRANSACTION    (0),
    INVENTORY           (1),
    PROFILE             (2),
    HISTORY             (3),
    SETTINGS            (4),

    LOG_OUT             (100);

    // endregion

    // region Properties

    private final int id;

    // endregion

    // region Constructors

    DrawerItem(int id) {
        this.id = id;
    }

    // endregion

    // region Static functions

    public static DrawerItem getEnum(int id) {
        switch (id) {
            case 0:
                return MAKE_TRANSACTION;
            case 1:
                return INVENTORY;
            case 2:
                return PROFILE;
            case 3:
                return HISTORY;
            case 4:
                return SETTINGS;

            case 100:
                return LOG_OUT;
        }

        return null;
    }

    // endregion

    // region Public functions

    public int getIdentifier() {
        return this.id;
    }

    public PrimaryDrawerItem getDrawerItem() {
        Context appContext = App.getContext();

        switch (this) {
            case MAKE_TRANSACTION:
                return new PrimaryDrawerItem()
                        .withIdentifier(this.getIdentifier())
                        .withName(appContext.getString(R.string.drawer_item_name_make_transaction))
                        .withIcon(R.mipmap.ic_launcher);    // TODO: Find icon
            case INVENTORY:
                return new PrimaryDrawerItem()
                        .withIdentifier(this.getIdentifier())
                        .withName(appContext.getString(R.string.drawer_item_name_inventory))
                        .withIcon(R.mipmap.ic_launcher);    // TODO: Find icon
            case PROFILE:
                return new PrimaryDrawerItem()
                        .withIdentifier(this.getIdentifier())
                        .withName(appContext.getString(R.string.drawer_item_name_profile))
                        .withIcon(R.mipmap.ic_launcher);    // TODO: Find icon
            case HISTORY:
                return new PrimaryDrawerItem()
                        .withIdentifier(this.getIdentifier())
                        .withName(appContext.getString(R.string.drawer_item_name_history))
                        .withIcon(R.mipmap.ic_launcher);    // TODO: Find icon
            case SETTINGS:
                return new PrimaryDrawerItem()
                        .withIdentifier(this.getIdentifier())
                        .withName(appContext.getString(R.string.drawer_item_name_settings))
                        .withIcon(R.mipmap.ic_launcher);    // TODO: Find icon


            case LOG_OUT:
                return new PrimaryDrawerItem()
                        .withIdentifier(this.getIdentifier())
                        .withName(appContext.getString(R.string.drawer_item_name_log_out))
                        .withIcon(R.mipmap.ic_launcher);    // TODO: Find icon

        }

        return new PrimaryDrawerItem();
    }

    // endregion
}
