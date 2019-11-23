package android.example.com.boguscode.view;

import android.content.Context;
import android.example.com.boguscode.R;
import android.example.com.boguscode.VideoAdapter;
import android.example.com.boguscode.di.MyApplication;
import android.example.com.boguscode.di.component.ApplicationComponent;
import android.example.com.boguscode.di.component.MainActivityComponent;
import android.example.com.boguscode.di.qualifier.ActivityContext;
import android.example.com.boguscode.di.qualifier.ApplicationContext;
import android.example.com.boguscode.presenter.MainPresenter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements VideoAdapter.OnRecyclerViewItemClickListener, MainView {
    private static String TAG = "MainActivity";

    private RecyclerView mVideoRecyclerView;
    private ProgressBar mProgressBar;
    private TextView mStaffPicks;
    private CardView mErrorViewCard;
    private VideoAdapter mVideoAdapter;

//    private MainPresenter mMainPresenter;

    @Inject
    @ApplicationContext
    public Context mContext;

    @Inject
    @ActivityContext
    public Context activityContext;

    @Inject
    MainPresenter mMainPresenter;

    MainActivityComponent mMainActivityComponent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();


//        mainActivityComponent = DaggerMainActivityComponent.builder()
//                .mainActivityContextModule(new MainActivityContextModule(this))
//                .mainActivityMvpModule(new MainActivityMvpModule(this))
//                .applicationComponent(applicationComponent)
//                .build();

        mMainActivityComponent.injectMainActivity(this);


        setUpMVP();
        bindViews();
        setLayoutManager();
        getVideoList();

        mErrorViewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainPresenter.onErrorViewCardClick();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onItemClick(int position) {
        mMainPresenter.onListItemClick(position);
    }

    @Override
    public void setData(ArrayList<JSONObject> dataList) {
        mVideoAdapter = new VideoAdapter(dataList, this);
        mVideoRecyclerView.setAdapter(mVideoAdapter);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        mStaffPicks.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mStaffPicks.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showErrorViewCard() {
        mErrorViewCard.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideErrorViewCard() {
        mErrorViewCard.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showVideoListView() {
        mVideoRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideVideoListView() {
        mVideoRecyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public String getRecyclerItemClickMessage(int position) {
        return getResources().getString(R.string.video_click_toast_message, position);
    }

    private void setUpMVP() {
//        mMainPresenter = new MainPresenterImpl(this);
    }

    private void getVideoList() {
        mMainPresenter.getVideoList();
    }

    private void bindViews() {
        mVideoRecyclerView = findViewById(R.id.videoListRecycler);
        mVideoRecyclerView.setHasFixedSize(true);
        mProgressBar = findViewById(R.id.progressBar);
        mStaffPicks = findViewById(R.id.videoStaffPicksText);
        mErrorViewCard = findViewById(R.id.errorView);
    }

    private void setLayoutManager() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mVideoRecyclerView.setLayoutManager(layoutManager);
    }
}
