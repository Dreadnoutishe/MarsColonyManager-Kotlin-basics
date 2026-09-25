interface Worker {
    val name: String
    val skills: Set<String>

    fun performTask()
}


abstract class ColonyMember(
    override val name: String,
    val age: Int,
    val role: String,
    override val skills: Set<String>
) : Worker {

    var available: Boolean = true

    fun introduce() {
        println("$name, $age — $role")
    }
}


class Engineer(
    name: String,
    age: Int
) : ColonyMember(
    name,
    age,
    "Engineer",
    setOf("Engineering", "Repair", "Construction")
) {
    override fun performTask() {
        println(" $name is inspecting colony systems.")
    }
}


class Scientist(
    name: String,
    age: Int
) : ColonyMember(
    name,
    age,
    "Scientist",
    setOf("Research", "Biology", "Geology")
) {
    override fun performTask() {
        println(" $name is analyzing Martian samples.")
    }
}


class Pilot(
    name: String,
    age: Int
) : ColonyMember(
    name,
    age,
    "Pilot",
    setOf("Piloting", "Navigation", "Exploration")
) {
    override fun performTask() {
        println(" $name is preparing the rover.")
    }
}

fun processCrew(
    crew: List<ColonyMember>,
    action: (ColonyMember) -> Unit
) {
    crew.forEach(action)
}