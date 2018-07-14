package com.chiachen.portfolio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.activity.customv.CustomViewExampleActivity;
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
    private static Map<Integer, String> sMap;

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
                case 22:{
                    openFragmentTabHost(view);
                    break;
                }
                case 23:{
                    openCountryWall(view);
                    break;
                }
                case 24:{
                    openWeather(view);
                    break;
                }

                case 25:{
                    openFragmentWithAnim(view);
                    break;
                }

                case 26:{
                    openActivityTransition(view);
                    break;
                }
                case 27:{
                    openFabAndSnackbar(view);
                    break;
                }
                case 28:{
                    openFabFollowsWidget(view);
                    break;
                }
                case 29:{
                    openCollapsingToolbar(view);
                    break;
                }
                case 30:{
                    openRxPagination(view);
                    break;
                }
                case 31:{
                    openCustomView(view);
                    break;
                }
                case 32:{
                    openCustomViewExample(view);
                    break;
                }
                case 33:{
                    openIntentService(view);
                    break;
                }
                case 34:{
                    openMergeViewStub(view);
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
        mRecyclerViewHomeAdapter = new RecyclerViewHomeAdapter(this, sMap);
        mRecyclerView.setAdapter(mRecyclerViewHomeAdapter);
        mRecyclerViewHomeAdapter.setOnItemClickListener(mOnItemClickListener);
    }


    private void prepareItemName() {
        sMap = new LinkedHashMap<Integer, String>() {{
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
            put(22, getString(R.string.fragment_tab_host));
            put(23, getString(R.string.weather));
            put(24, getString(R.string.mix_recycler_view));
            put(25, getString(R.string.fragment_with_anim));
            put(26, getString(R.string.activity_transition_1));
            put(27, getString(R.string.fab_and_snackbar));
            put(28, getString(R.string.fab_follows_widget));
            put(29, getString(R.string.collapsing_example));
            put(30, getString(R.string.rx_pagination));
            put(31, getString(R.string.custom_view));
            put(32, getString(R.string.custom_view_example));
            put(33, getString(R.string.intent_service_1));
            put(34, getString(R.string.merge_view_stub));
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

    public void openFragmentTabHost(View view) {
        startActivity(new Intent(MainActivity.this, FragmentTabHostActivity.class));
    }

    public void openCountryWall(View view) {
        startActivity(new Intent(MainActivity.this, WeatherActivity.class));
    }

    public void openWeather(View view) {
        startActivity(new Intent(MainActivity.this, RecyclerMixViewActivity.class));
    }

    public void openFragmentWithAnim(View view) {
        startActivity(new Intent(MainActivity.this, FragmentWithAnimActivity.class));
    }

    private void openActivityTransition(View view) {
        Intent intent_info = new Intent(MainActivity.this,GestureDetectorActivity.class);
        startActivity(intent_info);
        overridePendingTransition(R.anim.slide_up_info,R.anim.no_change);
    }

    private void openFabAndSnackbar(View view) {
        startActivity(new Intent(MainActivity.this, FabAndSnackbarActivity.class));
    }

    private void openFabFollowsWidget(View view) {
        startActivity(new Intent(MainActivity.this, FabFollowsWidgetActivity.class));
    }

    private void openCollapsingToolbar(View view) {
        startActivity(new Intent(MainActivity.this, CollapsingToolbarActivity.class));
    }

    private void openRxPagination(View view) {
        startActivity(new Intent(MainActivity.this, RxPaginationActivity.class));
    }

    private void openCustomView(View view) {
        startActivity(new Intent(MainActivity.this, CustomViewActivity.class));
    }

    private void openCustomViewExample(View view) {
        startActivity(new Intent(MainActivity.this, CustomViewExampleActivity.class));
    }

    private void openIntentService(View view) {
        startActivity(new Intent(MainActivity.this, IntentServiceActivity.class));
    }

    private void openMergeViewStub(View view) {
        startActivity(new Intent(MainActivity.this, MergeViewStubActivity.class));
    }

}
