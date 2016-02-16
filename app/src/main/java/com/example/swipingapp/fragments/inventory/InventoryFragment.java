package com.example.swipingapp.fragments.inventory;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swipingapp.DTOs.inventory.CategoryDTO;
import com.example.swipingapp.DTOs.inventory.ItemDTO;
import com.example.swipingapp.R;
import com.example.swipingapp.adapters.SectionAdapter;
import com.example.swipingapp.enums.Currency;
import com.example.swipingapp.fragments.inventory.adapter.ExpandableRecyclerAdapter;
import com.example.swipingapp.fragments.base.BaseFragment;
import com.example.swipingapp.responses.ErrorResponse;
import com.example.swipingapp.services.inventory.IInventoryService;
import com.example.swipingapp.services.inventory.InventoryServiceStub;
import com.example.swipingapp.services.settings.ISettingsService;
import com.example.swipingapp.utils.DialogUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

public class InventoryFragment extends BaseFragment {

    // region Constants

    public static final String TAG = InventoryFragment.class.getSimpleName();

    // endregion

    // region Properties

    private ISettingsService mSettingsService;
    private IInventoryService mInventoryService;
    private Currency mCurrency;
    private SectionAdapter mAdapter;

    // endregion

    // region UI references

    public TextView mSectionTitleTextView;
    public ImageButton mSectionDropDownArrow;
    public TextView mItemDateText;
    public CheckBox mItemSolvedCheckBox;
    private List<CategoryDTO> mItemList;
    private RecyclerView mRecyclerView;

    // endregion

    // region Override functions

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mInventoryService = InventoryServiceStub.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_inventory, container, false);

        ItemDTO item1 = new ItemDTO(1, "item1", 2, Currency.ICELANDIC_KRONA);
        ItemDTO item2 = new ItemDTO(2, "item2", 4, Currency.ICELANDIC_KRONA);
        ItemDTO item3 = new ItemDTO(3, "item3", 5, Currency.ICELANDIC_KRONA);
        ItemDTO item4 = new ItemDTO(4, "item4", 20, Currency.ICELANDIC_KRONA);

        ItemDTO item5 = new ItemDTO(1, "item10", 2, Currency.ICELANDIC_KRONA);
        ItemDTO item6 = new ItemDTO(2, "item20", 4, Currency.ICELANDIC_KRONA);
        ItemDTO item7 = new ItemDTO(3, "item30", 5, Currency.ICELANDIC_KRONA);
        ItemDTO item8 = new ItemDTO(4, "item40", 20, Currency.ICELANDIC_KRONA);
        ItemDTO item9 = new ItemDTO(4, "item50", 20, Currency.ICELANDIC_KRONA);


        ItemDTO item10 = new ItemDTO(1, "item100", 2, Currency.ICELANDIC_KRONA);
        ItemDTO item11 = new ItemDTO(2, "item200", 4, Currency.ICELANDIC_KRONA);
        ItemDTO item12 = new ItemDTO(3, "item300", 5, Currency.ICELANDIC_KRONA);
        ItemDTO item13 = new ItemDTO(4, "item400", 20, Currency.ICELANDIC_KRONA);
        ItemDTO item14 = new ItemDTO(4, "item500", 20, Currency.ICELANDIC_KRONA);


        ArrayList<ItemDTO> list1 = new ArrayList<>();
        list1.add(item1);
        list1.add(item2);
        list1.add(item3);
        list1.add(item4);

        ArrayList<ItemDTO> list2 = new ArrayList<>();
        list2.add(item5);
        list2.add(item6);
        list2.add(item7);
        list2.add(item8);
        list2.add(item9);

        ArrayList<ItemDTO> list3 = new ArrayList<>();
        list3.add(item10);
        list3.add(item11);
        list3.add(item12);
        list3.add(item13);
        list3.add(item14);

        CategoryDTO category1 = new CategoryDTO(1, "Category 1", list1);
        CategoryDTO category2 = new CategoryDTO(2, "Category 2", list2);
        CategoryDTO category3 = new CategoryDTO(2, "Category 3", list3);

        mItemList = Arrays.asList(category1, category2, category3);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        mAdapter = new SectionAdapter(getContext(), mItemList);

        mAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
            @Override
            public void onListItemExpanded(int position) {
                CategoryDTO expandedCategory = mItemList.get(position);

                String toastMsg = getResources().getString(R.string.expanded, expandedCategory.description);
                Toast.makeText(getActivity(),
                        toastMsg,
                        Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onListItemCollapsed(int position) {
                CategoryDTO collapsedCategory = mItemList.get(position);

                String toastMsg = getResources().getString(R.string.collapsed, collapsedCategory.description);
                Toast.makeText(getActivity(),
                        toastMsg,
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mSectionTitleTextView = (TextView) view.findViewById(R.id.parent_list_item_section_title_text_view);
        mSectionDropDownArrow = (ImageButton) view.findViewById(R.id.parent_list_item_expand_arrow);
        mItemDateText = (TextView) view.findViewById(R.id.txt_category_description);
        mItemSolvedCheckBox = (CheckBox) view.findViewById(R.id.child_list_item_crime_solved_check_box);

        mInventoryService.getCategories(1, new GetCategoriesResponse());

        return view;
    }

    @Override
    public String getTitle() {
        return getString(R.string.fragment_inventory_title);
    }

    // endregion

    // region Responses

    private class GetCategoriesResponse implements Callback<List<CategoryDTO>> {

        @Override
        public void onResponse(Call<List<CategoryDTO>> call, Response<List<CategoryDTO>> response) {

            if(response.isSuccess()) {
                mItemList = response.body();
                mAdapter = new SectionAdapter(getContext(), mItemList);
                mRecyclerView.setAdapter(mAdapter);
            } else {
                String title = "Oh fuck";
                String message = "message";

                try {
                    Converter<ResponseBody, ErrorResponse> errorConverter = mInventoryService.getRetrofit().responseBodyConverter(ErrorResponse.class, new Annotation[0]);
                    ErrorResponse error = errorConverter.convert(response.errorBody());
                    message = error.message;
                } catch (IOException e) {
                    // TODO: Better error handling
                    e.printStackTrace();
                }

                DialogUtils.displayMessageDialog(mContext, title, message);
            }
        }

        @Override
        public void onFailure(Call<List<CategoryDTO>> call, Throwable t) {
            Log.e("onFailure", "Something went terribly wrong :/");
        }
    }

    // endregion
}