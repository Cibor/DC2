package pl.ciborowski.konrad.entities

class TaskFileContent private constructor(
    val memorySize: String?,
    val debugMode: String?,
    val dataLines: List<String>?,
    var printableCellNumbers: String?,
    val programLines: List<String>?) {

    data class Builder(

        var memorySize: String? = null,
        var debugMode: String? = null,
        var dataLines: List<String>? = null,
        var programLines: List<String>? = null,
        var printableCellNumbers: String? = null) {

        fun withMemorySize(size: String?) = apply { this.memorySize = size }
        fun withDebugMode(debugMode: String?) = apply { this.debugMode = debugMode }
        fun withData(dataLines: List<String>) = apply {this.dataLines = dataLines }
        fun withProgramLines(programLines: List<String>) = apply { this.programLines = programLines }
        fun withprintableCellNumbers(printableCellNumbers: String?) = apply { this.printableCellNumbers = printableCellNumbers }

        fun build() = TaskFileContent(memorySize, debugMode, dataLines, printableCellNumbers, programLines) }

}



