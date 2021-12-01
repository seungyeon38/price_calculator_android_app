package com.example.pricecalculator.Databases;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class MenuWithIngredients{
    @Embedded
    public MenuTable menu;
    @Relation(
            parentColumn = "menuId",
            entityColumn = "ingredientId",
            associateBy = @Junction(MenuIngredientTable.class)
    )
    public List<IngredientTable> ingredientTableList;
}

//public class PlaylistWithSongs {
//    @Embedded public Playlist playlist;
//    @Relation(
//            parentColumn = "playlistId",
//            entityColumn = "songId",
//            associateBy = @Junction(PlaylistSongCrossref.class)
//    )
//    public List<Song> songs;
//}