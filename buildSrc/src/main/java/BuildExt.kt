import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.android.build.gradle.internal.dsl.BuildType
import org.gradle.api.NamedDomainObjectContainer

fun BaseAppModuleExtension.packagingBoilerPlate() {
    packagingOptions {
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
    }
}

fun NamedDomainObjectContainer<BuildType>.releaseBuild(block: BuildType.() -> Unit): BuildType {
    return getByName("release") {
        block()
    }
}

fun NamedDomainObjectContainer<BuildType>.debugBuild(block: BuildType.() -> Unit): BuildType {
    return getByName("debug") {
        block()
    }
}
