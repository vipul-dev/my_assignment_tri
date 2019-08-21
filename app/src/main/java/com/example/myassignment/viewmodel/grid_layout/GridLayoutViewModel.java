package com.example.myassignment.viewmodel.grid_layout;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myassignment.R;
import com.example.myassignment.adapter.GridLayoutAdapter;
import com.example.myassignment.api.ApiCallBack;
import com.example.myassignment.api.ApiManager;
import com.example.myassignment.api.ApiResponse;
import com.example.myassignment.api.ApiResponseListener;
import com.example.myassignment.api.DataResponseOfIcon;
import com.example.myassignment.api.FormatData;
import com.example.myassignment.base.activity.ActivityViewModel;
import com.example.myassignment.interf.OnItemClickListener;
import com.example.myassignment.model.HomeModel;
import com.example.myassignment.util.AppConstant;
import com.example.myassignment.util.CommonUtils;
import com.example.myassignment.util.LogUtils;
import com.example.myassignment.util.ProgressDialogUtils;
import com.example.myassignment.util.ToastUtils;
import com.example.myassignment.view.grid_layout.GridLayoutActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class GridLayoutViewModel extends ActivityViewModel<GridLayoutActivity> implements ApiResponseListener<ApiResponse>, OnItemClickListener {

    private static final int REQUEST_PERMISSION_CODE = 101;
    private static final String TAG = GridLayoutActivity.class.getSimpleName();
    public ObservableArrayList<HomeModel> mDataList = new ObservableArrayList<>();
    public ObservableArrayList<HomeModel> tempList = new ObservableArrayList<>();
    public ObservableArrayList<DataResponseOfIcon> apiResponsesList = new ObservableArrayList<>();
    public boolean isLoading = false;
    public GridLayoutManager layoutManager;
    public int visibleItemCount, pastVisibleItem, totalItemCount, totalpages = 0;
    public int currentpage = 1;
    private GridLayoutActivity activity;
    private OnItemClickListener itemClickListener;
    private GridLayoutAdapter gridLayoutAdapter;
    private int  count = 10;
    private boolean isExists = false;

    public GridLayoutViewModel(GridLayoutActivity activity) {
        super(activity);

        this.activity = activity;
        this.itemClickListener = this;

        if (!checkPermissionFromDevice()) {
            requestPermission();
        }

        layoutManager = new GridLayoutManager(activity, 2);
        setToolbar();
        /*
        * Call Api for get icon list
        * */
        callIconSetApi(count);
        setPagination();
        setSearchView();


    }

    private void setPagination() {
        activity.getBinding().rvGrid.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                if (dy > 0) {
                    if (currentpage <= totalpages) {
                        pastVisibleItem = layoutManager.findFirstVisibleItemPosition();
                        visibleItemCount = layoutManager.getChildCount();
                        totalItemCount = layoutManager.getItemCount();
                        if (!isLoading) {
                            if ((visibleItemCount + pastVisibleItem) >= totalItemCount) {
                                isLoading = true;
                                callIconSetApi(count + visibleItemCount );

                            }
                        }
                    }
                }
            }
        });
    }

    private void callIconSetApi(int countNo) {
        if (CommonUtils.isOnline(activity)) {
            ProgressDialogUtils.show(activity);
            ApiManager apiManager = new ApiManager(activity);

            ApiCallBack<ApiResponse> callBack = new ApiCallBack<>(this, AppConstant.GET_ICONSET, activity);

            apiManager.getIconSet(callBack, "medieval-flaticons", AppConstant.CLIENT_ID, AppConstant.CLIENT_SECRET, countNo);


        } else {
            ToastUtils.showToastShort(activity, AppConstant.CHECK_CONNECTION);
        }
    }

    private void setSearchView() {
        activity.getBinding().toolbar.etSearchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                filter(text);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void filter(String text) {
        if (text.equalsIgnoreCase("")) {
            gridLayoutAdapter.updateList(tempList);
        } else {
            ObservableArrayList<HomeModel> filteredList = new ObservableArrayList<>();
            for (HomeModel model : tempList) {
                if (model.getImageName().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(model);
                }
                mDataList.clear();
                mDataList.addAll(filteredList);
                gridLayoutAdapter.updateList(mDataList);
            }
        }
    }

    private void setToolbar() {
        activity.getBinding().toolbar.tvTitle.setVisibility(View.VISIBLE);
        activity.getBinding().toolbar.ivSearch.setVisibility(View.VISIBLE);
        activity.getBinding().toolbar.ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.getBinding().toolbar.llSearch.setVisibility(View.VISIBLE);
                activity.getBinding().toolbar.tvTitle.setVisibility(View.GONE);
                activity.getBinding().toolbar.ivSearch.setVisibility(View.GONE);
            }
        });

        activity.getBinding().toolbar.ivCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.getBinding().toolbar.llSearch.setVisibility(View.GONE);
                activity.getBinding().toolbar.tvTitle.setVisibility(View.VISIBLE);
                activity.getBinding().toolbar.ivSearch.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setRecycler(ObservableArrayList<DataResponseOfIcon> apiResponsesList) {
        ObservableArrayList<HomeModel> dataList = new ObservableArrayList<>();
        for (int i = 0; i < apiResponsesList.size(); i++) {
            HomeModel model = new HomeModel();

            model.setImage(apiResponsesList.get(i).getRasterSizes().get(apiResponsesList.get(i).getRasterSizes().size() - 1).getFormats().get(0).getPreviewUrl());
            model.setImageName(apiResponsesList.get(i).getRasterSizes().get(apiResponsesList.get(i).getRasterSizes().size() - 1).getFormats().get(0).getFormat());

            dataList.add(model);
        }
        mDataList.clear();
        tempList.clear();
        mDataList.addAll(dataList);
        tempList.addAll(dataList);

        gridLayoutAdapter = new GridLayoutAdapter(activity, mDataList, itemClickListener);
        activity.getBinding().rvGrid.setLayoutManager(layoutManager);
        activity.getBinding().rvGrid.setAdapter(gridLayoutAdapter);
        visibleItemCount = layoutManager.getItemCount();
        totalpages = (int) Math.ceil(totalItemCount / visibleItemCount);


    }



    private void requestPermission() {
        ActivityCompat.requestPermissions(activity, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE

        }, REQUEST_PERMISSION_CODE);
    }

    private boolean checkPermissionFromDevice() {
        int write_external_storage = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int read_external_storage = ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO);

        return write_external_storage == PackageManager.PERMISSION_GRANTED &&
                read_external_storage == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onApiSuccess(ApiResponse response, String apiName) {
        ProgressDialogUtils.dismiss();
        if (response != null) {
            if (currentpage == 1) {
                apiResponsesList.clear();
                apiResponsesList = response.getIconsSetData();
            } else {
                apiResponsesList.clear();
                apiResponsesList.addAll(response.getIconsSetData());
            }
            totalItemCount = response.getTotalCount();
            isLoading = false;
            currentpage++;
            setRecycler(apiResponsesList);
        }

    }


    @Override
    public void onApiError(String response, String apiName) {
        ProgressDialogUtils.dismiss();
    }

    @Override
    public void onApiFailure(String failureMessage, String apiName) {
        ProgressDialogUtils.dismiss();
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId()) {
            case R.id.ivdownload:

                if (!checkPermissionFromDevice()) {
                    requestPermission();
                    new DownLoadingFile(mDataList.get(position).getImage()).execute();
                } else {
                    new DownLoadingFile(mDataList.get(position).getImage()).execute();
                }
                break;

            default:
                break;

        }
    }

    /*
     * For Download png file
     *
     * */


    public class DownLoadingFile extends AsyncTask<String, String, String> {

        File apkStorage = null;
        File outputFile = null;
        private String downloadUrl;

        public DownLoadingFile(String downloadUrl) {
            this.downloadUrl = downloadUrl;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ToastUtils.showToastShort(activity, "Start downloading in background");
        }

        @Override
        protected String doInBackground(String... url) {


            File file = new File(downloadUrl);

            String imageName = file.getName();

            try {
                URL url1 = new URL(downloadUrl);
                HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
                connection.connect();


                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    LogUtils.LOGE(TAG, "Server returned HTTP" + connection.getResponseCode()
                            + " " + connection.getResponseMessage());

                }

                apkStorage = new File(Environment.getExternalStorageDirectory() + "/MyAssignment");

                if (!apkStorage.exists()) {
                    apkStorage.mkdir();

                    LogUtils.LOGE(TAG + "====>", "Directory Created");
                }

                String downloadFileName = imageName;

                outputFile = new File(apkStorage, downloadFileName);

                /*Create file if is not present*/

                if (!outputFile.exists()) {
                    outputFile.createNewFile();


                    LogUtils.LOGE(TAG + "====>", "File downloaded successfully.");
                    isExists = false;

                } else {
                    isExists = true;

                }

                FileOutputStream fos = new FileOutputStream(outputFile);

                InputStream is = connection.getInputStream();

                byte[] buffer = new byte[1024];//set buffer type

                int len = 0;

                while ((len = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
                is.close();


            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (isExists) {
                ToastUtils.showToastShort(activity, "This File is already downloaded.");
            } else {
                ToastUtils.showToastShort(activity, "File downloaded successfully.");
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            ToastUtils.showToastShort(activity, "File downloaded successfully.");

        }
    }
}
