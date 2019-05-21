package org.wordpress.android.ui.stats.refresh.lists.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.wordpress.android.fluxc.store.StatsStore.InsightType.LATEST_POST_SUMMARY
import org.wordpress.android.fluxc.store.StatsStore.StatsType
import org.wordpress.android.fluxc.store.StatsStore.TimeStatsType.OVERVIEW
import org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem

abstract class BaseStatsViewHolder(
    parent: ViewGroup,
    @LayoutRes layout: Int
) : ViewHolder(LayoutInflater.from(parent.context).inflate(layout, parent, false)) {
    @CallSuper
    open fun bind(statsType: StatsType?, items: List<BlockListItem>) {
        if (statsType == OVERVIEW || statsType == LATEST_POST_SUMMARY) {
            val layoutParams = itemView.layoutParams as? androidx.recyclerview.widget.StaggeredGridLayoutManager.LayoutParams
            layoutParams?.isFullSpan = true
        }
    }
}
