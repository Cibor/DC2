package pl.ciborowski.konrad.entities

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AddressingModeTest {

    @Test
    fun should_have_4_addressing_modes() {
        assertEquals(4, AddressingMode.values().size)
    }

}