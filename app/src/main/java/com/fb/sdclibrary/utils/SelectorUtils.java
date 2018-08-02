package com.fb.sdclibrary.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.res.ResourcesCompat;

/**
 * Description: 代码生成图片选择器
 *
 * @author: guoyongping
 * @date: 16/7/21 16:20
 */

public class SelectorUtils {

    /**
     * 控件选择器
     *
     * @param idNormal   默认图片id
     * @param inSelected 选中时图片id
     * @return
     */
    public static StateListDrawable createDrawableSelector(int idNormal, int inSelected) {
        Drawable checked = ResourcesCompat.getDrawable(Utils.getApp().getResources(), inSelected, null);
        Drawable unchecked = ResourcesCompat.getDrawable(Utils.getApp().getResources(), idNormal, null);
        Drawable disabled = ResourcesCompat.getDrawable(Utils.getApp().getResources(), inSelected, null);
        StateListDrawable stateList = new StateListDrawable();
        int statePressed = android.R.attr.state_pressed;
        int stateChecked = android.R.attr.state_checked;
        int stateFocused = android.R.attr.state_focused;
        int stateEnable = android.R.attr.state_enabled;
        int stateSelected = android.R.attr.state_selected;

        stateList.addState(new int[]{-stateEnable}, disabled);
        stateList.addState(new int[]{stateChecked}, checked);
        stateList.addState(new int[]{statePressed}, checked);
        stateList.addState(new int[]{stateFocused}, checked);
        stateList.addState(new int[]{stateSelected}, checked);
        stateList.addState(new int[]{}, unchecked);
        return stateList;
    }

    /**
     * 控件选择器
     *
     * @param normalDrawable   默认时的drawable
     * @param selectedDrawable 选中时drawable
     * @return
     */
    public static StateListDrawable createDrawableSelector(Drawable normalDrawable, Drawable selectedDrawable) {
        StateListDrawable stateList = new StateListDrawable();
        int statePressed = android.R.attr.state_pressed;
        int stateChecked = android.R.attr.state_checked;
        int stateFocused = android.R.attr.state_focused;
        int stateEnable = android.R.attr.state_enabled;
        int stateSelected = android.R.attr.state_selected;

        stateList.addState(new int[]{-stateEnable}, selectedDrawable);
        stateList.addState(new int[]{stateChecked}, selectedDrawable);
        stateList.addState(new int[]{statePressed}, selectedDrawable);
        stateList.addState(new int[]{stateFocused}, selectedDrawable);
        stateList.addState(new int[]{stateSelected}, selectedDrawable);
        stateList.addState(new int[]{}, normalDrawable);
        return stateList;
    }
}
