package com.xmartlabs.bigbang.core.controller

import android.content.SharedPreferences
import android.support.annotation.CheckResult
import com.xmartlabs.bigbang.core.model.SessionType

/**
 * Controller that manages the Session of the Application.
 *
 * The Session will be stored via the [SharedPreferencesController].
 */
abstract class CoreSessionController(private val sharedPreferencesController: SharedPreferencesController) : Controller() {
  companion object {
    private val PREFERENCES_KEY_SESSION = "session"
  }

  abstract fun getSessionType(): Class<out SessionType>

  /**
   * Retrieves the current stored [SessionType], if it exists.
   *
   * @return the current [SessionType], or `null` if none exists
   */
  open val abstractSession
    get() = sharedPreferencesController.getEntity(PREFERENCES_KEY_SESSION, getSessionType())

  /**
   * Returns whether the [SessionType] information is present on the device.
   *
   * @return whether or not the [SessionType] information exists
   */
  open val isSessionAlive
    @CheckResult
    get() = sharedPreferencesController.hasEntity(PREFERENCES_KEY_SESSION)

  /**
   * Stores the `session` into the [SharedPreferences].
   *
   * @param session the `S` object to be stored
   * *
   * @param <S> the [SessionType] object to be stored
   * */
  open fun <S : SessionType> saveSession(session: S) =
      sharedPreferencesController.saveEntity(PREFERENCES_KEY_SESSION, session)

  /** Deletes the session information  */
  open fun deleteSession() = sharedPreferencesController.deleteEntity(PREFERENCES_KEY_SESSION)
}
