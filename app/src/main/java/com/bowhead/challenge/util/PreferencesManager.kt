package com.bowhead.challenge.util

import android.content.Context
import android.content.SharedPreferences
import android.os.Build

object PreferencesManager {
    private val TAG = PreferencesManager::class.java.simpleName

    //    private static PreferencesManager instance;
    private var pref: SharedPreferences? = null
    fun init(context: Context) {
        val PREF_NAME = context.packageName
                pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE or Context.MODE_MULTI_PROCESS)

    }

    private val editor: SharedPreferences.Editor
        private get() = pref!!.edit()

    fun savePref(key: String?, value: Any?) {
        val editor = editor
        if (value is Boolean) {
            editor.putBoolean(key, (value as Boolean?)!!)
        } else if (value is Int) {
            editor.putInt(key, (value as Int?)!!)
        } else if (value is Float) {
            editor.putFloat(key, (value as Float?)!!)
        } else if (value is Long) {
            editor.putLong(key, (value as Long?)!!)
        } else if (value is String) {
            editor.putString(key, value as String?)
        } else if (value is Enum<*>) {
            editor.putString(key, value.toString())
        } else if (value != null) {
            throw RuntimeException("Attempting to save non-primitive preference")
        }
        editor.commit()
    }

    fun <T> getPref(key: String?): T? {
        return pref!!.all[key] as T?
    }

    fun <T> getPref(key: String?, defValue: T): T {
        val returnValue = pref!!.all[key] as T?
        return returnValue ?: defValue
    }

    fun remove(key: String?) {
        val editor = editor
        editor.remove(key)
        editor.commit()
    }

    fun clear() {
        val editor = editor
        editor.clear()
        editor.commit()
    }

    object keys {
        const val WALLET_DIR = "wallet_dir"
        const val PASS = "pass"
    }
}