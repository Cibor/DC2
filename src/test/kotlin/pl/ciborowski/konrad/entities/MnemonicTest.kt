package pl.ciborowski.konrad.entities

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MnemonicTest {

    @Test
    fun should_have_15_mnemonics() {
        assertEquals(15, Mnemonic.values().size)
    }
}