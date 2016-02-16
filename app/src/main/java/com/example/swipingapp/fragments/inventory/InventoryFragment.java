package com.example.swipingapp.fragments.inventory;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.swipingapp.DTOs.inventory.CategoryDTO;
import com.example.swipingapp.R;
import com.example.swipingapp.adapters.SectionAdapter;
import com.example.swipingapp.enums.Currency;
import com.example.swipingapp.fragments.base.BaseFragment;
import com.example.swipingapp.responses.ErrorResponse;
import com.example.swipingapp.services.inventory.IInventoryService;
import com.example.swipingapp.services.inventory.InventoryServiceStub;
import com.example.swipingapp.services.settings.ISettingsService;
import com.example.swipingapp.utils.DialogUtils;
import com.example.swipingapp.viewModels.inventory.CategoryViewModel;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
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

    private Button mAddCategoryButton;

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

        mItemList = new ArrayList<>();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mAddCategoryButton = (Button) view.findViewById(R.id.btn_add_category);

        mAdapter = new SectionAdapter(getContext(), mItemList);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mSectionTitleTextView = (TextView) view.findViewById(R.id.parent_list_item_section_title_text_view);
        mSectionDropDownArrow = (ImageButton) view.findViewById(R.id.parent_list_item_expand_arrow);
        mItemDateText = (TextView) view.findViewById(R.id.txt_category_description);
        mItemSolvedCheckBox = (CheckBox) view.findViewById(R.id.child_list_item_crime_solved_check_box);

        mInventoryService.getCategories(1, new GetCategoriesResponse());

        mAddCategoryButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                mInventoryService.addCategory(0, new CategoryViewModel("Testing"), new AddCategoryResponse());
            }
        });

        return view;
    }

    @Override
    public String getTitle() {
        return getString(R.string.fragment_inventory_title);
    }

    // endregion

    // region Responses

    private class AddCategoryResponse implements Callback<CategoryDTO> {

        @Override
        public void onResponse(Call<CategoryDTO> call, Response<CategoryDTO> response) {
            if(response.isSuccess()) {
                mAdapter.addItem(response.body());
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
        public void onFailure(Call<CategoryDTO> call, Throwable t) {
            Log.e("onFailure", "Something went terribly wrong :/");
        }
    }

    private class GetCategoriesResponse implements Callback<List<CategoryDTO>> {

        @Override
        public void onResponse(Call<List<CategoryDTO>> call, Response<List<CategoryDTO>> response) {

            if(response.isSuccess()) {
                mItemList = response.body();
                // TODO: This needs some rethinking, need to be able to set items dynamically
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