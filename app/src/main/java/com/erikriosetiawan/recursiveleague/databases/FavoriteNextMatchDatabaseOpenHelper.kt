package com.erikriosetiawan.recursiveleague.databases

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.erikriosetiawan.recursiveleague.models.NextMatch
import org.jetbrains.anko.db.*

class FavoriteNextMatchDatabaseOpenHelper(ctx: Context) :
    ManagedSQLiteOpenHelper(ctx, "FavoritesNextMatch.db", null, 1) {

    companion object {
        private var instance: FavoriteNextMatchDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): FavoriteNextMatchDatabaseOpenHelper {
            if (instance == null)
                instance = FavoriteNextMatchDatabaseOpenHelper(ctx.applicationContext)
            return instance as FavoriteNextMatchDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Create tables
        db?.createTable(
            NextMatch.TABLE_FAVORITE_NEXT_MATCH, true,
            NextMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            NextMatch.ID_EVENT to TEXT + UNIQUE,
            NextMatch.HOME_SCORE to TEXT,
            NextMatch.AWAY_SCORE to TEXT,
            NextMatch.EVENT to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Upgrade tables
        db?.dropTable(NextMatch.TABLE_FAVORITE_NEXT_MATCH, true)
    }
}

// Access property for Context
val Context.favoriteNextMatchDatabase: FavoriteNextMatchDatabaseOpenHelper
    get() = FavoriteNextMatchDatabaseOpenHelper.getInstance(applicationContext)