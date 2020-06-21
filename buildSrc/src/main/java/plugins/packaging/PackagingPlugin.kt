package plugins.packaging

import com.android.build.gradle.BaseExtension
import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logging

/**
 * Can use this plugin or an extension function. Not sure which is preferable tbh
 */
@Suppress("unused")
class PackagingPlugin : Plugin<Project> {

    private val logger = Logging.getLogger(PackagingPlugin::class.java)

    override fun apply(project: Project) {

        val androidExtension = project.extensions.findByType(BaseExtension::class.java)
        if (androidExtension != null) {
            logger.lifecycle("Located Android Extension.")
            androidExtension.packagingOptions(Action {
                exclude("META-INF/DEPENDENCIES")
                exclude("META-INF/LICENSE ")
                exclude("META-INF/LICENSE.txt ")
                exclude("META-INF/license.txt ")
                exclude("META-INF/NOTICE ")
                exclude("META-INF/NOTICE.txt ")
                exclude("META-INF/notice.txt ")
                exclude("META-INF/LGPL2.1 ")
                exclude("META-INF/MANIFEST.MF ")
                exclude("META-INF/rxjava.properties ")
                exclude("META-INF/ASL2.0 ")
            })
        } else {
            logger.error("Project does not contain Android Extension.")
        }
    }
}
