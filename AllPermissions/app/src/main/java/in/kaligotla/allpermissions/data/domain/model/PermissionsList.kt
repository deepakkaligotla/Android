package `in`.kaligotla.allpermissions.data.domain.model

import `in`.kaligotla.allpermissions.data.domain.model.entities.Permission

data class PermissionsList(
    val status: String,
    val data: List<Permission>
)