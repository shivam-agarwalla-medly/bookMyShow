package com.example

class MovieShow {

    companion object {
        private const val TOTAL_SEATS = 100
    }

    private val ticketMap: HashMap<MovieShowTime, Int> = hashMapOf()

    fun bookTicket(movieShowTime: MovieShowTime): Ticket {

        if (!ticketMap.containsKey(movieShowTime))
            ticketMap[movieShowTime] = 0

        val ticketNumber = ticketMap[movieShowTime]!!

        if (ticketNumber == TOTAL_SEATS)
            return error("All tickets booked for this show.")

        val ticket = Ticket(ticketNumber + 1, movieShowTime)

        ticketMap[movieShowTime] = ticketNumber + 1
        return ticket
    }
}