package id.my.arieftb.meowvie.persentation.model

data class Data<D>(var status: Status, var data: D? =null, var errorMessage: String? = null)

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}