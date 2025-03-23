package com.example.yourgarden.ui

import com.example.yourgarden.data.coupons.CouponsDao
import com.example.yourgarden.data.coupons.CouponsEntity

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
            CouponsEntity(
                title = "BEST BIRTHDAY OF YOUR LIFE ",
                code = "28062025",
                description = "Dostępne tylko 28.03.2025!",
                used = false,
                date = null
            ),
            CouponsEntity(
                title = "SWEET SURPRISE ",
                code = "1",
                description = "Aktywować najpóźniej 24h przed randką!",
                used = false,
                date = null
            ),
            CouponsEntity(
                title = "DINNER SURPRISE (outside)",
                code = "2",
                description = "Aktywować najpóźniej 24h przed randką!",
                used = false,
                date = null
            ),
            CouponsEntity(
                title = "DINNER SURPRISE (at home)",
                code = "3",
                description = "Aktywować najpóźniej 24h przed randką!",
                used = false,
                date = null
            ),
            CouponsEntity(
                title = "FILM SURPRISE",
                code = "4",
                description = "Aktywować najpóźniej 24h przed randką!",
                used = false,
                date = null
            ),
            CouponsEntity(
                title = "DATE SURPRISE (outside)",
                code = "5",
                description = "Aktywować najpóźniej 48h przed randką, dostępne jak jest ciepło!",
                used = false,
                date = null
            ),
            CouponsEntity(
                title = "DATE SURPRISE (at home)",
                code = "6",
                description = "Aktywować najpóźniej 48h przed randką!",
                used = false,
                date = null
            ),
            CouponsEntity(
                title = "SPICY SURPRISE 😏",
                code = "69",
                description = "Aktywować najpóźniej 2 tygodnie przed randką!",
                used = false,
                date = null
            ),
            CouponsEntity(
                title = "FREAKY SURPRISE",
                code = "7",
                description = "Aktywować najpóźniej 2 tygodnie przed randką!",
                used = false,
                date = null
            ),
            CouponsEntity(
                title = "DIY SURPRISE (from me)",
                code = "8",
                description = "Aktywować najpóźniej 72h przed randką!",
                used = false,
                date = null
            ),
            CouponsEntity(
                title = "DIY SURPRISE (we make it)",
                code = "9",
                description = "Aktywować najpóźniej 48h przed randką!",
                used = false,
                date = null
            ),
            CouponsEntity(
                title = "SPA SURPRISE",
                code = "10",
                description = "Aktywować najpóźniej 48h przed randką!",
                used = false,
                date = null
            ),
            CouponsEntity(
                title = "BEAUTY SPOT SURPRISE",
                code = "11",
                description = "Aktywować tylko w ładną pogodę (zalecane w Warszawie)!",
                used = false,
                date = null
            ),
            CouponsEntity(
                title = "COFFEHOUSE TEST",
                code = "12",
                description = "Aktywować najpóźniej 24h przed randką!",
                used = false,
                date = null
            ),
            CouponsEntity(
                title = "JUST BE WITH ME",
                code = "22062024",
                description = "Aktywować w dowolnym momencie!",
                used = false,
                date = null
            ),
            CouponsEntity(
                title = "COSY SURPRISE",
                code = "13",
                description = "Aktywować 48h przed randką!",
                used = false,
                date = null
            ),
            CouponsEntity(
                title = "KREMÓWKI",
                code = "2137",
                description = "Aktywować 24h przed randką!",
                used = false,
                date = null
            ),
        )
        initialCoupons.forEach { coupon ->
            if (couponsDao.getCouponByCode(coupon.code) == null) {
                couponsDao.insertCoupon(coupon)
            }
        }
    }
}