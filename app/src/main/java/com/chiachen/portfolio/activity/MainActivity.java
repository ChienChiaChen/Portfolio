package com.chiachen.portfolio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.activity.di.Dagger2ExampleActivity;
import com.chiachen.portfolio.activity.eventbus.EventBusMainActivity;
import com.chiachen.portfolio.activity.page.RoutingPageAActivity;
import com.chiachen.portfolio.activity.rxjava.RxJavaExampleActivity;
import com.chiachen.portfolio.adapter.OnItemClickListener;
import com.chiachen.portfolio.adapter.RecyclerViewHomeAdapter;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerViewHomeAdapter mRecyclerViewHomeAdapter;
    private static Map<Integer, String> mMap;

    private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {
        @Override
        public void ItemClickListener(View view, int position) {
            switch (position) {
                case 0: {
                    startProgressButtonActivity(view);
                    break;
                }
                case 1: {
                    openKeyboardActivity(view);
                    break;
                }
                case 2: {
                    openBottomTabActivity(view);
                    break;
                }
                case 3: {
                    openMVPActivity(view);
                    break;
                }
                case 4: {
                    openRxJavaRetrofit(view);
                    break;
                }
                case 5: {
                    openRoundImageView(view);
                    break;
                }
                case 6: {
                    openFlipCardActivity(view);
                    break;
                }
                case 7: {
                    openTransitionActivity(view);
                    break;
                }
                case 8: {
                    openReposListActivity(view);
                    break;
                }
                case 9: {
                    openRxJavaExampleActivity(view);
                    break;
                }
                case 10: {
                    openTabLayoutPractices(view);
                    break;
                }
                case 11: {
                    openBroadcastActivity(view);
                    break;
                }
                case 12: {
                    openSqliteActivity(view);
                    break;
                }
                case 13: {
                    openServiceActivity(view);
                    break;
                }
                case 14: {
                    openParcelableActivity(view);
                    break;
                }
                case 15: {
                    openBottomDialogActivity(view);
                    break;
                }
                case 16: {
                    openRoutingPageActivity(view);
                    break;
                }
                case 17: {
                    openDrawerActivity(view);
                    break;
                }
                case 18: {
                    openContinuousDownload(view);
                    break;
                }
                case 19: {
                    openEventBusMainPage(view);
                    break;
                }
                case 20: {
                    openDagger2Example(view);
                    break;
                }

                case 21: {
                    openMyRecyclerView(view);
                    break;
                }
            }
        }

        @Override
        public void ItemLongClickListener(View view, int position) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareItemName();
        mRecyclerView = (RecyclerView) findViewById(R.id.layout_recycler_view);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerViewHomeAdapter = new RecyclerViewHomeAdapter(this, mMap);
        mRecyclerView.setAdapter(mRecyclerViewHomeAdapter);
        mRecyclerViewHomeAdapter.setOnItemClickListener(mOnItemClickListener);
    }

    private void prepareItemName() {
        mMap = new LinkedHashMap<Integer, String>() {{
            put(0, getString(R.string.progress_button));
            put(1, getString(R.string.edit_text_button));
            put(2, getString(R.string.bottom_tab));
            put(3, getString(R.string.mvp_practice));
            put(4, getString(R.string.rxjava_retrofit_1));
            put(5, getString(R.string.round_image_view));
            put(6, getString(R.string.flip_card));
            put(7, getString(R.string.activity_transition));
            put(8, getString(R.string.dagger2_with_github));
            put(9, getString(R.string.rx_java_example));
            put(10, getString(R.string.tab_layout));
            put(11, getString(R.string.broadcast_receiver));
            put(12, getString(R.string.sqlite));
            put(13, getString(R.string.service));
            put(14, getString(R.string.parcelable));
            put(15, getString(R.string.bottom_dialog));
            put(16, getString(R.string.routing_page));
            put(17, getString(R.string.drawer));
            put(18, getString(R.string.continuous_download));
            put(19, getString(R.string.event_bus_main_title));
            put(20, getString(R.string.dagger2_example));
            put(21, getString(R.string.recycler_view_example));
        }};
    }

    public void startProgressButtonActivity(View view) {
        startActivity(new Intent(MainActivity.this, ProgressButtonActivity.class));
    }

    public void openKeyboardActivity(View view) {
        startActivity(new Intent(MainActivity.this, EditTextActivity.class));
    }

    public void openBottomTabActivity(View view) {
        startActivity(new Intent(MainActivity.this, BottomTabActivity.class));
    }

    public void openMVPActivity(View view) {
        startActivity(new Intent(MainActivity.this, MVPPracticeActivity.class));
    }

    public void openRxJavaRetrofit(View view) {
        startActivity(new Intent(MainActivity.this, RxJavaRetrofitActivity.class));
    }

    public void openRoundImageView(View view) {
        startActivity(new Intent(MainActivity.this, CustomImageActivity.class));
    }

    public void openFlipCardActivity(View view) {
        startActivity(new Intent(MainActivity.this, FlipCardActivity.class));
    }

    public void openTransitionActivity(View view) {
        startActivity(new Intent(MainActivity.this, ActivityTransitionA.class));
    }

    public void openReposListActivity(View view) {
        startActivity(new Intent(MainActivity.this, ReposListActivity.class));
    }

    public void openRxJavaExampleActivity(View view) {
        startActivity(new Intent(MainActivity.this, RxJavaExampleActivity.class));
    }

    public void openTabLayoutPractices(View view) {
        startActivity(new Intent(MainActivity.this, TabLayoutActivity.class));
    }

    public void openBroadcastActivity(View view) {
        startActivity(new Intent(MainActivity.this, BroadcastActivity.class));
    }

    public void openSqliteActivity(View view) {
        startActivity(new Intent(MainActivity.this, SqliteActivity.class));
    }

    public void openServiceActivity(View view) {
        startActivity(new Intent(MainActivity.this, ServiceActivity.class));
    }

    public void openParcelableActivity(View view) {
        startActivity(new Intent(MainActivity.this, ParcelableActivity.class));
    }

    public void openBottomDialogActivity(View view) {
        startActivity(new Intent(MainActivity.this, BottomDialogTestActivity.class));
    }

    public void openRoutingPageActivity(View view) {
        startActivity(new Intent(MainActivity.this, RoutingPageAActivity.class));
    }

    public void openDrawerActivity(View view) {
        startActivity(new Intent(MainActivity.this, DrawerExampleActivity.class));
    }

    public void openContinuousDownload(View view) {
        startActivity(new Intent(MainActivity.this, ContinuousDownloadActivity.class));
    }

    public void openEventBusMainPage(View view) {
        startActivity(new Intent(MainActivity.this, EventBusMainActivity.class));
    }

    public void openDagger2Example(View view) {
        startActivity(new Intent(MainActivity.this, Dagger2ExampleActivity.class));
    }

    public void openMyRecyclerView(View view) {
        startActivity(new Intent(MainActivity.this, RecyclerViewExampleActivity.class));
    }
}
