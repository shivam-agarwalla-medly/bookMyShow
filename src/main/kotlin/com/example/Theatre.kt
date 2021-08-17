package com.example

class Theatre(private val setOfMovieShows: MutableList<MovieShow>) {

    fun bookTicket(movieShowTime: MovieShowTime): Pair<Theatre, Ticket> {

        val movieShow = setOfMovieShows.find { it.movieShowTime == movieShowTime }!!

        if (movieShow.totalSeats == 0)
            throw error("All tickets booked for this show.")

        val ticket = Ticket(movieShow.totalSeats, movieShowTime)

        setOfMovieShows.remove(movieShow)
        setOfMovieShows.add(MovieShow(movieShowTime, movieShow.totalSeats - 1))

        val newTheatreState = Theatre(setOfMovieShows)
        return Pair(newTheatreState, ticket)
    }

    fun ticketsToBeBooked(movieShowTime: MovieShowTime): Int {
        val movieShow = setOfMovieShows.find { it.movieShowTime == movieShowTime }!!

        return movieShow.totalSeats
    }
}