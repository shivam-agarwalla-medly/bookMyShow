package com.example

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class ShowTest : StringSpec({
    "Reassign a new movie to a show which already has an assigned movie but has not sold any tickets"{
        val timeStamp = TimeStamp(ShowTime.NIGHT, LocalDate.now().plusDays(1))
        val show = Show(timeStamp, 100, Movie("London"))

        show.movie.getName() shouldBe "London"

        show.assignMovie("Delhi")

        show.movie.getName() shouldBe "Delhi"
    }

    "Assign a new movie to a show which already has an assigned movie but has sold tickets and thus throw an exception"{
        val timeStamp = TimeStamp(ShowTime.NIGHT, LocalDate.now().plusDays(1))
        val show = Show(timeStamp, 100, Movie("London"))
        val theatre = Theatre(listOf(show))
        val (newTheatreState, _) = theatre.bookTicket(timeStamp)

        val exception = shouldThrow<IllegalStateException> {
            val newMovieShow = newTheatreState.getMovieShowAtAMovieShowTime(timeStamp)
            newMovieShow.assignMovie("Delhi")
        }
        exception.message shouldBe "Tickets already sold for this Show."
    }
})