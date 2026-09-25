import kotlinx.coroutines.delay

suspend fun executeMission(
    mission: Mission,
    crew: List<ColonyMember>
) {
    println("\n Mission '${mission.name}' is starting...")
    println("Destination: ${mission.location}")
    println("Crew: ${crew.joinToString { it.name }}")

    crew.forEach { it.available = false }

    for (progress in 20..100 step 20) {

        mission.status = MissionStatus.InProgress(progress)

        println("Mission progress: $progress%")

        delay(1000)
    }

    val samplesCollected = mission.reward / 10

    mission.status = MissionStatus.Completed(samplesCollected)

    crew.forEach { it.available = true }

    println("! Mission '${mission.name}' completed!")
    println("Samples collected: $samplesCollected")
}