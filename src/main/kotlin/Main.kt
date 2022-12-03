import pl.ciborowski.konrad.controller.TaskFileParser
import pl.ciborowski.konrad.entities.DC2

fun main(args: Array<String>) {

    val fileName: String
    if (args.size > 0) {
        fileName = args[0]
    } else {
        fileName = "/Users/konradciborowski/asm/task3.asm"
    }

    val taskFileParser = TaskFileParser(fileName)
    val content = taskFileParser.readFileContent()
    val configuration = taskFileParser.createConfiguration(content)
    val instructions = taskFileParser.parseProgramSegment(content.programLines)

    val computer = DC2(configuration)

    val data = taskFileParser.parseDataSegment(content.dataLines)
    data.forEach { entry ->
        computer.memory.insert(entry.key, entry.value)
    }

    for ( cell in instructions.keys) {
        computer.memory.insert(cell, instructions.get(cell)!!)
    }
    computer.run()
}