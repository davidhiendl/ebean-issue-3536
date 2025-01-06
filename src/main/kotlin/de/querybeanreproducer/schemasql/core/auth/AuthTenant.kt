package de.querybeanreproducer.schemasql.core.auth

import io.ebean.Model
import io.ebean.annotation.DbDefault
import io.ebean.annotation.Index
import jakarta.persistence.*

@Entity
@Table(name = "auth_tenant")
class AuthTenant : Model() {

    @Id
    @GeneratedValue
    @Column(name = "id")
    var id: Long = 0L
        internal set

    @Index
    @Column(nullable = false)
    var name: String = ""

    @Index
    @Column(nullable = false)
    var active: Boolean = true

    @ManyToMany(fetch = FetchType.LAZY)
    var users = mutableListOf<AuthUser>()

    override fun toString(): String {
        return "AuthTenant(id=$id, name=$name)"
    }

}
