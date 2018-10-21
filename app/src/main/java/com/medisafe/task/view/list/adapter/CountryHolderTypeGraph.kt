package com.medisafe.task.view.list.adapter

import java.util.*

class CountryHolderTypeGraph(private vararg val sectionGroup: LinkedList<Int>) {

    // is used instead of SparseIntArray for quick test setup
    private val graph = HashMap<Int, Int>()

    private var sectionIterator: Iterator<LinkedList<Int>>
    private var groupIterator: Iterator<Int>

    init {
        sectionGroup.toMutableList()
        sectionIterator = sectionGroup.iterator()
        groupIterator = sectionIterator.next().iterator()
    }

    fun getTypeForPosition(position: Int) = graph[position]

    private fun peekSection(): LinkedList<Int> {
        return if (sectionIterator.hasNext()) {
            sectionIterator.next()
        } else {
            sectionIterator = sectionGroup.iterator()
            peekSection()
        }
    }

    private fun nextType(): Int {
        return if (groupIterator.hasNext()) {
            groupIterator.next()
        } else {
            groupIterator = peekSection().iterator()
            nextType()
        }

    }

    fun build(dataLength: Int) {
        if (graph.size > 0) {
            throw UnsupportedOperationException("CountryHolderTypeGraph has been already built")
        }
        for (i in 0..dataLength) {
            graph.put(i, nextType())
        }

    }

}

val cardSection = LinkedList<Int>(listOf(RecyclerViewAdapter.CARD, RecyclerViewAdapter.CARD))
val listRightTextSection = LinkedList<Int>(listOf(RecyclerViewAdapter.LIST_RIGHT_TEXT, RecyclerViewAdapter.LIST_RIGHT_TEXT))
val cardHalfRowSection = LinkedList<Int>(listOf(RecyclerViewAdapter.CARD_HALF_ROW, RecyclerViewAdapter.CARD_HALF_ROW))