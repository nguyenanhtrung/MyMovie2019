package com.example.mymovie2019.data.local.datasource.character

import com.example.mymovie2019.data.local.database.dao.CharacterDao
import com.example.mymovie2019.data.local.database.entity.CharacterEntity
import com.example.mymovie2019.data.local.model.CharacterParam
import javax.inject.Inject

class CharacterLocalDataSourceImp @Inject constructor(private val characterDao: CharacterDao) : CharacterLocalDataSource {

    override fun saveCharacters(characters: List<CharacterParam>) {
        if (characters.isNullOrEmpty()) {
            return
        }
        val characterEntities = characters.map {
            CharacterEntity(
                it.characterId,
                it.name,
                it.movieId,
                it.castId
            )
        }
        characterDao.insertDatas(characterEntities)
    }

    override fun checkExistCharsOfMovie(movieId: Int): Boolean {
        val status = characterDao.checkExistCharsOfMovie(movieId)
        return status == 1
    }

}