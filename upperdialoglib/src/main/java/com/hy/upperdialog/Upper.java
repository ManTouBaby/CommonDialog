package com.hy.upperdialog;


import android.content.Context;
import android.view.View;

import com.hy.upperdialog.dialog.DialogLayer;
import com.hy.upperdialog.manager.ActivityHolder;
import com.hy.upperdialog.popup.PopupLayer;
import com.hy.upperdialog.toast.ToastLayer;
import com.hy.upperdialog.view.UpperActivity;

/**
 * @author zhangliyang
 * GitHub: https://github.com/ZLYang110
 */
public class Upper {
    /**
    * 弹窗
    * */
    public static DialogLayer dialog(Context context) {
        return new DialogLayer(context);
    }

    public static void dialog(UpperActivity.OnLayerCreatedCallback callback) {
        UpperActivity.start(ActivityHolder.getApplication(), callback);
    }
   public static DialogLayer dialog() {
        return new DialogLayer(ActivityHolder.getCurrentActivity());
    }

    /**
     * toast
     * */
    public static ToastLayer toast() {
        return new ToastLayer(ActivityHolder.getCurrentActivity());
    }

    public static ToastLayer toast(Context context) {
        return new ToastLayer(context);
    }

    /**
     * popup
     * */
    public static PopupLayer popup(Context context) {
        return new PopupLayer(context);
    }

    public static PopupLayer popup(View targetView) {
        return new PopupLayer(targetView);
    }
}



