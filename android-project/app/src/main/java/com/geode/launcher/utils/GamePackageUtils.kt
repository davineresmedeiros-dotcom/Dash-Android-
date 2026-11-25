package com.geode.launcher.utils

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log

object GamePackageUtils {
    fun isGameInstalled(packageManager: PackageManager): Boolean {
        return try {
            packageManager.getPackageInfo(Constants.PACKAGE_NAME, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    fun showDownloadBadge(packageManager: PackageManager) =
        !isGameInstalled(packageManager)

    fun getGameVersionCode(packageManager: PackageManager): Long {
        val game = packageManager.getPackageInfo(Constants.PACKAGE_NAME, 0)

        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            game.longVersionCode
        } else {
            @Suppress("DEPRECATION")
            game.versionCode.toLong()
        }
    }

    fun getGameVersionCodeOrNull(packageManager: PackageManager): Long? {
        return try {
            getGameVersionCode(packageManager)
        } catch (e: PackageManager.NameNotFoundException) {
            null
        }
    }

    fun getGameVersionString(packageManager: PackageManager): String {
        return try {
            val game = packageManager.getPackageInfo(Constants.PACKAGE_NAME, 0)
            return game.versionName ?: "0.000"
        } catch (_: PackageManager.NameNotFoundException) {
            "0.000"
        }
    }

    private val gameVersionMap = mapOf(
        37L to "2.200",
        38L to "2.205",
        39L to "2.206",
        40L to "2.2074", // ok
        41L to "2.2081",
        42L to "2.209",
        43L to "2.2091",
    )

    fun getUnifiedVersionName(packageManager: PackageManager): String {
        val versionCode = getGameVersionCode(packageManager)
        return gameVersionMap[versionCode] ?: getGameVersionString(packageManager)
    }

    fun getSupportedUnifiedVersion(): String {
        return gameVersionMap[Constants.SUPPORTED_VERSION_CODE] ?: Constants.SUPPORTED_VERSION_STRING
    }
}