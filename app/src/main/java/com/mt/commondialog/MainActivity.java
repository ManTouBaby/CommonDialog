package com.mt.commondialog;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;


import com.hy.upperdialog.DownloadDialog;
import com.mt.commondialog.utils.ToastUtils;
import com.hy.upperdialog.EditDialog;
import com.hy.upperdialog.ListDialog;
import com.hy.upperdialog.LoadingDialog;
import com.hy.upperdialog.TipDialog;
import com.hy.upperdialog.listener.SimpleCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView_life)
    RecyclerView lifeIndexRecyclerView;

    @BindView(R.id.recyclerView_test)
    RecyclerView testIndexRecyclerView;

    private List<String> lifeIndices;
    private List<String> testIndices;

    private LifeIndexAdapter lifeIndexAdapter;
    private LifeIndexAdapter lifeIndexAdapter2;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);


        lifeIndexRecyclerView.setNestedScrollingEnabled(false);
        lifeIndexRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        lifeIndices = new ArrayList<>();
        lifeIndices.add("dialog");
        lifeIndices.add("Toast");
        lifeIndices.add("Popup");
        lifeIndices.add("Float");
        lifeIndexAdapter = new LifeIndexAdapter(this, lifeIndices);
        lifeIndexAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0://dialog
                        startActivity(new Intent(MainActivity.this, DialogActivity.class));
                        break;
                    case 1://Toast
                        startActivity(new Intent(MainActivity.this, ToastActivity.class));
                        // Toast.makeText(MainActivity.this,"待开发",Toast.LENGTH_LONG).show();
                        break;
                    case 2://Popup
                        startActivity(new Intent(MainActivity.this, PopupActivity.class));
                        // Toast.makeText(MainActivity.this,"待开发",Toast.LENGTH_LONG).show();
                        break;
                    case 3://Float
                        ToastUtils.show(MainActivity.this, "待开发");
                        //  Toast.makeText(MainActivity.this,"待开发",Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
        lifeIndexRecyclerView.setItemAnimator(new DefaultItemAnimator());
        lifeIndexRecyclerView.setAdapter(lifeIndexAdapter);


        testIndexRecyclerView.setNestedScrollingEnabled(false);
        testIndexRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        testIndices = new ArrayList<>();
        testIndices.add("LoadingDialog");
        testIndices.add("TipDialog");
        testIndices.add("ListDialog");
        testIndices.add("DownloadDialog");
        testIndices.add("EditDialog");
        lifeIndexAdapter2 = new LifeIndexAdapter(this, testIndices);
        lifeIndexAdapter2.setOnItemClickListener((parent, view, position, id) -> {
            switch (position) {
                case 0://LoadingDialog
                    Dialog dialog = LoadingDialog.createLoadingDialog(MainActivity.this, "请稍后...");
                    dialog.show();
                    App.sHandler.postDelayed(dialog::dismiss, 2000);
                    break;
                case 1://TipDialog
                    TipDialog.with(MainActivity.this, false)
                            // .yesTextColor(R.color.colorAccent)
                            //.setAdaption(false)
                            //.setAdaptionSize(false,300)
                            .message("你觉得好看么？")
                            .onYes(new SimpleCallback<Void>() {
                                @Override
                                public void onResult(Void data) {

                                }
                            })
                            .show();
                    break;
                case 2://ListDialog
                    List<String> lisStr = new ArrayList<>();
                    lisStr.add("选项1");
                    lisStr.add("选项2");
                    lisStr.add("选项3");
                    lisStr.add("选项4");

                    ListDialog.with(MainActivity.this)
                            .cancelable(true)
                            // .noTextColor(R.color.colorAccent)
                            .noYseBtn() //不显示确定按钮 三个相互冲突，请设置一个
                            //.noNoBtn()  //不显示取消按钮
                            //.noBtn() // 不显示按钮
//                        .title("列表动画")
                            .datas(lisStr)
                            .currSelectPos(1)
                            .listener((data, pos) -> {
                                Log.d("selectStr", lisStr.get(pos));
                                Toast.makeText(MainActivity.this, lisStr.get(pos), Toast.LENGTH_LONG).show();
                            })
                            .show();
                    break;
                case 3://DownloadDialog
//                    String url = "https://download.xloong.com/app/ARFusionMedia_20200508__v0.0.2.apk";
                    // String url="https://pdds-cdn.uc.cn/27-0/QuarkBrowser/2004/a918c565822ca56db5d5b3602b635ba1/QuarkBrowser_V4.1.0.132_android_pf3300_(Build200428142217).apk?auth_key=1589333697-0-0-a5e5c76b8bfc2a22c261511c3a3befac&SESSID=c0d03eea699479f67804585998605065";

                    //https://pdds-cdn.uc.cn/27-0/QuarkBrowser/2004/a918c565822ca56db5d5b3602b635ba1/QuarkBrowser_V4.1.0.132_android_pf3300_(Build200428142217).apk?auth_key=1589333697-0-0-a5e5c76b8bfc2a22c261511c3a3befac&SESSID=c0d03eea699479f67804585998605065
//                        download("",url,"",false);
                    String url = "http://20.97.0.221:7000/sjzh3/fileserver-sjzh/file/downloadFile.do?id=7a107cb6-e431-46c7-94d9-3a0bad27ec6a";
//                    DownloadDialog.with(MainActivity.this, true, url).setAutoInstall(true);
                    DownloadDialog.with(this, true,url)
                            .setAutoInstall(true)
                            .setFileName("zhsx.apk")
                            .setTitle("指挥视讯")
                            .startDownload();
                    break;
                case 4:
                    EditDialog.with(this)
                            .content("我是输入框数据我是输入框数据我是输入框数据我是输入框数据我是输入框数据")
                            .title("勤务汇报")
                            .listener((editView, label) -> {
                                System.out.println("我是汇报数据---->" + label);
                            })
                            .show();
                    break;
            }
        });
        testIndexRecyclerView.setItemAnimator(new DefaultItemAnimator());
        testIndexRecyclerView.setAdapter(lifeIndexAdapter2);
    }

    private static final int REQ_CODE_PERMISSION = 1;

    //    private RuntimeRequester mRuntimeRequester;
//    private void download(final String versionName, final String url, final String urlBackup, final boolean isForce) {
//        mRuntimeRequester = PermissionUtils.request(new RequestListener() {
//            @Override
//            public void onSuccess() {
//                DownloadDialog.with(MainActivity.this, isForce, url);
//            }
//
//            @Override
//            public void onFailed() {
//            }
//        }, MainActivity.this, REQ_CODE_PERMISSION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
//    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (mRuntimeRequester != null) {
//            mRuntimeRequester.onActivityResult(requestCode);
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


}
