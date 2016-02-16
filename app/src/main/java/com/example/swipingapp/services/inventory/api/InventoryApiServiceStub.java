package com.example.swipingapp.services.inventory.api;

import com.example.swipingapp.DTOs.inventory.CategoryDTO;
import com.example.swipingapp.DTOs.inventory.ItemDTO;
import com.example.swipingapp.enums.Currency;
import com.example.swipingapp.responses.ErrorResponse;
import com.example.swipingapp.services.base.api.BaseApiServiceStub;
import com.example.swipingapp.viewModels.inventory.CategoryViewModel;
import com.example.swipingapp.viewModels.inventory.ItemViewModel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Path;
import retrofit2.mock.Calls;

public class InventoryApiServiceStub<T extends IInventoryApiService> extends BaseApiServiceStub<T> implements IInventoryApiService {

    // region Properties

    private static List<CategoryDTO> mCategories;
    private static int mHighestCategoryId;
    private static int mHighestItemId;

    // endregion

    // region Constructors

    public InventoryApiServiceStub() {
        mCategories = new ArrayList<>();
        mHighestCategoryId = 0;
        mHighestItemId = 0;

        for(int i=0; i<4; i++) {
            List<ItemDTO> items = new ArrayList<>();

            for(int j=0; j<4; j++) {
                items.add(new ItemDTO(mHighestItemId, "Item " + mHighestItemId, 40, Currency.ICELANDIC_KRONA));
                mHighestItemId++;
            }

            mCategories.add(new CategoryDTO(mHighestCategoryId, "Category " + mHighestCategoryId, items));
            mHighestCategoryId++;
        }
    }

    // endregion

    // region API endpoints

    @Override
    public Call<List<CategoryDTO>> getCategories(@Path("userId") int userId) {
        Response<List<CategoryDTO>> response;
        ResponseBody responseBody;

        if(userId > 0) {
            response = Response.success(mCategories);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("Unable to get list");
            responseBody = ResponseBody.create(MediaType.parse(MEDIA_TYPE), mGson.toJson(errorResponse));
            response = Response.error(404, responseBody);
        }

        return mDelegate.returning(Calls.response(response)).getCategories(userId);
    }

    @Override
    public Call<CategoryDTO> addCategory(@Path("userId") int userId, @Body CategoryViewModel categoryViewModel) {
        Response<CategoryDTO> response;

        mHighestCategoryId++;
        response = Response.success(new CategoryDTO(mHighestCategoryId, categoryViewModel.description, new ArrayList<ItemDTO>()));

        return mDelegate.returning(Calls.response(response)).addCategory(userId, categoryViewModel);
    }

    @Override
    public Call<CategoryDTO> editCategory(@Path("userId") int userId, @Path("categoryId") int categoryId, @Body CategoryViewModel categoryViewModel) {
        Response<CategoryDTO> response;

        CategoryDTO category = mCategories.get(categoryId);
        category.description = categoryViewModel.description;

        response = Response.success(category);

        return mDelegate.returning(Calls.response(response)).editCategory(userId, categoryId, categoryViewModel);
    }

    @Override
    public Call<ResponseBody> deleteCategory(@Path("userId") int userId, @Path("categoryId") int categoryId) {
        Response<ResponseBody> response;
        ResponseBody responseBody = ResponseBody.create(MediaType.parse(MEDIA_TYPE), "Category deleted");

        mCategories.remove(categoryId);

        response = Response.success(responseBody);

        return mDelegate.returning(Calls.response(response)).deleteCategory(userId, categoryId);
    }

    @Override
    public Call<ItemDTO> addItem(@Path("userId") int userId, @Path("categoryId") int categoryId, @Body ItemViewModel itemViewModel) {
        Response<ItemDTO> response;

        mHighestItemId++;

        ItemDTO itemDto = new ItemDTO(mHighestItemId, itemViewModel.description, itemViewModel.amount, itemViewModel.currency);
        mCategories.get(categoryId).addItem(itemDto);

        response = Response.success(itemDto);

        return mDelegate.returning(Calls.response(response)).addItem(userId, categoryId, itemViewModel);
    }

    @Override
    public Call<ItemDTO> editItem(@Path("userId") int userId, @Path("categoryId") int categoryId, @Path("itemId") int itemId, @Body ItemViewModel itemViewModel) {
        Response<ItemDTO> response;

        ItemDTO item = mCategories.get(categoryId).items.get(itemId);
        item.description = itemViewModel.description;
        item.amount = itemViewModel.amount;
        item.currency = itemViewModel.currency;

        response = Response.success(item);

        return mDelegate.returning(Calls.response(response)).editItem(userId, categoryId, itemId, itemViewModel);
    }

    @Override
    public Call<ResponseBody> deleteItem(@Path("userId") int userId, @Path("categoryId") int categoryId, @Path("itemId") int itemId) {
        Response<ResponseBody> response;
        ResponseBody responseBody = ResponseBody.create(MediaType.parse(MEDIA_TYPE), "Item deleted");

        mCategories.get(categoryId).items.remove(itemId);

        response = Response.success(responseBody);

        return mDelegate.returning(Calls.response(response)).deleteItem(userId, categoryId, itemId);
    }

    // endregion
}
