package com.mvp.cn.mvp.base;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.mvp.cn.utils.CustomDialogUtils;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import java.util.Optional;


/**
 * 作者： qiaohao
 * 时间： 2017/4/27 13:19
 * 说明：BaseActivity
 */
public abstract class BaseActivity<T extends BasePresenter, V extends IBaseView> extends RxAppCompatActivity {


    public Dialog mLoadding;

    protected T mPrensenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResID());
        mPrensenter = initPresenter();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Optional.ofNullable(mPrensenter).ifPresent(prensenter -> {
                getLifecycle().addObserver(prensenter);
                prensenter.attachView((V) this);
            });
        } else {
            if (mPrensenter != null) {
                getLifecycle().addObserver(mPrensenter);
                mPrensenter.attachView((V) this);
            }
        }
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Optional
                    .ofNullable(mPrensenter)
                    .ifPresent(t -> {
                        t.detachView();
                    });
        } else {
            if (mPrensenter != null) {
                mPrensenter.detachView();
            }
        }

    }

    // 该方法必须重写，返回acitivity中对应的xml的id
    protected abstract int layoutResID();

    // 初始化组件的方法
    protected abstract void initViews();

    //初始化presenter操作类
    protected abstract T initPresenter();


    /**
     * toast参数是字符串
     * 显示默认位置
     *
     * @param text
     */
    public void showMessage(CharSequence text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void showLoadingDialog() {
        if (mLoadding == null) {
            mLoadding = CustomDialogUtils.getInstance(this).showLoaddingDialog();
            mLoadding.show();
        } else if (mLoadding != null && !mLoadding.isShowing()) {
            mLoadding.show();
        }
    }

    /**
     * 取消loadding 对话框的显示
     */
    public void dismissLoaddingDialog() {
        if (mLoadding != null && mLoadding.isShowing()) {
            mLoadding.cancel();
            mLoadding = null;
        }

    }


}
