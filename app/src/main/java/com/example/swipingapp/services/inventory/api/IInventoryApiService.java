package com.example.swipingapp.services.inventory.api;

import com.example.swipingapp.DTOs.inventory.CategoryDTO;
import com.example.swipingapp.DTOs.inventory.ItemDTO;
import com.example.swipingapp.viewModels.inventory.CategoryViewModel;
import com.example.swipingapp.viewModels.inventory.ItemViewModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IInventoryApiService {

    // region API endpoints

    @GET("inventory/{userId}/")
    Call<List<CategoryDTO>> getCategories(@Path("userId") int userId);

    @POST("inventory/{userId}/")
    Call<CategoryDTO> addCategory(@Path("userId") int userId, @Body CategoryViewModel categoryViewModel);

    @PUT("inventory/{userId}/{categoryId}/")
    Call<CategoryDTO> editCategory(@Path("userId") int userId, @Path("categoryId") int categoryId, @Body CategoryViewModel categoryViewModel);

    @DELETE("inventory/{userId}/{categoryId}")
    Call<ResponseBody> deleteCategory(@Path("userId") int userId, @Path("categoryId") int categoryId);

    @POST("inventory/{userId}/{categoryId}/")
    Call<ItemDTO> addItem(@Path("userId") int userId, @Path("categoryId") int categoryId, @Body ItemViewModel itemViewModel);

    @PUT("inventory/{userId}/{categoryId}/{itemId}")
    Call<ItemDTO> editItem(@Path("userId") int userId, @Path("categoryId") int categoryId, @Path("itemId") int itemId, @Body ItemViewModel itemViewModel);

    @DELETE("inventory/{userId}/{categoryId}/{itemId}")
    Call<ResponseBody> deleteItem(@Path("userId") int userId, @Path("categoryId") int categoryId, @Path("itemId") int itemId);

    // endregion
}
