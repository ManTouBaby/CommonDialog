package com.hy.upperdialog;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hy.upperdialog.view.DragLayout;

import static com.hy.upperdialog.utils.ResUtils.getResources;

/**
 * Author: Hrw
 * Date: 2020/9/15 16:28
 * Description: 可编辑输入框
 */
public class EditDialog {
    private final Context context;
    private CharSequence title;
    private CharSequence yesText;
    private CharSequence noText;
    private CharSequence inputContent;
    private OnEditInputListener mOnEditInputListener;
    private int yesTextColor = 0;
    private int noTextColor = 0;
    private boolean noBtn = false;
    private boolean noYesBtn;//是否显示确定
    private boolean noNoBtn = false;//是否显示取消
    private boolean cancelable = true;
    private EditText editText;

    public static EditDialog with(Context context) {
        return new EditDialog(context);
    }

    private EditDialog(Context context) {
        this.context = context;
//        Utils.init(context);
    }

    public EditDialog title(CharSequence title) {
        this.title = title;
        return this;
    }

    public EditDialog title(@StringRes int title) {
        this.title = context.getString(title);
        return this;
    }

    public EditDialog content(String inputContent) {
        this.inputContent = inputContent;
        return this;
    }

    public EditDialog yesText(CharSequence yesText) {
        this.yesText = yesText;
        return this;
    }

    public EditDialog yesText(@StringRes int yesText) {
        this.yesText = context.getString(yesText);
        return this;
    }

    public EditDialog noText(CharSequence noText) {
        this.noText = noText;
        return this;
    }

    public EditDialog noText(@StringRes int noText) {
        this.noText = context.getString(noText);
        return this;
    }

    public EditDialog noBtn() {
        noBtn = true;
        return this;
    }

    public EditDialog noYseBtn() {
        noYesBtn = true;
        return this;
    }

    public EditDialog noNoBtn() {
        noNoBtn = true;
        return this;
    }

    public EditDialog yesTextColor(@ColorRes int yesColor) {
        this.yesTextColor = yesColor;
        return this;
    }

    public EditDialog noTextColor(@ColorRes int noColor) {
        this.noTextColor = noColor;
        return this;
    }

    public EditDialog cancelable(boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    public EditDialog listener(OnEditInputListener listener) {
        this.mOnEditInputListener = listener;
        return this;
    }

    public void show() {
        Upper.dialog(context)
                .contentView(R.layout.basic_ui_dialog_edit)
                .gravity(Gravity.CENTER)
                .backgroundDimDefault()
                .dragDismiss(DragLayout.DragStyle.Bottom)
                .cancelableOnTouchOutside(cancelable)
                .cancelableOnClickKeyBack(cancelable)
                .bindData(layer -> {
                    editText = layer.getView(R.id.basic_ui_tv_dialog_edit);
                    editText.setText(inputContent);
                    editText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            inputContent = s;
                        }
                    });
                    LinearLayout llYesNo = layer.getView(R.id.basic_ui_ll_dialog_edit_yes_no);
                    View vLineH = layer.getView(R.id.basic_ui_v_dialog_edit_line_h);


                    if (noBtn) {
                        vLineH.setVisibility(View.GONE);
                        llYesNo.setVisibility(View.GONE);
                    } else {
                        vLineH.setVisibility(View.VISIBLE);
                        llYesNo.setVisibility(View.VISIBLE);
                        TextView tvYes = layer.getView(R.id.basic_ui_tv_dialog_edit_yes);
                        TextView tvNo = layer.getView(R.id.basic_ui_tv_dialog_edit_no);
                        View vLine = layer.getView(R.id.basic_ui_v_dialog_edit_line);
                        if (yesTextColor != 0) {
                            tvYes.setTextColor(getResources().getColor(yesTextColor));
                        }
                        if (noTextColor != 0) {
                            tvNo.setTextColor(getResources().getColor(noTextColor));
                        }
                        if (noNoBtn) {
                            tvNo.setVisibility(View.GONE);
                            vLine.setVisibility(View.GONE);
                        } else if (noYesBtn) {
                            noBtn = true;
                            tvYes.setVisibility(View.GONE);
                            vLine.setVisibility(View.GONE);
                        } else {
                            tvYes.setVisibility(View.VISIBLE);
                            tvNo.setVisibility(View.VISIBLE);
                            vLine.setVisibility(View.VISIBLE);
                        }
                        if (yesText != null) {
                            tvYes.setText(yesText);
                        } else {
                            tvYes.setText(R.string.basic_ui_dialog_btn_yes);
                        }
                        if (noText != null) {
                            tvNo.setText(noText);
                        } else {
                            tvNo.setText(R.string.basic_ui_dialog_btn_no);
                        }
                    }

                    TextView tvTitle = layer.getView(R.id.basic_ui_tv_dialog_edit_title);
                    if (title == null) {
                        tvTitle.setVisibility(View.GONE);
                    } else {
                        tvTitle.setText(title);
                    }
                })
                .onClickToDismiss((layer, v) -> {
                    if (mOnEditInputListener != null) {
                        mOnEditInputListener.OnEditInput(editText,inputContent);
                    }
                }, R.id.basic_ui_tv_dialog_edit_yes)
                .onClickToDismiss(R.id.basic_ui_tv_dialog_edit_no)
                .show();
    }

    public interface OnEditInputListener {
        void OnEditInput(EditText editText,CharSequence label);
    }
}
