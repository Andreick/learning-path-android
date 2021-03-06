package com.andreick.dependencyinjection.common.dependencyinjection.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.andreick.dependencyinjection.screens.common.ScreenNavigator
import com.andreick.dependencyinjection.screens.common.ScreenNavigatorImplementation
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
abstract class ActivityModule {

    @ActivityScoped
    @Binds
    abstract fun screenNavigator(screenNavigator: ScreenNavigatorImplementation): ScreenNavigator

    companion object {
        @Provides
        fun appCompactActivity(activity: Activity) = activity as AppCompatActivity

        @Provides
        fun layoutInflater(activity: Activity) = activity.layoutInflater

        @Provides
        fun fragmentManager(activity: AppCompatActivity) = activity.supportFragmentManager
    }
}