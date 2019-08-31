package com.erikriosetiawan.recursiveleague.activity

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.erikriosetiawan.recursiveleague.model.League
import org.jetbrains.anko.*

class LeagueDetailsActivity : AppCompatActivity() {

    lateinit var league: League

    companion object {
        const val LEAGUE_KEY = "ERSMDT1519"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        league = intent.getParcelableExtra(LEAGUE_KEY)
        LeagueDetailsActivityUI().setContentView(this)

        setActionBar(league.name)
    }

    inner class LeagueDetailsActivityUI : AnkoComponent<LeagueDetailsActivity> {
        override fun createView(ui: AnkoContext<LeagueDetailsActivity>) = with(ui) {

            scrollView {
                lparams(width = matchParent, height = matchParent)

                verticalLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.VERTICAL

                    imageView {
                        imageResource = league.image!!
                    }.lparams(width = dip(250), height = dip(250)) {
                        margin = dip(15)
                        gravity = Gravity.CENTER_HORIZONTAL
                    }

                    textView {
                        text = league.name
                        textSize = 16f
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams(width = matchParent, height = wrapContent) {
                        margin = dip(10)
                    }

                    textView {
                        text = league.description
                    }.lparams(width = matchParent, height = wrapContent) {
                        margin = dip(10)
                    }
                }
            }
        }
    }

    private fun setActionBar(title: String?) {
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = title
            (supportActionBar as ActionBar).setDisplayHomeAsUpEnabled(true)
            (supportActionBar as ActionBar).setDisplayShowHomeEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
