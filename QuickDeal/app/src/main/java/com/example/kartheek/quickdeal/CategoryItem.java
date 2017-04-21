package com.example.kartheek.quickdeal;

/**
 * Created by kartheek on 18/4/17.
 */

public class CategoryItem {


    private String mCategoryName;

    private int mCategoryImage;

    public CategoryItem(String CategoryName, int CategoryImage) {
        mCategoryImage = CategoryImage;
        mCategoryName = CategoryName;
    }

    //getter function

    public String getCategoryName() {
        return mCategoryName;
    }

    public int getCategoryImage() {
        return mCategoryImage;
    }

    //setter function

    public void setCategoryName(String CategoryName) {
        mCategoryName = CategoryName;
    }

    public void setCategoryImage(int CategoryImage) {
        mCategoryImage = CategoryImage;
    }
}