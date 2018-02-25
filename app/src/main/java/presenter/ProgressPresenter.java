package presenter;

import view.IProgressButtonView;

/**
 * Created by jianjiacheng on 25/02/2018.
 */

public class ProgressPresenter implements IProgressButtonPresenter {
    private IProgressButtonView mView;

    public ProgressPresenter(IProgressButtonView view) {
        mView = view;
    }

    @Override
    public void init() {
        mView.init();
    }

    @Override
    public void handleDownloading() {
        mView.handleDownloading();
    }

    @Override
    public void handleDownloadEnd() {
        mView.handleDownloadEnd();
    }
}
