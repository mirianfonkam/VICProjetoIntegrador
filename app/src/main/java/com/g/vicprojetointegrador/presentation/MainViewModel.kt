package com.g.vicprojetointegrador.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.g.vicprojetointegrador.data.model.Movie
import com.g.vicprojetointegrador.data.repository.MovieListingRepository

/*
 * ViewModel: responsible for holding and processing data required by the User Interface
 * Used to separate your logic from your Views.
 */
class MainViewModel(private val movieListingRepository: MovieListingRepository = MovieListingRepository()) : ViewModel() {

    //Visible only by the ViewModel
    private val _popularMoviesLiveData = MutableLiveData<List<Movie>>()
    private val _progressBar = MutableLiveData<Boolean>()
    private val _errorLiveData = MutableLiveData<String>()

    //Exposed to the Activity/Fragment, not mutable
    val popularMoviesLiveData : LiveData<List<Movie>> = _popularMoviesLiveData
    val progressBar : LiveData<Boolean> = _progressBar
    val errorLiveData : LiveData<String> = _errorLiveData

    init {
        fetchPopularMovies()
    }

    fun fetchPopularMovies() {
        _popularMoviesLiveData.value = movieListingRepository.getMovies()
    }

    //Substituir o acesso ao repository da activity/fragment pelo ViewModel;
    //A tela já poderá exibir os estados de "loading" (que aguarda o retorno da API), "erro genérico" e "sucesso" (trazendo a lista de filmes);
}