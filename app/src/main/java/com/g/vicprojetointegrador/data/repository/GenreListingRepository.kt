package com.g.vicprojetointegrador.data.repository

import com.g.vicprojetointegrador.data.model.GenresResponse
import com.g.vicprojetointegrador.data.repository.network.NetworkInstance
import io.reactivex.rxjava3.core.Single

class GenreListingRepository {
    fun getGenres() : Single<GenresResponse> {
        return NetworkInstance.getService().getGenres()
    }
}

