package com.example

class Theatre(private val listOfShows: List<Show>) {

    fun bookTicket(timeStamp: TimeStamp): Pair<Theatre, Ticket> {

        val movieShow = listOfShows.first { it.timeStamp == timeStamp }

        timeStamp.validateDate()
        movieShow.movie.validateMovieInfo()
        movieShow.validateRemainingSeats()

        val ticket = Ticket(movieShow.remainingSeats, timeStamp)

        val copyOfListOfMovieShows = updateMovieShowList(movieShow, timeStamp)

        val newTheatreState = Theatre(copyOfListOfMovieShows)
        return Pair(newTheatreState, ticket)
    }

    private fun updateMovieShowList(show: Show, timeStamp: TimeStamp): List<Show> {
        val copyOfListOfMovieShows = listOfShows.toMutableList()

        copyOfListOfMovieShows.remove(show)
        copyOfListOfMovieShows.add(
            Show(
                timeStamp,
                show.totalSeats,
                show.movie,
                show.remainingSeats - 1
            )
        )

        return copyOfListOfMovieShows.toList()
    }

    fun getNumberOfTicketsAvailable(timeStamp: TimeStamp) =
        listOfShows.first { it.timeStamp == timeStamp }.remainingSeats

    fun getMovieNameOfAShow(timeStamp: TimeStamp) = listOfShows.first { it.timeStamp == timeStamp }.movie.getName()

    fun getMovieShowAtAMovieShowTime(timeStamp: TimeStamp) =
        listOfShows.first { it.timeStamp == timeStamp }
}