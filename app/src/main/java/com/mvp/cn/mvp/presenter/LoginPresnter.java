package com.mvp.cn.presenter;

import android.os.Handler;
import android.os.Message;

import com.mvp.cn.iview.ILoginView;

/**
 * 作者： qiaohao
 * 时间： 2017/4/27 14:02
 * 说明：LoginPresnter
 */
public class LoginPresnter extends BasePresenter<ILoginView> {

    private static final int LOGIN_REQUEST_CODE = 0x01;
    public ILoginView mLoginInterface;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mLoginInterface.dismissLoadingDialog();
            switch (msg.what){
                case 1:
                    mLoginInterface.requestSuccess();
                    break;
                case 3:
                    int progress = (int) msg.obj;
                    mLoginInterface.onProgress(progress);
                    break;
                case 2:
                    mLoginInterface.requestFail();
                    break;
            }
        }
    };



    public LoginPresnter(ILoginView loginInterface) {
        this.mLoginInterface = loginInterface;
    }

    public void login() {
//        String userName = mLoginInterface.getUserName();
//        String userPwd = mLoginInterface.getUserPwd();
//        if (userName != null && !userName.equals("") && userPwd != null && !userPwd.equals("")) {
//            HttpRequestUtil.getOkClient().login(userPwd).enqueue(new Callback<String>() {
//                @Override
//                public void onResponse(Call<String> call, Response<String> response) {
//                    mHandler.sendEmptyMessage(1);
//                }
//
//                @Override
//                public void onFailure(Call<String> call, Throwable t) {
//                    mHandler.sendEmptyMessage(2);
//                }
//            });
//
//
//        }


    }
}