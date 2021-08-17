package com.example

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class TheatreTest : StringSpec({

    "Book tickets for a Movie Show"{
        val timeStamp = TimeStamp(ShowTime.EVENING, LocalDate.now().plusDays(1))
        val shows = listOf(Show(timeStamp, 100, Movie("Delhi")))
        val theatre = Theatre(shows)

        val (newTheatreState, ticket) = theatre.bookTicket(timeStamp)
        ticket.ticketNumber shouldBe 100

        newTheatreState.getNumberOfTicketsAvailable(timeStamp) shouldBe 99
    }

    "Book invalid number of tickets for a show and check if error occurs"{
        val timeStamp = TimeStamp(ShowTime.AFTERNOON, LocalDate.now().plusDays(1))
        val shows = listOf(Show(timeStamp, 1, Movie("Delhi")))
        val theatre = Theatre(shows)

        val (newTheatreState, _) = theatre.bookTicket(timeStamp)

        val exception = shouldThrow<IllegalStateException> {
            newTheatreState.bookTicket(timeStamp)
        }
        exception.message shouldBe "All tickets booked for this show."
    }

    "Book ticket for a movie show after 7 days and check if error occurs"{
        val timeStamp = TimeStamp(ShowTime.AFTERNOON, LocalDate.now().plusDays(100))
        val shows = listOf(Show(timeStamp, 10, Movie("Delhi")))
        val theatre = Theatre(shows)

        val exception = shouldThrow<IllegalStateException> {
            theatre.bookTicket(timeStamp)
        }
        exception.message shouldBe "Cannot book tickets of shows beyond 7 days."
    }

    "Book tickets for different Movie Shows"{
        val timeStamp1 = TimeStamp(ShowTime.MORNING, LocalDate.now().plusDays(1))
        val timeStamp2 = TimeStamp(ShowTime.NIGHT, LocalDate.now().plusDays(1))
        val shows =
            listOf(Show(timeStamp1, 100, Movie("Delhi")), Show(timeStamp2, 200, Movie("Delhi")))
        val theatre = Theatre(shows)

        val (newTheatreState, ticket) = theatre.bookTicket(timeStamp1)
        ticket.ticketNumber shouldBe 100

        val (_, ticket1) = newTheatreState.bookTicket(timeStamp2)
        ticket1.ticketNumber shouldBe 200
    }

    "View Movie Information of a Movie Show"{
        val show = Show(TimeStamp(ShowTime.NIGHT, LocalDate.now().plusDays(1)), 100, Movie("Mumbai"))
        val theatre = Theatre(listOf(show))

        theatre.getMovieNameOfAShow(show.timeStamp) shouldBe "Mumbai"
    }

    "Book tickets for a Show with no Movie Info and expect an Exception"{
        val timeStamp = TimeStamp(ShowTime.NIGHT, LocalDate.now().plusDays(1))
        val show = Show(timeStamp, 100)
        val theatre = Theatre(listOf(show))

        val exception = shouldThrow<IllegalStateException> {
            theatre.bookTicket(timeStamp)
        }
        exception.message shouldBe "Cannot book tickets as there is no movie information."
    }

    "Add Movie Information to a Show"{
        val timeStamp = TimeStamp(ShowTime.NIGHT, LocalDate.now().plusDays(1))
        val show = Show(timeStamp, 100)
        val theatre = Theatre(listOf(show))

        val exception = shouldThrow<IllegalStateException> {
            theatre.bookTicket(timeStamp)
        }
        exception.message shouldBe "Cannot book tickets as there is no movie information."

        show.assignMovie("Delhi")

        val (_, ticket) = theatre.bookTicket(timeStamp)
        ticket.ticketNumber shouldBe 100
    }
})