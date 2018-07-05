package com.chiachen.portfolio.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.adapter.PaginationAdapter;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.processors.PublishProcessor;

public class RxPaginationActivity extends AppCompatActivity {
    private final int VISIBLE_OFFSET = 1;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private PublishProcessor<Integer> paginator = PublishProcessor.create();

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private LinearLayoutManager mLinearLayoutManager;
    PaginationAdapter paginationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_pagination);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManager);

        paginationAdapter = new PaginationAdapter();
        recyclerView.setAdapter(paginationAdapter);

        setupLoadMoreListener();
        subscribeForData();
    }

    private int lastVisibleItem, totalItemCount;
    private boolean loading = false;
    private int pageNumber = 1;

    private void setupLoadMoreListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = mLinearLayoutManager.getItemCount();
                lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
                if (!loading && totalItemCount<= (lastVisibleItem + VISIBLE_OFFSET)) {
                    pageNumber++;
                    paginator.onNext(pageNumber);
                    loading = true;
                }
            }
        });
    }

    private void subscribeForData() {
        Disposable disposable =
                paginator
                        .onBackpressureDrop()
                        .concatMap(new Function<Integer, Publisher<List<String>>>() {
                            @Override
                            public Publisher<List<String>> apply(@NonNull Integer page) throws Exception {
                                loading = true;
                                progressBar.setVisibility(View.VISIBLE);
                                return dataFromNetwork(page);
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<String>>() {
                            @Override
                            public void accept(@NonNull List<String> items) throws Exception {
                                    paginationAdapter.addItems(items);
                                    paginationAdapter.notifyDataSetChanged();
                                    loading = false;
                                    progressBar.setVisibility(View.INVISIBLE);
                                }
                        });

        compositeDisposable.add(disposable);
        paginator.onNext(pageNumber);

    }

    private Flowable<List<String>> dataFromNetwork(final int page) {
        return Flowable.just(true).delay(2, TimeUnit.SECONDS).map(new Function<Boolean, List<String>>() {
            @Override
            public List<String> apply(@NonNull Boolean value) throws Exception {
                List<String> items = new ArrayList<>();
                for (int i = 1; i <= 10; i++) {
                    items.add("Item " + (page * 10 + i));
                }
                return items;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
