package com.bowhead.challenge.api

import io.reactivex.Flowable
import io.reactivex.functions.Function
import org.web3j.abi.EventEncoder
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.Address
import org.web3j.abi.datatypes.DynamicArray
import org.web3j.abi.datatypes.Event
import org.web3j.abi.datatypes.Type
import org.web3j.abi.datatypes.generated.Bytes32
import org.web3j.abi.datatypes.generated.Uint256
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameter
import org.web3j.protocol.core.RemoteCall
import org.web3j.protocol.core.RemoteFunctionCall
import org.web3j.protocol.core.methods.request.EthFilter
import org.web3j.protocol.core.methods.response.BaseEventResponse
import org.web3j.protocol.core.methods.response.Log
import org.web3j.protocol.core.methods.response.TransactionReceipt
import org.web3j.tx.Contract
import org.web3j.tx.TransactionManager
import org.web3j.tx.gas.ContractGasProvider
import java.math.BigInteger
import java.util.*
import java.util.Arrays;




/**
 *
 * Auto generated code.
 *
 * **Do not modify!**
 *
 * Please use the [web3j command line tools](https://docs.web3j.io/command_line.html),
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * [codegen module](https://github.com/web3j/web3j/tree/master/codegen) to update.
 *
 *
 * Generated with web3j version 4.5.6.
 */
class ContractWrapper : Contract {
    companion object {
        private const val BINARY = "0x608060405234801561001057600080fd5b506105ca806100206000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c80631bbfae0e14610046578063919932d214610074578063e2329539146100d3575b600080fd5b6100726004803603602081101561005c57600080fd5b8101908080359060200190929190505050610101565b005b61007c610228565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b838110156100bf5780820151818401526020810190506100a4565b505050509050019250505060405180910390f35b6100ff600480360360208110156100e957600080fd5b8101908080359060200190929190505050610377565b005b60008111610177576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f546865206c6576656c206d7573742062652067726561746572207468616e203081525060200191505060405180910390fd5b806000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055507fe29d35093005f4d575e1003753426b57a7f64378ba73332eef9c6ccc2b8decd63382604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019250505060405180910390a150565b606060008060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050600081116102e3576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260178152602001807f546865207573657220646f6573206e6f7420657869737400000000000000000081525060200191505060405180910390fd5b600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002080548060200260200160405190810160405280929190818152602001828054801561036c57602002820191906000526020600020905b815481526020019060010190808311610358575b505050505091505090565b60008060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905060008111610430576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260178152602001807f546865207573657220646f6573206e6f7420657869737400000000000000000081525060200191505060405180910390fd5b600060018060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205401905080600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208390806001815401808255809150509060018203906000526020600020016000909192909190915055507fe5757054728aef6cf2a9fad0ec3f084d682d2236659f4251f1e3f1f189b65ecc33828402604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019250505060405180910390a150505056fea265627a7a72315820fecf1ded2f636b8a985e04d0aa5e2b8a1d0fd4b342c10233c4d92047605a7d9364736f6c63430005100032"
        const val FUNC_REGISTERUSER = "registerUser"
        const val FUNC_ADDHEALTHDATA = "addHealthData"
        const val FUNC_GETHEALTHDATA = "getHealthData"
        val NEWUSEREARNINGS_EVENT = Event("NewUserEarnings",
                Arrays.asList<TypeReference<*>>(object : TypeReference<Address?>() {}, object : TypeReference<Uint256?>() {}))
        val USERREGISTERED_EVENT = Event("UserRegistered",
                Arrays.asList<TypeReference<*>>(object : TypeReference<Address?>() {}, object : TypeReference<Uint256?>() {}))
        protected var _addresses: HashMap<String, String>? = null
        @Deprecated("")
        fun load(contractAddress: String?, web3j: Web3j?, credentials: Credentials?, gasPrice: BigInteger?, gasLimit: BigInteger?): ContractWrapper {
            return ContractWrapper(contractAddress, web3j, credentials, gasPrice, gasLimit)
        }

        @Deprecated("")
        fun load(contractAddress: String?, web3j: Web3j?, transactionManager: TransactionManager?, gasPrice: BigInteger?, gasLimit: BigInteger?): ContractWrapper {
            return ContractWrapper(contractAddress, web3j, transactionManager, gasPrice, gasLimit)
        }

        fun load(contractAddress: String?, web3j: Web3j?, credentials: Credentials?, contractGasProvider: ContractGasProvider?): ContractWrapper {
            return ContractWrapper(contractAddress, web3j, credentials, contractGasProvider)
        }

        fun load(contractAddress: String?, web3j: Web3j?, transactionManager: TransactionManager?, contractGasProvider: ContractGasProvider?): ContractWrapper {
            return ContractWrapper(contractAddress, web3j, transactionManager, contractGasProvider)
        }

        fun deploy(web3j: Web3j?, credentials: Credentials?, contractGasProvider: ContractGasProvider?): RemoteCall<ContractWrapper> {
            return deployRemoteCall(ContractWrapper::class.java, web3j, credentials, contractGasProvider, BINARY, "")
        }

        @Deprecated("")
        fun deploy(web3j: Web3j?, credentials: Credentials?, gasPrice: BigInteger?, gasLimit: BigInteger?): RemoteCall<ContractWrapper> {
            return deployRemoteCall(ContractWrapper::class.java, web3j, credentials, gasPrice, gasLimit, BINARY, "")
        }

        fun deploy(web3j: Web3j?, transactionManager: TransactionManager?, contractGasProvider: ContractGasProvider?): RemoteCall<ContractWrapper> {
            return deployRemoteCall(ContractWrapper::class.java, web3j, transactionManager, contractGasProvider, BINARY, "")
        }

        @Deprecated("")
        fun deploy(web3j: Web3j?, transactionManager: TransactionManager?, gasPrice: BigInteger?, gasLimit: BigInteger?): RemoteCall<ContractWrapper> {
            return deployRemoteCall(ContractWrapper::class.java, web3j, transactionManager, gasPrice, gasLimit, BINARY, "")
        }

        fun getPreviouslyDeployedAddress(networkId: String): String? {
            return _addresses!![networkId]
        }

        init {
            _addresses = HashMap()
            _addresses!!["4"] = "0xc7c44c64DBddA29e2723FD1056ab876A54ef24CE"
            _addresses!!["1611110989908"] = "0xDE5a34b81B0Efa4773B5097f7676f4fFe9F79f42"
        }
    }

    @Deprecated("")
    protected constructor(contractAddress: String?, web3j: Web3j?, credentials: Credentials?, gasPrice: BigInteger?, gasLimit: BigInteger?) : super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit) {
    }

    protected constructor(contractAddress: String?, web3j: Web3j?, credentials: Credentials?, contractGasProvider: ContractGasProvider?) : super(BINARY, contractAddress, web3j, credentials, contractGasProvider) {}

    @Deprecated("")
    protected constructor(contractAddress: String?, web3j: Web3j?, transactionManager: TransactionManager?, gasPrice: BigInteger?, gasLimit: BigInteger?) : super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit) {
    }

    protected constructor(contractAddress: String?, web3j: Web3j?, transactionManager: TransactionManager?, contractGasProvider: ContractGasProvider?) : super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider) {}

    fun getNewUserEarningsEvents(transactionReceipt: TransactionReceipt?): List<NewUserEarningsEventResponse> {
        val valueList = extractEventParametersWithLog(NEWUSEREARNINGS_EVENT, transactionReceipt)
        val responses = ArrayList<NewUserEarningsEventResponse>(valueList.size)
        for (eventValues in valueList) {
            val typedResponse = NewUserEarningsEventResponse()
            typedResponse.log = eventValues.log
            typedResponse.user = eventValues.nonIndexedValues[0].value as String
            typedResponse.earning = eventValues.nonIndexedValues[1].value as BigInteger
            responses.add(typedResponse)
        }
        return responses
    }

    fun newUserEarningsEventFlowable(filter: EthFilter?): Flowable<NewUserEarningsEventResponse> {
        return web3j.ethLogFlowable(filter).map(object : Function<Log?, NewUserEarningsEventResponse> {
            override fun apply(log: Log): NewUserEarningsEventResponse {
                val eventValues = extractEventParametersWithLog(NEWUSEREARNINGS_EVENT, log)
                val typedResponse = NewUserEarningsEventResponse()
                typedResponse.log = log
                typedResponse.user = eventValues.nonIndexedValues[0].value as String
                typedResponse.earning = eventValues.nonIndexedValues[1].value as BigInteger
                return typedResponse
            }

        })
    }

    fun newUserEarningsEventFlowable(startBlock: DefaultBlockParameter?, endBlock: DefaultBlockParameter?): Flowable<NewUserEarningsEventResponse> {
        val filter = EthFilter(startBlock, endBlock, getContractAddress())
        filter.addSingleTopic(EventEncoder.encode(NEWUSEREARNINGS_EVENT))
        return newUserEarningsEventFlowable(filter)
    }

    fun getUserRegisteredEvents(transactionReceipt: TransactionReceipt?): List<UserRegisteredEventResponse> {
        val valueList = extractEventParametersWithLog(USERREGISTERED_EVENT, transactionReceipt)
        val responses = ArrayList<UserRegisteredEventResponse>(valueList.size)
        for (eventValues in valueList) {
            val typedResponse = UserRegisteredEventResponse()
            typedResponse.log = eventValues.log
            typedResponse.user = eventValues.nonIndexedValues[0].value as String
            typedResponse.level = eventValues.nonIndexedValues[1].value as BigInteger
            responses.add(typedResponse)
        }
        return responses
    }

    fun userRegisteredEventFlowable(filter: EthFilter?): Flowable<UserRegisteredEventResponse> {
        return web3j.ethLogFlowable(filter).map(object : Function<Log?, UserRegisteredEventResponse> {
            override fun apply(log: Log): UserRegisteredEventResponse {
                val eventValues = extractEventParametersWithLog(USERREGISTERED_EVENT, log)
                val typedResponse = UserRegisteredEventResponse()
                typedResponse.log = log
                typedResponse.user = eventValues.nonIndexedValues[0].value as String
                typedResponse.level = eventValues.nonIndexedValues[1].value as BigInteger
                return typedResponse
            }
        })
    }

    fun userRegisteredEventFlowable(startBlock: DefaultBlockParameter?, endBlock: DefaultBlockParameter?): Flowable<UserRegisteredEventResponse> {
        val filter = EthFilter(startBlock, endBlock, getContractAddress())
        filter.addSingleTopic(EventEncoder.encode(USERREGISTERED_EVENT))
        return userRegisteredEventFlowable(filter)
    }

    fun registerUser(level: BigInteger?): RemoteFunctionCall<TransactionReceipt> {
        val function = org.web3j.abi.datatypes.Function(
                FUNC_REGISTERUSER,
                Arrays.asList<Type<*>>(Uint256(level)), emptyList())
        return executeRemoteCallTransaction(function)
    }

    fun addHealthData(data: ByteArray?): RemoteFunctionCall<TransactionReceipt> {
        val function = org.web3j.abi.datatypes.Function(
                FUNC_ADDHEALTHDATA,
                Arrays.asList<Type<*>>(Bytes32(data)), emptyList())
        return executeRemoteCallTransaction(function)
    }

    val healthData: RemoteFunctionCall<List<*>>
        get() {
            val function = org.web3j.abi.datatypes.Function(FUNC_GETHEALTHDATA,
                    Arrays.asList(),
                    Arrays.asList<TypeReference<*>>(object : TypeReference<DynamicArray<Bytes32?>?>() {}))
            return RemoteFunctionCall(function) {
                val result = executeCallSingleValueReturn<Type<*>, List<*>>(function, List::class.java) as List<Type<*>>
                convertToNative<Type<*>, Any>(result)
            }
        }

    override fun getStaticDeployedAddress(networkId: String): String {
        return _addresses!![networkId]!!
    }

    class NewUserEarningsEventResponse : BaseEventResponse() {
        var user: String? = null
        var earning: BigInteger? = null
    }

    class UserRegisteredEventResponse : BaseEventResponse() {
        var user: String? = null
        var level: BigInteger? = null
    }
}
