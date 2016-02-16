package com.example.swipingapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swipingapp.DTOs.inventory.CategoryDTO;
import com.example.swipingapp.DTOs.inventory.ItemDTO;
import com.example.swipingapp.R;
import com.example.swipingapp.fragments.Inventory.Adapter.ExpandableRecyclerAdapter;
import com.example.swipingapp.fragments.Inventory.ItemViewHolder;
import com.example.swipingapp.fragments.Inventory.Model.ParentListItem;
import com.example.swipingapp.fragments.Inventory.SectionViewHolder;

import java.util.List;

public class SectionAdapter extends ExpandableRecyclerAdapter<SectionViewHolder, ItemViewHolder> {

    // region Properties

    private LayoutInflater mInflator;

    // endregion

    // region Constructors

    public SectionAdapter(Context context, @NonNull List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
        mInflator = LayoutInflater.from(context);
    }

    // endregion

    // region Override functions

    @Override
    public SectionViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View sectionView = mInflator.inflate(R.layout.fragment_inventory_section_listview_parent, parentViewGroup, false);
        return new SectionViewHolder(sectionView);
    }

    @Override
    public ItemViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View itemView = mInflator.inflate(R.layout.fragment_inventory_item_listview_child, childViewGroup, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindParentViewHolder(SectionViewHolder sectionViewHolder, int position, ParentListItem parentListItem) {
        CategoryDTO category = (CategoryDTO) parentListItem;
        sectionViewHolder.bind(category);
    }

    @Override
    public void onBindChildViewHolder(ItemViewHolder itemViewHolder, int position, Object childListItem) {
        ItemDTO item = (ItemDTO) childListItem;
        itemViewHolder.bind(item);
    }

    // endregion
}
