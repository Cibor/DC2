import pl.ciborowski.konrad.controller.TaskFileParser
import pl.ciborowski.konrad.entities.DC2

fun main(args: Array<String>) {

    val fileName: String
    if (args.size > 0) {
        fileName = args[0]
    } else {
        fileName = "/Users/konradciborowski/asm/task.asm"
    }

    val taskFileParser = TaskFileParser(fileName)
    val computer = DC2(memorySize = 512)

    val content = taskFileParser.parse()

    content.data.forEach { entry ->
        computer.memory.insert(entry.key, entry.value)
    }

    var cell = 0
    for ( instruction in content.instructions) {
        computer.memory.insert(cell, instruction)
        cell++
    }
    computer.run()
}