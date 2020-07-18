package kek.plantain.data.delegate

import kek.plantain.data.entity.Rubles
import kek.plantain.data.entity.Sector
import kek.plantain.data.entity.toRubles
import kek.plantain.utils.extensions.extractValue
import kek.plantain.utils.extensions.slice
import kek.plantain.utils.extensions.writeValue
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class RublesExtract(sector: Sector, block: Int, range: IntRange) :
    ReadWriteSector(sector, block, range), ReadWriteProperty<Any?, Rubles> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): Rubles {
        return sector.data
            .slice(block, range)
            .extractValue()
            .toRubles()
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Rubles) {
        sector.data[block].writeValue(value.raw, range)
    }
}