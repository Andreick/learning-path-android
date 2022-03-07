package com.andreick.dependencyinjection.screens.common.fragments

import androidx.fragment.app.Fragment
import com.andreick.dependencyinjection.common.dependencyinjection.DaggerPresentationComponent
import com.andreick.dependencyinjection.common.dependencyinjection.Injector
import com.andreick.dependencyinjection.common.dependencyinjection.presentation.PresentationModule
import com.andreick.dependencyinjection.screens.common.activities.BaseActivity

open class BaseFragment : Fragment() {

    private val presentationComponent by lazy {
        DaggerPresentationComponent.builder()
            .presentationModule(PresentationModule((requireActivity() as BaseActivity).activityComponent))
            .build()
    }

    protected val injector get() = Injector(presentationComponent)
}