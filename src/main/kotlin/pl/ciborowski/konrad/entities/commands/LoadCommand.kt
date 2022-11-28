package pl.ciborowski.konrad.entities.commands

import pl.ciborowski.konrad.controller.calculateOperandValue
import pl.ciborowski.konrad.entities.Command
import pl.ciborowski.konrad.entities.DC2
import pl.ciborowski.konrad.entities.Mnemonic.LOAD
import pl.ciborowski.konrad.entities.Operand

class LoadCommand : Command() {

    override val mnemonic get() = LOAD
    override fun execute(computer: DC2, operand: Operand) {
        computer.accumulator = calculateOperandValue(operand, computer.memory, computer.accumulator)
        computer.programCounter++
    }
}