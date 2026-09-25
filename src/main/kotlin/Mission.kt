data class Mission(
    val id: Int,
    val name: String,
    val location: String,
    val requiredCrew: Int,
    val reward: Int,
    val resourceReward: Map<String, Double>,
    var status: MissionStatus = MissionStatus.Available
)


sealed class MissionStatus {

    object Available : MissionStatus()

    data class InProgress(
        val progress: Int
    ) : MissionStatus()

    data class Completed(
        val samplesCollected: Int
    ) : MissionStatus()

    data class Failed(
        val reason: String
    ) : MissionStatus()
}