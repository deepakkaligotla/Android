package `in`.kaligotla.allpermissions.usecase

interface BaseUseCase<In, Out>{
    suspend fun execute(input: In): Out
}