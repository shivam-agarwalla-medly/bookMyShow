package com.example

class Show(
    val timeStamp: TimeStamp,
    val totalSeats: Int,
    val movie: Movie = Movie(),
    val remainingSeats: Int = totalSeats
) {
    fun assignMovie(movieName: String) {

        if (totalSeats == remainingSeats)
            movie.setName(movieName)
        else
            throw error("Tickets already sold for this Show.")
    }

    fun validateRemainingSeats() {
        if (remainingSeats == 0)
            throw error("All tickets booked for this show.")
    }
}