package de.querybeanreproducer.schemasql.core.auth

import java.time.Instant

interface ITemporalAwareDomainModel {
    var modifiedAt: Instant
    var createdAt: Instant
}
