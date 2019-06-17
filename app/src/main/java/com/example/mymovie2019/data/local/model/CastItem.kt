package com.example.mymovie2019.data.local.model

data class CastItem(var id: Int = -1,
                    var name: String = "",
                    var imagePath: String? = "",
                    var itemType: ItemType = ItemType.NORMAL) {

    constructor(castItem: CastItem) : this(castItem.id,castItem.name, castItem.imagePath, castItem.itemType) {
        id = castItem.id
        name = castItem.name
        imagePath = castItem.imagePath
        itemType = castItem.itemType
    }
}