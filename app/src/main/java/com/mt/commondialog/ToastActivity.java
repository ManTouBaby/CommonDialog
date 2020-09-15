package com.mt.commondialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import com.mt.commondialog.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ToastActivity extends AppCompatActivity implements View.OnClickListener {


    private Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        unbinder = ButterKnife.bind(this);

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

   @OnClick({
           R.id.tv_show,
           R.id.tv_show_success,
           R.id.tv_show_fail,
           R.id.tv_show_normal,
   })
    @Override
    public void onClick(View v) {
       switch (v.getId()) {
           case R.id.tv_show:
               ToastUtils.show(ToastActivity.this,"轻量级浮层弹窗");
               break;
           case R.id.tv_show_success:
               ToastUtils.showSuccess(ToastActivity.this,"成功了");
               break;
           case R.id.tv_show_fail:
               ToastUtils.showFail(ToastActivity.this,"失败了");
               break;
           case R.id.tv_show_normal:
               ToastUtils.showNormal(ToastActivity.this,"测试");
               break;

       }
    }
}
