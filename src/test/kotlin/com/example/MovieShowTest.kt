package com.example

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MovieShowTest : StringSpec({

    "Book few tickets for a Movie Show"{
        val eveningShow = MovieShow("London", ShowTime.EVENING)

        eveningShow.bookTicket().ticketNumber shouldBe 1
        eveningShow.bookTicket().ticketNumber shouldBe 2
        eveningShow.bookTicket().ticketNumber shouldBe 3
        eveningShow.bookTicket().ticketNumber shouldBe 4
        eveningShow.bookTicket().ticketNumber shouldBe 5
        eveningShow.bookTicket().ticketNumber shouldBe 6
    }

    "Book 100 tickets for a show and check if error occurs"{
        val afternoonShow = MovieShow("London", ShowTime.AFTERNOON)

        for (i in 1..99) {
            afternoonShow.bookTicket()
        }

        afternoonShow.bookTicket().ticketNumber shouldBe 100

        val exception = shouldThrow<IllegalStateException> {
            afternoonShow.bookTicket()
        }
        exception.message shouldBe "All tickets booked for this show."

    }

    "Book tickets for different movies"{
        val morningShow = MovieShow("Delhi", ShowTime.MORNING)
        val nightShow = MovieShow("London", ShowTime.NIGHT)

        morningShow.bookTicket().ticketNumber shouldBe 1
        morningShow.bookTicket().ticketNumber shouldBe 2

        nightShow.bookTicket().ticketNumber shouldBe 1
        nightShow.bookTicket().ticketNumber shouldBe 2
        nightShow.bookTicket().ticketNumber shouldBe 3

    }
})