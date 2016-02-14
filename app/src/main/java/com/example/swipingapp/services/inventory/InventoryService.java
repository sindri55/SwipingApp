package com.example.swipingapp.services.inventory;

import com.example.swipingapp.DTOs.inventory.CategoryDTO;
import com.example.swipingapp.DTOs.inventory.ItemDTO;
import com.example.swipingapp.services.base.BaseService;
import com.example.swipingapp.services.inventory.api.IInventoryApiService;
import com.example.swipingapp.viewModels.inventory.CategoryViewModel;
import com.example.swipingapp.viewModels.inventory.ItemViewModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class InventoryService<T extends IInventoryApiService> extends BaseService<T> implements IInventoryService {

    // region Properties

    private static IInventoryService mInstance;

    // endregion

    // region Constructors

    public InventoryService(Class<T> apiServiceClassType) {
        super(apiServiceClassType);
    }

    // endregion

    // region Get instance (Singleton)

    public static IInventoryService getInstance() {
        if(mInstance == null) {
            mInstance = new InventoryService<>(IInventoryApiService.class);
        }
        
        return mInstance;
    }

    // endregion

    // region Override functions

    @Override
    public void getCategories(int userId, Callback<List<CategoryDTO>> response) {
        Call<List<CategoryDTO>> result = getApiService().getCategories(userId);
        result.enqueue(response);
    }

    @Override
    public void addCategory(int userId, CategoryViewModel categoryViewModel, Callback<CategoryDTO> response) {
        Call<CategoryDTO> result = getApiService().addCategory(userId, categoryViewModel);
        result.enqueue(response);
    }

    @Override
    public void editCategory(int userId, int categoryId, CategoryViewModel categoryViewModel, Callback<CategoryDTO> response) {
        Call<CategoryDTO> result = getApiService().editCategory(userId, categoryId, categoryViewModel);
        result.enqueue(response);
    }

    @Override
    public void deleteCategory(int userId, int categoryId, Callback<ResponseBody> response) {
        Call<ResponseBody> result = getApiService().deleteCategory(userId, categoryId);
        result.enqueue(response);
    }

    @Override
    public void addItem(int userId, int categoryId, ItemViewModel itemViewModel, Callback<ItemDTO> response) {
        Call<ItemDTO> result = getApiService().addItem(userId, categoryId, itemViewModel);
        result.enqueue(response);
    }

    @Override
    public void editItem(int userId, int categoryId, int itemId, ItemViewModel itemViewModel, Callback<ItemDTO> response) {
        Call<ItemDTO> result = getApiService().editItem(userId, categoryId, itemId, itemViewModel);
        result.enqueue(response);
    }

    @Override
    public void deleteItem(int userId, int categoryId, int itemId, Callback<ResponseBody> response) {
        Call<ResponseBody> result = getApiService().deleteItem(userId, categoryId, itemId);
        result.enqueue(response);
    }

    // endregion
}
