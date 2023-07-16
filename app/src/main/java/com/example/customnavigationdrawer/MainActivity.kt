package com.example.customnavigationdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.customnavigationdrawer.fragment.FavoriteFragment
import com.example.customnavigationdrawer.fragment.HistoryFragmet
import com.example.customnavigationdrawer.fragment.HomeFragment
import com.google.android.material.navigation.NavigationView


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val FRAGMENT_HOME =1
    private val FRAGMENT_FAVORITE =2
    private val FRAGMENT_HISTORY =3

    private var mCurrentFragment =FRAGMENT_HOME

    private  lateinit var mDrawerLayout : DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mDrawerLayout =findViewById(R.id.drawer_layout)
        val mToolbar : Toolbar =findViewById(R.id.toolbar)
        setSupportActionBar(mToolbar)
        val toggle = ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        mDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView: NavigationView =findViewById(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener (this)

        replaceFragment(HomeFragment())
        navigationView.menu.findItem(R.id.nav_home).isChecked = true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val id : Int =item.itemId
        if(id==R.id.nav_home){
            if(mCurrentFragment!=FRAGMENT_HOME){
                replaceFragment(HomeFragment())
                mCurrentFragment=FRAGMENT_HOME
            }
        }
        else if(id==R.id.nav_favorite){
            if(mCurrentFragment!=FRAGMENT_FAVORITE){
                replaceFragment(FavoriteFragment())
                mCurrentFragment=FRAGMENT_FAVORITE
            }
        }
        else if(id==R.id.nav_history){
            if(mCurrentFragment!=FRAGMENT_HISTORY){
                replaceFragment(HistoryFragmet())
                mCurrentFragment=FRAGMENT_HISTORY
            }
        }
        // khi click vào thì drawer sẽ thu lại
        mDrawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    @Deprecated("Deprecated in Java")
    // khi ấn nút back thì app sẽ k thoát ra ngoài ma chỉ đóng drawer
    override fun onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START)
        }
        else{
            super.onBackPressed()
        }
    }
    fun replaceFragment(fragment: Fragment) {
        val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content_frame, fragment)
        // xác nhận và thực hiện sự thay đổi fragment
        transaction.commit()
    }

}