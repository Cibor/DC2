package pl.ciborowski.konrad.entities.commands

import pl.ciborowski.konrad.controller.calculateOperandValue
import pl.ciborowski.konrad.entities.Command
import pl.ciborowski.konrad.entities.DC2
import pl.ciborowski.konrad.entities.Mnemonic.JNEG
import pl.ciborowski.konrad.entities.Operand

class JNegCommand : Command() {

    override val mnemonic get() = JNEG

    override fun execute(computer: DC2, operand: Operand) {
        if (computer.accumulator < 0) {
            computer.programCounter = calculateOperandValue(operand, computer.memory, computer.accumulator)
        } else {
            computer.programCounter++
        }
    }
}