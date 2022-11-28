package pl.ciborowski.konrad.controller

import pl.ciborowski.konrad.entities.DC2Config
import pl.ciborowski.konrad.entities.Instruction
import pl.ciborowski.konrad.entities.TaskFileContent
import java.io.File
import java.util.*

class TaskFileParser(val fileName: String) {

    fun readFileContent(): TaskFileContent {
        val dataLines = mutableListOf<String>()
        val programLines = mutableListOf<String>()
        val lines = File(fileName).readLines()
        var memorySize: String? = null
        var debugMode: String? = null
        var printableCellNumbers: String? = null
        var dataSegment = false
        var programSegment = false
        for (line in lines) {
            if (line.trim().isEmpty() || line.startsWith("#")) {
                continue
            }
            if (line.startsWith("DEBUG: ")) {
                val tokens = line.trim().split(" ")
                require(tokens.size == 2) { " Incorrect format of line $line in configuration file" }
                debugMode = tokens.get(1)
                continue
            }
            if (line.startsWith("MEMORY: ")) {
                val tokens = line.trim().split(" ")
                require(tokens.size == 2) { " Incorrect format of line $line in configuration file" }
                memorySize = tokens.get(1)
                continue
            }
            if (line.trim() == "DATA:") {
                dataSegment = true
                programSegment = false
                continue
            }
            if (line.trim().startsWith("PRINT: ")) {
                val tokens = line.trim().split(" ")
                require(tokens.size == 2) { " Incorrect format of line $line in configuration file" }
                printableCellNumbers = tokens.get(1)
            }
            if (line.trim() == "PROGRAM:") {
                dataSegment = false
                programSegment = true
                continue
            }
            if (dataSegment) {
                dataLines.add(line)
            }
            if (programSegment) {
                programLines.add(line)
            }
        }
        return TaskFileContent.Builder()
            .withMemorySize(memorySize)
            .withData(dataLines)
            .withDebugMode(debugMode)
            .withprintableCellNumbers(printableCellNumbers)
            .withProgramLines(programLines)
            .build()
    }


    fun parseProgramSegment(programlines: List<String>?): List<Instruction> {
        require (programlines != null) { " Program segment is missing " }
        val instructions = mutableListOf<Instruction>()
        for (line in programlines) {
            instructions.add(readFileContent(line))
        }
        require (instructions.isNotEmpty()) { " Program segment is empty " }
        return instructions.toList()
    }

    fun parseDataSegment(dataSegmentlines: List<String>?): Map<Int, Int> {
        if (dataSegmentlines == null) {
            return emptyMap()
        }
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

    fun createConfiguration(fileContent: TaskFileContent): DC2Config {
        val memorySize: Int
        if (fileContent.memorySize == null) {
            memorySize = 512
        } else {
            memorySize = fileContent.memorySize.toInt()
        }
        require(fileContent.programLines != null) { " Program section missing " }
        val debugMode: Boolean
        if (fileContent.debugMode == null) {
            debugMode = false
        } else {
            require(fileContent.debugMode == "ON" || fileContent.debugMode == "OFF") { " Debug mode should be ON/OFF " }
            debugMode = fileContent.debugMode == "ON"
        }
        val printableCellNumbers = parsePrintableCells(fileContent.printableCellNumbers)
        return DC2Config(memorySize, printableCellNumbers, debugMode)
    }

    fun parsePrintableCells(printableCellNumbers: String?): SortedSet<Int> {
        if (printableCellNumbers == null) {
            sortedSetOf<Int>()
        }
        val cellsNumbersInRange = mutableSetOf<Int>()
        val tokens = printableCellNumbers!!.split(",")
        for (token in tokens) {
            if (token.contains(".")) {
                cellsNumbersInRange.addAll(parseRange(token))
            } else {
                cellsNumbersInRange.add(token.toInt())
            }
        }
        return cellsNumbersInRange.toSortedSet()
    }

    private fun parseRange(range: String): Set<Int> {
        val rangeRegex = """(\d+)\.\.(\d+)""".toRegex()
        require(rangeRegex.matches(range)) { " The range $range has incorrect format " }
        val matchResult = rangeRegex.find(range)
        val rangeStart = matchResult!!.groups[1]!!.value.toInt()
        val rangeEnd = matchResult.groups[2]?.value!!.toInt()
        require(rangeStart < rangeEnd) { " The ends of the range $range are incorrect " }
        val cellsNumbersInRange = mutableSetOf<Int>()
        for (cellNumber in rangeStart until rangeEnd + 1) {
            cellsNumbersInRange.add(cellNumber)
        }
        return cellsNumbersInRange.toSet()
    }
}