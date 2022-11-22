package pl.ciborowski.konrad.entities

class DC2(val memorySize: Int) {

    var programCounter: Int = 0
    var accumulator: Int = 0
    var memory: Memory = Memory(memorySize)

    fun invokeCommand(command: Command, operand: Operand) {
        command.execute(this, operand)
    }
}