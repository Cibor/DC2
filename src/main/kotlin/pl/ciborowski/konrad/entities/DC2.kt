package pl.ciborowski.konrad.entities

import pl.ciborowski.konrad.entities.MemoryCellType.*
import pl.ciborowski.konrad.entities.AddressingMode.*

class DC2(val configuration: DC2Config) {

    var programCounter = 0
    var accumulator = 0
    var memory: Memory
    var debugMode: Boolean

    init {
        require(configuration.memorySize > 0) { "Memory size cannot be negative"}
        memory = Memory(configuration.memorySize)
        debugMode = configuration.debugMode
        require(configuration.printableCells.isNotEmpty()) { "Printable cells range is empty" }
        require(configuration.printableCells.max() <= configuration.memorySize) { " Printable cell number exceeds memory size "}
    }



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
            if (debugMode) {
                printInstruction(Instruction(command, operand))
                print("\t")
                printState()
            }
        } while (programCounter >= 0)
        if (!debugMode) {
            printState()
        }
    }

    private fun printState() {
        print("PC: $programCounter\t")
        print("A: $accumulator\t")
        var firstItemPrinted = false
        for (cellNumber in configuration.printableCells) {
            val cell = memory.readCell(cellNumber)
            if (firstItemPrinted) {
                print(", ")
            }
            if (cell.type() == DATA) {
                print("$cellNumber: ${cell.numericValue()} ")
            } else {
                print("$cellNumber: ")
                print("""""")
                printInstruction((cell as InstructionMemoryCell).instruction)
                print("""""")
            }
            firstItemPrinted = true
        }
        println()
    }

    private fun printInstruction(instruction: Instruction) {
        print("${instruction.command.mnemonic.name} ")
        when (instruction.operand.addressingMode) {
            IMMEDIATE -> print(". ")
            DIRECT -> print("@ ")
            INDIRECT -> print("* ")
            OFFSET -> print("* ")
        }
        print(instruction.operand.argument)
    }
}