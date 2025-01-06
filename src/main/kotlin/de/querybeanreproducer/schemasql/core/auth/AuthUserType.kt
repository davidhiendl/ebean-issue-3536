package de.querybeanreproducer.schemasql.core.auth

/**
 * NOTICE database use ordinal, do not reorder/remove elements or add elements except at the end of the list
 */
enum class AuthUserType {
    ROOT,
    SYSTEM,
    STAFF,
    TENANT,
}
