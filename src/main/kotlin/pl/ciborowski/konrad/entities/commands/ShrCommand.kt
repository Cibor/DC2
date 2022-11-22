package pl.ciborowski.konrad.entities.commands

import pl.ciborowski.konrad.controller.calculateOperandValue
import pl.ciborowski.konrad.entities.Command
import pl.ciborowski.konrad.entities.DC2
import pl.ciborowski.konrad.entities.Mnemonic.SHL
import pl.ciborowski.konrad.entities.Operand

class ShrCommand : Command() {

    override val mnemonic get() = SHL
    override fun execute(computer: DC2, operand: Operand) {
        computer.accumulator = computer.accumulator shr calculateOperandValue(operand, computer.memory, computer.accumulator)
    }
}