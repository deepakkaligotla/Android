package `in`.kaligotla.deepak.domain.repository

import `in`.kaligotla.deepak.domain.model.Response

typealias SignOutResponse = Response<Boolean>
typealias RevokeAccessResponse = Response<Boolean>

interface ProfileRepository {
    val displayName: String
    val photoUrl: String
    val userPhoneNumber: String

    suspend fun signOut(): SignOutResponse
    suspend fun revokeAccess(): RevokeAccessResponse
}