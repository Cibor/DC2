package pl.ciborowski.konrad.entities.commands

import pl.ciborowski.konrad.controller.calculateOperandValue
import pl.ciborowski.konrad.entities.Command
import pl.ciborowski.konrad.entities.DC2
import pl.ciborowski.konrad.entities.Mnemonic.ADD
import pl.ciborowski.konrad.entities.Operand

class AddCommand : Command() {

    override val mnemonic get() = ADD
    override fun execute(computer: DC2, operand: Operand) {
        computer.accumulator += calculateOperandValue(operand, computer.memory, computer.accumulator)
        computer.programCounter++
    }
}