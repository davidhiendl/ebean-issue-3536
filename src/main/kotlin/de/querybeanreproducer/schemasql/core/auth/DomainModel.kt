package de.querybeanreproducer.schemasql.core.auth

import io.ebean.Model
import jakarta.persistence.*

@MappedSuperclass
abstract class DomainModel : Model() {

    @Id
    @GeneratedValue
    @Column(name = "id")
    var id: Long = 0L
        internal set

    @Version
    @Column(name = "version")
    var version: Long? = null
        internal set

    val isPersisted: Boolean
        get() = id > 0

}
