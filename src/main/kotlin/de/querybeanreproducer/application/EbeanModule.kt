package de.querybeanreproducer.application

import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import io.ebean.Database
import io.ebean.DatabaseFactory
import io.ebean.config.DatabaseConfig
import io.ebean.datasource.DataSourceBuilder
import io.ebean.datasource.DataSourcePool
import io.ebean.platform.postgres.PostgresPlatform
import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}

class EbeanModule() {

    fun createDatabase(): Database {
        return buildDatabaseInstance()
    }

    // create DataSource instance, this represents a connection pool to the database
    private fun buildDataSourceInstance(): DataSourcePool {
        val host = "localhost"
        val port = "5439"
        val username = "postgres"
        val password = "Test12345"
        val driver = "org.postgresql.Driver"
        val databaseName = "querybeanreproducer"
        val jdbcUrl = "jdbc:postgresql://$host:$port/$databaseName";

        logger.info { "Configuring DataSource driver=$driver jdbcUrl=$jdbcUrl username=$username" }

        // datasource
        return DataSourceBuilder.create()
            .username(username)
            .password(password)
            .url(jdbcUrl)
            .driver(driver)
            .build()
    }

    /**
     * Build Ebean Database instance using supplied arguments
     */
    private fun buildDatabaseInstance(): Database {
        // configuration
        val config = DatabaseConfig()
        config.name = "db"
        config.dbSchema = "core" // set default schema
        config.databasePlatform = PostgresPlatform()

        // disable automatic ddl, this is handled manually on a per-schema basis
        config.isDdlGenerate = false
        config.isDdlRun = false
        config.isRunMigration = false

        config.objectMapper = JsonMapper.builder()
            .addModule(ParameterNamesModule())
            .addModule(Jdk8Module())
            .addModule(JavaTimeModule())
            .build()

        config.addPackage("de.querybeanreproducer.schemasql")

        val rawDataSource = buildDataSourceInstance()
        config.dataSource = rawDataSource

        return DatabaseFactory.create(config)
    }
}
