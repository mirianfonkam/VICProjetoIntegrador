package com.g.vicprojetointegrador.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.g.vicprojetointegrador.data.model.MovieDetails
import com.g.vicprojetointegrador.domain.GetMovieDetailsUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDetailsViewModel(private val movieId: Int = -1) : ViewModel() {
    private val _extraMovieDetailsLiveData = MutableLiveData<MovieDetails>()
    private val _errorLiveData = MutableLiveData<String>()

    val movieLiveData  : LiveData<MovieDetails> = _extraMovieDetailsLiveData
    val errorLiveData : LiveData<String> = _errorLiveData

    private var disposables = CompositeDisposable()

    private val getMovieDetailsUseCase = GetMovieDetailsUseCase()

    init {
        getMovieDetails()
    }

    private fun getMovieDetails() {
        disposables.add(getMovieDetailsUseCase.execute(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _extraMovieDetailsLiveData.setValue(it)
            }, { error ->
                _errorLiveData.setValue("Error on extra movie detail: ${error.message}")
            })
        )
    }
}