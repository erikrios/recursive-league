package com.erikriosetiawan.recursiveleague.databases

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.erikriosetiawan.recursiveleague.models.LastMatch
import com.erikriosetiawan.recursiveleague.models.NextMatch
import com.erikriosetiawan.recursiveleague.models.Team
import org.jetbrains.anko.db.*

class FavoriteMatchDatabaseOpenHelper(ctx: Context) :
    ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 1) {

    companion object {
        private var instance: FavoriteMatchDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): FavoriteMatchDatabaseOpenHelper {
            if (instance == null)
                instance = FavoriteMatchDatabaseOpenHelper(ctx.applicationContext)
            return instance as FavoriteMatchDatabaseOpenHelper
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

        db?.createTable(
            NextMatch.TABLE_FAVORITE_NEXT_MATCH, true,
            NextMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            NextMatch.ID_EVENT to TEXT + UNIQUE,
            NextMatch.HOME_SCORE to TEXT,
            NextMatch.AWAY_SCORE to TEXT,
            NextMatch.EVENT to TEXT
        )

        db?.createTable(
            Team.TABLE_FAVORITE_TEAM, true,
            Team.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Team.ID_TEAM to TEXT + UNIQUE,
            Team.TEAM_NAME to TEXT,
            Team.TEAM_BADGE to TEXT,
            Team.DESCRIPTION to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Upgrade tables
        db?.dropTable(LastMatch.TABLE_FAVORITE_LAST_MATCH, true)
        db?.dropTable(NextMatch.TABLE_FAVORITE_NEXT_MATCH, true)
        db?.dropTable(Team.TABLE_FAVORITE_TEAM, true)
    }
}

// Access property for Context
val Context.favoriteMatchDatabase: FavoriteMatchDatabaseOpenHelper
    get() = FavoriteMatchDatabaseOpenHelper.getInstance(applicationContext)