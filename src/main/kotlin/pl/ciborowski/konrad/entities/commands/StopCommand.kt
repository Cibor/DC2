package pl.ciborowski.konrad.entities.commands

import pl.ciborowski.konrad.entities.Command
import pl.ciborowski.konrad.entities.DC2
import pl.ciborowski.konrad.entities.Mnemonic.STOP
import pl.ciborowski.konrad.entities.Operand

class StopCommand : Command() {

    override val mnemonic = STOP
    override fun execute(computer: DC2, operand: Operand) {
        return
    }
}