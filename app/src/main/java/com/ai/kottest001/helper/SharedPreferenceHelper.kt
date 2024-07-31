package com.ai.kottest001.helper

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferenceHelper @Inject constructor(@ApplicationContext context : Context) {
    companion object {
        private const val NAME = "chat_gpt"
        private const val MODE = Context.MODE_PRIVATE
    }
    private var preferences: SharedPreferences = context.getSharedPreferences(NAME, MODE)

    private val OPENAI_API_KEY = Pair("OPENAI_API_KEY", "")
    private val OPENAI_PROJECT_KEY = Pair("OPENAI_PROJECT_KEY", "")
    private val OPENAI_ORG_KEY = Pair("OPENAI_ORG_KEY", "")

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit)
    {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var openAIkey: String?
        // custom getter to get a preference of a desired type, with a predefined default value
        get()
        {
            return preferences.getString(OPENAI_API_KEY.first, OPENAI_API_KEY.second)
        }

        // custom setter to save a preference back to preferences file
        set(value)
        {
            preferences.edit {
                it.putString(OPENAI_API_KEY.first, value)
            }
        }

    var openPrjkey: String?
        get()
        {
            return preferences.getString(OPENAI_PROJECT_KEY.first, OPENAI_PROJECT_KEY.second)
        }
        set(value)
        {
            preferences.edit {
                it.putString(OPENAI_PROJECT_KEY.first, value)
            }
        }

    var openOrgkey: String?
        get()
        {
            return preferences.getString(OPENAI_ORG_KEY.first, OPENAI_ORG_KEY.second)
        }
        set(value)
        {
            preferences.edit {
                it.putString(OPENAI_ORG_KEY.first, value)
            }
        }

    var SharedPreferences.clearValues
        get() = { }
        set(value) {
            preferences.edit {
                it.clear()
            }
        }
}