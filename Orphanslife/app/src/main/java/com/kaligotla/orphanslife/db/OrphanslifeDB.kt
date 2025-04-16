package com.kaligotla.orphanslife.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kaligotla.orphanslife.model.entity.*

@Database(
    entities = [Admin::class, Sponsor::class, Child::class, Orphanage::class, Role::class,
               AdoptiveStatus::class, AdoptReq::class, Location::class, Donation::class,
               OrphanageActivities::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(MyConverters::class)
abstract class OrphanslifeDB: RoomDatabase() {

    abstract fun adminDao(): AdminDao
    abstract fun sponsorDao(): SponsorDao
    abstract fun childDao(): ChildDao
    abstract fun orphanageDao(): OrphanageDao
    abstract fun roleDao(): RoleDao
    abstract fun adoptiveStatusDao(): AdoptiveStatusDao
    abstract fun adoptReqDao(): AdoptReqDao
    abstract fun donationDao(): DonationDao
    abstract fun locationDao(): LocationDao
    abstract fun orphanageActivitiesDao(): OrphanageActivitiesDao

    companion object {
        @Volatile
        private var INSTANCE: OrphanslifeDB? = null

        fun getDatabase(context: Context): OrphanslifeDB {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE notes ADD COLUMN lastUpdate INTEGER NOT NULL DEFAULT 0")
            }
        }

        private fun buildDatabase(context: Context): OrphanslifeDB {
            return Room.databaseBuilder(
                context.applicationContext,
                OrphanslifeDB::class.java,
                "orphanslife"
            ).allowMainThreadQueries().build()
        }
    }
}