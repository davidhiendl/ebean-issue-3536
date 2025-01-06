package de.querybeanreproducer.schemasql.core.auth

import io.ebean.annotation.DbDefault
import io.ebean.annotation.WhenCreated
import io.ebean.annotation.WhenModified
import io.ebean.config.dbplatform.DbDefaultValue
import java.time.Instant
import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass

@MappedSuperclass
abstract class TemporalAwareDomainModel : DomainModel(),
    ITemporalAwareDomainModel {

    @WhenModified
    @Column(name = "modified_at")
    @DbDefault(DbDefaultValue.NOW)
    override lateinit var modifiedAt: Instant

    @WhenCreated
    @Column(name = "created_at")
    @DbDefault(DbDefaultValue.NOW)
    override lateinit var createdAt: Instant

}
