package com.andreick.dependencyinjection.screens.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andreick.dependencyinjection.questions.FetchQuestionsUseCase
import com.andreick.dependencyinjection.questions.Question
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyOtherViewModel @Inject constructor(
    private val fetchQuestionsUseCase: FetchQuestionsUseCase
) : ViewModel() {

    private val _questions = MutableLiveData<List<Question>>()
    val question: LiveData<List<Question>> = _questions
}