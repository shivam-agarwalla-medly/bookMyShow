package com.example

import java.time.LocalDate

class Theatre(private val listOfMovieShows: List<MovieShow>) {

    fun bookTicket(movieShowTime: MovieShowTime): Pair<Theatre, Ticket> {

        validateDate(movieShowTime)

        val movieShow = listOfMovieShows.find { it.movieShowTime == movieShowTime }!!

        validateRemainingSeats(movieShow)

        val ticket = Ticket(movieShow.totalSeats, movieShowTime)

        val copyOfListOfMovieShows = updateMovieShowList(movieShow, movieShowTime)

        val newTheatreState = Theatre(copyOfListOfMovieShows)
        return Pair(newTheatreState, ticket)
    }

    private fun validateRemainingSeats(movieShow: MovieShow) {
        if (movieShow.totalSeats == 0)
            throw error("All tickets booked for this show.")
    }

    private fun validateDate(movieShowTime: MovieShowTime) {
        if (movieShowTime.date > LocalDate.now().plusDays(7))
            throw error("Cannot book tickets of shows beyond 7 days.")
    }

    private fun updateMovieShowList(movieShow: MovieShow, movieShowTime: MovieShowTime): List<MovieShow> {
        val copyOfListOfMovieShows = listOfMovieShows.toMutableList()

        copyOfListOfMovieShows.remove(movieShow)
        copyOfListOfMovieShows.add(MovieShow(movieShowTime, movieShow.totalSeats - 1))

        return copyOfListOfMovieShows.toList()
    }

    fun getNumberOfTicketsAvailable(movieShowTime: MovieShowTime) =
        listOfMovieShows.find { it.movieShowTime == movieShowTime }!!.totalSeats
}