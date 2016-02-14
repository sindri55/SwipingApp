package com.example.swipingapp.services.inventory;

import com.example.swipingapp.DTOs.inventory.CategoryDTO;
import com.example.swipingapp.DTOs.inventory.ItemDTO;
import com.example.swipingapp.services.base.IBaseService;
import com.example.swipingapp.viewModels.inventory.CategoryViewModel;
import com.example.swipingapp.viewModels.inventory.ItemViewModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Callback;

public interface IInventoryService extends IBaseService {

    // region Public functions

    void getCategories(int userId, Callback<List<CategoryDTO>> response);

    void addCategory(int userId, CategoryViewModel categoryViewModel, Callback<CategoryDTO> response);
    void editCategory(int userId, int categoryId, CategoryViewModel categoryViewModel, Callback<CategoryDTO> response);
    void deleteCategory(int userId, int categoryId, Callback<ResponseBody> response);

    void addItem(int userId, int categoryId, ItemViewModel itemViewModel, Callback<ItemDTO> response);
    void editItem(int userId, int categoryId, int itemId, ItemViewModel itemViewModel, Callback<ItemDTO> response);
    void deleteItem(int userId, int categoryId, int itemId, Callback<ResponseBody> response);

    // endregion
}
