package com.example

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.util.*


class MovieShowTest : StringSpec({

    "Book few tickets for a Movie Show"{
        val movieShow = MovieShow()

        val movieShowTime = MovieShowTime(ShowTime.EVENING, Date(2021, 8, 17))

        movieShow.bookTicket(movieShowTime).ticketNumber shouldBe 1
        movieShow.bookTicket(movieShowTime).ticketNumber shouldBe 2
        movieShow.bookTicket(movieShowTime).ticketNumber shouldBe 3
        movieShow.bookTicket(movieShowTime).ticketNumber shouldBe 4
        movieShow.bookTicket(movieShowTime).ticketNumber shouldBe 5
        movieShow.bookTicket(movieShowTime).ticketNumber shouldBe 6
    }

    "Book 100 tickets for a show and check if error occurs"{
        val movieShow = MovieShow()

        val movieShowTime = MovieShowTime(ShowTime.AFTERNOON, Date(2021, 8, 17))

        for (i in 1..99) {
            movieShow.bookTicket(movieShowTime)
        }

        movieShow.bookTicket(movieShowTime).ticketNumber shouldBe 100

        val exception = shouldThrow<IllegalStateException> {
            movieShow.bookTicket(movieShowTime)
        }
        exception.message shouldBe "All tickets booked for this show."

    }

    "Book tickets for different Movie Shows"{
        val movieShow = MovieShow()

        val movieShowTime1 = MovieShowTime(ShowTime.MORNING, Date(2021, 8, 17))
        val movieShowTime2 = MovieShowTime(ShowTime.NIGHT, Date(2021, 8, 18))

        movieShow.bookTicket(movieShowTime1).ticketNumber shouldBe 1
        movieShow.bookTicket(movieShowTime1).ticketNumber shouldBe 2

        movieShow.bookTicket(movieShowTime2).ticketNumber shouldBe 1
        movieShow.bookTicket(movieShowTime2).ticketNumber shouldBe 2
        movieShow.bookTicket(movieShowTime2).ticketNumber shouldBe 3
    }
})