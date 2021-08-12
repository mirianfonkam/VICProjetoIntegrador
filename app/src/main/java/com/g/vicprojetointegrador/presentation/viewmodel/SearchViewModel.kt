package com.g.vicprojetointegrador.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.g.vicprojetointegrador.data.model.Genre
import com.g.vicprojetointegrador.data.model.Movie
import com.g.vicprojetointegrador.domain.*
import com.g.vicprojetointegrador.utils.toBoolean
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers



class SearchViewModel(context: Application) : AndroidViewModel(context) {

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
    private val checkFavoriteStatusUseCase = CheckFavoriteStatusUseCase(context)
    private val saveFavoriteMovieUseCase = SaveFavoriteMovieUseCase(context)
    private val deleteFavoriteMovieUseCase = DeleteFavoriteMovieUseCase(context)
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
                _movieListingLiveData.postValue(it)
                if (it.isNotEmpty()) _isSearchResultsEmpty.postValue(false) else {
                     _isSearchResultsEmpty.postValue(true)
                }

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
                _movieListingLiveData.postValue(it)
            }, { error ->
                _errorGenericLiveData.postValue("An error occurred: ${error.message}")
            })
        )
    }

    fun checkFavoriteStatus(movie: Movie) : Movie {
        disposables.add(checkFavoriteStatusUseCase.execute(movie.id)
            .subscribeOn(Schedulers.io())
            .subscribe ({
                movie.isFavorited = it.toBoolean()
            } , {
                error ->
                _errorGenericLiveData.postValue("An error on db load: ${error.message}")
            })
        )
        return movie
    }

    //setValue: sets the value instantly
    //postValue: Asynchronous updating



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