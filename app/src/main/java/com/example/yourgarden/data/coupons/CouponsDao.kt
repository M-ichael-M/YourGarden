package com.example.yourgarden.data.coupons

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.yourgarden.data.song.SongEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CouponsDao {
    @Query("SELECT * FROM coupons WHERE code = :code LIMIT 1")
    suspend fun getCouponByCode(code: String): CouponsEntity?

    @Update
    suspend fun updateCoupon(coupon: CouponsEntity)

    @Query("SELECT * FROM coupons")
    suspend fun getAllCoupons(): List<CouponsEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCoupon(coupon: CouponsEntity)
}