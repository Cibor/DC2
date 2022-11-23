package pl.ciborowski.konrad.controller

import pl.ciborowski.konrad.entities.Instruction
import pl.ciborowski.konrad.entities.TaskFileContent
import java.io.File

class TaskFileParser(val fileName: String) {

    fun parse() : TaskFileContent {
        val dataLines = mutableListOf<String>()
        val programLines = mutableListOf<String>()
        val lines = File(fileName).readLines()
        var dataSegment = false
        var programSegment = false
        for (line in lines) {
            if (line.trim().isEmpty() || line.startsWith("#")) {
                continue
            }
            if (line.trim() == "DATA:") {
                dataSegment = true
                programSegment = false
            }
            if (line.trim() == "PROGRAM:") {
                dataSegment = false
                programSegment = true
            }
            if (dataSegment) {
                dataLines.add(line)
            }
            if (programSegment) {
                programLines.add(line)
            }
        }
        val data = parseDataSegment(dataLines)
        val program = parseProgramSegment(programLines)
        return TaskFileContent(data, program)
    }

}

fun parseProgramSegment(programlines: List<String>) : List<Instruction> {
    val instructions = mutableListOf<Instruction>()
    for (line in programlines) {
        instructions.add(parse(line))
    }
    return instructions.toList()
}

fun parseDataSegment(dataSegmentlines: List<String>) : Map<Int, Int> {
    val memoryDataMap = mutableMapOf<Int, Int>()
    val dataRegex = """(\d+):\s+([+-]*\d+)\s*""".toRegex()
    for (line in dataSegmentlines) {
        require(dataRegex.matches(line)) { "Incorrect format of data for $line" }
        val matchResult = dataRegex.find(line)
        val cellNumberAsString = matchResult!!.groups[1]!!.value
        val cellValueAsString = matchResult.groups[2]!!.value
        val cell = cellNumberAsString.toInt()
        require(!memoryDataMap.containsKey(cell)) { "Value for cell $cell already defined in the data segment" }
        memoryDataMap.put(cell, cellValueAsString.toInt())
    }
    return memoryDataMap.toMap()
}
