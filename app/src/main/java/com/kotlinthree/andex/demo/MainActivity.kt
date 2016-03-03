package com.kotlinthree.andex.demo

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView

import com.kotlinthree.andex.component.findView
import com.kotlinthree.andex.handler.post
import com.kotlinthree.andex.handler.postDelay

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val imageView = findView<ImageView>(R.id.image_view)

        getString(R.string.app_name)
        getString(R.string.action_settings_param, 1234)

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener (fun(view: View) {
            Snackbar.make(view, "Replace with your own action", Snackbar
                    .LENGTH_LONG)
                    .setAction("Action", null).show()
        })

//        Handler().postDelayed({ Log.d("MainActivity", "MainActivity ${this@MainActivity}") }, 20 * 1000)
        postDelay(delayMillis = 20 * 1000){
            Log.d("MainActivity", "MainActivity ${this@MainActivity}")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
