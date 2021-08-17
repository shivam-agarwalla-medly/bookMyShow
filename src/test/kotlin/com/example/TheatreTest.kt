package com.example

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.util.*


class TheatreTest : StringSpec({

    "Book tickets for a Movie Show"{
        val movieShowTime = MovieShowTime(ShowTime.EVENING, Date(2021, 8, 17))
        val movieShows = mutableListOf(MovieShow(movieShowTime, 100))
        val theatre = Theatre(movieShows)

        val (newTheatreState, ticket) = theatre.bookTicket(movieShowTime)
        ticket.ticketNumber shouldBe 100

        newTheatreState.ticketsToBeBooked(movieShowTime) shouldBe 99
    }

    "Book invalid number of tickets for a show and check if error occurs"{
        val movieShowTime = MovieShowTime(ShowTime.AFTERNOON, Date(2021, 8, 17))
        val movieShows = mutableListOf(MovieShow(movieShowTime, 1))
        val theatre = Theatre(movieShows)

        val (newTheatreState, ticket) = theatre.bookTicket(movieShowTime)
        ticket.ticketNumber shouldBe 1
        newTheatreState.ticketsToBeBooked(movieShowTime) shouldBe 0

        val exception = shouldThrow<IllegalStateException> {
            theatre.bookTicket(movieShowTime)
        }
        exception.message shouldBe "All tickets booked for this show."

    }

    "Book tickets for different Movie Shows"{
        val movieShowTime1 = MovieShowTime(ShowTime.MORNING, Date(2021, 8, 17))
        val movieShowTime2 = MovieShowTime(ShowTime.NIGHT, Date(2021, 8, 18))
        val movieShows = mutableListOf(MovieShow(movieShowTime1, 100), MovieShow(movieShowTime2, 200))
        val theatre = Theatre(movieShows)

        val (newTheatreState, ticket) = theatre.bookTicket(movieShowTime1)
        ticket.ticketNumber shouldBe 100

        val (newTheatreState1, ticket1) = newTheatreState.bookTicket(movieShowTime2)
        ticket1.ticketNumber shouldBe 200
    }
})