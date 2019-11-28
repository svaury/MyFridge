package com.example.svaury.myfridge.presentatio.view;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import com.example.svaury.myfridge.App;
import com.example.svaury.myfridge.MainActivity;
import com.example.svaury.myfridge.R;
import com.example.svaury.myfridge.presentatio.model.Product;
import com.example.svaury.myfridge.presentatio.presenter.FoodPresenterImpl;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cbm0447 on 22/12/2017.
 */

public class FridgeListFragment extends Fragment implements ProductView {


    @Inject
    FoodPresenterImpl foodPresenter;

    @BindView(R.id.rvProducts)
    RecyclerView recyclerView;

    ArrayList<Product> productDetails = new ArrayList<>();
    ProductAdapter productAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fridge_list_layout,null);

        ButterKnife.bind(this,view);
        ((App) getActivity().getApplication()).getComponent().inject(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        foodPresenter.init(this);
        productAdapter = new ProductAdapter(productDetails);
        recyclerView.setAdapter(productAdapter);
        ItemTouchHelper ith = new ItemTouchHelper(new CustomItemTouchHelperCallback(productAdapter));
        ith.attachToRecyclerView(recyclerView);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        productAdapter.resetList();
        foodPresenter.getAllProducts();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        productAdapter.resetList();
        foodPresenter.getAllProducts();

    }

    @Override
    public void addProductList(Product product) {
        if(!productAdapter.getProducts().contains(product)) {
            productAdapter.addProduct(product);
        }
    }


    @OnClick (R.id.switchFragmentButton)
    public void switchToScan(){
        Fragment f = new ScanBarFragment();
        f.setTargetFragment(this, Activity.RESULT_OK);
        ((MainActivity) getActivity()).switchFragment(f,"scanBar");

    }

    @Override
    public void removeProductList(@NotNull Product product) {
        productAdapter.removeProduct(product);
    }
}
