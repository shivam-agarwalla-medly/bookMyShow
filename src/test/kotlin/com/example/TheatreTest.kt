package com.example

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class TheatreTest : StringSpec({

    "Book tickets for a Movie Show"{
        val movieShowTime = MovieShowTime(ShowTime.EVENING, LocalDate.now().plusDays(1))
        val movieShows = listOf(MovieShow(movieShowTime, 100))
        val theatre = Theatre(movieShows)

        val (newTheatreState, ticket) = theatre.bookTicket(movieShowTime)
        ticket.ticketNumber shouldBe 100

        newTheatreState.getNumberOfTicketsAvailable(movieShowTime) shouldBe 99
    }

    "Book invalid number of tickets for a show and check if error occurs"{
        val movieShowTime = MovieShowTime(ShowTime.AFTERNOON, LocalDate.now().plusDays(1))
        val movieShows = listOf(MovieShow(movieShowTime, 1))
        val theatre = Theatre(movieShows)

        val (newTheatreState, ticket) = theatre.bookTicket(movieShowTime)
        ticket.ticketNumber shouldBe 1
        newTheatreState.getNumberOfTicketsAvailable(movieShowTime) shouldBe 0

        val exception = shouldThrow<IllegalStateException> {
            newTheatreState.bookTicket(movieShowTime)
        }
        exception.message shouldBe "All tickets booked for this show."
    }

    "Book ticket for a movie show after 7 days and check if error occurs"{
        val movieShowTime = MovieShowTime(ShowTime.AFTERNOON, LocalDate.now().plusDays(10))
        val movieShows = listOf(MovieShow(movieShowTime, 10))
        val theatre = Theatre(movieShows)

        val exception = shouldThrow<IllegalStateException> {
            theatre.bookTicket(movieShowTime)
        }
        exception.message shouldBe "Cannot book tickets of shows beyond 7 days."
    }

    "Book tickets for different Movie Shows"{
        val movieShowTime1 = MovieShowTime(ShowTime.MORNING, LocalDate.now().plusDays(1))
        val movieShowTime2 = MovieShowTime(ShowTime.NIGHT, LocalDate.now().plusDays(1))
        val movieShows = listOf(MovieShow(movieShowTime1, 100), MovieShow(movieShowTime2, 200))
        val theatre = Theatre(movieShows)

        val (newTheatreState, ticket) = theatre.bookTicket(movieShowTime1)
        ticket.ticketNumber shouldBe 100

        val (_, ticket1) = newTheatreState.bookTicket(movieShowTime2)
        ticket1.ticketNumber shouldBe 200
    }
})