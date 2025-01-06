package de.querybeanreproducer.application

import de.querybeanreproducer.schemasql.core.auth.query.QAuthTenant
import de.querybeanreproducer.schemasql.core.auth.query.QAuthUser
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlin.system.exitProcess

private val logger = KotlinLogging.logger {}

object App {

    @JvmStatic
    fun main(args: Array<String>) {

        // logStartupJVMArgs()

        // show error when using arguments
        if (args.isNotEmpty()) {
            logger.error { "Started with arguments, configuration does not rely on arguments: ${args.contentDeepToString()}" }
            exitProcess(1)
        }

        val db = EbeanModule().createDatabase()

        val users = QAuthUser(db)
            .tenants.filterMany(
                QAuthTenant()
                    .name.eq("Test")
                    .expressionList
            )
            .findList()

        // start micronaut
        logger.debug { "Exiting main thread" }
    }

}
