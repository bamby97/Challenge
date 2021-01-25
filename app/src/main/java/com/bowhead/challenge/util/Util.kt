package com.bowhead.challenge.util

import com.bowhead.challenge.models.LogItem
import org.web3j.abi.datatypes.generated.Bytes32
import org.web3j.utils.Numeric


class Util {
    companion object{
        val SEPARATOR="*"

        fun serializeObject(logItem: LogItem): Bytes32{
            val string=logItem.feeling+"*"+logItem.sleepQuality
            return stringToBytes32(string)
        }

        fun stringToBytes32(string: String): Bytes32 {
            val byteValue = string.toByteArray()
            val byteValueLen32 = ByteArray(32)
            System.arraycopy(byteValue, 0, byteValueLen32, 0, byteValue.size)
            return Bytes32(byteValueLen32)
        }
        fun deserializeObject(dataInBytes32: Bytes32):LogItem{
            val response=hexToASCII(Numeric.toHexStringNoPrefix(dataInBytes32.value))
            val fields=response?.split(SEPARATOR)
            return LogItem(if(fields?.get(0)!=null) fields.get(0) else " " ,if(fields?.get(1)!=null)fields?.get(1)else " ")
        }

        fun hexToASCII(hexValue: String): String? {
            val output = StringBuilder("")
            var i = 0
            while (i < hexValue.length) {
                val str = hexValue.substring(i, i + 2)
                output.append(str.toInt(16).toChar())
                i += 2
            }
            return output.toString()
        }
    }
}