package com.erikriosetiawan.recursiveleague.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erikriosetiawan.recursiveleague.R.id.league_image
import com.erikriosetiawan.recursiveleague.R.id.league_name
import com.erikriosetiawan.recursiveleague.models.League
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class LeagueAdapter(
    private val leagues: List<League>,
    private val listener: (League) -> Unit
) : RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LeagueUI().createView(
                AnkoContext.create(p0.context, p0)
            )
        )
    }

    override fun getItemCount(): Int = leagues.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindItem(leagues[p1], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val leagueName: TextView = view.find(league_name)
        private val leagueImage: ImageView = view.find(league_image)

        fun bindItem(leagues: League, listener: (League) -> Unit) {
            leagueName.text = leagues.name
            loadImageBackground(leagues, this)
            itemView.setOnClickListener { listener(leagues) }
        }

        private fun loadImageBackground(league: League, holder: ViewHolder) {
            doAsync {
                val loadImage = league.image?.let { Picasso.get().load(it) }

                uiThread {
                    loadImage?.into(holder.leagueImage)
                }
            }

        }
    }

    class LeagueUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL

                    imageView {
                        id = league_image
                    }.lparams {
                        height = dip(50)
                        width = dip(50)
                    }

                    textView {
                        id = league_name
                        textSize = 16f
                    }.lparams {
                        margin = dip(15)
                    }
                }
            }
        }
    }
}