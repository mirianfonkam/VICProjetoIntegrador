package com.g.vicprojetointegrador

import com.g.vicprojetointegrador.utils.formatHourMinutes
import com.g.vicprojetointegrador.utils.formatPercentage
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieDetailsUnitTest {

    @Test
    fun formatting_minutes_to_Hours_minutes() {
        assertEquals(
            "0h 00min",
            0.formatHourMinutes()
        )

        assertEquals(
            "1h 30min",
            90.formatHourMinutes()
        )

        assertEquals(
            "0h 15min",
            15.formatHourMinutes()
        )

        assertEquals(
            "10h 10min",
            610.formatHourMinutes()
        )
    }

    @Test
    fun formatting_Percentage() {
        assertEquals(
            "0%",
            0.0.formatPercentage()
        )

        assertEquals(
            "50%",
            5.0.formatPercentage()
        )

        assertEquals(
            "100%",
            10.0.formatPercentage()
        )

        assertEquals(
            "79%",
            7.9.formatPercentage()
        )
    }

}


// MOVE IT TO TEST
//    private val allMovies = listOf(
//        Movie(1,"Ford v Ferrari",
//            "American car designer Carroll Shelby and drive. Miles battle corporate " +
//                    "interference and the laws of physics to build a revolutionary race car for Ford.",
//            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/dR1Ju50iudrOh3YgfwkAU1g2HZe.jpg",
//            80.0,
//            "11/14/2019",
//            175,
//            listOf(1,3,5)),
//        Movie(2,"Frozen",
//            "Young princess Anna of Arendelle dreams about finding true love at her sister Elsa’s coronation." +
//                    " Fate takes her on a dangerous journey in an attempt to end the eternal winter that has fallen over the kingdom.",
//            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/mbPrrbt8bSLcHSBCHnRclPlMZPl.jpg",
//            73.0,
//            "11/27/2013",
//            142,
//            listOf(1,3,5)),
//        Movie(3,
//            "Into the Wild",
//            "After graduating from Emory University in 1992, top student and athlete Christopher McCandless abandons his possessions, " +
//                    "gives his entire \$24,000 savings account to charity, and hitchhikes to Alaska to live in the wilderness." ,
//            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2MSGZEE6XZd2r4ODNziwAw7Hpw0.jpg",
//            78.0,
//            "10/19/2007",
//            128,
//            listOf(5))
//    )

// move to test
//private val allGenres = listOf(
//    Genre(1, "Ação"),
//    Genre(2, "Anime"),
//    Genre(3, "Biografia"),
//    Genre(4, "Comédia"),
//    Genre(5, "Drama"),
//    Genre(6, "Animação"),
//    Genre(7, "Família"),
//)
//
//fun getGenres() : List<Genre>{
//    return allGenres
//}