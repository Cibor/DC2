package pl.ciborowski.konrad.entities

import pl.ciborowski.konrad.entities.MemoryCellType.INSTRUCTION

class DC2(val memorySize: Int) {

    var programCounter: Int = 0
    var accumulator: Int = 0
    var memory: Memory = Memory(memorySize)

    fun invokeCommand(command: Command, operand: Operand) {
        command.execute(this, operand)
    }

    fun run() {
        programCounter = 0
        do {
            val cell = memory.readCell(programCounter)
            require(cell.type() == INSTRUCTION) { "CRASH! Trying to read an instruction from cell $programCounter" }
            val instructionCell = cell as InstructionMemoryCell
            val command = instructionCell.instruction.command
            val operand = instructionCell.instruction.operand
            invokeCommand(command, operand)
            if (!command.modifiesProgramCounter()) {
                programCounter++
            }
        } while (programCounter >= 0)
    }
}