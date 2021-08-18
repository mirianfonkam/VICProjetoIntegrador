package com.g.vicprojetointegrador.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.g.vicprojetointegrador.data.model.Genre
import com.g.vicprojetointegrador.data.model.Movie
import com.g.vicprojetointegrador.domain.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchViewModel() : ViewModel() {

    // Visible only by the ViewModel
    private val _genresLiveData = MutableLiveData<List<Genre>>()
    private val _progressBar = MutableLiveData<Boolean>()
    private val _errorGenericLiveData = MutableLiveData<String>()
    private val _isSearchResultsEmpty = MutableLiveData<Boolean>()
    private val _movieListingLiveData = MutableLiveData<List<Movie>>()

    // Exposed to the Activity/Fragment, not mutable
    val genresLiveData : LiveData<List<Genre>> = _genresLiveData
    val progressBar : LiveData<Boolean> = _progressBar
    val errorGenericLiveData : LiveData<String> = _errorGenericLiveData
    val isSearchResultsEmpty : LiveData<Boolean> = _isSearchResultsEmpty
    val movieListingLiveData : LiveData<List<Movie>> = _movieListingLiveData

    private var disposables = CompositeDisposable()

    // UseCase Instances
    private val getMoviesByGenreUseCase = GetMoviesByGenreUseCase()
    private val getGenresUseCase = GetGenresUseCase()
    private val saveFavoriteMovieUseCase = SaveFavoriteMovieUseCase()
    private val deleteFavoriteMovieUseCase = DeleteFavoriteMovieUseCase()
    private val searchMoviesByQueryUseCase = SearchMoviesByQueryUseCase()

    init {
        getGenres()
    }

    fun searchMoviesByQuery(query : String){
        disposables.add(searchMoviesByQueryUseCase.execute(query)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                _progressBar.postValue(true)
            }
            .map { it.results }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _progressBar.postValue(false)
                _movieListingLiveData.value = it
                _isSearchResultsEmpty.value = it.isEmpty()

            }, { error ->
                _errorGenericLiveData.postValue("An error occurred: ${error.message}")
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
                _errorGenericLiveData.postValue("${error.message}")
            })
        )
    }

    fun getMoviesByGenre(genreId : String){
        disposables.add(getMoviesByGenreUseCase.execute(genreId)
            .subscribeOn(Schedulers.io())              //Schedulers.io(): Suitable for network requests (I/O bounds)
            .doOnSubscribe {
                _progressBar.postValue(true)
            }  //do something (update the UI) before the task started
            .map { it.results }
            .observeOn(AndroidSchedulers.mainThread()) //Specify that the next operations should be called on the main thread.
            .subscribe({
                _progressBar.postValue(false)
                _movieListingLiveData.setValue(it)
            }, { error ->
                _errorGenericLiveData.postValue("An error occurred: ${error.message}")
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

    //This will dispose the disposable when the ViewModel has been cleared, like when the activity has been closed.
    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}