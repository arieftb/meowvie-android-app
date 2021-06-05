package id.my.arieftb.meowvie.persentation.model

class Data<D>(var status: Status, var data: Data<D>?, var errorMessage: String? = null)

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

