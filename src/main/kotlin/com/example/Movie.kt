package com.example

class Movie(private var name: String = "") {

    fun getName() = name

    fun setName(name: String) {
        this.name = name
    }
    
    fun validateMovieInfo() {
        if (name == "")
            throw error("Cannot book tickets as there is no movie information.")
    }
}