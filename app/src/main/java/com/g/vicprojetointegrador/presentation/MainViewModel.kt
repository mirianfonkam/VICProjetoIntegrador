package com.g.vicprojetointegrador.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.g.vicprojetointegrador.data.model.Movie
import com.g.vicprojetointegrador.data.repository.MovieListingRepository
import com.g.vicprojetointegrador.domain.GetMoviesUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

/*
 * ViewModel: responsible for holding and processing data required by the User Interface
 * Used to separate your logic from your Views.
 */
class MainViewModel(private val movieListingRepository: MovieListingRepository = MovieListingRepository()) : ViewModel() {

    //Visible only by the ViewModel
    private val _popularMoviesLiveData = MutableLiveData<List<Movie>>()
    private val _progressBar = MutableLiveData<Boolean>()
    private val _errorLiveData = MutableLiveData<String>()
    private val _searchQuery = MutableLiveData<String>()

    //Exposed to the Activity/Fragment, not mutable
    val popularMoviesLiveData : LiveData<List<Movie>> = _popularMoviesLiveData
    val progressBar : LiveData<Boolean> = _progressBar
    val errorLiveData : LiveData<String> = _errorLiveData
    val searchQuery : LiveData<String> = _searchQuery

    private var disposable = CompositeDisposable()

    private val getMoviesUseCase = GetMoviesUseCase()

//
    init {
        getPopularMovies()
    }

//    fun fetchPopularMovies() {
//        _popularMoviesLiveData.value = movieListingRepository.getMovies()
//    }

    fun getPopularMovies() {
        disposable.add(getMoviesUseCase.execute()
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

    //This will dispose the disposable when the ViewModel has been cleared, like when the activity has been closed.
    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }


}