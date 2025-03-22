package com.example.yourgarden.ui

import com.example.yourgarden.data.coupons.CouponsDao
import com.example.yourgarden.data.coupons.CouponsEntity
import java.util.Date

class CouponsRepository(private val couponsDao: CouponsDao) {

    suspend fun getCouponByCode(code: String): CouponsEntity? {
        return couponsDao.getCouponByCode(code)
    }

    suspend fun updateCoupon(coupon: CouponsEntity) {
        couponsDao.updateCoupon(coupon)
    }

    suspend fun getAllCoupons(): List<CouponsEntity> {
        return couponsDao.getAllCoupons()
    }

    suspend fun insertInitialCoupons() {
        val initialCoupons = listOf(
            CouponsEntity(title = "KUPON10", code = "10", used = false, date = null),
            CouponsEntity(title = "KUPON20", code = "20", used = false, date = null)
        )
        initialCoupons.forEach { coupon ->
            if (couponsDao.getCouponByCode(coupon.code) == null) {
                couponsDao.insertCoupon(coupon) // Poprawione z updateCoupon na insertCoupon
            }
        }
    }
}