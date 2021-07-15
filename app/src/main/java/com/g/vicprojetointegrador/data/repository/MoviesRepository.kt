package com.g.vicprojetointegrador.data.repository

import com.g.vicprojetointegrador.data.model.Movie

//const val IMG_PATH =

class MoviesRepository {
    private val allMovies = listOf(
        Movie(1,"Ford v Ferrari",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/dR1Ju50iudrOh3YgfwkAU1g2HZe.jpg",
            80,
            "PG-13",
            "11/14/2019",
            "2h 33m",
            "American car designer Carroll Shelby and drive. Miles battle corporate " +
                    "interference and the laws of physics to build a revolutionary race car for Ford.",
            listOf(1,3,5)),
        Movie(2,"Frozen",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/mbPrrbt8bSLcHSBCHnRclPlMZPl.jpg",
            73,
            "PG",
            "11/27/2013",
            "1h 42m",
            "Young princess Anna of Arendelle dreams about finding true love at her sister Elsa’s coronation." +
                    " Fate takes her on a dangerous journey in an attempt to end the eternal winter that has fallen over the kingdom.",
            listOf(1,3,5)),
        Movie(3,"Into the Wild",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2MSGZEE6XZd2r4ODNziwAw7Hpw0.jpg",
            78,
            "R",
            "10/19/2007",
            "2h 28m",
            "After graduating from Emory University in 1992, top student and athlete Christopher McCandless abandons his possessions, " +
                    "gives his entire \$24,000 savings account to charity, and hitchhikes to Alaska to live in the wilderness." ,
            listOf(5))
    )


    fun getMovies() : List<Movie>{
        return allMovies
    }
}