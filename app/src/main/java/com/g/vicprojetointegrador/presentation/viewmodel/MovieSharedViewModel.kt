package com.g.vicprojetointegrador.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.g.vicprojetointegrador.data.model.Genre
import com.g.vicprojetointegrador.data.model.Movie
import com.g.vicprojetointegrador.domain.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

/*
 * ViewModel: responsible for holding and processing data required by the User Interface
 * Used to separate the logic from the Views.
 */
class MovieSharedViewModel() : ViewModel() {

    // Visible only by the ViewModel
    private val _popularMoviesLiveData = MutableLiveData<List<Movie>>()
    private val _genresLiveData = MutableLiveData<List<Genre>>()
    private val _progressBar = MutableLiveData<Boolean>()
    private val _errorLiveData = MutableLiveData<String>()
    private val _favoriteMoviesLiveData = MutableLiveData<List<Movie>>()

    // Exposed to the Activity/Fragment, not mutable
    val popularMoviesLiveData  : LiveData<List<Movie>> = _popularMoviesLiveData
    val favoriteMoviesLiveData : LiveData<List<Movie>> = _favoriteMoviesLiveData
    val genresLiveData : LiveData<List<Genre>> = _genresLiveData
    val progressBar : LiveData<Boolean> = _progressBar
    val errorLiveData : LiveData<String> = _errorLiveData

    private var disposables = CompositeDisposable()
    private var disposable : Disposable? = null

    // UseCase Instances
    private val getMoviesUseCase = GetPopularMoviesUseCase()
    private val getMoviesByGenreUseCase = GetMoviesByGenreUseCase()
    private val getGenresUseCase = GetGenresUseCase()
    private val getFavoriteMovieUseCase = GetFavoriteMoviesUseCase()
    private val getFavoriteStatusUseCase = CheckFavoriteStatusUseCase()
    private val saveFavoriteMovieUseCase = SaveFavoriteMovieUseCase()
    private val deleteFavoriteMovieUseCase = DeleteFavoriteMovieUseCase()

    init {
        getGenres()
        getFavoriteMovies()
        getPopularMovies()
    }

    fun getPopularMovies(){
        disposable?.dispose()
        disposable = getMoviesUseCase.execute()
            .subscribeOn(Schedulers.io())              //Schedulers.io(): Suitable for network requests (I/O bounds)
            .doOnSubscribe {
                _progressBar.postValue(true)
            }  //do something (update the UI) before the task started
            .map { it.results }
            .observeOn(AndroidSchedulers.mainThread()) //Specify that the next operations should be called on the main thread.
            .subscribe({
                _progressBar.postValue(false)
                _popularMoviesLiveData.setValue(it)
            }, { error ->
                _errorLiveData.postValue("An error occurred: ${error.message}")
            }).apply { disposables.add(this) }
    }
    //setValue: sets the value instantly
    //postValue: Asynchronous updating

    fun getMoviesByGenre(genreId : String){
        disposable?.dispose()
       disposable =  getMoviesByGenreUseCase.execute(genreId)
            .subscribeOn(Schedulers.io())              //Schedulers.io(): Suitable for network requests (I/O bounds)
            .doOnSubscribe {
                _progressBar.postValue(true)
            }  //do something (update the UI) before the task started
            .map { it.results }
            .observeOn(AndroidSchedulers.mainThread()) //Specify that the next operations should be called on the main thread.
            .subscribe({
                _progressBar.postValue(false)
                _popularMoviesLiveData.setValue(it)
            }, { error ->
                _errorLiveData.postValue("An error occurred: ${error.message}")
            }).apply {
                disposables.add(this)
            }
    }


    private fun getFavoriteMovies() {
        disposables.add(getFavoriteMovieUseCase.execute()
            .subscribeOn(Schedulers.io())
            .subscribe ({
                _favoriteMoviesLiveData.postValue(it)
            } , { error ->
                _errorLiveData.postValue("An error occurred: ${error.message}")
            })
        )
    }

    fun favoriteClicked(movie: Movie) {
        movie.isFavorited = !movie.isFavorited //Sets a switch on click

        if (movie.isFavorited) {
            disposables.add(saveFavoriteMovieUseCase.execute(movie)
                .subscribeOn(Schedulers.io())
                .subscribe()
            )

        } else {
            disposables.add(deleteFavoriteMovieUseCase.execute(movie)
                .subscribeOn(Schedulers.io())
                .subscribe()
            )
        }
    }

    private fun getGenres(){
        disposables.add(getGenresUseCase.execute()
            .subscribeOn(Schedulers.io())
            .map { it.genres }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _genresLiveData.postValue(it)
            }, { error ->
                _errorLiveData.postValue("${error.message}")
            })
        )
    }

    //This will dispose the disposable when the ViewModel has been cleared, like when the activity has been closed.
    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }


}