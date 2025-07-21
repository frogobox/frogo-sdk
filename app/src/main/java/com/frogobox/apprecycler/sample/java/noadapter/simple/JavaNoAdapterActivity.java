package com.frogobox.apprecycler.sample.java.noadapter.simple;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.frogobox.apprecycler.core.BaseJavaActivity;
import com.frogobox.apprecycler.model.ExampleModel;
import com.frogobox.databinding.ActivityFrogoRvListBinding;
import com.frogobox.recycler.core.FrogoRecyclerNotifyListener;
import com.frogobox.recycler.core.IFrogoViewAdapter;
import com.frogobox.ui.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class JavaNoAdapterActivity extends BaseJavaActivity<ActivityFrogoRvListBinding> {

    @NotNull
    @Override
    public ActivityFrogoRvListBinding setupViewBinding() {
        return ActivityFrogoRvListBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        setupFrogoRecyclerView();
        setupDetailActivity("Java No Adapter");
    }

    private ArrayList<ExampleModel> listData() {
        ArrayList<ExampleModel> exampleModels = new ArrayList<>();
        // exampleModels.add(new ExampleModel(Constant.NICK_NAME));
        // exampleModels.add(new ExampleModel(Constant.NICK_NAME));
        // exampleModels.add(new ExampleModel(Constant.NICK_NAME));
        // exampleModels.add(new ExampleModel(Constant.NICK_NAME));
        return exampleModels;
    }

    private void setupFrogoRecyclerView() {

        IFrogoViewAdapter frogoViewAdapterCallback = new IFrogoViewAdapter<ExampleModel>() {
            @Override
            public boolean areContentsTheSame(ExampleModel oldItem, ExampleModel newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areItemsTheSame(ExampleModel oldItem, ExampleModel newItem) {
                return oldItem.getName().equals(newItem.getName());
            }

            @Override
            public void onItemLongClicked(@NonNull View view, ExampleModel data, int position, @NonNull FrogoRecyclerNotifyListener<ExampleModel> notifyListener) {
                // setup item long clicked on frogo recycler view
                showToast(data.getName());
            }

            @Override
            public void onItemClicked(@NonNull View view, ExampleModel data, int position, @NonNull FrogoRecyclerNotifyListener<ExampleModel> notifyListener) {
                // setup item clicked on frogo recycler view
                showToast(data.getName());
            }

            @Override
            public void setupInitComponent(@NonNull View view, ExampleModel data, int position, @NonNull FrogoRecyclerNotifyListener<ExampleModel> notifyListener) {
                // Init component content item recyclerview
                TextView tvExample = view.findViewById(R.id.frogo_rv_list_type_1_tv_title);
                tvExample.setText(data.getName());
            }
        };

        binding.frogoRecyclerView.injector()
                .addData(listData())
                .addCustomView(R.layout.frogo_rv_list_type_1)
                .addEmptyView(null)
                .addCallback(frogoViewAdapterCallback)
                .createLayoutStaggeredGrid(2)
                .build();
    }

}