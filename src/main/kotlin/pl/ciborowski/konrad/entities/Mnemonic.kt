package pl.ciborowski.konrad.entities

import pl.ciborowski.konrad.entities.commands.*

enum class Mnemonic(val command: Command) {
    NULL(NullCommand()),
    STOP(StopCommand()),
    LOAD(LoadCommand()),
    STORE(StoreCommand()),
    JUMP(JumpCommand()),
    JNEG(JNegCommand()),
    JZERO(JZeroCommand()),
    ADD(AddCommand()),
    SUB(SubCommand()),
    AND(AndCommand()),
    OR(OrCommand()),
    NOT(NotCommand()),
    XOR(XorCommand()),
    SHL(ShlCommand()),
    SHR(ShrCommand())
}