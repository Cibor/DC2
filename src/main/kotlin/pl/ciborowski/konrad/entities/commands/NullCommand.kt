package pl.ciborowski.konrad.entities.commands

import pl.ciborowski.konrad.entities.Command
import pl.ciborowski.konrad.entities.DC2
import pl.ciborowski.konrad.entities.Mnemonic.NULL
import pl.ciborowski.konrad.entities.Operand

class NullCommand : Command() {

    override val mnemonic get() = NULL
    override fun execute(computer: DC2, operand: Operand) {
        computer.programCounter++
    }
}