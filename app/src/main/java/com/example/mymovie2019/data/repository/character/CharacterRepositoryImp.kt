package com.example.mymovie2019.data.repository.character

import com.example.mymovie2019.data.local.datasource.character.CharacterLocalDataSource
import com.example.mymovie2019.data.local.model.CharacterParam
import javax.inject.Inject

class CharacterRepositoryImp @Inject constructor(private val characterLocalDataSource: CharacterLocalDataSource) : CharacterRepository{


    override fun saveCharacters(characters: List<CharacterParam>) {
        characterLocalDataSource.saveCharacters(characters)
    }
}