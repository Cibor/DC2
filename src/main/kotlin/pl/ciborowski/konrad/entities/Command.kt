package pl.ciborowski.konrad.entities

abstract class Command {

    abstract val mnemonic: Mnemonic

    abstract fun execute(computer: DC2, operand: Operand)

    override fun equals(other: Any?): Boolean =
        other is Command &&
                this.mnemonic == other.mnemonic

    override fun hashCode(): Int {
        return mnemonic.hashCode()
    }

}