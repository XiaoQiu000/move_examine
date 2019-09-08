package com.satsoftec.frame.presenter;

import com.satsoftec.frame.executer.BaseExecuter;

/**
 * @author 10124
 * @date 2017/7/26
 */
public interface ProgressPresenter<T extends BaseExecuter> extends BasePresenter<T> {
    //用来展示进度条
    void showProgress();

    //用来隐藏进度条
    void hideProgress();

    //用来关闭进度条弹窗（只是隐藏进度条弹窗，finish activity时会报错）
    void dismissProgress();
}
