package com.medisafe.task.view.list.adapter

import androidx.recyclerview.widget.GridLayoutManager
import com.medisafe.task.view.list.adapter.RecyclerViewAdapter.Companion.CARD_HALF_ROW

class SpanGroupSize (private val graph: CountryHolderTypeGraph): GridLayoutManager.SpanSizeLookup() {

    override fun getSpanSize(position: Int): Int {
        return if( graph.getTypeForPosition(position) == CARD_HALF_ROW) 1 else 2
    }

}



