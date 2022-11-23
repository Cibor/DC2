package pl.ciborowski.konrad.controller

import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

class TaskFileParserTest {

    @Test
    fun should_return_a_map_for_good_input() {
        val lines = listOf("50: 5", "51: -2", "52: 3", "66: 0")
        val data = mapOf(50 to 5, 51 to -2, 52 to 3, 66 to 0)
        assertEquals(data, parseDataSegment(lines))
    }

    @Test
    fun should_empty_map_for_no_input() {
        assertEquals(emptyMap<Int, Int>(), parseDataSegment(listOf()))
    }

    @Test
    fun should_throw_exception_for_duplicate_data() {
        val lines = listOf("50: 5", "51: -2", "52: 3", "66: 0", "51: -2")
        assertThrows<IllegalArgumentException> { parseDataSegment(lines) }
    }

    @Test
    fun should_throw_exception_for_incorrect_format() {
        val lines = listOf("50: 5", "51: -2", "52:3", "66: 0", "51: -2")
        assertThrows<IllegalArgumentException> { parseDataSegment(lines) }
    }

    @Test
    fun should_throw_exception_for_non_integers() {
        val lines = listOf("50a: 5")
        assertThrows<IllegalArgumentException> { parseDataSegment(lines) }
    }

    @Test
    fun should_throw_exception_for_empty_cell_number() {
        val lines = listOf(": 5")
        assertThrows<IllegalArgumentException> { parseDataSegment(lines) }
    }
}