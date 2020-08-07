package plugins.stuff

data class Available(
        val integration: Any?,
        val milestone: String,
        val release: Any?
)

data class Current(
        val count: Int,
        val dependencies: List<DependencyCurrent>
)

data class CurrentGradle(
        val isFailure: Boolean,
        val isUpdateAvailable: Boolean,
        val reason: String,
        val version: String
)

data class DependencyOutDated(
        val available: Available,
        val group: String,
        val name: String,
        val projectUrl: String?,
        val version: String
)

data class DependencyExceeded(
        val group: String,
        val latest: String,
        val name: String,
        val projectUrl: String?,
        val version: String
)

data class DependencyUnresolved(
        val group: String,
        val name: String,
        val projectUrl: String?,
        val reason: String,
        val version: String
)

data class DependencyCurrent(
        val group: String,
        val name: String,
        val projectUrl: String?,
        val version: String
)

data class Exceeded(
        val count: Int,
        val dependencies: List<DependencyExceeded>
)

data class Gradle(
        val current: CurrentGradle,
        val enabled: Boolean,
        val nightly: Nightly,
        val releaseCandidate: ReleaseCandidate,
        val running: Running
)

data class GradleResults(
        val count: Int,
        val current: Current,
        val exceeded: Exceeded,
        val gradle: Gradle,
        val outdated: Outdated,
        val unresolved: Unresolved
)

data class Nightly(
        val isFailure: Boolean,
        val isUpdateAvailable: Boolean,
        val reason: String,
        val version: String
)

data class Outdated(
        val count: Int,
        val dependencies: List<DependencyOutDated>
)

data class ReleaseCandidate(
        val isFailure: Boolean,
        val isUpdateAvailable: Boolean,
        val reason: String,
        val version: String
)

data class Running(
        val isFailure: Boolean,
        val isUpdateAvailable: Boolean,
        val reason: String,
        val version: String
)

data class Unresolved(
        val count: Int,
        val dependencies: List<DependencyUnresolved>
)

data class SimpleDependency(
        val group: String,
        val name: String,
        val currentVersion: String,
        var projectUrl: String? = null
) {
        val id = "$group:$name"
        var latest: String? = null
        var latestStable: String? = null
}

data class DependencyCustomerUpdate(
        val group: String,
        val name: String,
        val latest: String?,
        val latestStable: String?
)
