import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    val crew = mutableListOf<ColonyMember>(
        Engineer("Alex", 32),
        Scientist("Dr. Miller", 45),
        Pilot("Sarah", 29),
        Engineer("Emma", 28),
        Scientist("David", 38)
    )
2
    val missions = mutableListOf(

        Mission(
            1,
            "Search for Water",
            "Valles Marineris",
            2,
            100,
            mapOf(
                "Oxygen" to 10.0,
                "Food" to 5.0
            )
        ),

        Mission(
            2,
            "Solar Array Repair",
            "Solar Field Alpha",
            2,
            120,
            mapOf(
                "Energy" to 15.0
            )
        ),

        Mission(
            3,
            "Greenhouse Expedition",
            "Gale Crater",
            3,
            150,
            mapOf(
                "Food" to 15.0,
                "Oxygen" to 5.0
            )
        )
    )

    val colony = Colony(
        "Ares-1",
        crew,
        missions
    )

    var running = true

    while (running) {

        colony.showStatus()

        println(
            """
            
            ---------- CONTROL PANEL ----------
            1. View crew
            2. View missions
            3. Send crew on mission
            4. Advance one Sol
            5. Colony statistics
            0. Exit
            -----------------------------------
            """.trimIndent()
        )

        print("Choose an action: ")

        when (readlnOrNull()?.toIntOrNull()) {

            1 -> colony.showCrew()

            2 -> showMissions(colony.missions)

            3 -> launchMission(colony)

            4 -> {
                colony.advanceSol()

                if (colony.hasFailed()) {
                    println("\nThe Ares-1 colony could not survive.")
                    println("Final survival time: ${colony.sol} Sols")
                    running = false
                }
            }

            5 -> showStatistics(colony)

            0 -> {
                println("\nShutting down Mars Colony Manager...")
                running = false
            }

            else -> println("Invalid command.")
        }
    }
}

fun showMissions(missions: List<Mission>) {

    println("\n--- MISSIONS ---")

    missions.forEach { mission ->

        val statusText = when (val status = mission.status) {

            MissionStatus.Available ->
                "AVAILABLE"

            is MissionStatus.InProgress ->
                "IN PROGRESS (${status.progress}%)"

            is MissionStatus.Completed ->
                "COMPLETED"

            is MissionStatus.Failed ->
                "FAILED: ${status.reason}"
        }

        println(
            "${mission.id}. ${mission.name} | " +
                    "${mission.location} | " +
                    "Crew: ${mission.requiredCrew} | " +
                    statusText
        )
    }
}

suspend fun launchMission(colony: Colony) {

    showMissions(colony.missions)

    print("\nEnter mission ID: ")

    val id = readlnOrNull()?.toIntOrNull()

    val mission = colony.missions.find {
        it.id == id
    }

    if (mission == null) {
        println("Mission not found.")
        return
    }

    if (mission.status !is MissionStatus.Available) {
        println("This mission is not available.")
        return
    }

    val availableCrew = colony.crew.filter {
        it.available
    }

    if (availableCrew.size < mission.requiredCrew) {
        println("Not enough available crew.")
        return
    }

    val selectedCrew = availableCrew.take(
        mission.requiredCrew
    )

    executeMission(mission, selectedCrew)
    colony.applyMissionReward(mission)
}

fun showStatistics(colony: Colony) {

    println("\n--- COLONY STATISTICS ---")

    val ages = colony.crew.map {
        it.age
    }

    val totalAge = ages.reduce { total, age ->
        total + age
    }

    val averageAge =
        totalAge.toDouble() / ages.size

    val completedMissions = colony.missions.filter {
        it.status is MissionStatus.Completed
    }

    val totalReward = completedMissions
        .map { it.reward }
        .fold(0) { total, reward ->
            total + reward
        }

    println("Crew members: ${colony.crew.size}")
    println("Average age: ${"%.1f".format(averageAge)}")
    println("Completed missions: ${completedMissions.size}")
    println("Total research points: $totalReward")
}