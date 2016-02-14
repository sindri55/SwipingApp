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

    // region API endpoints

    @Override
    public Call<List<CategoryDTO>> getCategories(@Path("userId") int userId) {
        ArrayList<CategoryDTO> categories;
        ArrayList<ItemDTO> items;

        categories = new ArrayList<>();
        items = new ArrayList<>();

        items.add(new ItemDTO(1, "item 1", 10, Currency.ICELANDIC_KRONA));
        items.add(new ItemDTO(2, "item 2", 20, Currency.ICELANDIC_KRONA));
        items.add(new ItemDTO(3, "item 3", 30, Currency.ICELANDIC_KRONA));

        categories.add(new CategoryDTO(1, "category 1", items));
        categories.add(new CategoryDTO(2, "category 2", items));
        categories.add(new CategoryDTO(3, "category 3", items));

        Response<List<CategoryDTO>> response;
        ResponseBody responseBody;

        if(userId > 0) {
            response = Response.success((List<CategoryDTO>) categories);
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

        response = Response.success(new CategoryDTO(10, categoryViewModel.description, new ArrayList<ItemDTO>()));

        return mDelegate.returning(Calls.response(response)).addCategory(userId, categoryViewModel);
    }

    @Override
    public Call<CategoryDTO> editCategory(@Path("userId") int userId, @Path("categoryId") int categoryId, @Body CategoryViewModel categoryViewModel) {
        Response<CategoryDTO> response;

        response = Response.success(new CategoryDTO(10, categoryViewModel.description, new ArrayList<ItemDTO>()));

        return mDelegate.returning(Calls.response(response)).editCategory(userId, categoryId, categoryViewModel);
    }

    @Override
    public Call<ResponseBody> deleteCategory(@Path("userId") int userId, @Path("categoryId") int categoryId) {
        Response<ResponseBody> response;
        ResponseBody responseBody = ResponseBody.create(MediaType.parse(MEDIA_TYPE), "Category deleted");

        response = Response.success(responseBody);

        return mDelegate.returning(Calls.response(response)).deleteCategory(userId, categoryId);
    }

    @Override
    public Call<ItemDTO> addItem(@Path("userId") int userId, @Path("categoryId") int categoryId, @Body ItemViewModel itemViewModel) {
        Response<ItemDTO> response;

        response = Response.success(new ItemDTO(110, itemViewModel.description, itemViewModel.amount, itemViewModel.currency));

        return mDelegate.returning(Calls.response(response)).addItem(userId, categoryId, itemViewModel);
    }

    @Override
    public Call<ItemDTO> editItem(@Path("userId") int userId, @Path("categoryId") int categoryId, @Path("itemId") int itemId, @Body ItemViewModel itemViewModel) {
        Response<ItemDTO> response;

        response = Response.success(new ItemDTO(110, itemViewModel.description, itemViewModel.amount, itemViewModel.currency));

        return mDelegate.returning(Calls.response(response)).editItem(userId, categoryId, itemId, itemViewModel);
    }

    @Override
    public Call<ResponseBody> deleteItem(@Path("userId") int userId, @Path("categoryId") int categoryId, @Path("itemId") int itemId) {
        Response<ResponseBody> response;
        ResponseBody responseBody = ResponseBody.create(MediaType.parse(MEDIA_TYPE), "Item deleted");

        response = Response.success(responseBody);

        return mDelegate.returning(Calls.response(response)).deleteItem(userId, categoryId, itemId);
    }

    // endregion
}
