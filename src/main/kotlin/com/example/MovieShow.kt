package com.example

class MovieShow(private val movieName: String, private val time: ShowTime) {

    private val TOTAL_SEATS = 100

    private val ticketList: MutableList<Ticket> = mutableListOf()

    fun bookTicket(): Ticket {

        val ticketListSize = ticketList.size

        if (ticketListSize == TOTAL_SEATS)
            return error("All tickets booked for this show.")

        val ticket = Ticket(ticketListSize + 1, this.time)
        ticketList.add(ticket)

        return ticket
    }
}