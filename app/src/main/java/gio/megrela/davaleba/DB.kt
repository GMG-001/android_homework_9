package gio.megrela.davaleba

import android.content.Context
import androidx.room.*
import androidx.room.Dao

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class DB : RoomDatabase() {

    companion object {
        fun build(context: Context): DB {
            return Room.databaseBuilder(
                context,
                DB::class.java,
                "appdb"
            ).allowMainThreadQueries().build()
        }
    }

    abstract fun dao(): gio.megrela.davaleba.Dao
}

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(items: List<UserEntity>)

    @Query("SELECT * FROM userentity")
    fun getAll(): List<UserEntity>
}