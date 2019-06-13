package com.example.mymovie2019.data.local.datasource.character

import com.example.mymovie2019.data.local.model.CharacterParam

interface CharacterLocalDataSource {

    fun saveCharacters(characters: List<CharacterParam>)

    fun checkExistCharsOfMovie(movieId: Int): Boolean
}