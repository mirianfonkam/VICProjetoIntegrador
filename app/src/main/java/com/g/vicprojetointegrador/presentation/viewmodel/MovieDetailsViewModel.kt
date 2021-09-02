package com.g.vicprojetointegrador.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.g.vicprojetointegrador.data.model.Movie
import com.g.vicprojetointegrador.data.model.MovieDetails
import com.g.vicprojetointegrador.domain.CheckFavoriteStatusUseCase
import com.g.vicprojetointegrador.domain.DeleteFavoriteMovieUseCase
import com.g.vicprojetointegrador.domain.GetMovieDetailsUseCase
import com.g.vicprojetointegrador.domain.SaveFavoriteMovieUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDetailsViewModel(private val movieId: Int) : ViewModel() {
    private val _extraMovieDetailsLiveData = MutableLiveData<MovieDetails>()
    private val _errorLiveData = MutableLiveData<String>()
    private val _isFavorited = MutableLiveData<Boolean>()


    val extraMovieDetailsLiveData : LiveData<MovieDetails> = _extraMovieDetailsLiveData
    val errorLiveData : LiveData<String> = _errorLiveData
    val isFavorited : LiveData<Boolean> = _isFavorited

    private var disposables = CompositeDisposable()

    private val getMovieDetailsUseCase = GetMovieDetailsUseCase()
    private val getFavoriteStatusUseCase = CheckFavoriteStatusUseCase()
    private val saveFavoriteMovieUseCase = SaveFavoriteMovieUseCase()
    private val deleteFavoriteMovieUseCase = DeleteFavoriteMovieUseCase()

    init {
        getMovieDetails()
        checkFavoriteStatus()
    }

    private fun getMovieDetails() {
        disposables.add(getMovieDetailsUseCase(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _extraMovieDetailsLiveData.setValue(it)
            }, { error ->
                _errorLiveData.postValue("Error on extra movie detail: ${error.message}")
            })
        )
    }

    private fun checkFavoriteStatus() {
        disposables.add(getFavoriteStatusUseCase(movieId)
            .subscribeOn(Schedulers.io())
            .subscribe ({
                _isFavorited.postValue(it)
            } , { error ->
                _errorLiveData.postValue("An error on db load: ${error.message}")
            })
        )
    }

    fun favoriteClicked(movie: Movie) {

        movie.isFavorited = !movie.isFavorited //Sets a switch on click

        if (movie.isFavorited) {
            disposables.add(saveFavoriteMovieUseCase(movie)
                .subscribeOn(Schedulers.io())
                .subscribe()
            )
        } else {
            disposables.add(deleteFavoriteMovieUseCase(movie)
                .subscribeOn(Schedulers.io())
                .subscribe()
            )
        }
    }

    //This will dispose the disposable when the ViewModel has been cleared, like when the activity has been closed.
    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}