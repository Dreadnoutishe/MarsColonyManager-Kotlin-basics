class Colony(
    val name: String,
    val crew: MutableList<ColonyMember>,
    val missions: MutableList<Mission>
) {

    var sol: Int = 1

    val resources: MutableMap<String, Double> = mutableMapOf(
        "Oxygen" to 85.0,
        "Energy" to 80.0,
        "Food" to 75.0
    )

    fun showStatus() {
        println("\n========== MARS COLONY: $name ==========")
        println("Sol: $sol")
        println("Population: ${crew.size}")

        for ((resource, amount) in resources) {
            println("$resource: ${"%.1f".format(amount)}%")
        }

        if (isOperational()) {
            println("Status: OPERATIONAL")
        } else {
            println("Status: WARNING")
        }
    }


    fun isOperational(): Boolean {
        return resources.values.all { it >= 20.0 }
    }


    fun advanceSol() {
        sol++

        resources["Oxygen"] =
            (resources.getValue("Oxygen") - 3.0).coerceAtLeast(0.0)

        resources["Energy"] =
            (resources.getValue("Energy") - 4.0).coerceAtLeast(0.0)

        resources["Food"] =
            (resources.getValue("Food") - 2.0).coerceAtLeast(0.0)

        println("\n☀ Sol $sol has begun.")

        println("\nCrew performing daily tasks:")

        processCrew(crew) { member ->
            if (member.available) {
                member.performTask()
            }
        }

        when {
            resources.getValue("Oxygen") < 30 ->
                println("⚠ WARNING: Oxygen level is low!")

            resources.getValue("Energy") < 30 ->
                println("⚠ WARNING: Energy level is low!")

            else ->
                println("Colony systems remain stable.")
        }

        if (hasFailed()) {

            println("\n☠ COLONY FAILURE!")

            resources.forEach { (resource, value) ->
                if (value <= 0.0) {
                    println("$resource has been depleted!")
                }
            }
        }
    }


    fun showCrew() {
        println("\n--- CREW ---")

        crew.forEach {
            val status = if (it.available) "AVAILABLE" else "ON MISSION"
            println("${it.name} — ${it.role} — $status")
        }

        // Set demonstration: unique skills across the whole colony
        val allSkills: Set<String> = crew
            .flatMap { it.skills }
            .toSet()

        println("\nColony skills: ${allSkills.joinToString()}")
    }
    fun applyMissionReward(mission: Mission) {

        println("\n--- MISSION REWARDS ---")

        mission.resourceReward.forEach { (resource, amount) ->

            val currentValue = resources.getValue(resource)

            resources[resource] =
                (currentValue + amount).coerceAtMost(100.0)

            println("+$amount $resource")
        }
    }
    fun hasFailed(): Boolean {
        return resources.values.any { it <= 0.0 }
    }
}

