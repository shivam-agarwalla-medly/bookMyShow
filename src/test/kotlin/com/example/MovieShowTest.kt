package com.example

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.util.*


class MovieShowTest : StringSpec({

    "Book few tickets for a Movie Show"{
        val movieShow = MovieShow(100)

        val movieShowTime = MovieShowTime(ShowTime.EVENING, Date(2021, 8, 17))

        movieShow.bookTicket(movieShowTime).ticketNumber shouldBe 1
        movieShow.bookTicket(movieShowTime).ticketNumber shouldBe 2
    }

    "Book invalid number of tickets for a show and check if error occurs"{
        val movieShow = MovieShow(1)

        val movieShowTime = MovieShowTime(ShowTime.AFTERNOON, Date(2021, 8, 17))

        movieShow.bookTicket(movieShowTime).ticketNumber shouldBe 1

        val exception = shouldThrow<IllegalStateException> {
            movieShow.bookTicket(movieShowTime)
        }
        exception.message shouldBe "All tickets booked for this show."

    }

    "Book tickets for different Movie Shows"{
        val movieShow = MovieShow(100)

        val movieShowTime1 = MovieShowTime(ShowTime.MORNING, Date(2021, 8, 17))
        val movieShowTime2 = MovieShowTime(ShowTime.NIGHT, Date(2021, 8, 18))

        movieShow.bookTicket(movieShowTime1).ticketNumber shouldBe 1
        movieShow.bookTicket(movieShowTime1).ticketNumber shouldBe 2

        movieShow.bookTicket(movieShowTime2).ticketNumber shouldBe 1
    }
})