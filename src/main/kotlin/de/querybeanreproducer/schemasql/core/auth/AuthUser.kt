package de.querybeanreproducer.schemasql.core.auth

import io.ebean.Model
import io.ebean.annotation.DbDefault
import io.ebean.annotation.Index
import jakarta.persistence.*

@Entity
@Table(name = "auth_user")
class AuthUser : Model() {

    @Id
    @GeneratedValue
    @Column(name = "id")
    var id: Long = 0L
        internal set

    @DbDefault("3")
    var userType: AuthUserType = AuthUserType.TENANT

    @Column(nullable = false)
    var firstName: String? = null

    @Column(nullable = false)
    var lastName: String? = null

    @Index(unique = true)
    @Column(nullable = false)
    var email: String? = null

    @ManyToMany(mappedBy = "users")
    var tenants: MutableList<AuthTenant> = mutableListOf()

    override fun toString(): String {
        return "AuthUser(id=$id, userType=$userType, firstName=$firstName, lastName=$lastName, email=$email)"
    }

}
