package com.ducdiep.newsapp.app

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.ducdiep.newsapp.IS_BACKGROUND
import com.ducdiep.newsapp.activities.LockScreenActivity

class App : Application(), Application.ActivityLifecycleCallbacks,
    DefaultLifecycleObserver {

    private var isBackground = false

    override fun onCreate() {
        super<Application>.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        registerActivityLifecycleCallbacks(this)
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        isBackground = true
    }

    override fun onActivityResumed(p0: Activity) {
        if (isBackground && p0 !is LockScreenActivity) {
            val intent = Intent(this, LockScreenActivity::class.java)
            intent.putExtra(IS_BACKGROUND, true)
            p0.startActivity(intent)
            isBackground = false
        }
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) = Unit

    override fun onActivityStarted(activity: Activity) = Unit

    override fun onActivityPaused(activity: Activity) = Unit

    override fun onActivityStopped(activity: Activity) = Unit

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) = Unit

    override fun onActivityDestroyed(activity: Activity) = Unit
}