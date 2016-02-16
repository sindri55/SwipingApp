package com.example.swipingapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.swipingapp.DTOs.inventory.CategoryDTO;
import com.example.swipingapp.DTOs.inventory.ItemDTO;
import com.example.swipingapp.R;
import com.example.swipingapp.extensions.App;
import com.example.swipingapp.fragments.inventory.adapter.ExpandableRecyclerAdapter;
import com.example.swipingapp.fragments.inventory.model.ParentWrapper;
import com.example.swipingapp.fragments.inventory.viewHolder.ItemViewHolder;
import com.example.swipingapp.fragments.inventory.model.ParentListItem;
import com.example.swipingapp.fragments.inventory.viewHolder.SectionViewHolder;

import java.util.List;

public class SectionAdapter extends ExpandableRecyclerAdapter<SectionViewHolder, ItemViewHolder> implements ExpandableRecyclerAdapter.ExpandCollapseListener {

    // region Properties

    private LayoutInflater mInflator;
    private Context mContext;
    private SectionViewHolder.CategoryButtonListener mCategoryButtonListener;

    // endregion

    // region Constructors

    public SectionAdapter(Context context, @NonNull List<? extends ParentListItem> parentItemList, SectionViewHolder.CategoryButtonListener categoryButtonListener) {
        super(parentItemList);
        setExpandCollapseListener(this);
        mContext = context;
        mCategoryButtonListener = categoryButtonListener;
        mInflator = LayoutInflater.from(context);
    }

    // endregion

    // region Override functions

    @Override
    public SectionViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View sectionView = mInflator.inflate(R.layout.fragment_inventory_section_listview_parent, parentViewGroup, false);
        return new SectionViewHolder(sectionView, mCategoryButtonListener);
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

    // region Public functions

    public void addCategory(CategoryDTO category) {
        ParentWrapper parentWrapper = new ParentWrapper(category);

        mItemList.add(parentWrapper);

        notifyItemInserted(mItemList.size() - 1);
    }

    public void deleteCategory(int categoryId) {
        int index = -1;

        // TODO: Do better
        for(int i=0; i<mItemList.size(); i++) {
            ParentWrapper current = (ParentWrapper) mItemList.get(i);
            CategoryDTO currentCategory = (CategoryDTO) current.getParentListItem();
            if(currentCategory.categoryId == categoryId) {
                index = i;
            }
        }

        if(index < 0) {
            return;
        }

        mItemList.remove(index);

        notifyItemRemoved(index);
    }

    // endregion

    // region ExpandCollapseListener

    @Override
    public void onListItemExpanded(int position) {
        ParentWrapper parentWrapper = (ParentWrapper) getListItem(position);
        CategoryDTO category = (CategoryDTO) parentWrapper.getParentListItem();

        String toastMsg = App.getContext().getString(R.string.expanded, category.description);
        Toast.makeText(mContext,
                toastMsg,
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onListItemCollapsed(int position) {
        ParentWrapper parentWrapper = (ParentWrapper) getListItem(position);
        CategoryDTO category = (CategoryDTO) parentWrapper.getParentListItem();

        String toastMsg = App.getContext().getString(R.string.collapsed, category.description);
        Toast.makeText(mContext,
                toastMsg,
                Toast.LENGTH_SHORT)
                .show();
    }

    // endregion
}
