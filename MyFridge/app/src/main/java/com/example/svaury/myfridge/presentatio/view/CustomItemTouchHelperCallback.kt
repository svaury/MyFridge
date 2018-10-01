package com.example.svaury.myfridge.presentatio.view

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper

/**
 * Created by cbm0447 on 27/12/2017.
 */
class CustomItemTouchHelperCallback( adapter: ProductAdapter): ItemTouchHelper.Callback() {

    var productAdapter : ProductAdapter = adapter

    override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int =
            ItemTouchHelper.Callback.makeMovementFlags(ItemTouchHelper.ACTION_STATE_SWIPE or ItemTouchHelper.ACTION_STATE_DRAG,
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
        productAdapter.switchProductsPosition(viewHolder?.adapterPosition?:0, target?.adapterPosition ?: 0 )
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) =
            productAdapter.removeProduct(viewHolder?.adapterPosition ?:0)


}