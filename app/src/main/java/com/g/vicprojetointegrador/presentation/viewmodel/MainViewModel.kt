package com.g.vicprojetointegrador.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.g.vicprojetointegrador.data.model.Genre
import com.g.vicprojetointegrador.data.model.Movie
import com.g.vicprojetointegrador.domain.GetGenresUseCase
import com.g.vicprojetointegrador.domain.GetMoviesByGenreUseCase
import com.g.vicprojetointegrador.domain.GetPopularMoviesUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

/*
 * ViewModel: responsible for holding and processing data required by the User Interface
 * Used to separate the logic from the Views.
 */
class MainViewModel : ViewModel() {

    //Visible only by the ViewModel
    private val _popularMoviesLiveData = MutableLiveData<List<Movie>>()

    private val _moviesByGenreLiveData = MutableLiveData<List<Movie>>()
    private val _genresLiveData = MutableLiveData<List<Genre>>()
    private val _progressBar = MutableLiveData<Boolean>()
    private val _errorLiveData = MutableLiveData<String>()



    private val _favoriteMoviesLiveData = MutableLiveData<List<Movie>>()
    private val _searchQuery = MutableLiveData<String>()


    //Exposed to the Activity/Fragment, not mutable
    val popularMoviesLiveData  : LiveData<List<Movie>> = _popularMoviesLiveData
    val favoriteMoviesLiveData : LiveData<List<Movie>> = _favoriteMoviesLiveData
    val movieByGenreLiveData : LiveData<List<Movie>> = _moviesByGenreLiveData
    val genresLiveData : LiveData<List<Genre>> = _genresLiveData
    val progressBar : LiveData<Boolean> = _progressBar
    val errorLiveData : LiveData<String> = _errorLiveData
    val searchQuery : LiveData<String> = _searchQuery

    private var disposables = CompositeDisposable()

    private val getMoviesUseCase = GetPopularMoviesUseCase()
    private val getMoviesByGenreUseCase = GetMoviesByGenreUseCase()
    private val getGenresUseCase = GetGenresUseCase()


    init {
        getPopularMovies()
        getGenres()
    }


    private fun getPopularMovies(){
        disposables.add(getMoviesUseCase.execute()
            .subscribeOn(Schedulers.io())              //Schedulers.io(): Suitable for network requests (I/O bounds)
            .doOnSubscribe {
                _progressBar.setValue(true)
            }  //do something (update the UI) before the task started
            .map { it.results }
            .observeOn(AndroidSchedulers.mainThread()) //Specify that the next operations should be called on the main thread.
            .subscribe({
                _progressBar.postValue(false)
                _popularMoviesLiveData.postValue(it)
            }, { error ->
                _errorLiveData.postValue("An error occurred: ${error.message}")
            })
        )
    }
    //setValue: sets the value instantly
    //postValue: Asynchronous updating

    fun getMoviesByGenre(genreId : String){
        disposables.add(getMoviesByGenreUseCase.execute(genreId)
            .subscribeOn(Schedulers.io())              //Schedulers.io(): Suitable for network requests (I/O bounds)
            .doOnSubscribe {
                _progressBar.setValue(true)
            }  //do something (update the UI) before the task started
            .map { it.results }
            .observeOn(AndroidSchedulers.mainThread()) //Specify that the next operations should be called on the main thread.
            .subscribe({
                _progressBar.postValue(false)
                _moviesByGenreLiveData.postValue(it)
            }, { error ->
                _errorLiveData.postValue("An error occurred: ${error.message}")
            })
        )
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

//    fun favoriteClicked(movie: Movie) {
//
//        movie.isFavorited = !movie.isFavorited //Sets a switch on click
//
//        if (movie.isFavorited) {
//            //SaveFavoriteMovieUseCase(movie)
//        } else {
//            //DeleteFavoriteMovieUseCase(movie)
//        }
////        database
////        .movieDao() .insertMovie(movie)
////
////            .subscribeOn(Schedulers.io())
////            .subscribe()
////            .addTo(disposables)
//    }

    //This will dispose the disposable when the ViewModel has been cleared, like when the activity has been closed.
    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }


}