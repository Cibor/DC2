package pl.ciborowski.konrad.entities.commands

import org.junit.jupiter.api.BeforeEach
import pl.ciborowski.konrad.entities.DC2
import pl.ciborowski.konrad.entities.MemoryCellType.DATA

open class CommandTest {

    fun assertMemoryEquals(values: IntArray) : Boolean {
        if (values.size != computer.memorySize) {
            return false
        }

        for (i in 0 until computer.memorySize - 1) {
            val cell = computer.memory.readCell(i)
            if (cell.type() != DATA ||  cell.numericValue() != values[i]) {
                return false
            }
        }
        return true
    }

    protected lateinit var computer: DC2

    @BeforeEach
    protected fun setUp() {
        computer = DC2(5)
    }
}