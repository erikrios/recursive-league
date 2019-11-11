package com.erikriosetiawan.recursiveleague.databases

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.erikriosetiawan.recursiveleague.models.LastMatch
import org.jetbrains.anko.db.*

class FavoriteLastMatchDatabaseOpenHelper(ctx: Context) :
    ManagedSQLiteOpenHelper(ctx, "FavoriteLastMatch.db", null, 1) {

    companion object {
        private var instance: FavoriteLastMatchDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): FavoriteLastMatchDatabaseOpenHelper {
            if (instance == null)
                instance = FavoriteLastMatchDatabaseOpenHelper(ctx.applicationContext)
            return instance as FavoriteLastMatchDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Create tables
        db?.createTable(
            LastMatch.TABLE_FAVORITE_LAST_MATCH, true,
            LastMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            LastMatch.ID_EVENT to TEXT + UNIQUE,
            LastMatch.HOME_SCORE to TEXT,
            LastMatch.AWAY_SCORE to TEXT,
            LastMatch.THUMB to TEXT,
            LastMatch.EVENT to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Upgrade tables
        db?.dropTable(LastMatch.TABLE_FAVORITE_LAST_MATCH, true)
    }
}

// Access property for Context
val Context.favoriteLastMatchDatabase: FavoriteLastMatchDatabaseOpenHelper
    get() = FavoriteLastMatchDatabaseOpenHelper.getInstance(applicationContext)