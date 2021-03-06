package com.uksusoff.rock63.ui

import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.widget.ViewStubCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.uksusoff.rock63.R
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.ViewById

/**
 * Created by Vyacheslav Vodyanov on 13.05.2016.
 */
@EActivity
abstract class BaseMenuActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    @ViewById(R.id.drawer_layout)
    protected lateinit var drawer: DrawerLayout
    @ViewById(R.id.toolbar)
    protected lateinit var toolbar: Toolbar
    @ViewById(R.id.nav_view)
    protected lateinit var navigationView: NavigationView

    @AfterViews
    protected open fun init() {
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        )

        drawer.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun setContentView(@IdRes layoutResID: Int) {
        super.setContentView(R.layout.a_base_with_menu)

        val contentStub = findViewById<ViewStubCompat>(R.id.content_stub)
        contentStub.layoutResource = layoutResID
        contentStub.inflate()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_news -> NewsListActivity_.intent(this).start()
            R.id.menu_events -> EventsListActivity_.intent(this).start()
            R.id.menu_info -> SettingsActivity_.intent(this).start()
            R.id.menu_radio_vz -> RadioPlayerActivity_.intent(this).start()
        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}