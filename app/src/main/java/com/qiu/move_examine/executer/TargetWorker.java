package com.qiu.move_examine.executer;

import com.qiu.move_examine.contract.TargetContract;

/**
 * @author Mr.Qiu
 */
public class TargetWorker implements TargetContract.TargetExecute {
    private TargetContract.TargetPresenter presenter;

    public TargetWorker(TargetContract.TargetPresenter presenter) {
        this.presenter = presenter;
    }
}
